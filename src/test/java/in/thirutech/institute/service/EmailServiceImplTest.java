package in.thirutech.institute.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.mail.javamail.JavaMailSender;

import in.thirutech.institute.service.impl.EmailServiceImpl;

class EmailServiceImplTest {

    @Test
    void shouldExtractEmailsFromExcelWorkbook() throws Exception {
        JavaMailSender mailSender = mock(JavaMailSender.class);
        EmailServiceImpl service = new EmailServiceImpl(mailSender);

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Students");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Name");
            header.createCell(1).setCellValue("Email");

            Row row1 = sheet.createRow(1);
            row1.createCell(0).setCellValue("Alice");
            row1.createCell(1).setCellValue("alice@test.com");

            Row row2 = sheet.createRow(2);
            row2.createCell(0).setCellValue("Bob");
            row2.createCell(1).setCellValue("bob@test.com");

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            List<String> emails = service.extractEmails(new ByteArrayInputStream(outputStream.toByteArray()));

            assertEquals(List.of("alice@test.com", "bob@test.com"), emails);
        }
    }
}
