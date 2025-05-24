package com.service.carservice.services;

import com.service.carservice.cache.EmployeeList;
import com.service.carservice.models.Car;
import com.service.carservice.models.Employee;
import com.service.carservice.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService extends BaseService {
    private final EmployeeRepository employeeRepository;
    private EmployeeList items;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.items = employeeRepository.getAll();
    }

    public void addEmployee(Employee employee) {
        employee.setId(employeeRepository.getNextId(true));
        items.insert(employee);
    }

    public Employee getEmployeeById(int id) {
        return getById(id);
    }

    public boolean updateEmployeeById(int id, Employee updatedEmployee) {
        updatedEmployee.setId(id);
        return updateById(id, updatedEmployee);
    }

    public boolean updateById(int id, Employee updated) {
        for (int i = 0; i < items.getSize(); i++) {
            if (items.getByIndex(i).getId() == id) {
                items.setByIndex(i, updated);
                return true;
            }
        }
        return false;
    }

    public Employee getById(int id){
        for (int i = 0; i < items.getSize(); i++) {
            Employee item = items.getByIndex(i);
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void deleteById(int id) {
        Employee value = getById(id);
        items.deleteByValue(value);
    }

    public Employee[] getAll() {
        return items.toArray();
    }


    public void persistOnShutdown() {
        employeeRepository.persistToFile(items);
    }
}