package com.service.carservice.dto;

import com.service.carservice.models.Car;
import com.service.carservice.models.Owner;
import com.service.carservice.models.ServiceRecord;

public class ServiceRecordRequestDTO {
    private ServiceRecord serviceRecord;
    private Car car;
    private Owner owner;

    public ServiceRecord getServiceRecord() {
        return serviceRecord;
    }

    public void setServiceRecord(ServiceRecord serviceRecord) {
        this.serviceRecord = serviceRecord;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}