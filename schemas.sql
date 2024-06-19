CREATE DATABASE MedicalRecords;

-- Step 2: Use the Database
USE MedicalRecords;

-- Step 3: Create the Table
CREATE TABLE Diagnoses (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           patientName VARCHAR(100) NOT NULL,
                           diagnosis TEXT NOT NULL,
                           date DATE NOT NULL
);

-- Step 4: Insert Data
INSERT INTO Diagnoses (patientName, diagnosis, date)
VALUES ('John Doe', 'Flu', '2024-06-19'),
       ('Jane Smith', 'Allergy', '2024-06-18'),
       ('Alice Johnson', 'Covid-19', '2024-06-17');
