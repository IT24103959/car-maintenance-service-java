package com.service.carservice.services;

import com.service.carservice.cache.AdminList;
import org.springframework.stereotype.Service;

import com.service.carservice.models.Admin;
import com.service.carservice.repositories.AdminRepository;

@Service
public class AdminService extends BaseService {
    private final AdminRepository adminRepository;
    private AdminList items;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
        this.items = adminRepository.getAll();
    }

    public void addAdmin(Admin admin) {
        admin.setId(adminRepository.getNextId(true));
        items.insert(admin);
    }

    public Admin getAdminById(int id) {
        return getById(id);
    }

    public boolean updateAdminById(int id, Admin updatedAdmin) {
        updatedAdmin.setId(id);
        return updateById(id, updatedAdmin);
    }

    public boolean updateById(int id, Admin updated) {
        for (int i = 0; i < items.getSize(); i++) {
            if (items.getByIndex(i).getId() == id) {
                items.setByIndex(i, updated);
                return true;
            }
        }
        return false;
    }

    public Admin getById(int id){
        for (int i = 0; i < items.getSize(); i++) {
            Admin item = items.getByIndex(i);
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void deleteById(int id) {
        Admin value = getById(id);
        items.deleteByValue(value);
    }

    protected void persistOnShutdown() {
        adminRepository.persistToFile(items);
    }


    public Admin[] getAll() {
        return items.toArray();
    }
}