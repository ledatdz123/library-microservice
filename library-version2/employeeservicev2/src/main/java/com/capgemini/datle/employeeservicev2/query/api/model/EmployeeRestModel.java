package com.capgemini.datle.employeeservicev2.query.api.model;

import lombok.Data;

@Data
public class EmployeeRestModel {
    public String employeeId;
    public String firstName;
    public String lastName;
    public String KIN;
    public Boolean isDisciplined;
}
