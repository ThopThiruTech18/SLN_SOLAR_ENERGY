-- ✅ INSTITUTE MANAGEMENT SYSTEM - COMPLETE DATABASE INITIALIZATION
-- Sample data initialization script for Institute Management Project

-- ---------------------------
-- ✅ 1️⃣ USERS (25 Records)
-- ---------------------------
INSERT INTO users (full_name, email, password, role) VALUES
('Rajesh Kumar','rajesh@thirutech.com','pass@123','STUDENT'),
('Priya Sharma','priya@thirutech.com','pass@123','STUDENT'),
('Kiran Reddy','kiran@thirutech.com','pass@123','STUDENT'),
('Anjali Mehta','anjali@thirutech.com','pass@123','STUDENT'),
('Arjun Rao','arjun@thirutech.com','pass@123','STUDENT'),
('Sneha Verma','sneha@thirutech.com','pass@123','STUDENT'),
('Vikram Singh','vikram@thirutech.com','pass@123','STUDENT'),
('Meena Patel','meena@thirutech.com','pass@123','STUDENT'),
('Rohit Das','rohit@thirutech.com','pass@123','STUDENT'),
('Divya Nair','divya@thirutech.com','pass@123','STUDENT'),
('Suresh Babu','suresh@thirutech.com','pass@123','TRAINER'),
('Lakshmi Narayan','lakshmi@thirutech.com','pass@123','TRAINER'),
('Manoj Kumar','manoj@thirutech.com','pass@123','TRAINER'),
('Kavitha Rao','kavitha@thirutech.com','pass@123','TRAINER'),
('Rahul Iyer','rahul@thirutech.com','pass@123','TRAINER'),
('Admin Thiru','admin@thirutech.com','admin@123','ADMIN'),
('Staff Ramesh','ramesh@thirutech.com','staff@123','STAFF'),
('Sanjay Gupta','sanjay@thirutech.com','pass@123','STUDENT'),
('Neha Agarwal','neha@thirutech.com','pass@123','STUDENT'),
('Amit Joshi','amit@thirutech.com','pass@123','STUDENT'),
('Pooja Kulkarni','pooja@thirutech.com','pass@123','STUDENT'),
('Nikhil Jain','nikhil@thirutech.com','pass@123','STUDENT'),
('Harsha Reddy','harsha@thirutech.com','pass@123','STUDENT'),
('Tejaswini','tejaswini@thirutech.com','pass@123','STUDENT'),
('Gopal Krishna','gopal@thirutech.com','pass@123','STUDENT');

-- ---------------------------
-- ✅ 2️⃣ COURSES (25 Records)
-- ---------------------------
INSERT INTO courses (course_name,duration_in_months,fee_amount,description) VALUES
('Full Stack Java',6,65000,'Java + Spring Boot + React'),
('Python Full Stack',5,60000,'Python + Django + React'),
('Data Science & AI',6,85000,'ML + Deep Learning'),
('DevOps with AWS',4,70000,'Docker + K8s + AWS'),
('Angular Developer',3,40000,'Angular 17'),
('React Developer',3,45000,'React + Redux'),
('Microservices',3,50000,'Spring Cloud'),
('System Design',2,30000,'HLD + LLD'),
('DSA for Product Companies',4,55000,'Data Structures'),
('MongoDB & NoSQL',2,25000,'MongoDB'),
('Kafka & Streaming',2,35000,'Kafka'),
('Docker Mastery',2,30000,'Docker'),
('Kubernetes',2,35000,'K8s'),
('Spring Boot Advanced',3,50000,'Security + JWT'),
('GraphQL',2,30000,'GraphQL API'),
('NodeJS Backend',3,45000,'Node + Express'),
('Flutter',3,40000,'Mobile Dev'),
('UI/UX Design',2,30000,'Figma'),
('Testing Automation',3,45000,'Selenium'),
('Cyber Security',4,75000,'Security'),
('Cloud Architecture',4,80000,'AWS Architect'),
('AI Generative',3,90000,'GenAI'),
('Power BI',2,30000,'BI'),
('SQL Mastery',2,25000,'Advanced SQL'),
('Linux Admin',2,30000,'Linux');

-- ---------------------------------------------------------------
-- ✅ 3️⃣ STUDENTS (18 Records - user_id 1–10 and 18–25)
-- ---------------------------------------------------------------
INSERT INTO students (user_id,phone,address,qualification,joining_date) VALUES
(1,'9000000001','Hyderabad','B.Tech','2026-01-10'),
(2,'9000000002','Bangalore','B.Sc','2026-01-12'),
(3,'9000000003','Chennai','MCA','2026-01-15'),
(4,'9000000004','Mumbai','B.Com','2026-01-18'),
(5,'9000000005','Pune','B.Tech','2026-01-20'),
(6,'9000000006','Delhi','BCA','2026-01-22'),
(7,'9000000007','Vizag','B.Tech','2026-01-25'),
(8,'9000000008','Warangal','B.Sc','2026-01-27'),
(9,'9000000009','Nagpur','BCA','2026-02-01'),
(10,'9000000010','Mysore','B.Tech','2026-02-02'),
(18,'9000000018','Jaipur','BCA','2026-02-03'),
(19,'9000000019','Lucknow','B.Com','2026-02-05'),
(20,'9000000020','Indore','B.Tech','2026-02-07'),
(21,'9000000021','Noida','BCA','2026-02-10'),
(22,'9000000022','Surat','B.Sc','2026-02-12'),
(23,'9000000023','Bhopal','B.Tech','2026-02-15'),
(24,'9000000024','Patna','BCA','2026-02-18'),
(25,'9000000025','Ranchi','B.Tech','2026-02-20');

-- ----------------------------------------
-- ✅ 4️⃣ TRAINERS (5 Records - user_id 11–15)
-- ----------------------------------------
INSERT INTO trainers (user_id,expertise,salary,joining_date) VALUES
(11,'Java,Spring Boot',95000,'2025-11-01'),
(12,'Python,ML',105000,'2025-12-01'),
(13,'DevOps,AWS',90000,'2025-10-15'),
(14,'Angular,React',85000,'2025-09-20'),
(15,'System Design,DSA',120000,'2025-08-01');

-- ----------------------------------------
-- ✅ 5️⃣ BATCHES (25 Records)
-- ----------------------------------------
INSERT INTO batches 
(batch_name, course_id, trainer_id, start_date, end_date, schedule, status) VALUES
('FSJ-MOR-01',1,1,'2026-02-01','2026-08-01','Morning 7AM-9AM','ONGOING'),
('PY-EVE-01',2,2,'2026-02-05','2026-07-05','Evening 6PM-8PM','ONGOING'),
('DS-WKD-01',3,2,'2026-02-10','2026-08-10','Weekend 9AM-1PM','ONGOING'),
('DEV-MOR-01',4,3,'2026-02-15','2026-06-15','Morning 10AM-12PM','ONGOING'),
('ANG-EVE-01',5,4,'2026-02-20','2026-05-20','Evening 7PM-9PM','ONGOING'),
('REACT-MOR-01',6,4,'2026-03-01','2026-06-01','Morning 8AM-10AM','ONGOING'),
('MIC-WKD-01',7,1,'2026-03-05','2026-06-05','Weekend 10AM-2PM','ONGOING'),
('SYS-EVE-01',8,5,'2026-03-10','2026-05-10','Evening 5PM-7PM','ONGOING'),
('DSA-MOR-01',9,5,'2026-03-15','2026-07-15','Morning 6AM-8AM','ONGOING'),
('MON-EVE-01',10,3,'2026-03-20','2026-05-20','Evening 6PM-8PM','ONGOING'),
('KAF-MOR-01',11,3,'2026-03-25','2026-05-25','Morning 9AM-11AM','ONGOING'),
('DOC-WKD-01',12,3,'2026-04-01','2026-06-01','Weekend 2PM-6PM','UPCOMING'),
('K8S-EVE-01',13,3,'2026-04-05','2026-06-05','Evening 4PM-6PM','UPCOMING'),
('SPR-MOR-01',14,1,'2026-04-10','2026-07-10','Morning 7AM-9AM','UPCOMING'),
('GQL-WKD-01',15,1,'2026-04-15','2026-06-15','Weekend 10AM-1PM','UPCOMING'),
('NODE-EVE-01',16,4,'2026-04-20','2026-07-20','Evening 6PM-8PM','UPCOMING'),
('FLUT-MOR-01',17,4,'2026-04-25','2026-07-25','Morning 8AM-10AM','UPCOMING'),
('UIUX-WKD-01',18,5,'2026-05-01','2026-07-01','Weekend 9AM-12PM','UPCOMING'),
('TEST-EVE-01',19,5,'2026-05-05','2026-08-05','Evening 5PM-7PM','UPCOMING'),
('CYB-MOR-01',20,2,'2026-05-10','2026-09-10','Morning 10AM-12PM','UPCOMING'),
('CLOUD-WKD-01',21,2,'2026-05-15','2026-09-15','Weekend 2PM-5PM','UPCOMING'),
('AI-EVE-01',22,2,'2026-05-20','2026-08-20','Evening 6PM-8PM','UPCOMING'),
('BI-MOR-01',23,1,'2026-05-25','2026-07-25','Morning 7AM-9AM','UPCOMING'),
('SQL-EVE-01',24,3,'2026-06-01','2026-08-01','Evening 6PM-8PM','UPCOMING'),
('LINUX-WKD-01',25,3,'2026-06-05','2026-08-05','Weekend 10AM-2PM','UPCOMING');

-- -----------------------------------------------------
-- ✅ 6️⃣ STUDENT_BATCH (25 Records)
-- -----------------------------------------------------
INSERT INTO student_batch (student_id,batch_id,enrollment_date) VALUES
-- Batch 1
(1,1,'2026-02-01'),
(2,1,'2026-02-01'),
-- Batch 2
(3,2,'2026-02-05'),
(4,2,'2026-02-05'),
-- Batch 3
(5,3,'2026-02-10'),
(6,3,'2026-02-10'),
-- Batch 4
(7,4,'2026-02-15'),
(8,4,'2026-02-15'),
-- Batch 5
(9,5,'2026-02-20'),
(10,5,'2026-02-20'),
-- Batch 6
(11,6,'2026-03-01'),
(12,6,'2026-03-01'),
-- Batch 7
(13,7,'2026-03-05'),
(14,7,'2026-03-05'),
-- Batch 8
(15,8,'2026-03-10'),
(16,8,'2026-03-10'),
-- Batch 9
(17,9,'2026-03-15'),
(18,9,'2026-03-15'),
-- Batch 10
(1,10,'2026-03-20'),
(3,10,'2026-03-20'),
-- Batch 11
(5,11,'2026-03-25'),
-- Batch 12
(7,12,'2026-04-01'),
-- Batch 13
(9,13,'2026-04-05'),
-- Batch 14
(11,14,'2026-04-10'),
-- Batch 15
(13,15,'2026-04-15');

-- -----------------------------------------------------
-- ✅ 7️⃣ PAYMENTS (25 Records)
-- -----------------------------------------------------
INSERT INTO payments 
(student_id,course_id,amount_paid,payment_date,payment_mode,status) VALUES
(1,1,30000,'2026-02-01','UPI','PAID'),
(2,1,65000,'2026-02-02','NETBANKING','PAID'),
(3,2,40000,'2026-02-03','CARD','PAID'),
(4,2,20000,'2026-02-04','CASH','PAID'),
(5,3,50000,'2026-02-05','UPI','PAID'),
(6,3,60000,'2026-02-06','CARD','PAID'),
(7,4,35000,'2026-02-07','UPI','PAID'),
(8,4,45000,'2026-02-08','NETBANKING','PAID'),
(9,5,55000,'2026-02-09','CARD','PAID'),
(10,5,25000,'2026-02-10','UPI','PAID'),
(11,6,30000,'2026-02-11','UPI','PAID'),
(12,6,70000,'2026-02-12','NETBANKING','PAID'),
(13,7,45000,'2026-02-13','CARD','PAID'),
(14,7,65000,'2026-02-14','UPI','PAID'),
(15,8,20000,'2026-02-15','CASH','PAID'),
(16,8,50000,'2026-02-16','CARD','PAID'),
(17,9,75000,'2026-02-17','NETBANKING','PAID'),
(18,9,60000,'2026-02-18','UPI','PAID'),
(1,10,20000,'2026-03-01','UPI','PAID'),
(2,10,15000,'2026-03-02','CASH','PAID'),
(3,11,25000,'2026-03-03','NETBANKING','PAID'),
(4,12,30000,'2026-03-04','CARD','PAID'),
(5,13,15000,'2026-03-05','UPI','PAID'),
(6,14,20000,'2026-03-06','CARD','PAID'),
(7,15,10000,'2026-03-07','CASH','PAID');

-- ------------------------------
-- ✅ 8️⃣ ATTENDANCE (25 Records)
-- ------------------------------
INSERT INTO attendance (student_id,batch_id,attendance_date,status) VALUES
(1,1,'2026-02-02','PRESENT'),
(2,1,'2026-02-02','ABSENT'),
(3,2,'2026-02-06','PRESENT'),
(4,2,'2026-02-06','PRESENT'),
(5,3,'2026-02-11','ABSENT'),
(6,3,'2026-02-11','PRESENT'),
(7,4,'2026-02-16','PRESENT'),
(8,4,'2026-02-16','PRESENT'),
(9,5,'2026-02-21','ABSENT'),
(10,5,'2026-02-21','PRESENT'),
(11,6,'2026-03-02','PRESENT'),
(12,6,'2026-03-02','ABSENT'),
(13,7,'2026-03-06','PRESENT'),
(14,7,'2026-03-06','PRESENT'),
(15,8,'2026-03-11','PRESENT'),
(16,8,'2026-03-11','ABSENT'),
(17,9,'2026-03-16','PRESENT'),
(18,9,'2026-03-16','PRESENT'),
(1,10,'2026-03-21','PRESENT'),
(2,10,'2026-03-21','ABSENT'),
(3,11,'2026-03-26','PRESENT'),
(4,12,'2026-04-02','PRESENT'),
(5,13,'2026-04-06','ABSENT'),
(6,14,'2026-04-11','PRESENT'),
(7,15,'2026-04-16','PRESENT');

-- ----------------------------------
-- ✅ 9️⃣ COURSE_MATERIALS (25 Records)
-- ----------------------------------
INSERT INTO course_materials (course_id,file_name,file_path) VALUES
(1,'Java Basics','/materials/java_basics.pdf'),
(2,'Python Intro','/materials/python_intro.pdf'),
(3,'ML Fundamentals','/materials/ml.pdf'),
(4,'AWS Setup','/materials/aws.pdf'),
(5,'Angular Components','/materials/angular.pdf'),
(6,'React Hooks','/materials/react.pdf'),
(7,'Microservices Arch','/materials/micro.pdf'),
(8,'System Design Notes','/materials/system.pdf'),
(9,'DSA Basics','/materials/dsa.pdf'),
(10,'MongoDB CRUD','/materials/mongo.pdf'),
(11,'Kafka Intro','/materials/kafka.pdf'),
(12,'Docker Guide','/materials/docker.pdf'),
(13,'Kubernetes Pods','/materials/k8s.pdf'),
(14,'Spring Security','/materials/security.pdf'),
(15,'GraphQL API','/materials/graphql.pdf'),
(16,'Node Express','/materials/node.pdf'),
(17,'Flutter Widgets','/materials/flutter.pdf'),
(18,'Figma Design','/materials/figma.pdf'),
(19,'Selenium','/materials/selenium.pdf'),
(20,'Cyber Security','/materials/cyber.pdf'),
(21,'AWS Architect','/materials/architect.pdf'),
(22,'Generative AI','/materials/genai.pdf'),
(23,'Power BI','/materials/powerbi.pdf'),
(24,'Advanced SQL','/materials/sql.pdf'),
(25,'Linux Admin','/materials/linux.pdf');
