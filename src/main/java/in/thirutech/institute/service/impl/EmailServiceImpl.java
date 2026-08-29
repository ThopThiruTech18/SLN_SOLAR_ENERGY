package in.thirutech.institute.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import in.thirutech.institute.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public List<String> extractEmails(InputStream excelInputStream) throws IOException {
        List<String> emails = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(excelInputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            if (sheet == null) {
                return emails;
            }

            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                return emails;
            }

            int emailColumnIndex = -1;
            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                String cellValue = getCellValueAsString(headerRow.getCell(i));
                if (cellValue != null && (cellValue.equalsIgnoreCase("email")
                        || cellValue.equalsIgnoreCase("email address")
                        || cellValue.equalsIgnoreCase("e-mail")
                        || cellValue.equalsIgnoreCase("student email"))) {
                    emailColumnIndex = i;
                    break;
                }
            }

            if (emailColumnIndex == -1) {
                return emails;
            }

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) {
                    continue;
                }

                String email = getCellValueAsString(row.getCell(emailColumnIndex));
                if (email != null && !email.isBlank()) {
                    emails.add(email.trim());
                }
            }
        }

        return emails;
    }

    @Override
    public void sendBatchNotification(String recipientEmail, String recipientName, String batchName, String courseName,
            String startDate, String time, String trainerName, String instituteName,
            String instituteAddress, String institutePhone, String instituteWebsite,
            String instituteEmail) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(recipientEmail);
            helper.setSubject("New Batch Started: " + batchName);

            String html = buildHtmlBody(recipientName, batchName, courseName, startDate, time, trainerName,
                    instituteName, instituteAddress, institutePhone, instituteWebsite, instituteEmail);

            helper.setText(html, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email to: " + recipientEmail, e);
        }
    }

    private String buildHtmlBody(String recipientName, String batchName, String courseName, String startDate,
            String time, String trainerName, String instituteName, String instituteAddress,
            String institutePhone, String instituteWebsite, String instituteEmail) {
        String safeName = recipientName == null || recipientName.isBlank() ? "Student" : recipientName;
        return "<html><body style='font-family: Arial, sans-serif; background:#f5f7fb; padding:24px;'>"
                + "<div style='max-width:700px; margin:0 auto; background:#ffffff; border:1px solid #e5e7eb; border-radius:12px; overflow:hidden;'>"
                + "<div style='background:#0d6efd; color:#fff; padding:22px 28px;'><h2 style='margin:0;'>"
                + instituteName + "</h2>"
                + "<div style='font-size:14px; margin-top:6px;'>Batch Notification</div></div>"
                + "<div style='padding:28px;'>"
                + "<p>Dear <strong>" + safeName + "</strong>,</p>"
                + "<p>We are pleased to inform you that a new batch is starting soon at <strong>" + instituteName
                + "</strong>.</p>"
                + "<div style='background:#f8f9fa; border-left:4px solid #0d6efd; padding:18px; border-radius:8px; margin:18px 0;'>"
                + "<p><strong>Batch Name:</strong> " + batchName + "</p>"
                + "<p><strong>Course:</strong> " + courseName + "</p>"
                + "<p><strong>Start Date:</strong> " + startDate + "</p>"
                + "<p><strong>Time:</strong> " + time + "</p>"
                + "<p><strong>Trainer:</strong> " + trainerName + "</p>"
                + "</div>"
                + "<p>Please make sure to join the batch on time and be ready for the classes.</p>"
                + "<p>For more details, contact our support team.</p>"
                + "<p>Warm regards,<br><strong>" + instituteName + " Team</strong></p>"
                + "</div>"
                + "<div style='background:#111827; color:#ffffff; padding:18px 28px; font-size:12px; line-height:1.8;'>"
                + "<strong>" + instituteName + "</strong><br>"
                + instituteAddress + "<br>"
                + "Phone: " + institutePhone + "<br>"
                + "Website: " + instituteWebsite + "<br>"
                + "Email: " + instituteEmail + ""
                + "</div>"
                + "</div></body></html>";
    }

    private String getCellValueAsString(org.apache.poi.ss.usermodel.Cell cell) {
        if (cell == null) {
            return null;
        }

        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            default -> null;
        };
    }
}
