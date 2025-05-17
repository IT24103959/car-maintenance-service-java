package com.service.carservice.services;

import com.service.carservice.models.Admin;
import com.service.carservice.repositories.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService extends BaseService<Admin> {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        super(adminRepository.getAll());
        this.adminRepository = adminRepository;
    }

    public void addAdmin(Admin admin) {
        admin.setId(adminRepository.getNextId(true));
        items.add(admin);
    }

    public Admin getAdminById(int id) {
        return getById(id);
    }

    public boolean updateAdminById(int id, Admin updatedAdmin) {
        return updateById(id, updatedAdmin);
    }

    @Override
    protected int getId(Admin admin) {
        return admin.getId();
    }

    @Override
    protected void persistOnShutdown() {
        adminRepository.persistToFile(items);
    }
}
