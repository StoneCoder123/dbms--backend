
create database hospital_db;
use hospital_db;

CREATE TABLE Doctor(
DoctorID INT PRIMARY KEY auto_increment,
FirstName VARCHAR(50) NOT NULL,
LastName VARCHAR(50),
DOB DATE NOT NULL,
Education VARCHAR(50),
Gender VARCHAR(10) NOT NULL,
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
Gender VARCHAR(50),
History VARCHAR(500),
DOB DATE NOT NULL,
Password VARCHAR(255)
);

CREATE TABLE Staff(
StaffID INT PRIMARY KEY auto_increment,
FirstName VARCHAR(50) NOT NULL,
LastName VARCHAR(50),
DOB DATE NOT NULL,
Education VARCHAR(50),
Gender VARCHAR(50),
Post VARCHAR(30),
Email VARCHAR(80),
Phone VARCHAR(15),
Password VARCHAR(255)
);


CREATE TABLE Chemist(
ChemistID INT PRIMARY KEY auto_increment,
FirstName VARCHAR(50) NOT NULL,
LastName VARCHAR(50),
DOB DATE NOT NULL,
Gender CHAR(1),
Email VARCHAR(80),
Phone VARCHAR(15),
Password VARCHAR(255)
);

CREATE TABLE Bill(
BillID INT PRIMARY KEY auto_increment,
PatientID INT References Patient(PatientID),
TotalCost INT
);

CREATE TABLE Room(
RoomID INT PRIMARY KEY auto_increment,
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


CREATE TABLE Admin(
AdminID INT PRIMARY KEY auto_increment,
Email varchar(80),
DoctorID INT REFERENCES Doctor(DoctorID),
AccessLevel VARCHAR(50),
Password VARCHAR(255)
);

CREATE TABLE Machinery(
MachineID INT PRIMARY KEY auto_increment,
Name VARCHAR(50) NOT NULL,
Cost INT
);

CREATE TABLE Medicines(
MedicineID INT PRIMARY KEY auto_increment,
MedicineName VARCHAR(50) NOT NULL,
Cost INT,
Type VARCHAR(50),
CompanyName VARCHAR(50)
);

CREATE TABLE Surgery(
SurgeryID INT PRIMARY KEY auto_increment,
PatientID INT REFERENCES Patient(PatientID),
DoctorID INT REFERENCES Doctor(DoctorID),
Type VARCHAR(30),
CriticalLevel INT NOT NULL,
Cost INT DEFAULT 0
);

CREATE TABLE Helps_In(
SurgeryID INT REFERENCES Surgery(SurgeryID),
StaffID INT REFERENCES STAFF(STAFFID),
Rating INT
);

CREATE TABLE Expenditure_Department(
DeptID INT PRIMARY KEY,
Name VARCHAR(30) NOT NULL,
Phone VARCHAR(15),
Email VARCHAR(80)
);

CREATE TABLE Approves_Expense(
AdminID INT REFERENCES Admin(AdminID),
DeptID INT REFERENCES Expenditure_Department(DeptID),
Type VARCHAR(30),
Amount INT
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
CREATE TABLE appointments (
    appointmentID INT AUTO_INCREMENT PRIMARY KEY,
    patientID INT NOT NULL,
    doctorID INT NOT NULL,
    time DATETIME DEFAULT NULL,
    status INT NOT NULL,
    FOREIGN KEY (patientID) REFERENCES patient(patientID),
    FOREIGN KEY (doctorID) REFERENCES doctor(doctorID), 
    Cost INT DEFAULT 0
);


-- TRIGGERS --

DELIMITER $$

create trigger surgeryBill
after insert on Surgery
for each row
BEGIN
	insert into BILL(PatientID, TotalCost) value
    (new.PatientID, new.Cost);
END $$

create trigger appointmentBill
after insert on appointments
for each row
BEGIN
	insert into BILL(PatientID, TotalCost) value
    (new.PatientID, new.Cost);
END $$


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
