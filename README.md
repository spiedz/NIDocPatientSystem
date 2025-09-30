# NIDoc Patient System

A Java-based patient management system for healthcare facilities developed as part of OOP1A coursework.

## Description

NIDoc Patient System is a console-based application that manages patient records including:
- Patient demographic information (hospital number, name, address)
- Health metrics (age, BMI)
- Vaccination records (flu jab, COVID jab)
- Patient categorization (Child, Adult, OAP)

## Features

- **Patient Management**: Add, view, and manage up to 20 patient records
- **Patient Search**: Find patients by hospital number
- **Health Tracking**: Update BMI, age, and vaccination status
- **Statistical Reports**: 
  - View patients under 18
  - View patients over 65
  - Identify over-65s without vaccinations
  - Find overweight patients (BMI > 25)
- **Patient Categories**: Automatic categorization based on age
  - Child: Under 18 years
  - Adult: 18-65 years
  - OAP (Old Age Pensioner): Over 65 years

## Technical Details

- **Language**: Java
- **IDE**: NetBeans
- **Build System**: Apache Ant
- **Student ID**: 50099648

## Project Structure

```
NIDocPatientSystem/
├── src/
│   └── nidocpatientsystem/
│       ├── NIDocPatientSystem.java  # Main application with menu system
│       └── Patient.java             # Patient class with data management
├── build/                           # Compiled classes (ignored in git)
├── dist/                           # Distribution files (ignored in git)
└── nbproject/                      # NetBeans project configuration
```

## How to Run

1. Open the project in NetBeans IDE
2. Build the project (Clean and Build)
3. Run the main class: `NIDocPatientSystem`

## Usage

The application provides a menu-driven interface with the following options:

1. **Display All Patient Details** - Shows all registered patients
2. **Display Single Patient Details** - Search and select a patient by hospital number
3. **Add New Patient** - Register a new patient (max 20 patients)
4. **Find Patients Group (Stats)** - Access statistical reports menu
5. **Record Patient Jabs** - Update vaccination records for selected patient
6. **Update BMI** - Calculate and update BMI for selected patient
7. **Update Age** - Update age for selected patient
8. **Exit** - Close the application

## Validation Rules

- **Hospital Number**: Must be exactly 10 digits, unique for each patient
- **Age**: Must be between 0 and 120 years
- **Address**: Maximum 40 characters
- **Date Format**: dd-mm-yy
- **BMI Calculation**: Requires positive weight (kg) and height (meters)

## Sample Data

The system comes pre-loaded with 5 test patients for demonstration purposes.

## Author

Student ID: 50099648

## License

Educational project - all rights reserved.

