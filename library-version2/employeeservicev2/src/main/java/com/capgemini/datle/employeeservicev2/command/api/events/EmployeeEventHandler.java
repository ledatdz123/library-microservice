package com.capgemini.datle.employeeservicev2.command.api.events;

import com.capgemini.datle.employeeservicev2.command.api.data.Employee;
import com.capgemini.datle.employeeservicev2.command.api.data.EmployeeRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EmployeeEventHandler {
    private EmployeeRepository employeeRepository;

    public EmployeeEventHandler(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @EventHandler
    public void on(EmployeeAddedEvent employeeAddedEvent){
        Employee employee=new Employee();
        BeanUtils.copyProperties(employeeAddedEvent, employee);
        employeeRepository.save(employee);
    }
    @EventHandler
    public void on(EmployeeUpdatedEvent employeeUpdatedEvent){
        Employee employee=new Employee();
        BeanUtils.copyProperties(employeeUpdatedEvent, employee);
        employeeRepository.save(employee);
    }
    @EventHandler
    public void on(EmployeeRemovedEvent employeeRemovedEvent){
        Employee employee=new Employee();
        BeanUtils.copyProperties(employeeRemovedEvent, employee);
        employeeRepository.delete(employee);
    }
}
