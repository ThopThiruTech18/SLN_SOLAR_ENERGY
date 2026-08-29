package in.thirutech.institute.controller;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import in.thirutech.institute.service.EmailService;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-batch-notification")
    public ResponseEntity<Map<String, Object>> sendBatchNotification(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "batchName", required = false, defaultValue = "New Batch") String batchName,
            @RequestParam(value = "courseName", required = false, defaultValue = "Course") String courseName,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "time", required = false, defaultValue = "10:00 AM") String time,
            @RequestParam(value = "trainerName", required = false, defaultValue = "Trainer") String trainerName,
            @RequestParam(value = "instituteName", required = false, defaultValue = "ThiruTech Institute") String instituteName,
            @RequestParam(value = "instituteAddress", required = false, defaultValue = "Bangalore") String instituteAddress,
            @RequestParam(value = "institutePhone", required = false, defaultValue = "+91-9000000000") String institutePhone,
            @RequestParam(value = "instituteWebsite", required = false, defaultValue = "https://example.com") String instituteWebsite,
            @RequestParam(value = "instituteEmail", required = false, defaultValue = "info@example.com") String instituteEmail)
            throws IOException {

        Map<String, Object> response = new HashMap<>();
        List<String> sent = new ArrayList<>();
        List<String> failed = new ArrayList<>();

        try (InputStream in = file.getInputStream()) {
            List<String> emails = emailService.extractEmails(in);

            if (emails.isEmpty()) {
                response.put("success", false);
                response.put("message", "No valid email addresses found in the uploaded Excel file.");
                response.put("sent", sent);
                response.put("failed", failed);
                return ResponseEntity.badRequest().body(response);
            }

            for (String email : emails) {
                try {
                    String recipientName = email.contains("@") ? email.substring(0, email.indexOf('@')) : "Student";
                    emailService.sendBatchNotification(email, recipientName, batchName, courseName,
                            startDate != null ? startDate : LocalDate.now().toString(), time,
                            trainerName, instituteName, instituteAddress, institutePhone,
                            instituteWebsite, instituteEmail);
                    sent.add(email);
                } catch (Exception ex) {
                    failed.add(email + " -> " + ex.getMessage());
                }
            }
        }

        response.put("success", true);
        response.put("message", "Batch notification sending completed.");
        response.put("totalFound", sent.size() + failed.size());
        response.put("sent", sent);
        response.put("failed", failed);
        response.put("sentCount", sent.size());
        response.put("failedCount", failed.size());

        return ResponseEntity.ok(response);
    }
}
