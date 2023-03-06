package com.capgemini.datle.employeeservicev2.query.api.handler;

import com.capgemini.datle.commonservice.model.EmployeeCommonModel;
import com.capgemini.datle.commonservice.queries.GetEmployeeDetail;
import com.capgemini.datle.employeeservicev2.command.api.data.Employee;
import com.capgemini.datle.employeeservicev2.command.api.data.EmployeeRepository;
import com.capgemini.datle.employeeservicev2.query.api.model.EmployeeRestModel;
import com.capgemini.datle.employeeservicev2.query.api.queries.QueryAllEmployee;
import com.capgemini.datle.employeeservicev2.query.api.queries.QueryEmployeeDetail;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeQueryHandler {
    private EmployeeRepository employeeRepository;

    public EmployeeQueryHandler(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @QueryHandler
    public EmployeeRestModel getBookDetail(QueryEmployeeDetail queryEmployeeDetail){
        var employeeOptional=employeeRepository.findById(queryEmployeeDetail.getEmployeeId());
        EmployeeRestModel employeeRestModel=new EmployeeRestModel();
        if(employeeOptional.isPresent()){
            Employee employee=employeeOptional.get();
            BeanUtils.copyProperties(employee, employeeRestModel);
        }
        return employeeRestModel;
    }
    @QueryHandler
    public List<EmployeeRestModel> getAllEmployee(QueryAllEmployee queryAllEmployee){
        List<Employee> employees=employeeRepository.findAll();
        List<EmployeeRestModel> employeeList=new ArrayList<>();
        EmployeeRestModel employeeRestModel=null;
        for (Employee employeeEntity: employees
             ) {
            employeeRestModel=new EmployeeRestModel();
            BeanUtils.copyProperties(employeeEntity, employeeRestModel);
            employeeList.add(employeeRestModel);
        }
        return employeeList;
    }
    @QueryHandler
    public EmployeeCommonModel getEmployeeDetail(GetEmployeeDetail getEmployeeDetail){
        Employee employee=employeeRepository.findById(getEmployeeDetail.getEmployeeId()).get();
        EmployeeCommonModel employeeCommonModel=new EmployeeCommonModel();
        BeanUtils.copyProperties(employee, employeeCommonModel);
        return employeeCommonModel;
    }
}
