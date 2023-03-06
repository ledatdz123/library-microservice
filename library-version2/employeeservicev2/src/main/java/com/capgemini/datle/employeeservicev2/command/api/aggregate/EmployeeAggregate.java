package com.capgemini.datle.employeeservicev2.command.api.aggregate;


import com.capgemini.datle.employeeservicev2.command.api.command.AddEmployeeCommand;
import com.capgemini.datle.employeeservicev2.command.api.command.RemoveEmployeeCommand;
import com.capgemini.datle.employeeservicev2.command.api.command.UpdateEmployeeCommand;
import com.capgemini.datle.employeeservicev2.command.api.events.EmployeeAddedEvent;
import com.capgemini.datle.employeeservicev2.command.api.events.EmployeeRemovedEvent;
import com.capgemini.datle.employeeservicev2.command.api.events.EmployeeUpdatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@NoArgsConstructor
public class EmployeeAggregate {
    @AggregateIdentifier
    public String employeeId;
    public String firstName;
    public String lastName;
    public String KIN;
    public Boolean isDisciplined;
    @CommandHandler
    public EmployeeAggregate(AddEmployeeCommand addEmployeeCommand){
        EmployeeAddedEvent employeeAddedEvent=new EmployeeAddedEvent();
        BeanUtils.copyProperties(addEmployeeCommand, employeeAddedEvent);
        AggregateLifecycle.apply(employeeAddedEvent);
    }
    @EventSourcingHandler
    public void on(EmployeeAddedEvent employeeAddedEvent){
        this.employeeId=employeeAddedEvent.getEmployeeId();
        this.firstName=employeeAddedEvent.getFirstName();
        this.lastName=employeeAddedEvent.getLastName();
        this.KIN=employeeAddedEvent.getKIN();
        this.isDisciplined=employeeAddedEvent.getIsDisciplined();
    }

    @CommandHandler
    public void handle(UpdateEmployeeCommand updateEmployeeCommand){
        EmployeeUpdatedEvent employeeUpdatedEvent=new EmployeeUpdatedEvent();
        BeanUtils.copyProperties(updateEmployeeCommand, employeeUpdatedEvent);
        AggregateLifecycle.apply(employeeUpdatedEvent);
    }
    @EventSourcingHandler
    public void on(EmployeeUpdatedEvent employeeUpdatedEvent){
        this.employeeId=employeeUpdatedEvent.getEmployeeId();
        this.firstName=employeeUpdatedEvent.getFirstName();
        this.lastName=employeeUpdatedEvent.getLastName();
        this.KIN=employeeUpdatedEvent.getKIN();
        this.isDisciplined=employeeUpdatedEvent.getIsDisciplined();
    }

    @CommandHandler
    public void handle(RemoveEmployeeCommand removeEmployeeCommand){
        EmployeeRemovedEvent employeeRemovedEvent=new EmployeeRemovedEvent();
        BeanUtils.copyProperties(removeEmployeeCommand, employeeRemovedEvent);
        AggregateLifecycle.apply(employeeRemovedEvent);
    }
    @EventSourcingHandler
    public void on(EmployeeRemovedEvent employeeRemovedEvent){
        this.employeeId=employeeRemovedEvent.employeeId;
    }
}
