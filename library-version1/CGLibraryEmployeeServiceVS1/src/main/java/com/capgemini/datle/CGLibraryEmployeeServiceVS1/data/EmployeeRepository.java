package com.capgemini.datle.CGLibraryEmployeeServiceVS1.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
