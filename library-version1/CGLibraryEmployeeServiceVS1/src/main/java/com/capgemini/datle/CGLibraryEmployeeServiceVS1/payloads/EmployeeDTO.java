package com.capgemini.datle.CGLibraryEmployeeServiceVS1.payloads;

public class EmployeeDTO {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String KIN;
    private Boolean isDisciplined;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getKIN() {
        return KIN;
    }

    public void setKIN(String KIN) {
        this.KIN = KIN;
    }

    public Boolean getDisciplined() {
        return isDisciplined;
    }

    public void setDisciplined(Boolean disciplined) {
        isDisciplined = disciplined;
    }
}
