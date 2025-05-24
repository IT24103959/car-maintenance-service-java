package com.service.carservice.cache;

import com.service.carservice.models.ServiceRecord;

class ServiceRecordLink{
    public ServiceRecord instance;
    public ServiceRecordLink next;

    public ServiceRecordLink(ServiceRecord instance) {
        this.instance = instance;
    }
}

public class ServiceRecordList extends BaseList{
    private ServiceRecordLink first;


    public ServiceRecordList() {
        super();
        first = null;
    }

    public void insert(ServiceRecord instance) {
        ServiceRecordLink newLink = new ServiceRecordLink(instance);

        if (first == null) {
            first = newLink;
        }else{
            ServiceRecordLink temp = first;
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = newLink;
        }
        size++;
    }

    public void deleteByValue(ServiceRecord instance) {
        ServiceRecordLink temp = first;
        ServiceRecordLink prev = null;
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

    public ServiceRecord[] toArray(){
        ServiceRecordLink temp = first;

        ServiceRecord[] data = new ServiceRecord[size];
        for (int i = 0; i < size; i++) {
            data[i] = temp.instance;
            temp = temp.next;
        }
        return data;
    }

    public ServiceRecord getByIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        ServiceRecordLink current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.instance;
    }

    public void setByIndex(int index, ServiceRecord instance) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        ServiceRecordLink current = first;
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
