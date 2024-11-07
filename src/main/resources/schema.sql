drop database hospital_db;
-- Create the database
CREATE DATABASE hospital_db;
USE hospital_db;
-- Create Doctor table
CREATE TABLE Doctor (
    DoctorID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50),
    DOB DATE NOT NULL,
    Education VARCHAR(50),
    Gender VARCHAR(10),
    Phone VARCHAR(15) NOT NULL,
    Email VARCHAR(255) UNIQUE NOT NULL,
    Post VARCHAR(50),
    Department VARCHAR(50),
    Specialization VARCHAR(30),
    Password VARCHAR(255) NOT NULL
);
-- Create Patient table
CREATE TABLE Patient (
    PatientID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50),
    Address VARCHAR(200),
    NTK VARCHAR(50),
    Email VARCHAR(80) UNIQUE NOT NULL,
    Gender VARCHAR(50),
    History VARCHAR(500),
    DOB DATE NOT NULL,
    Password VARCHAR(255) NOT NULL
);
-- Create Chemist table
CREATE TABLE Chemist (
    ChemistID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50),
    DOB DATE NOT NULL,
    Gender VARCHAR(10),
    Email VARCHAR(80) UNIQUE NOT NULL,
    Phone VARCHAR(15) NOT NULL,
    Password VARCHAR(255) NOT NULL
);
-- Create Bill table
CREATE TABLE Bill (
    BillID INT PRIMARY KEY AUTO_INCREMENT,
    PatientID INT,
    TotalCost INT DEFAULT 0,
    Status INT DEFAULT 0,
    TYPE VARCHAR(100),
    FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE
);
-- Create TakesDoctorSal table
CREATE TABLE TakesDoctorSal (
    DsID INT PRIMARY KEY AUTO_INCREMENT,
    DoctorID INT,
    Salary INT DEFAULT 0,
    IssueDate DATE NOT NULL,
    FOREIGN KEY (DoctorID) REFERENCES Doctor(DoctorID) ON DELETE CASCADE
);
-- Create TakesChemistSal table
CREATE TABLE TakesChemistSal (
    CsID INT PRIMARY KEY AUTO_INCREMENT,
    ChemistID INT,
    Salary INT DEFAULT 0,
    IssueDate DATE NOT NULL,
    FOREIGN KEY (ChemistID) REFERENCES Chemist(ChemistID) ON DELETE CASCADE
);
-- Create Room table
CREATE TABLE Room (
    RoomID INT PRIMARY KEY AUTO_INCREMENT,
    RoomType VARCHAR(50) NOT NULL,
    Cost INT
);
-- Create RoomBooking table
CREATE TABLE RoomBooking (
    RoomBookingID INT PRIMARY KEY AUTO_INCREMENT,
    RoomID INT,
    PatientID INT,
    BookFrom DATE NOT NULL,
    BookTill DATE NOT NULL,
    NumDays INT DEFAULT 0,
    BillID INT,
    FOREIGN KEY (RoomID) REFERENCES Room(RoomID) ON DELETE CASCADE,
    FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE,
    FOREIGN KEY (BillID) REFERENCES Bill(BillID) ON DELETE CASCADE
);
-- Create Admin table
CREATE TABLE Admin (
    AdminID INT PRIMARY KEY AUTO_INCREMENT,
    Email VARCHAR(80) UNIQUE NOT NULL,
    DoctorID INT,
    AccessLevel VARCHAR(50),
    Password VARCHAR(255) NOT NULL,
    FOREIGN KEY (DoctorID) REFERENCES Doctor(DoctorID) ON DELETE
    SET NULL
);
-- Create Machinery table
CREATE TABLE Machinery (
    MachineID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(50) NOT NULL,
    Cost INT DEFAULT 0
);
-- Create Medicines table
CREATE TABLE Medicines (
    MedicineID INT PRIMARY KEY AUTO_INCREMENT,
    MedicineName VARCHAR(50) UNIQUE NOT NULL,
    Cost INT DEFAULT 0,
    Type VARCHAR(50),
    CompanyName VARCHAR(50),
    Amount INT DEFAULT 0
);
-- Create MedicineRequests table
CREATE TABLE MedicineRequests (
    RequestID INT PRIMARY KEY AUTO_INCREMENT,
    MedicineID INT NOT NULL,
    MedicineName VARCHAR(50) NOT NULL,
    Cost INT DEFAULT 0,
    Type VARCHAR(50),
    CompanyName VARCHAR(50),
    Amount INT DEFAULT 0
);
CREATE TABLE PharmacyRequests(
    RequestID INT PRIMARY KEY auto_increment,
    MedicineName VARCHAR(50) NOT NULL,
    PatientID INT,
    Amount INT DEFAULT 0,
    FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE
);
-- Create Surgery table
CREATE TABLE Surgery (
    SurgeryID INT PRIMARY KEY AUTO_INCREMENT,
    PatientID INT,
    DoctorID INT,
    BillID INT,
    Type VARCHAR(30),
    CriticalLevel INT NOT NULL,
    Time DATETIME DEFAULT NULL,
    FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE,
    FOREIGN KEY (DoctorID) REFERENCES Doctor(DoctorID) ON DELETE CASCADE,
    FOREIGN KEY (BillID) REFERENCES Bill(BillID) ON DELETE CASCADE
);
-- Create appointments table
CREATE TABLE appointments (
    appointmentID INT AUTO_INCREMENT PRIMARY KEY,
    patientID INT,
    doctorID INT,
    time DATETIME DEFAULT NULL,
    status INT NOT NULL,
    billID INT,
    prescription VARCHAR(500),
    FOREIGN KEY (patientID) REFERENCES Patient(PatientID) ON DELETE CASCADE,
    FOREIGN KEY (doctorID) REFERENCES Doctor(DoctorID) ON DELETE CASCADE,
    FOREIGN KEY (billID) REFERENCES Bill(BillID) ON DELETE
    SET NULL
);
CREATE TABLE MachineHiring (
    HiringID INT PRIMARY KEY AUTO_INCREMENT,
    DoctorID INT,
    MachineID INT,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    FOREIGN KEY (DoctorID) REFERENCES Doctor(DoctorID) ON DELETE CASCADE,
    FOREIGN KEY (MachineID) REFERENCES Machinery(MachineID) ON DELETE CASCADE,
    CHECK (EndDate >= StartDate)
);