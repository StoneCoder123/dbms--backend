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
    Gender VARCHAR(10) NOT NULL,
    Phone VARCHAR(15),
    Email VARCHAR(255),
    Post VARCHAR(50),
    Department VARCHAR(50),
    Specialization VARCHAR(30),
    Password VARCHAR(255)
);

-- Create Patient table
CREATE TABLE Patient (
    PatientID INT PRIMARY KEY AUTO_INCREMENT,
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

-- Create Chemist table
CREATE TABLE Chemist (
    ChemistID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50),
    DOB DATE NOT NULL,
    Gender CHAR(1),
    Email VARCHAR(80),
    Phone VARCHAR(15),
    Password VARCHAR(255)
);

-- Create Bill table
CREATE TABLE Bill (
    BillID INT PRIMARY KEY AUTO_INCREMENT,
    PatientID INT,
    TotalCost INT,
    Status INT DEFAULT 0,
    TYPE VARCHAR(100),
    FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE
);

-- Create TakesDoctorSal table
CREATE TABLE TakesDoctorSal (
    DsID INT PRIMARY KEY AUTO_INCREMENT,
    DoctorID INT,
    Salary INT,
    IssueDate DATE NOT NULL,
    FOREIGN KEY (DoctorID) REFERENCES Doctor(DoctorID) ON DELETE CASCADE
);

-- Create TakesChemistSal table
CREATE TABLE TakesChemistSal (
    CsID INT PRIMARY KEY AUTO_INCREMENT,
    ChemistID INT,
    Salary INT,
    IssueDate DATE NOT NULL,
    FOREIGN KEY (ChemistID) REFERENCES Chemist(ChemistID) ON DELETE CASCADE
);

-- Create Room table
CREATE TABLE Room (
    RoomID INT PRIMARY KEY AUTO_INCREMENT,
    RoomType VARCHAR(50),
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
    FOREIGN KEY (RoomID) REFERENCES Room(RoomID) ON DELETE CASCADE,
    FOREIGN KEY (PatientID) REFERENCES Patient(PatientID) ON DELETE CASCADE
);

-- Create Admin table
CREATE TABLE Admin (
    AdminID INT PRIMARY KEY AUTO_INCREMENT,
    Email VARCHAR(80),
    DoctorID INT,
    AccessLevel VARCHAR(50),
    Password VARCHAR(255),
    FOREIGN KEY (DoctorID) REFERENCES Doctor(DoctorID) ON DELETE SET NULL
);

-- Create Machinery table
CREATE TABLE Machinery (
    MachineID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(50) NOT NULL,
    Cost INT
);

-- Create Medicines table
CREATE TABLE Medicines (
    MedicineID INT PRIMARY KEY AUTO_INCREMENT,
    MedicineName VARCHAR(50) NOT NULL,
    Cost INT,
    Type VARCHAR(50),
    CompanyName VARCHAR(50),
    Amount INT DEFAULT 0
);

-- Create MedicineRequests table
CREATE TABLE MedicineRequests (
    RequestID INT PRIMARY KEY AUTO_INCREMENT,
    MedicineID INT,
    MedicineName VARCHAR(50) NOT NULL,
    Cost INT,
    Type VARCHAR(50),
    CompanyName VARCHAR(50),
    Amount INT DEFAULT 0
    -- FOREIGN KEY (MedicineID) REFERENCES Medicines(MedicineID) ON DELETE SET NULL--
);

CREATE TABLE PharmacyRequests(
RequestID INT PRIMARY KEY auto_increment,
MedicineName VARCHAR(50) NOT NULL,
PatientID INT references Patient(PatientID),
Amount INT DEFAULT 0
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

-- Create users table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fullName VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create appointments table
CREATE TABLE appointments (
    appointmentID INT AUTO_INCREMENT PRIMARY KEY,
    patientID INT NOT NULL,
    doctorID INT NOT NULL,
    time DATETIME DEFAULT NULL,
    status INT NOT NULL,
    billID INT DEFAULT NULL,
    prescription VARCHAR(500),
    FOREIGN KEY (patientID) REFERENCES Patient(PatientID) ON DELETE CASCADE,
    FOREIGN KEY (doctorID) REFERENCES Doctor(DoctorID) ON DELETE CASCADE,
    FOREIGN KEY (billID) REFERENCES Bill(BillID) ON DELETE SET NULL
);
