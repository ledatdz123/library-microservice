package com.capgemini.datle.employeeservicev2.command.api.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeUpdatedEvent {
    public String employeeId;
    public String firstName;
    public String lastName;
    public String KIN;
    public Boolean isDisciplined;
}
