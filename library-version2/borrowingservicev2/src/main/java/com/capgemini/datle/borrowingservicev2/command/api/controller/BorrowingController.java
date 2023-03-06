package com.capgemini.datle.borrowingservicev2.command.api.controller;

import com.capgemini.datle.borrowingservicev2.command.api.command.AddBorrowingCommand;
import com.capgemini.datle.borrowingservicev2.command.api.model.BorrowingModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("api/v2/borrowing")
public class BorrowingController {
    @Autowired
    private CommandGateway commandGateway;
    @PostMapping
    public String addBookBorrowing(@RequestBody BorrowingModel model){
        AddBorrowingCommand command=AddBorrowingCommand
                .builder()
                .borrowingId(UUID.randomUUID().toString())
                .bookId(model.getBookId())
                .employeeId(model.getEmployeeId())
                .borrowingDate(new Date())
                .status("CREATED")
                .build();
        commandGateway.sendAndWait(command);
        return "Borrow Created Success";
    }
}
