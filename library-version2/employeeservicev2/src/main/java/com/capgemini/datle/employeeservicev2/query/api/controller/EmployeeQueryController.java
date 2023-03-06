package com.capgemini.datle.employeeservicev2.query.api.controller;

import com.capgemini.datle.commonservice.model.BookCommonModel;
import com.capgemini.datle.commonservice.model.BorrowingCommonModel;
import com.capgemini.datle.commonservice.queries.GetAllBook;
import com.capgemini.datle.commonservice.queries.GetListBorrowingByEmployee;
import com.capgemini.datle.employeeservicev2.query.api.model.EmployeeRestModel;
import com.capgemini.datle.employeeservicev2.query.api.queries.QueryAllEmployee;
import com.capgemini.datle.employeeservicev2.query.api.queries.QueryEmployeeDetail;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v2/employees")
public class EmployeeQueryController {
    @Autowired
    private QueryGateway queryGateway;
    @GetMapping("/{employeeId}")
    public EmployeeRestModel getEmployeeDetail(@PathVariable("employeeId") String employeeId){
        QueryEmployeeDetail queryEmployee=new QueryEmployeeDetail(employeeId);
        return queryGateway.query(queryEmployee, ResponseTypes.instanceOf(EmployeeRestModel.class))
                .join();
    }
    @GetMapping
    public List<EmployeeRestModel> getAllEmployee(){
        QueryAllEmployee queryAllEmployee=new QueryAllEmployee();
        return queryGateway.query(queryAllEmployee, ResponseTypes.multipleInstancesOf(EmployeeRestModel.class))
                .join();
    }
    @GetMapping("/{employeeId}/book")
    public List<BookCommonModel> getBookBorrowedByEmployee(@PathVariable("employeeId") String employeeId){
        GetListBorrowingByEmployee getListBorrowingByEmployee=GetListBorrowingByEmployee
                .builder()
                .employeeId(employeeId)
                .build();
        List<BorrowingCommonModel> listBorrowing=queryGateway
                .query(getListBorrowingByEmployee,
                        ResponseTypes.multipleInstancesOf(BorrowingCommonModel.class))
                .join();
        GetAllBook getAllBook=new GetAllBook();
        List<BookCommonModel> listBook=queryGateway
                .query(getAllBook,
                        ResponseTypes.multipleInstancesOf(BookCommonModel.class))
                .join();
        List<BookCommonModel> listBookByEmployee=new ArrayList<>();
        for (int i=0; i<listBook.size(); i++){
            for (int j=0; j<listBorrowing.size(); j++){
                if (listBook.get(i).getBookId().equals(listBorrowing.get(j).getBookId())){
                    listBookByEmployee.add(listBook.get(i));
                    continue;
                }
            }
        }
//        listBookByEmployee=listBook
//                .stream()
//                .filter(x->listBorrowing.stream().anyMatch(y->y.getBookId().equals(x.getBookId())))
//                .collect(Collectors.toList());
        return listBookByEmployee;
    }
}
