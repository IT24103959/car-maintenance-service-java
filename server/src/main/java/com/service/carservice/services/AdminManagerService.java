package com.service.carservice.services;

import com.service.carservice.models.Admin;
import com.service.carservice.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class AdminManagerService {

    private final AdminRepository adminRepository;
    private LinkedList<Admin> admins;

    @Autowired
    public AdminManagerService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
        this.admins = new LinkedList<>(adminRepository.getAllAdmins());
    }

    public void addAdmin(Admin admin) {
        admin.setId(adminRepository.getNextId()); // Fetch nextId from repository
        admins.add(admin);
        adminRepository.addAdmin(admin);
    }

    public List<Admin> getAllAdmins() {
        return admins;
    }

    public void deleteAdmin(int id) {
        admins.removeIf(admin -> admin.getId() == id);
        adminRepository.persistToFile(admins);
    }
}
