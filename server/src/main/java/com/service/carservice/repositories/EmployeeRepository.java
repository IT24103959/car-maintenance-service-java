package com.service.carservice.repositories;

import com.service.carservice.models.Employee;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepository extends BaseRepository<Employee> {
    @Override
    protected String getFilePath() {
        return "src/main/resources/data/employees.json";
    }

    @Override
    protected Class<Employee> getTypeClass() {
        return Employee.class;
    }

    @Override
    protected int getId(Employee employee) {
        return employee.getId();
    }

}
