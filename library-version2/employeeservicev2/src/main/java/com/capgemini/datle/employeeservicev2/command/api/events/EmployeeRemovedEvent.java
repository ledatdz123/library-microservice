package com.capgemini.datle.employeeservicev2.command.api.events;

import lombok.Data;

@Data
public class EmployeeRemovedEvent {
    public String employeeId;
}
