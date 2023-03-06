package com.capgemini.datle.borrowingservicev2.query.api.controller;

import com.capgemini.datle.borrowingservicev2.query.api.model.BorrowingRestModel;
import com.capgemini.datle.borrowingservicev2.query.api.queries.GetBorrowingByEmployee;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v2/borrowing")
public class BorrowingQueryController {
    @Autowired
    private QueryGateway queryGateway;
    @GetMapping("/{employeeId}")
    public List<BorrowingRestModel> getBorrowingByEmployee(@PathVariable("employeeId") String employeeId){
        GetBorrowingByEmployee getBorrowingByEmployee=GetBorrowingByEmployee
                .builder()
                .emplyeeId(employeeId)
                .build();
        List<BorrowingRestModel> list=queryGateway
                .query(getBorrowingByEmployee, ResponseTypes.multipleInstancesOf(BorrowingRestModel.class))
                .join();
        return list;
    }
}
