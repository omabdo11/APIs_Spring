package com.spring.boot.controller;

import com.spring.boot.dto.EmployeeDto;
import com.spring.boot.service.EmployeeService;
import jakarta.transaction.SystemException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping ("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<EmployeeDto> addEmployeeDto(@RequestBody @Valid EmployeeDto employeeDto) throws SystemException {
        return   ResponseEntity.created(URI.create("/add")).body(employeeService.addEmployeeDto(employeeDto));
    }

    @PutMapping("/updata")
    public ResponseEntity<EmployeeDto> updateEmployeeDto(@RequestBody @Valid EmployeeDto employeeDto) throws SystemException {

        return ResponseEntity.ok(employeeService.updateEmployeeDto(employeeDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<EmployeeDto> deleteEmployeeDto(@RequestParam Long id) {
        employeeService.deleteEmployeeDto(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getEmployee")
    public ResponseEntity<List<EmployeeDto>> getAccountsDto() {
        return  ResponseEntity.ok(employeeService.getAllEmployeeDto());
    }
    @GetMapping("/getBYID")
    public ResponseEntity<EmployeeDto> getEmployeeById(@RequestParam Long id) throws SystemException {
        return ResponseEntity.ok(employeeService.getEmployeeDtoById(id));
    }
    @GetMapping("/getByListOfId")
    public ResponseEntity<List<EmployeeDto>> getEmployeeByListOfId(@RequestParam List<Long> idList) throws SystemException {
        return ResponseEntity.ok(employeeService.findEmployeeDtoByListOfId(idList));
    }
    @GetMapping("/getByListOfName")
    public ResponseEntity<List<EmployeeDto>> getEmployeeByListOfName(@RequestParam List<String> listOfEmployeeDto) throws SystemException {
        return ResponseEntity.ok(employeeService.findEmployeeDtoByListOfName(listOfEmployeeDto));
    }

    @PostMapping("/saveEmployeeWithListOfEmail")
    public ResponseEntity<EmployeeDto> saveEmployeeWithListOfEmail(@RequestBody EmployeeDto employeeDto) throws SystemException {
    return ResponseEntity.ok(employeeService.saveEmployeeWithEmail(employeeDto));
    }
}