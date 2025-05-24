package com.service.carservice.cache;

import com.service.carservice.models.Employee;

class EmployeeLink{
    public Employee instance;
    public EmployeeLink next;

    public EmployeeLink(Employee instance) {
        this.instance = instance;
    }
}

public class EmployeeList extends BaseList{
    private EmployeeLink first;


    public EmployeeList() {
        super();
        first = null;
    }

    public void insert(Employee instance) {
        EmployeeLink newLink = new EmployeeLink(instance);

        if (first == null) {
            first = newLink;
        }else{
            EmployeeLink temp = first;
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = newLink;
        }
        size++;
    }

    public void deleteByValue(Employee instance) {
        EmployeeLink temp = first;
        EmployeeLink prev = null;
        while(temp != null){
            prev = temp;
            if(temp.instance.getId() == instance.getId()){
                prev.next = temp.next;
                size--;
                return;
            }
            temp = temp.next;
        }
    }

    public Employee[] toArray(){
        EmployeeLink temp = first;

        Employee[] data = new Employee[size];
        for (int i = 0; i < size; i++) {
            data[i] = temp.instance;
            temp = temp.next;
        }
        return data;
    }

    public Employee getByIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        EmployeeLink current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.instance;
    }

    public void setByIndex(int index, Employee instance) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        EmployeeLink current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.instance = instance;
    }

    public void clean(){
        first = null;
        size = 0;
    }


}
