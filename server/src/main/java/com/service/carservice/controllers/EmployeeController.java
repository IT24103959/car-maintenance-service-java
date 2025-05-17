package com.service.carservice.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.service.carservice.models.Employee;
import com.service.carservice.services.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController extends BaseController<Employee> {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<Employee[]> getEmployees() {
        return response(employeeService.getAll(), Employee.class);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        return response(employeeService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Void> addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
        return response(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
        employeeService.deleteById(id);
        return response(HttpStatus.NO_CONTENT);
    }

}
