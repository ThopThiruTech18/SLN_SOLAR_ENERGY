# Institute Management System - API Documentation

## Overview
Complete JSON documentation for all API endpoints in the Institute Management System. Each endpoint is documented with request/response structures, examples, and error handling.

## Documentation Structure

### Files Organization
- **00-index.json** - Main index with all endpoints listed and common information
- **01-auth-endpoints.json** - Authentication & Registration (2 endpoints)
- **02-student-endpoints.json** - Student Management (6 endpoints)
- **03-user-endpoints.json** - User Account Management (6 endpoints)
- **04-course-endpoints.json** - Course Management (6 endpoints)
- **05-batch-endpoints.json** - Batch Management (7 endpoints)
- **06-trainer-endpoints.json** - Trainer Management (6 endpoints)
- **07-enrollment-endpoints.json** - Student Enrollments (2 endpoints)
- **08-payment-endpoints.json** - Payment Management (7 endpoints)
- **09-attendance-endpoints.json** - Attendance Tracking (4 endpoints)
- **10-notes-endpoints.json** - Course Material Upload (5 endpoints)
- **11-dashboard-endpoints.json** - Dashboard Statistics (1 endpoint)

**Total: 39 Endpoints across 11 Controllers**

## How to Use

### 1. Start with the Index
Open `00-index.json` to see all available endpoints organized by controller.

### 2. Find Your Endpoint
Each endpoint has:
- **id**: Unique identifier (e.g., AUTH_001)
- **name**: Human-readable name
- **method**: HTTP method (GET, POST, PUT, DELETE)
- **endpoint**: API path (e.g., /api/auth/login)
- **description**: What the endpoint does

### 3. Check Request/Response Structure
For each endpoint:

#### Request Section
- **contentType**: Content type (application/json or multipart/form-data)
- **body**: Object properties with types and examples
- **pathParameters**: URL parameters (if any)
- **queryParameters**: Query string parameters (if any)
- **example**: Sample request data

#### Response Section
- **statusCode**: HTTP status code (200, 201, 204, etc.)
- **contentType**: Response content type
- **body**: Object structure or array of objects
- **example**: Sample response data

#### Errors Section
- Possible error codes and descriptions

## Example Usage

### Login Endpoint Example
```json
// REQUEST
POST /api/auth/login
Content-Type: application/json

{
  "email": "student@thirutech.com",
  "password": "securePassword123"
}

// RESPONSE (201)
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "email": "student@thirutech.com",
  "role": "STUDENT",
  "message": "Login successful"
}
```

### Create Student Endpoint Example
```json
// REQUEST
POST /api/students
Content-Type: application/json
Authorization: Bearer {token}

{
  "userId": 5,
  "phone": "9876543210",
  "address": "123 Main Street, Bangalore",
  "qualification": "B.Tech in IT",
  "joiningDate": "2024-01-15",
  "status": "ACTIVE"
}

// RESPONSE (201)
{
  "studentId": 10,
  "fullName": "Raj Kumar",
  "email": "raj@thirutech.com",
  "phone": "9876543210",
  "address": "123 Main Street, Bangalore",
  "qualification": "B.Tech in IT",
  "joiningDate": "2024-01-15",
  "status": "ACTIVE"
}
```

## Common Data Types

### Enums
- **StudentStatus**: ACTIVE, INACTIVE, SUSPENDED
- **BatchStatus**: ACTIVE, INACTIVE, COMPLETED
- **PaymentStatus**: PENDING, COMPLETED, FAILED, REFUNDED
- **PaymentMode**: CASH, CHEQUE, UPI, NET_BANKING, CREDIT_CARD, DEBIT_CARD
- **AttendanceStatus**: PRESENT, ABSENT, LEAVE
- **Role**: STUDENT, TRAINER, ADMIN

## Authentication
All endpoints except `/api/auth/login` and `/api/auth/register` require JWT authentication.

Include the token in the Authorization header:
```
Authorization: Bearer {jwt_token}
```

Get the token from the login endpoint response.

## HTTP Status Codes

| Code | Meaning |
|------|---------|
| 200 | OK - Request successful |
| 201 | Created - Resource created successfully |
| 204 | No Content - Resource deleted successfully |
| 400 | Bad Request - Invalid request data or missing required fields |
| 401 | Unauthorized - Missing or invalid authentication token |
| 404 | Not Found - Resource not found |
| 409 | Conflict - Resource already exists or duplicate enrollment |
| 500 | Internal Server Error - Server error occurred |

## Date Formats

- **Date**: YYYY-MM-DD (e.g., 2024-01-15)
- **DateTime**: YYYY-MM-DDTHH:mm:ss (e.g., 2024-01-15T10:30:00)

## Important Notes

1. **Base URL**: Update `http://localhost:8080` with your actual server URL
2. **Decimal Format**: Use standard decimal numbers (e.g., 5000.00)
3. **File Upload**: The Notes endpoint uses `multipart/form-data` for file uploads
4. **Path Parameters**: Replace `{id}` in URLs with actual IDs
5. **Query Parameters**: Optional parameters can be omitted if not needed

## Endpoint Categories

### Authentication (2 endpoints)
- User login and registration

### Data Management (29 endpoints)
- CRUD operations for Students, Users, Courses, Batches, Trainers
- Enrollment management
- Payment tracking
- Attendance marking
- Course material upload/download

### Analytics (1 endpoint)
- Dashboard statistics

### Specialized Operations (7 endpoints)
- Get active/ongoing records
- Get statistics and revenue
- Special filters and searches

## Example Request Flow

1. **Register/Login** → Get JWT token
2. **Create Course** → Courses for students to enroll in
3. **Create Batch** → Assign trainer and schedule
4. **Create Student** → Student records linked to user accounts
5. **Enroll** → Add students to batches
6. **Mark Attendance** → Track class attendance
7. **Record Payments** → Track fee payments
8. **Upload Notes** → Share study materials
9. **View Dashboard** → Monitor system statistics

## API Testing Tools

You can test these endpoints using:
- **Postman** - Import the JSON files to create requests
- **cURL** - Command-line tool
- **Thunder Client** - VS Code extension
- **REST Client** - VS Code extension

## Questions?
Refer to the specific endpoint JSON file for detailed request/response structures and examples.
