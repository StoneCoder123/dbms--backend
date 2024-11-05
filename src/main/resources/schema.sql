
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
TotalCost INT,
Status INT DEFAULT 0,
TYPE Varchar(100)
);

CREATE TABLE TakesDoctorSal(
DsID INT PRIMARY KEY auto_increment,
DoctorID INT REFERENCES Doctor(DoctorID),
Salary INT,
IssueDate DATE NOT NULL
);

CREATE TABLE TakesChemistSal(
CsID INT PRIMARY KEY auto_increment,
ChemistID INT REFERENCES Chemist(ChemistID),
Salary INT,
IssueDate DATE NOT NULL
);

CREATE TABLE RoomBooking(
RoomBookingID INT PRIMARY KEY auto_increment,
RoomID INT REFERENCES Room(RoomID),
PatientID INT REFERENCES Patient(PatientID),
BookFrom DATE NOT NULL,
BookTill DATE NOT NULL,
NumDays INT DEFAULT 0
);

CREATE TABLE Room(
RoomID INT PRIMARY KEY auto_increment,
RoomType VARCHAR(50),
Cost INT
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

CREATE TABLE MedicineRequests(
RequestID INT PRIMARY KEY auto_increment,
MedicineID INT ,
MedicineName VARCHAR(50) NOT NULL,
Cost INT,
Type VARCHAR(50),
CompanyName VARCHAR(50),
Amount INT DEFAULT 0
);

CREATE TABLE Medicines(
MedicineID INT PRIMARY KEY auto_increment,
MedicineName VARCHAR(50) NOT NULL,
Cost INT,
Type VARCHAR(50),
CompanyName VARCHAR(50),
Amount INT DEFAULT 0
);

CREATE TABLE Surgery(
SurgeryID INT PRIMARY KEY auto_increment,
PatientID INT REFERENCES Patient(PatientID),
DoctorID INT REFERENCES Doctor(DoctorID),
BillID INT REFERENCES Bill(BillID),
Type VARCHAR(30),
CriticalLevel INT NOT NULL,
Time DATETIME DEFAULT NULL
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
    billID INT DEFAULT NULL,
    prescription VARCHAR(500),
    FOREIGN KEY (patientID) REFERENCES patient(patientID),
    FOREIGN KEY (doctorID) REFERENCES doctor(doctorID),
    FOREIGN KEY (billID) references bill(billID)
);
