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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.carservice.dto.ServiceRecordRequestDTO;
import com.service.carservice.models.ServiceRecord;
import com.service.carservice.services.ServiceRecordService;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/service-records")
public class ServiceController {

    @Autowired
    private ServiceRecordService serviceRecordService;

    @GetMapping
    public ResponseEntity<ServiceRecord[]> getServices(@RequestParam(defaultValue = "asc") String order) {
        return new ResponseEntity<>(serviceRecordService.getAllServiceRecords(order), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceRecord> getServiceById(@PathVariable int id) {
        return new ResponseEntity<>(serviceRecordService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addService(@RequestBody ServiceRecordRequestDTO serviceRecord) {
        serviceRecordService.addServiceRecord(serviceRecord);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable int id) {
        serviceRecordService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceRecord> updateServiceRecord(@PathVariable int id,
            @RequestBody ServiceRecordRequestDTO requestDTO) {
        try {
            ServiceRecord updatedRecord = serviceRecordService.updateServiceRecord(id, requestDTO);
            if (updatedRecord == null) {
                return new ResponseEntity(null, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(updatedRecord, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/complete/{id}")
    public ResponseEntity<ServiceRecord> completeServiceRecord(@PathVariable int id) {
        ServiceRecord serviceRecord = serviceRecordService.completeServiceRecord(id);
        if (serviceRecord == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(serviceRecord, HttpStatus.OK);
    }
}