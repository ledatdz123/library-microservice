package com.capgemini.datle.CGLibraryEmployeeServiceVS1.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
    String resourceName;
    String filedName;
    String fieldValue;

    public ResourceNotFoundException(String resourceName, String filedName, String fieldValue) {
        super(String.format("%s not found with %s : %l",resourceName,filedName, fieldValue));
        this.resourceName = resourceName;
        this.filedName = filedName;
        this.fieldValue = fieldValue;
    }
}
