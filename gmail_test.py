import smtplib
from email.message import EmailMessage

sender = "thopthirutech@gmail.com"
password = "trnp vozj xuuf etrh"
recipient = "gollathirumalesh7755@gmail.com"

msg = EmailMessage()
msg["Subject"] = "Gmail SMTP verification from Institute Management app"
msg["From"] = sender
msg["To"] = recipient
msg.set_content("This is a live SMTP verification email from the Spring Boot Institute Management project. If you received this, the Gmail setup is working correctly.")

server = smtplib.SMTP("smtp.gmail.com", 587)
server.starttls()
server.login(sender, password)
server.send_message(msg)
server.quit()
print("EMAIL_SENT_OK")
