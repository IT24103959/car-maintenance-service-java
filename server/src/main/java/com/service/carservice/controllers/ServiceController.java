package com.service.carservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.carservice.dto.ServiceRecordRequestDTO;
import com.service.carservice.models.ServiceRecord;
import com.service.carservice.services.ServiceRecordService;

@RestController
@RequestMapping("/api/service-records")
public class ServiceController extends BaseController<ServiceRecord> {

    @Autowired
    private ServiceRecordService serviceRecordService;

    @GetMapping
    public ResponseEntity<ServiceRecord[]> getServices() {
        return response(serviceRecordService.getAll(), ServiceRecord.class);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceRecord> getServiceById(@PathVariable int id) {
        return response(serviceRecordService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Void> addService(@RequestBody ServiceRecordRequestDTO serviceRecord) {
        serviceRecordService.addServiceRecord(serviceRecord);
        return response(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable int id) {
        serviceRecordService.deleteById(id);
        return response(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceRecord> updateServiceRecord(@PathVariable int id,
            @RequestBody ServiceRecordRequestDTO requestDTO) {
        try {
            ServiceRecord updatedRecord = serviceRecordService.updateServiceRecord(id, requestDTO);
            if (updatedRecord == null) {
                return response(null, HttpStatus.NOT_FOUND);
            }

            return response(updatedRecord, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return response(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/complete/{id}")
    public ResponseEntity<ServiceRecord> completeServiceRecord(@PathVariable int id) {
        ServiceRecord serviceRecord = serviceRecordService.completeServiceRecord(id);
        if (serviceRecord == null) {
            return response(null, HttpStatus.NOT_FOUND);
        }
        return response(serviceRecord, HttpStatus.OK);
    }
}