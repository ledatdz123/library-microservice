package com.capgemini.datle.CGLibraryEmployeeServiceVS1.service;

import com.capgemini.datle.CGLibraryEmployeeServiceVS1.data.Employee;
import com.capgemini.datle.CGLibraryEmployeeServiceVS1.data.EmployeeRepository;
import com.capgemini.datle.CGLibraryEmployeeServiceVS1.exceptions.ResourceNotFoundException;
import com.capgemini.datle.CGLibraryEmployeeServiceVS1.payloads.BookDTO;
import com.capgemini.datle.CGLibraryEmployeeServiceVS1.payloads.BorrowingDTO;
import com.capgemini.datle.CGLibraryEmployeeServiceVS1.payloads.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements IEmployeeService{
    @Autowired
    private EmployeeRepository employeeRepo;
    @Autowired
    private WebClient.Builder webClient;
    @Override
    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        Employee employee=new Employee();
        employee.setEmployeeId(UUID.randomUUID().toString());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName((employeeDTO.getLastName()));
        employee.setKIN(employeeDTO.getKIN());
        employee.setDisciplined(false);
        Employee saveEmployee=employeeRepo.save(employee);
        return employeeToDTO(saveEmployee);
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, String employeeId) {
        Employee employee=employeeRepo.findById(employeeId)
                .orElseThrow(()-> new ResourceNotFoundException("Employee", "Id", employeeId));
        employee.setDisciplined(employeeDTO.getDisciplined());
        Employee saveEmployee=employeeRepo.save(employee);
        return employeeToDTO(saveEmployee);
    }

    @Override
    public Boolean removeEmployee(String employeeId) {
        Employee employee=employeeRepo.findById(employeeId)
                .orElseThrow(()->new ResourceNotFoundException("Employee", "Id", employeeId));
        try {
            employeeRepo.delete(employee);
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public EmployeeDTO getEmployeeDetails(String employeeId) {
        Employee employee=employeeRepo.findById(employeeId)
                .orElseThrow(()->new ResourceNotFoundException("Employee", "Id", employeeId));
        return employeeToDTO(employee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployee() {
        List<Employee> employeeList=employeeRepo.findAll();
        List<EmployeeDTO>employeeDTOS=employeeList.stream().map(employee -> employeeToDTO(employee)).collect(Collectors.toList());
        return employeeDTOS;
    }

    @Override
    public List<BookDTO> getEmployeeBorrowedBook(String employeeId) {
        List<BookDTO> listBook=webClient
                .build()
                .get()
                .uri("http://localhost:8090/api/v1/books/")
                .retrieve()
                .bodyToFlux(BookDTO.class)
                .collectList()
                .block();
        List<BorrowingDTO> listBorrowing=webClient
                .build()
                .get()
                .uri("http://localhost:8090/api/v1/borrowing/"+employeeId)
                .retrieve()
                .bodyToFlux(BorrowingDTO.class)
                .collectList()
                .block();

        List<BookDTO> listBookEmployee=new ArrayList<>();
        for (int i=0; i<listBook.size(); i++){
            for (int j=0; j<listBorrowing.size(); j++){
                if (listBook.get(i).getBookId().equals(listBorrowing.get(j).getBookId())){
                    listBookEmployee.add(listBook.get(i));
                    continue;
                }
            }
        }
        return listBookEmployee;
    }

    public EmployeeDTO employeeToDTO(Employee employee){
        EmployeeDTO employeeDTO=new EmployeeDTO();
        employeeDTO.setEmployeeId(employee.getEmployeeId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setKIN(employee.getKIN());
        employeeDTO.setDisciplined(employee.getDisciplined());
        return employeeDTO;
    }
}
