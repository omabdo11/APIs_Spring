package com.spring.boot.ModelMapper;

import com.spring.boot.dto.EmployeeDto;
import com.spring.boot.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper EMPLOYEE_MAPPER = Mappers.getMapper(EmployeeMapper.class);

    Employee ToEmployee(EmployeeDto employeeDto);
    EmployeeDto ToEmployeeDto(Employee employee);

    List<EmployeeDto> ToEmployeeDtoList(List<Employee> employees);
    List<Employee> ToEmployeeList(List<EmployeeDto> employeeDtos);
}
