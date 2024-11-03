create database hospital_db;
use hospital_db;
select * from doctor;
-- TABLES --
select * from Patient;
drop table Doctor;
drop table Patient;
CREATE TABLE Doctor(
DoctorID INT PRIMARY KEY auto_increment,
FirstName VARCHAR(50) NOT NULL,
LastName VARCHAR(50),
DOB DATE NOT NULL,
Education VARCHAR(50),
Gender CHAR(1) NOT NULL,
Phone VARCHAR(15),
Email VARCHAR(255),
Post VARCHAR(50),
Department VARCHAR(50),
Specialization VARCHAR(30),
Password VARCHAR(255)
);


CREATE TABLE Patient(
PatientID INT PRIMARY KEY auto_increment,
FirstName VARCHAR(50) NOT NULL,
LastName VARCHAR(50),
Address VARCHAR(200),
NTK VARCHAR(50),
Email VARCHAR(80),
Gender CHAR(1),
History VARCHAR(500),
DOB DATE NOT NULL,
Password VARCHAR(255)
);

drop table Staff;
CREATE TABLE Staff(
StaffID INT PRIMARY KEY auto_increment,
FirstName VARCHAR(50) NOT NULL,
LastName VARCHAR(50),
DOB DATE NOT NULL,
Education VARCHAR(50),
Gender CHAR(1),
Post VARCHAR(30),
Email VARCHAR(80),
Phone VARCHAR(15),
Password VARCHAR(255)
);

drop table Chemist;

CREATE TABLE Chemist(
ChemistID INT PRIMARY KEY,
FirstName VARCHAR(50) NOT NULL,
LastName VARCHAR(50),
DOB DATE NOT NULL,
Gender CHAR(1),
Email VARCHAR(80),
Phone VARCHAR(15),
Password VARCHAR(255)
);

CREATE TABLE Bill(
BillID INT PRIMARY KEY,
PatientID INT References Patient(PatientID),
TotalCost INT
);

CREATE TABLE Room(
RoomID INT PRIMARY KEY,
RoomType VARCHAR(50),
Cost INT,
PatientID INT REFERENCES Patient(PatientID)
);

CREATE TABLE Billing_department(
DeptID INT PRIMARY KEY,
Name VARCHAR(100),
Phone VARCHAR(15),
Email VARCHAR(80),
BillID INT REFERENCES Bill(BillID)
);

drop table admin;

CREATE TABLE Admin(
AdminID INT PRIMARY KEY auto_increment,
Email varchar(80),
DoctorID INT REFERENCES Doctor(DoctorID),
AccessLevel VARCHAR(50),
Password VARCHAR(255)
);

CREATE TABLE Machinery(
MachineID INT PRIMARY KEY,
Name VARCHAR(50) NOT NULL,
Cost INT
);

CREATE TABLE Medicines(
MedicineID INT PRIMARY KEY,
MedicineName VARCHAR(50) NOT NULL,
Cost INT,
Type VARCHAR(50),
CompanyName VARCHAR(50)
);

CREATE TABLE Surgery(
SurgeryID INT PRIMARY KEY,
PatientID INT REFERENCES Patient(PatientID),
DoctorID INT REFERENCES Doctor(DoctorID),
Type VARCHAR(30),
CriticalLevel INT NOT NULL
);

CREATE TABLE Helps_In(
SurgeryID INT REFERENCES Surgery(SurgeryID),
StaffID INT REFERENCES STAFF(STAFFID),
Rating INT
);

CREATE TABLE Approves_Expense(
AdminID INT REFERENCES Admin(AdminID),
DeptID INT REFERENCES Expenditure_Department(DeptID),
Type VARCHAR(30),
Amount INT
);

CREATE TABLE Expenditure_Department(
DeptID INT PRIMARY KEY,
Name VARCHAR(30) NOT NULL,
Phone VARCHAR(15),
Email VARCHAR(80)
);

CREATE TABLE WorkerIDs(
DoctorID INT REFERENCES Doctor(DoctorID),
StaffID INT REFERENCES Staff(STaffID),
ChemistID INT REFERENCES CHEMIST(CHEMISTID),
WORKERID VARCHAR(200)
);

CREATE TABLE Take_worker_sal(
WorkerID VARCHAR(200) REFERENCES WORKERIDS(WorkerID),
Date DATE NOT NULL,
Time TIME NOT NULL,
Bonus INT
);

CREATE TABLE Handles_Inventory(
MedicineID INT REFERENCES Medicines(MedicineID),
ChemistID INT REFERENCES Chemist(ChemistID)
);

CREATE TABLE Appoint_Doctor(
AdminID INT REFERENCES Admin(AdminID),
DoctorID INT REFERENCES Doctor(DoctorID),
Post VARCHAR(30),
Department VARCHAR(30),
Salary INT
);

CREATE TABLE Treatment_Procedure(
DoctorID INT REFERENCES Doctor(DoctorID),
PatientID INT REFERENCES Patient(PatientID),
Critical INT NOT NULL
);

CREATE TABLE Takes_Care(
PatientID INT REFERENCES Patient(PatientID),
StaffID INT REFERENCES Staff(StaffID),
Rating INT,
Feedback VARCHAR(300)
);

CREATE TABLE Patient_Phone(
Phone VARCHAR(15) PRIMARY KEY,
PatientID INT REFERENCES Patient(PatientID)
);

CREATE TABLE Appoint_Staff(
AdminID INT REFERENCES Admin(AdminID),
StaffID INT REFERENCES Staff(StaffID),
Remarks VARCHAR(300),
Strengths VARCHAR(300),
Weakness VARCHAR(300)
);

CREATE TABLE Appoint_Chemist(
AdminID INT REFERENCES Admin(AdminID),
ChemistID INT REFERENCES Chemist(ChemistID),
Remarks VARCHAR(300),
Strengths VARCHAR(300),
Weakness VARCHAR(300)
);

CREATE TABLE Doctor_Uses(
DoctorID INT REFERENCES Doctor(DoctorID),
MachineID INT REFERENCES Machinery(MachineID),
AccessLevel INT NOT NULL,
CostPerHour INT
);

CREATE TABLE Staff_Operates(
MachineID INT REFERENCES Machinery(MachineID),
StaffID INT REFERENCES Staff(StaffID)
);

CREATE TABLE Salary(
WorkerID VARCHAR(200) REFERENCES WORKERIDS(WorkerID),
Amount INT
);

CREATE TABLE Buys_Medicine(
PatientID INT REFERENCES Patient(PatientID),
ChemistID INT REFERENCES Chemist(ChemistID),
Date DATE
);

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fullName VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);



-- TRIGGERS --

DELIMITER $$

CREATE TRIGGER before_insert_doctor
AFTER INSERT ON DOCTOR
FOR EACH ROW
BEGIN
	INSERT INTO WORKERIDS(DoctorID, StaffID, ChemistID, WorkerID) value
    (NEW.DOCTORID, 0, 0, CONCAT(NEW.DOCTORID, '-0-0'));
END $$

CREATE TRIGGER before_insert_staff
AFTER INSERT ON STAFF
FOR EACH ROW
BEGIN
	INSERT INTO WORKERIDS(DoctorID, StaffID, ChemistID, WORKERID) value
    (0, NEW.STAFFID, 0, CONCAT('0-', NEW.STAFFID, '-0'));
END $$

CREATE TRIGGER before_insert_chemist
AFTER INSERT ON CHEMIST
FOR EACH ROW
BEGIN
	INSERT INTO WORKERIDS(DoctorID, StaffID, ChemistID, WORKERID) value
    (0, 0, NEW.CHEMISTID, CONCAT('0-0-', NEW.CHEMISTID));
END $$

DELIMITER ;



-- POPULATION INCREASING PROCEDURE --

INSERT INTO Doctor VALUES
(1, 'Ashish', 'Bazad', '2004-05-25', 'UG', 'M', '7015584371', 'ashish.student.cse22@iitbhu.ac.in', 'Cardiologist', 'Cardiology', 'Heart Surgeon','$2a$10$fVMH6qQJ.J3fxcLc4q4pUucOWUJOZWSdbgxZzIRhJVB9wpX8KXci2'),
(2, 'Eshaan', 'Sodhi', '2004-05-25', 'UG', 'O', '7015589371', 'eshaan.sodhi.cse22@iitbhu.ac.in', 'Gynecologist', 'Gynecology', 'Delivery','$2a$10$fVMH6qQJ.J3fxcLc4q4pUucOWUJOZWSdbgxZzIRhJVB9wpX8KXci2'),
(3, 'Shikhar', 'Srivastav', '2004-05-25', 'UG', 'O', '7015588271', 'shikhar.srivastav.cd.cse22@iitbhu.ac.in', 'Neurologist', 'Neurology', 'Brain Tumour','$2a$10$fVMH6qQJ.J3fxcLc4q4pUucOWUJOZWSdbgxZzIRhJVB9wpX8KXci2'),
(4, 'Shubham', 'Jain', '2004-03-25', 'UG', 'M', '7015588311', 'shubham.jain.cd.cse22@iitbhu.ac.in', 'Andrologist', 'Andrology', 'Sperm Fertility','$2a$10$fVMH6qQJ.J3fxcLc4q4pUucOWUJOZWSdbgxZzIRhJVB9wpX8KXci2'),
(5, 'Tanmay', 'Bisht', '2005-05-25', 'UG', 'M', '7015588321', 'tanmay.bisht.cd.cse22@iitbhu.ac.in', 'Gastrologist', 'Gastrology', 'Dysentery','$2a$10$fVMH6qQJ.J3fxcLc4q4pUucOWUJOZWSdbgxZzIRhJVB9wpX8KXci2')
;

select * from Doctor;

INSERT INTO Staff VALUES
(1, 'Divya', 'Sharma', '2006-06-06', 'UG', 'F', 'Nurse', 'divya@gmail.com', '1234567890'),
(2, 'Nandini', 'Sharma', '2007-07-07', 'UG', 'F', 'Receptionist', 'nandini@gmail.com', '1234567899'),
(3, 'Sanchita', 'Kalra', '2006-07-06', 'UG', 'F', 'Cleaner', 'sanchita@gmail.com', '1234567790'),
(4, 'Priyanshi', 'Jha', '2006-06-07', 'UG', 'F', 'Nurse', 'priyanshi@gmail.com', '1224567890'),
(5, 'Ameeta', 'Sengupta', '2005-05-05', 'UG', 'F', 'Dietician', 'ameeta@gmail.com', '1234527890')
;


INSERT INTO Chemist VALUES
(1, 'Karishma', 'Sanatani', '2004-04-04', 'F', 'karishma@gmail.com', '123456781'),
(2, 'Kritika', 'Wadhwa', '2004-05-04', 'F', 'kritika@gmail.com', '123456786'),
(3, 'Aatishya', 'Sood', '2004-07-04', 'F', 'aatishya@gmail.com', '123452781'),
(4, 'Jatasya', 'Bisht', '2004-04-07', 'F', 'jatasya@gmail.com', '123656781'),
(5, 'Sneha', 'Modi', '2004-04-09', 'F', 'sneha@gmail.com', '124456781')
;

INSERT INTO Admin VALUES
(1, 1, 'RBAC'),
(2, 5, 'ABAC')
;

INSERT INTO Medicines VALUES
(1, 'PCM', 2, 'Fever', 'Cipla'),
(2, 'Cetrizine', 10, 'Cough', 'Cipla'),
(3, 'Seradon', 3, 'Headache', 'Abbott'),
(4, 'Luliconazole', 23, 'Fungus', 'Lulitec'),
(5, 'PanD', 5, 'Gas', 'Abbott')
;

INSERT INTO Machinery VALUES
(1, 'MRI', 100000),
(2, 'CT SCAN', 2000000),
(3, 'X-RAY', 500000),
(4, 'Ultrasound', 200000)
;

INSERT INTO Staff_Operates VALUES
(1, 1),
(2, 5),
(3, 4),
(4, 2)
;

INSERT INTO Patient VALUES
(1, 'Sanchita', 'Kalra', 'House No. 224, Street No. 1, Chandigarh', 'Kassi', 'sanchita@gmail.com', 'F', 'None', '2006-07-06','$Z1pL@w7R#4g!3dQ'),
(2, 'Kshitij', 'Sharma', 'House No. 225, Street No. 32, Jamshedpur, Jharkhand', 'Priyanshi Goyal', 'kshitij@gmail.com', 'O', 'None', '2004-07-06','F@8p*T1$kR9&u2Z^'),
(3, 'Deepanshu', 'Sau', 'House No. 214, Street No. 2, Tata City, Jharkhand', 'Bhakti', 'deepanshu@gmail.com', 'M', 'None', '2004-04-11','m^3$H1x&0L!jV9*c'),
(4, 'Ashish', 'Dalal', 'House No. 324, Street No. 34, Nagpur', 'Manya Pandey', 'ashishdalal@gmail.com', 'M', 'None', '2004-12-11','Q4%lP2&gK!8nY@1z'),
(5, 'Rohit', 'Kaushik', 'House No. 24, Street No. 2, Varanasi', 'Tanmay Jain', 'rohit@gmail.com', 'M', 'None', '2006-02-06','W!7cX@0m#5uR2$Z%'),
(6, 'Nikhil', 'Totla', 'House No. 4, Street No. 3, Chandigarh', 'Jagriti', 'nikhil@gmail.com', 'M', 'None', '2004-04-06','W!7cX@0m#5uR2$Z%'),
(7, 'Abhishek', 'Chauhan', 'House No. 42, Street No. 23, Gurugram, Haryana', 'Khushi', 'abhishek@gmail.com', 'M', 'None', '2004-05-07','W!7cX@0m#5uR2$Z%')
;

INSERT INTO Surgery VALUES
(1, 1, 2, 'Delivery', 9),
(2, 2, 4, 'Infertility', 3),
(3, 3, 1, 'Heart Transplant', 10),
(4, 4, 3, 'Psychotic Reactions', 4)
;

INSERT INTO Helps_in VALUES
(1, 1, 5),
(2, 4, 7),
(4, 4, 6),
(3, 5, 8)
;

INSERT INTO Patient_Phone VALUES
('1234509876', 1),
('1234567790', 1),
('5432167890', 2),
('2121478789', 3),
('1234432112', 4),
('7890912341', 5),
('2342312323', 6),
('9789713451', 7)
;

INSERT INTO Bill VALUES
(1, 1, 20000),
(2, 4, 12342),
(3, 2, 1233),
(4, 6, 700900),
(5, 7, 22122),
(6, 3, 1232221),
(7, 5, 232)
;

INSERT INTO Treatment_Procedure VALUES
(1, 1, 7),
(3, 2, 4),
(4, 3, 2),
(5, 7, 3),
(2, 4, 4),
(4, 5, 8),
(2, 6, 7)
;

INSERT INTO Takes_Care VALUES
(1, 3, 6, 'Good'),
(2, 4, 8, 'Great'),
(4, 1, 5, 'None'),
(3, 5, 4, 'Needs Improvement'),
(5, 3, 5, 'None'),
(7, 2, 5, 'Ok'),
(6, 2, 6, 'None')
;

INSERT INTO Room VALUES
(1, 'Delux', 150000, 1),
(2, 'General', 20000, 2),
(3, 'Cabin', 30000, 4),
(4, 'General', 20000, 5),
(5, 'Delux', 150000, 6)
;

INSERT INTO Buys_Medicine VALUES
(1, 1, '2024-10-01'),
(3, 2, '2024-09-11'),
(5, 5, '2024-06-12'),
(7, 4, '2024-03-22')
;

INSERT INTO Expenditure_Department(DeptID, Name, Phone, Email) VALUES
(1, 'Cardiology', '1387461832', 'cardib@gmail.com'),
(2, 'Neurology', '1387462832', 'neuro@gmail.com'),
(3, 'Gynecology', '1382161832', 'gynae@gmail.com'),
(4, 'Andrology', '1387461822', 'adnro@gmail.com')
;

INSERT INTO SALARY VALUES
('1-0-0', 200000),
('0-3-0', 100000),
('2-0-0', 250000),
('0-0-4', 150000),
('0-0-2', 140000)
;

INSERT INTO APPOINT_CHEMIST VALUES
(2, 1, 'Good', 'Confidence', 'Lack of Bargaining'),
(1, 2, 'Poor', 'Memory', 'Short Term Memory'),
(4, 1, 'Great', 'Confidence', 'Amnetia')
;

INSERT INTO APPOINT_STAFF VALUES
(2, 1, 'Good', 'Confidence', 'Lack of Bargaining'),
(1, 2, 'Poor', 'Memory', 'Short Term Memory'),
(4, 1, 'Great', 'Confidence', 'Amnetia')
;

INSERT INTO APPOINT_DOCTOR VALUES
(2, 4, 'Andrologist', 'Andrology', 200000),
(1, 2, 'Gynecologist', 'Gynecology', 100000),
(2, 3, 'Neurologist', 'Neurology', 200000)
;

INSERT INTO TAKE_WORKER_SAL VALUES
('1-0-0', '2024-04-23', '12:12:12', 200000),
('0-3-0', '2024-04-23', '12:12:12', 100000),
('0-0-2', '2024-04-23', '12:12:12', 150000)
;

INSERT INTO HANDLES_INVENTORY VALUES
(1, 2),
(2, 1),
(3, 4),
(4, 3)
;

INSERT INTO DOCTOR_USES VALUES
(1, 3, 3, 1000),
(2, 2, 2, 200),
(3, 1, 4, 500),
(4, 4, 1, 270)
;

INSERT INTO APPROVES_EXPENSE VALUES
(1, 1, 'ECG', 1000000),
(2, 2, 'FINANCIAL', 100000)
;

INSERT INTO BILLING_DEPARTMENT VALUES
(1, 'Pharmacy Billing', '1234512345', 'pharmacybilling@gmail.com', 1),
(2, 'Consulting Billing', '1234511345', 'consultingbilling@gmail.com', 2)
;

show 