package com.service.carservice.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.service.carservice.models.Admin;
import com.service.carservice.service.AdminManagerService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminManagerService adminManagerService;

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdmin(@PathVariable int id) {
        Admin admin = adminManagerService.getAdminById(id);
        if (admin == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addAdmin(@RequestBody Admin admin) {
        adminManagerService.addAdmin(admin.getUsername());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
