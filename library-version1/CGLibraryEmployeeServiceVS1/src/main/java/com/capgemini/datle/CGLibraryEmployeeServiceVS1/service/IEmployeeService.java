package com.capgemini.datle.CGLibraryEmployeeServiceVS1.service;

import com.capgemini.datle.CGLibraryEmployeeServiceVS1.payloads.BookDTO;
import com.capgemini.datle.CGLibraryEmployeeServiceVS1.payloads.EmployeeDTO;

import java.util.List;

public interface IEmployeeService {
    EmployeeDTO addEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, String employeeId);
    Boolean removeEmployee(String employeeId);
    EmployeeDTO getEmployeeDetails(String employeeId);
    List<EmployeeDTO> getAllEmployee();
    List<BookDTO> getEmployeeBorrowedBook(String employeeId);
}
