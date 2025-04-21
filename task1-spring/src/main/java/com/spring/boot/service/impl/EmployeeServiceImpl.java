package com.spring.boot.service.impl;

import com.spring.boot.ModelMapper.EmployeeMapper;
import com.spring.boot.dto.EmployeeDto;
import com.spring.boot.exception.CustomSystemException;
import com.spring.boot.model.Email;
import com.spring.boot.model.Employee;
import com.spring.boot.repo.EmployeeRepo;
import com.spring.boot.service.EmployeeService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public EmployeeDto addEmployeeDto(EmployeeDto employeeDto) throws SystemException {
        if(Objects.nonNull(employeeDto.getId())){
            throw new CustomSystemException("error.id.invalid");
        }
        Employee employee= EmployeeMapper.EMPLOYEE_MAPPER.ToEmployee(employeeDto);
        employeeRepo.save(employee);
         return employeeDto;
    }

    @Override
    public EmployeeDto updateEmployeeDto(EmployeeDto employeeDto) throws SystemException {
        if(Objects.isNull(employeeDto.getId())) {
            throw new CustomSystemException("error.id.valid");
        }
        Optional<Employee> employee = employeeRepo.findById(employeeDto.getId());
        if(employee.isEmpty()){
            throw new SystemException("error.id.found" + employeeDto.getId());
        }
        Employee employee1 = EmployeeMapper.EMPLOYEE_MAPPER.ToEmployee(employeeDto);
        employeeRepo.save(employee1);
        return employeeDto;
    }

    @Override
    public boolean deleteEmployeeDto(Long id) {
        Optional<Employee> employee = employeeRepo.findById(id);
        if(employee.isEmpty()){
            return false;
        }
        employeeRepo.deleteById(id);
        return true;
    }

    @Override
    public List<EmployeeDto> getAllEmployeeDto() {
        List<Employee> employees = employeeRepo.findAll();
        return extractEmployee(employees);
    }

    @Override
    public EmployeeDto getEmployeeDtoById(Long id) throws SystemException {
        if(Objects.isNull(id)){
            throw new CustomSystemException("error.id.valid");
        }
        Optional<Employee> employee = employeeRepo.findById(id);
        if(employee.isEmpty()){
            throw new CustomSystemException("error.id.found");
        }
        EmployeeDto employeeDto = EmployeeMapper.EMPLOYEE_MAPPER.ToEmployeeDto(employee.get());
        return employeeDto;
    }

    @Override
    public List<EmployeeDto> findEmployeeDtoByListOfId(List<Long> idList) throws SystemException {
         if(Objects.isNull(idList)){
             throw new CustomSystemException("error.idList.found");
         }
         Optional<Employee> employee =employeeRepo.findById(idList.get(0));
         if(employee.isEmpty()){
             throw new CustomSystemException("error.id.found");
         }
         List<Employee> employees = employeeRepo.findEmployeeDtoByListOfId(idList);
        return extractEmployee(employees);
    }

    @Override
    public List<EmployeeDto> findEmployeeDtoByListOfName(List<String> listOfEmployeeDto) throws SystemException {
        if(listOfEmployeeDto == null || listOfEmployeeDto.isEmpty()){
            throw new CustomSystemException("error.nameList.found");
        }
        Optional<Employee> employee =employeeRepo.findByName( listOfEmployeeDto.get(0));
        if(employee.isEmpty()){
            throw new CustomSystemException("error.name.found");
        }
        List<Employee>  employees = employeeRepo.findByNameList(listOfEmployeeDto);
        return extractEmployee(employees);
    }

    @Override
    public EmployeeDto saveEmployeeWithEmail(EmployeeDto employeeDto) throws SystemException {
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setAge(employeeDto.getAge());
        employee.setSalary(employeeDto.getSalary());

         List<Email> emailList = new ArrayList<>();

         for (Email email : employeeDto.getEmails()) {
             Email em  = new Email();
             em.setEmployee(employee);
             emailList.add(em);
             em.setType(email.getType());
             em .setValue(email.getValue());
         }
        employee.setEmails(emailList);
       Employee employee1 =EmployeeMapper.EMPLOYEE_MAPPER.ToEmployee(employeeDto);
       employeeRepo.save(employee);
        return employeeDto;
    }

    private static List<EmployeeDto> extractEmployee(List<Employee> employees) {
        List <EmployeeDto> employeeDtos  = new ArrayList<>();
        for (Employee employee1 : employees) {
            EmployeeDto employeeDto = EmployeeMapper.EMPLOYEE_MAPPER.ToEmployeeDto(employee1);
            employeeDto.setEmails(null);
            employeeDtos.add(employeeDto);
        }
        return employeeDtos;
    }


}
