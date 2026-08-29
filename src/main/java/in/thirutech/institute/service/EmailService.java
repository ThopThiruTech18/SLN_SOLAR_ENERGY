package in.thirutech.institute.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface EmailService {
    List<String> extractEmails(InputStream excelInputStream) throws IOException;

    void sendBatchNotification(String recipientEmail, String recipientName, String batchName, String courseName,
            String startDate, String time, String trainerName, String instituteName,
            String instituteAddress, String institutePhone, String instituteWebsite,
            String instituteEmail);
}
