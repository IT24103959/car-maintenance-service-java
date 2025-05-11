package com.service.carservice.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.service.carservice.dto.ServiceRecordRequestDTO;
import com.service.carservice.models.ServiceRecord;
import com.service.carservice.repositories.ServiceRecordRepository;
import com.service.carservice.util.SelectionSort;

@Service
public class ServiceRecordService {

    private final ServiceRecordRepository serviceRecordRepository;
    private LinkedList<ServiceRecord> serviceRecords;

    public ServiceRecordService(ServiceRecordRepository serviceRecordRepository) {
        this.serviceRecordRepository = serviceRecordRepository;
        this.serviceRecords = new LinkedList<>(serviceRecordRepository.loadServiceRecords());
    }

    public void addServiceRecord(ServiceRecordRequestDTO requestDTO) {
        ServiceRecord newRecord = requestDTO.getServiceRecord();
        newRecord.setCar(requestDTO.getCar());
        newRecord.getCar().setOwner(requestDTO.getOwner());
        newRecord.setId(serviceRecordRepository.getNextId()); // Fetch nextId from repository
        serviceRecords.add(newRecord);
        serviceRecordRepository.addServiceRecord(newRecord);
    }

    public ServiceRecord updateServiceRecord(int id, ServiceRecordRequestDTO recordDTO) {
        ServiceRecord currentRecord = serviceRecordRepository.getServiceRecordById(id);
        if (currentRecord != null) {
            ServiceRecord updatedRecord = recordDTO.getServiceRecord();
            currentRecord.setDate(updatedRecord.getDate());
            currentRecord.setDescription(updatedRecord.getDescription());
            currentRecord.setCost(updatedRecord.getCost());
            currentRecord.setCar(recordDTO.getCar());
            currentRecord.getCar().setOwner(recordDTO.getOwner());
            currentRecord.setCompleted(updatedRecord.getCompleted());
            serviceRecordRepository.saveServiceRecords(serviceRecordRepository.getAllServiceRecords());
        }
        return currentRecord;
    }

    public ServiceRecord completeServiceRecord(int id) {
        ServiceRecord record = serviceRecordRepository.getServiceRecordById(id);
        if (record != null) {
            record.setCompleted(true);
            serviceRecordRepository.saveServiceRecords(serviceRecordRepository.getAllServiceRecords());
        }
        return record;
    }

    public ServiceRecord getServiceRecordById(int id) {
        return serviceRecordRepository.getServiceRecordById(id);
    }

    public List<ServiceRecord> getServiceRecords() {
        return serviceRecords;
    }

    public void deleteServiceRecord(int id) {
        serviceRecords.removeIf(record -> record.getId() == id);
        serviceRecordRepository.persistToFile(serviceRecords);
    }

    public void sortServiceRecordsByDate(String order) {
        List<ServiceRecord> records = serviceRecordRepository.getAllServiceRecords();
        if (order.equalsIgnoreCase("asc")) {
            SelectionSort.sortAscending(records);
        } else {
            SelectionSort.sortDescending(records);
        }
        serviceRecordRepository.saveServiceRecords(records);
    }
}