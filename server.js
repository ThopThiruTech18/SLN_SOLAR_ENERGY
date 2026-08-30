const http = require('node:http');
const fs = require('node:fs');
const path = require('node:path');
const { URL } = require('node:url');

const port = Number(process.env.PORT) || 3000;
const root = __dirname;
const dataDirectory = path.join(root, 'data');
const csvPath = path.join(dataDirectory, 'enquiries.csv');
const csvHeader = 'submitted_at,name,phone,project_type,message,email,address\n';

function csvEscape(value) {
  return `"${String(value ?? '').replace(/"/g, '""')}"`;
}

function send(response, statusCode, body, contentType = 'application/json') {
  response.writeHead(statusCode, {
    'Content-Type': `${contentType}; charset=utf-8`,
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Methods': 'GET,POST,OPTIONS',
    'Access-Control-Allow-Headers': 'Content-Type'
  });
  response.end(body);
}

function serveFile(request, response) {
  const requestedPath = decodeURIComponent(new URL(request.url, `http://${request.headers.host}`).pathname);
  const relativePath = requestedPath === '/' ? 'index.html' : requestedPath.replace(/^[/\\]+/, '');
  const filePath = path.resolve(root, relativePath);
  const relativeToRoot = path.relative(root, filePath);

  if (relativeToRoot.startsWith('..') || path.isAbsolute(relativeToRoot) || !fs.existsSync(filePath) || fs.statSync(filePath).isDirectory()) {
    send(response, 404, 'Not found', 'text/plain');
    return;
  }

  const contentTypes = { '.html': 'text/html', '.css': 'text/css', '.js': 'text/javascript', '.jpg': 'image/jpeg', '.jpeg': 'image/jpeg', '.png': 'image/png', '.webp': 'image/webp' };
  send(response, 200, fs.readFileSync(filePath), contentTypes[path.extname(filePath)] || 'application/octet-stream');
}

function saveEnquiry(request, response) {
  let body = '';
  request.on('data', (chunk) => {
    body += chunk;
    if (body.length > 10000) request.destroy();
  });
  request.on('end', () => {
    try {
      const enquiry = JSON.parse(body);
      if (!enquiry.name?.trim() || !enquiry.phone?.trim()) {
        send(response, 400, JSON.stringify({ error: 'Name and phone are required.' }));
        return;
      }

      fs.mkdirSync(dataDirectory, { recursive: true });
      if (!fs.existsSync(csvPath)) fs.writeFileSync(csvPath, csvHeader, 'utf8');
      const row = [new Date().toISOString(), enquiry.name, enquiry.phone, enquiry.system || enquiry.type, enquiry.message, enquiry.email, enquiry.address]
        .map(csvEscape)
        .join(',') + '\n';
      fs.appendFileSync(csvPath, row, 'utf8');
      send(response, 201, JSON.stringify({ saved: true }));
    } catch (error) {
      send(response, 400, JSON.stringify({ error: 'Invalid enquiry data.' }));
    }
  });
}

const server = http.createServer((request, response) => {
  if (request.method === 'OPTIONS') {
    send(response, 204, '');
    return;
  }
  if (request.method === 'POST' && request.url === '/api/enquiries') {
    saveEnquiry(request, response);
    return;
  }
  if (request.method === 'GET') {
    serveFile(request, response);
    return;
  }
  send(response, 405, JSON.stringify({ error: 'Method not allowed.' }));
});

server.listen(port, '0.0.0.0', () => {
  console.log(`SLN Solar Energy running on all network interfaces at port ${port}`);
});