package com.capgemini.datle.employeeservicev2.command.api.controller;

import com.capgemini.datle.employeeservicev2.command.api.command.AddEmployeeCommand;
import com.capgemini.datle.employeeservicev2.command.api.command.RemoveEmployeeCommand;
import com.capgemini.datle.employeeservicev2.command.api.command.UpdateEmployeeCommand;
import com.capgemini.datle.employeeservicev2.command.api.model.EmployeeModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v2/employees")
public class EmployeeController {
    @Autowired
    private CommandGateway commandGateway;

    public EmployeeController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String addEmployee(@RequestBody EmployeeModel employeeModel){
        AddEmployeeCommand addEmployeeCommand=AddEmployeeCommand
                .builder()
                .employeeId(UUID.randomUUID().toString())
                .firstName(employeeModel.getFirstName())
                .lastName(employeeModel.getLastName())
                .KIN(employeeModel.getKIN())
                .isDisciplined(false)
                .build();
        commandGateway.sendAndWait(addEmployeeCommand);
        return "Add Employee Success";
    }
    @PutMapping
    public String updateEmployee(@RequestBody EmployeeModel employeeModel){
        UpdateEmployeeCommand updateEmployeeCommand=UpdateEmployeeCommand
                .builder()
                .employeeId(employeeModel.getEmployeeId())
                .firstName(employeeModel.getFirstName())
                .lastName(employeeModel.getLastName())
                .KIN(employeeModel.getKIN())
                .isDisciplined(employeeModel.getIsDisciplined())
                .build();
        commandGateway.sendAndWait(updateEmployeeCommand);
        return "Employee Update Success";
    }
    @DeleteMapping("/{employeeId}")
    public String removeEmployee(@PathVariable("employeeId") String employeeId){
        RemoveEmployeeCommand removeEmployeeCommand=RemoveEmployeeCommand
                .builder()
                .employeeId(employeeId)
                .build();
        commandGateway.sendAndWait(removeEmployeeCommand);
        return "Remove Employee Success";
    }
}
