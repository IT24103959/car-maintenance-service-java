package com.service.carservice.services;

import com.service.carservice.cache.ServiceRecordList;
import org.springframework.stereotype.Service;

import com.service.carservice.dto.ServiceRecordRequestDTO;
import com.service.carservice.models.Car;
import com.service.carservice.models.ServiceRecord;
import com.service.carservice.repositories.ServiceRecordRepository;
import com.service.carservice.util.SelectionSort;

@Service
public class ServiceRecordService extends BaseService {
    private final ServiceRecordRepository serviceRecordRepository;
    private ServiceRecordList items;

    public ServiceRecordService(ServiceRecordRepository serviceRecordRepository) {
        this.items = serviceRecordRepository.getAll();
        this.serviceRecordRepository = serviceRecordRepository;
    }

    public ServiceRecord[] getAll(String order) {
        if (order.equalsIgnoreCase("asc")) {
            SelectionSort.sortAscending(items);
        } else {
            SelectionSort.sortDescending(items);
        }
        return items.toArray();
    }

    public void addServiceRecord(ServiceRecordRequestDTO requestDTO) {
        ServiceRecord newRecord = requestDTO.getServiceRecord();
        Car car = requestDTO.getCar();
        car.setOwner(requestDTO.getOwner());
        newRecord.setCar(car);
        newRecord.setId(serviceRecordRepository.getNextId(true));
        items.insert(newRecord);
    }

    public ServiceRecord updateServiceRecord(int id, ServiceRecordRequestDTO recordDTO) {
        ServiceRecord updatedRecord = recordDTO.getServiceRecord();
        ServiceRecord currentRecord = getById(id);
        if (currentRecord != null) {
            currentRecord.setDate(updatedRecord.getDate());
            currentRecord.setDescription(updatedRecord.getDescription());
            currentRecord.setCost(updatedRecord.getCost());
            currentRecord.setCar(recordDTO.getCar());
            currentRecord.getCar().setOwner(recordDTO.getOwner());
            currentRecord.setCompleted(updatedRecord.getCompleted());
            currentRecord.setEmployeeId(updatedRecord.getEmployeeId());
        }
        return currentRecord;
    }

    public ServiceRecord completeServiceRecord(int id) {
        ServiceRecord record = getById(id);
        if (record != null) {
            record.setCompleted(true);
        }
        return record;
    }

    protected int getId(ServiceRecord record) {
        return record.getId();
    }

    protected void persistOnShutdown() {
        serviceRecordRepository.persistToFile(items);
    }

    public ServiceRecord getById(int id){
        for (int i = 0; i < items.getSize(); i++) {
            ServiceRecord item = items.getByIndex(i);
            if (getId(item) == id) {
                return item;
            }
        }
        return null;
    }

    public void deleteById(int id) {
        ServiceRecord value = getById(id);
        items.deleteByValue(value);
    }

}