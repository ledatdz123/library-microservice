package com.capgemini.datle.employeeservicev2.command.api.model;

import lombok.Data;

@Data
public class EmployeeModel {
    public String employeeId;
    public String firstName;
    public String lastName;
    public String KIN;
    public Boolean isDisciplined;
}
