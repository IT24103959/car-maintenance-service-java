package com.service.carservice.repositories;

import com.service.carservice.models.Employee;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository extends BaseRepository<Employee> {
    protected String getFilePath() {
        return "src/main/resources/data/employees.json";
    }

    protected Class<Employee> getTypeClass() {
        return Employee.class;
    }

    protected int getId(Employee employee) {
        return employee.getId();
    }

}
