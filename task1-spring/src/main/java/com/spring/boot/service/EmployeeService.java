package com.spring.boot.service;

import com.spring.boot.dto.EmployeeDto;
import jakarta.transaction.SystemException;


import java.util.List;

public interface EmployeeService {


    EmployeeDto addEmployeeDto(EmployeeDto employeeDto) throws SystemException;
   EmployeeDto updateEmployeeDto(EmployeeDto employeeDto) throws SystemException;
    boolean  deleteEmployeeDto( Long id);
     List<EmployeeDto> getAllEmployeeDto();
    EmployeeDto getEmployeeDtoById(Long id) throws SystemException;
    List<EmployeeDto> findEmployeeDtoByListOfId( List<Long> idList) throws SystemException;
   List<EmployeeDto> findEmployeeDtoByListOfName(List<String> listOfEmployeeDto) throws SystemException;

   EmployeeDto saveEmployeeWithEmail(EmployeeDto employeeDto) throws SystemException;

}
