package com.capgemini.datle.employeeservicev2.command.api.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeAddedEvent {
    public String employeeId;
    public String firstName;
    public String lastName;
    public String KIN;
    public Boolean isDisciplined;
}
