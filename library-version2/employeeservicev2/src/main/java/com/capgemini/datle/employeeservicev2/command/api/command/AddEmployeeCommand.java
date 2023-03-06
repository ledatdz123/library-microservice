package com.capgemini.datle.employeeservicev2.command.api.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class AddEmployeeCommand {
    @TargetAggregateIdentifier
    public String employeeId;
    public String firstName;
    public String lastName;
    public String KIN;
    public Boolean isDisciplined;
}
