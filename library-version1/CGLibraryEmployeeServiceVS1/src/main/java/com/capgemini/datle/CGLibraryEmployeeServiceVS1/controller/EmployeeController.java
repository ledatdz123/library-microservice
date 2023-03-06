package com.capgemini.datle.CGLibraryEmployeeServiceVS1.controller;

import com.capgemini.datle.CGLibraryEmployeeServiceVS1.payloads.BookDTO;
import com.capgemini.datle.CGLibraryEmployeeServiceVS1.payloads.EmployeeDTO;
import com.capgemini.datle.CGLibraryEmployeeServiceVS1.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    @Autowired
    private IEmployeeService iEmployeeService;
    @PostMapping
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO create=iEmployeeService.addEmployee(employeeDTO);
        return new ResponseEntity<>(create, HttpStatus.CREATED);
    }
    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEployee(@RequestBody EmployeeDTO employeeDTO,
                                                     @PathVariable("employeeId") String employeeId){
        EmployeeDTO update=iEmployeeService.updateEmployee(employeeDTO, employeeId);
        return ResponseEntity.ok(update);
    }
    @DeleteMapping("/{employeeId}")
    public Boolean removeEmployee(@PathVariable("employeeId") String id){
        return iEmployeeService.removeEmployee(id);
    }
    @GetMapping
    public List<EmployeeDTO> getAllEmployee(){
        return iEmployeeService.getAllEmployee();
    }
    @GetMapping ("/{employeeId}")
    public EmployeeDTO getEmployeeDetails(@PathVariable("employeeId") String id){
        return iEmployeeService.getEmployeeDetails(id);
    }
    @GetMapping("/{employeeId}/books")
    public ResponseEntity<List<BookDTO>> getEmployeeBorrowedBook(@PathVariable("employeeId") String employeeId){
        List<BookDTO> listBook=iEmployeeService.getEmployeeBorrowedBook(employeeId);
        return ResponseEntity.ok(listBook);
    }
}
