package com.service.carservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.service.carservice.models.Admin;
import com.service.carservice.services.AdminService;

import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping
    public ResponseEntity<Admin[]> getAdmins() {
        return new ResponseEntity<>(adminService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable int id) {
        return new ResponseEntity<>(adminService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addAdmin(@RequestBody Admin admin) {
        adminService.addAdmin(admin);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAdmin(@PathVariable int id, @RequestBody Admin updatedAdmin) {
        if (adminService.updateAdminById(id, updatedAdmin)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable int id) {
        adminService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}