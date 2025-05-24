package com.service.carservice.services;

import com.service.carservice.models.Employee;
import com.service.carservice.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService extends BaseService<Employee> {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        super(employeeRepository.getAll());
        this.employeeRepository = employeeRepository;
    }

    public void addEmployee(Employee employee) {
        employee.setId(employeeRepository.getNextId(true));
        items.add(employee);
    }

    public Employee[] getAllEmployees() {
        Employee[] itemsArr = new Employee[items.size()];
        return itemsArr;
    }

    public Employee getEmployeeById(int id) {
        return getById(id);
    }

    public boolean updateEmployeeById(int id, Employee updatedEmployee) {
        updatedEmployee.setId(id);
        return updateById(id, updatedEmployee);
    }

    protected int getId(Employee employee) {
        return employee.getId();
    }

    public void persistOnShutdown() {
        employeeRepository.persistToFile(items);
    }
}
