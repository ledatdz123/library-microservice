package com.capgemini.datle.employeeservicev2.command.api.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
@Data
public class Employee {
    @Id
    public String employeeId;
    public String firstName;
    public String lastName;
    public String KIN;
    public Boolean isDisciplined;
}
