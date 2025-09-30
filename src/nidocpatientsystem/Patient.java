/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nidocpatientsystem;
/**
 *
 * @author 50099648
 */
public class Patient {
    // Fields
    private final long hospitalNumber; //unique 10 digits ID for patient
    private String forename;
    private String surname;
    private String address; 
    private String dateLastVisit;// will be formated as dd-mm-yy
    private boolean fluJabIssued;
    private boolean covidJabIssued;
    private int age;
    private double bmi;

    // constructs a new patient with all required details and provided placeholder where needed i.e bmi which isn't asked for during the add patient method
    public Patient(long hospitalNumber, String forename, String surname, String address, String dateLastVisit, int age) {
        this.hospitalNumber = hospitalNumber;
        this.forename = forename;
        this.surname = surname;
        this.address = address;
        this.dateLastVisit = dateLastVisit;
        this.age = age;
        this.fluJabIssued = false;
        this.covidJabIssued = false;
        this.bmi = 0.0;
    }

    // getting and setting methods
    public long getHospitalNumber() {
        return hospitalNumber;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }
    //updates address, while truncating the address provided down to a 40 maximum
    public void setAddress(String address) {
        if (address.length() > 40) {
            this.address = address.substring(0, 40);
        } else {
            this.address = address;
        }
    }

    public String getDateLastVisit() {
        return dateLastVisit;
    }

    public void setDateLastVisit(String dateLastVisit) {
        this.dateLastVisit = dateLastVisit;
    }

    public boolean isFluJabIssued() {
        return fluJabIssued;
    }

    public void setFluJabIssued(boolean fluJabIssued) {
        this.fluJabIssued = fluJabIssued;
    }

    public boolean isCovidJabIssued() {
        return covidJabIssued;
    }

    public void setCovidJabIssued(boolean covidJabIssued) {
        this.covidJabIssued = covidJabIssued;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 0 && age <= 110) {
            this.age = age;
        }
    }

    public double getBMI() {
        return bmi;
    }
    // Calculates and stores BMI using weight and height
    public void calcBMI(double weight, double height) {
        if (height > 0) {
            this.bmi = weight / (height * height);
        }
    }
    // sets type of patient by age ie under 18 beturn chil and over 65 are old-age-pensionor anything else is just adult
    public String getType() {
        if (age < 18) return "Child";
        if (age > 65) return "OAP";
        return "Adult";
    }

    // return a formatted string containing all patient details
    public String getDetails() {
        return "Hospital Number: " + hospitalNumber + "\n" +
               "Name: " + forename + " " + surname + "\n" +
               "Type: " + getType() + "\n" +
               "Address: " + address + "\n" +
               "Last Visit: " + dateLastVisit + "\n" +
               "Flu Jab: " + (fluJabIssued ? "Yes" : "No") + "\n" +
               "Covid Jab: " + (covidJabIssued ? "Yes" : "No") + "\n" +
               "Age: " + age + "\n" +
               "BMI: " + String.format("%.2f", bmi);
    }
}

