package com.service.carservice.services;

import org.springframework.stereotype.Service;

import com.service.carservice.dto.ServiceRecordRequestDTO;
import com.service.carservice.models.Car;
import com.service.carservice.models.ServiceRecord;
import com.service.carservice.repositories.ServiceRecordRepository;
import com.service.carservice.util.LinkedList;
import com.service.carservice.util.SelectionSort;

@Service
public class ServiceRecordService extends BaseService<ServiceRecord> {
    private final ServiceRecordRepository serviceRecordRepository;

    public ServiceRecordService(ServiceRecordRepository serviceRecordRepository) {
        super(serviceRecordRepository.getAll());
        this.serviceRecordRepository = serviceRecordRepository;
    }

    public LinkedList<ServiceRecord> getAll(String order) {
        if (order.equalsIgnoreCase("asc")) {
            SelectionSort.sortAscending(items);
        } else {
            SelectionSort.sortDescending(items);
        }
        return items;
    }

    public ServiceRecord[] getAllServiceRecords(String order) {
        LinkedList<ServiceRecord> itemsData = getAll(order);
        ServiceRecord[] itemsArr = new ServiceRecord[itemsData.size()];
        for (int i = 0; i < items.size(); i++) {
            itemsArr[i] = items.get(i);
        }
        return itemsArr;
    }

    public void addServiceRecord(ServiceRecordRequestDTO requestDTO) {
        ServiceRecord newRecord = requestDTO.getServiceRecord();
        Car car = requestDTO.getCar();
        car.setOwner(requestDTO.getOwner());
        newRecord.setCar(car);
        newRecord.setId(serviceRecordRepository.getNextId(true));
        items.add(newRecord);
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

    @Override
    protected int getId(ServiceRecord record) {
        return record.getId();
    }

    @Override
    protected void persistOnShutdown() {
        serviceRecordRepository.persistToFile(items);
    }
}