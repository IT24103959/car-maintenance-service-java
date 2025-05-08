package com.service.carservice.controllers;

import java.util.List;

import com.service.carservice.models.ServiceRecord;
import com.service.carservice.dto.ServiceRecordRequestDTO;
import com.service.carservice.services.ServiceTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/service-records")
public class ServiceController {

    @Autowired
    private ServiceTrackerService serviceTrackerService;

    @PostMapping
    public ResponseEntity<ServiceRecord> createServiceRecord(@RequestBody ServiceRecordRequestDTO requestDTO) {
        try {
            serviceTrackerService.addServiceRecord(requestDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<ServiceRecord>> getServiceRecords() {
        List<ServiceRecord> serviceRecords = serviceTrackerService.getServiceRecords();
        return new ResponseEntity<>(serviceRecords, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceRecord(@PathVariable int id) {
        serviceTrackerService.deleteServiceRecord(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/sorted")
    public List<ServiceRecord> getSortedServiceRecords(@RequestParam(defaultValue = "asc") String order) {
        serviceTrackerService.sortServiceRecordsByDate(order);
        return serviceTrackerService.getServiceRecords();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceRecord> updateServiceRecord(@PathVariable int id,
            @RequestBody ServiceRecordRequestDTO requestDTO) {
        try {
            ServiceRecord updatedRecord = serviceTrackerService.updateServiceRecord(id, requestDTO);
            if (updatedRecord == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(updatedRecord, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceRecord> getServiceRecordById(@PathVariable int id) {
        ServiceRecord serviceRecord = serviceTrackerService.getServiceRecordById(id);
        if (serviceRecord == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(serviceRecord, HttpStatus.OK);
    }

    @PatchMapping("/complete/{id}")
    public ResponseEntity<ServiceRecord> completeServiceRecord(@PathVariable int id) {
        ServiceRecord serviceRecord = serviceTrackerService.completeServiceRecord(id);
        if (serviceRecord == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(serviceRecord, HttpStatus.OK);
    }
}