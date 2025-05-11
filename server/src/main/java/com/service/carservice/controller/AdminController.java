package com.service.carservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.service.carservice.models.Admin;
import com.service.carservice.services.AdminManagerService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminManagerService adminManagerService;

    @GetMapping
    public ResponseEntity<List<Admin>> getAdmins() {
        List<Admin> admins = adminManagerService.getAllAdmins();

        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addAdmin(@RequestBody Admin admin) {
        adminManagerService.addAdmin(admin);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable int id) {
        adminManagerService.deleteAdmin(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
