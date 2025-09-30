/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package nidocpatientsystem;
/**
 *
 * @author 50099648
 */
import java.util.Scanner;

public class NIDocPatientSystem {
    private static final int MAX_PATIENTS = 20; //maximum patient capacity
    private static Patient[] patients = new Patient[MAX_PATIENTS]; // patient storage
    private static int patientCount = 0; // current patient count
    private static int selectedPatientIndex = -1; //currently selected patient (-1 is none)

    // main application
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        //5 hardcoded patients for testing
        patients[patientCount++] = new Patient(1234567890L, "John", "Doe", "10 Main St", "12-03-24", 30);
        patients[patientCount++] = new Patient(9876543210L, "Alice", "Smith", "20 Elm St", "05-02-24", 17);
        patients[patientCount++] = new Patient(5556667770L, "Bob", "Brown", "5 Oak Ave", "22-01-24", 72);
        patients[patientCount++] = new Patient(3334445550L, "Emma", "Wilson", "30 Pine Rd", "15-03-24", 50);
        patients[patientCount++] = new Patient(9998887770L, "Michael", "Johnson", "8 Maple Dr", "10-03-24", 65);
                
        //main menu loop, only accepts 1-8, and constantly runs,
        int choice = 0;
             do {
            displayMainMenu();
            System.out.print("\nEnter choice: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter a number: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> displayAllPatients();
                case 2 -> displaySinglePatient(scanner);
                case 3 -> addNewPatient(scanner);
                case 4 -> statsMenu(scanner);
                case 5 -> updateJabs(scanner);
                case 6 -> updateBMI(scanner);
                case 7 -> updateAge(scanner);
                case 8 -> System.out.println("Exiting system...");
                default -> System.out.println("Invalid option. Try again.");
            }
        } while (choice != 8);

        scanner.close(); //scanner closes after main loop finishes to ensure no ram leakage.
    }

    //the main menu method which prints the main menu
    private static void displayMainMenu() {
        System.out.println("\n\t\t\tNI Doc-Patient System\n\n\t\t\t\tMain Menu\n");
        System.out.println("\t\t\t\tCurrent selected patient: " + (selectedPatientIndex != -1 ? patients[selectedPatientIndex].getHospitalNumber() : "None"));
        System.out.println("1. Display All Patient Details" + "\t\t" + "5. Record Patient Jabs");
        System.out.println("2. Display Single Patient Details" + "\t" + "6. Update BMI");
        System.out.println("3. Add New Patient" + "\t\t\t" + "7. Update Age");
        System.out.println("4. Find Patients Group (Stats)" + "\t\t" + "8. Exit");
    }


    private static void displayAllPatients() {
        if (patientCount == 0) {
            System.out.println("No patients available.");
            return;
        }
        for (int index = 0; index < patientCount; index++) {
            System.out.println(patients[index].getDetails() + "\n");
        }
    }

    // displays a single patient record, and then selects that patient which change the selectedPatientIndex, which allows age and bmi and jab to updated.
    private static void displaySinglePatient(Scanner scanner) {
        System.out.print("Enter hospital number (10-digit) of the patient: ");
        try {
            long hospNumber = scanner.nextLong();
            int index = findPatientByHospitalNumber(hospNumber); //finds patient by hospital number, if hospnumber is not found it returns a -1
            if (index != -1) {
                selectedPatientIndex = index;
                System.out.println("Patient found:");
                System.out.println(patients[index].getDetails());
            } else {
                System.out.println("Patient not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid hospital number format.");
        }
    }
    // add patient  which checks if there's more than 20 patient and then allows user to add more patients
    private static void addNewPatient(Scanner scanner) {
        if (patientCount >= MAX_PATIENTS) {
            System.out.println("Maximum number of patients reached.");
            return;
        }
        
        try {
            int hospNumber = 0;
        boolean isValid = false;
        
        // hosp number input with validation
        while (!isValid) {
            System.out.print("Enter hospital number (must be 10 digits) or 0 to cancel: ");
            String input = scanner.nextLine();
            
            if (input.equals("0")) {
                System.out.println("Cancelled adding new patient.");
                return;
            }
            
            // Check if it's exactly 10 digits
            if (input.length() != 10 || !input.matches("\\d+")) {
                System.out.println("Error: Must be exactly 10 digits (numbers only)");
                continue;
            }
            
            hospNumber = Integer.parseInt(input);
            
            // Check for duplicate
            boolean duplicate = false; //assumes theres no duplicates
            for (int index = 0; index < patientCount; index++) { //loops through all patients untill index is equal to patient count
                if (patients[index].getHospitalNumber() == hospNumber) {
                    duplicate = true;
                    break;
                }
            }
            
            if (duplicate) {
                System.out.println("Error: This hospital number already exists");
            } else {
                isValid = true;  // Exit loop if valid and unique
            }
        }   //will catch an exception if format is wrong at end, i used the NumberFormatException
            // all code re-used for the validation for forename,surname and address, simple validation, trims and removes all empty spaces and if it's null it will error and make the user retry
            System.out.print("Enter forename: ");
            String forename;
            while ((forename = scanner.nextLine().trim()).isEmpty()) {
            System.out.print("Forename cannot be empty. Try again: ");
        }

            System.out.print("Enter surname: ");
            String surname;
            
            while ((surname = scanner.nextLine().trim()).isEmpty()) {
                System.out.print("Surname cannot be empty. Try again: ");
        }   

            System.out.print("Enter address (max 40 chars): ");
            String address;
            
            while (true) {
                address = scanner.nextLine().trim();
                if (address.length() <= 40 && !address.isEmpty()) break;
                        System.out.print("Invalid address. Max 40 chars. Try again: ");
        }
            System.out.print("Enter date last visit (dd-mm-yy): ");
            String dateLastVisit = scanner.nextLine().trim();
            
            //checks the format of the dateLastVisit to ensure data integerity and consistantly accross the program (dd-mm-yy)
            while (!dateLastVisit.matches("\\d{2}-\\d{2}-\\d{2}")) {
                System.out.print("Invalid format. Use dd-mm-yy: ");
                dateLastVisit = scanner.nextLine().trim();
            }
            //simple validation to ensure age is greater than 0 and less than 120
            int age;
                while (true) {
                    System.out.print("Enter age: ");
                        try {
                            age = Integer.parseInt(scanner.nextLine());
                            if (age >= 0 && age <= 120) {
                                break; } else {
                                System.out.println("age must be between 0 and 120. try again.");
                            }
                        } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Try again.");
                 }
            }
    
            Patient newPatient = new Patient(hospNumber, forename, surname, address, dateLastVisit, age);
            patients[patientCount++] = newPatient;
            System.out.println("Patient added successfully.");
            //exception handling just for format to ensure no numbers in forename ect
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format. Patient not added.");
        }
    }
    //stats menu, which allows 4 different formats to display patients
    private static void statsMenu(Scanner scanner) {
        int statsChoice = 0;
        System.out.println("\nNI Doc-Patient System Stats Menu");
        System.out.println("1. Display all patients under 18");
        System.out.println("2. Display all patients over 65");
        System.out.println("3. Display over 65s without a jab");
        System.out.println("4. Display overweight patients (BMI > 25)");
        System.out.println("5. Return to Main Menu");
        System.out.print("Please enter menu choice = ");
        while (statsChoice != 5)
        {
            try {
                statsChoice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }

            switch (statsChoice) {
                case 1 -> {
                    for (int i = 0; i < patientCount; i++) {
                        if (patients[i].getAge() < 18) {
                            System.out.println(patients[i].getDetails());
                        }
                    }
                }
                case 2 -> {
                    for (int i = 0; i < patientCount; i++) {
                        if (patients[i].getAge() > 65) {
                            System.out.println(patients[i].getDetails());
                        }
                    }
                }
                case 3 -> {
                    for (int i = 0; i < patientCount; i++) {
                        if (patients[i].getAge() > 65 && !patients[i].isFluJabIssued() && !patients[i].isCovidJabIssued()) {
                            System.out.println(patients[i].getDetails());
                        }
                    }
                }
                case 4 -> {
                    for (int i = 0; i < patientCount; i++) {
                        if (patients[i].getBMI() > 25) {
                            System.out.println(patients[i].getDetails());
                        }
                    }
                }
                case 5 -> System.out.println("Returning to Main Menu.");
                default -> System.out.println("Invalid option. Try again.");
            }
        }

    }

    private static void updateJabs(Scanner scanner) {
        if (selectedPatientIndex == -1) {
            System.out.println("No patient selected.");
            return;
        }

        Patient p = patients[selectedPatientIndex];

        System.out.print("Flu jab (yes/no): ");
        p.setFluJabIssued(scanner.nextLine().equalsIgnoreCase("yes"));

        System.out.print("Covid jab (yes/no): ");
        p.setCovidJabIssued(scanner.nextLine().equalsIgnoreCase("yes"));

        System.out.println("Jabs updated.");
    }

    private static void updateBMI(Scanner scanner) {
        if (selectedPatientIndex == -1) {
            System.out.println("No patient selected. Please display a patient record first.");
            return;
        }
        
        Patient p = patients[selectedPatientIndex]; // Patient in P array to be P, patients[selectedPatientIndex] which is the current patient selected, to allow for code to be re-used
        
        try {
            System.out.print("Enter weight in kg: ");
            double weight = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter height in meters: ");
            double height = Double.parseDouble(scanner.nextLine());
            if (height<0) { // need height to be greater than 0(validation)
                p.calcBMI(weight,height);
            }
            else
                System.out.println("Height must be greater than 0."); 
            p.calcBMI(weight, height);
            //bmi is rounded to 2 decimal point to allo
            System.out.println("BMI updated: " + String.format("%.2f", p.getBMI()));
            // another exception to only allow numbers, as bmi would not be able to be calculated
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. BMI not updated.");
        }
    }
    //method to update patient's age then calls patients[selectedPatientIndex].setAge(newAge) then prints of the Type ie child,adult ect to confirm correct logic
    private static void updateAge(Scanner scanner) {
        if (selectedPatientIndex == -1) {
            System.out.println("No patient selected. Please display a patient record first.");
            return;
        }
        Patient p = patients[selectedPatientIndex];
        try {
            System.out.print("Enter new age (0-120): ");
            int newAge = Integer.parseInt(scanner.nextLine());
            p.setAge(newAge);
            System.out.println("Age updated. New patient type: " + p.getType());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Age not updated.");
        }
    }


    private static int findPatientByHospitalNumber(long hospNumber) {
        for (int index = 0; index < patientCount; index++) {
            if (patients[index].getHospitalNumber() == hospNumber) {
                return index;
            }
        }
        return -1;
    }
}

