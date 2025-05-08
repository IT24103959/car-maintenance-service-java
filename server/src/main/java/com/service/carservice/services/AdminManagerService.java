package com.service.carservice.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.service.carservice.models.Admin;
import org.springframework.stereotype.Service;

@Service
public class AdminManagerService {

    private static final String FILE_PATH = "src/main/resources/data/admins.txt";
    private LinkedList<Admin> admins;
    private int nextId = 1;

    public AdminManagerService() {
        admins = new LinkedList<>();
        loadAdmins();
        setNextId();
    }

    private void setNextId() {
        int maxId = 0;
        for (Admin admin : admins) {
            if (admin.getId() > maxId) {
                maxId = admin.getId();
            }
        }
        nextId = maxId + 1;
    }

    public void addAdmin(String name, String email) {
        Admin admin = new Admin(nextId, name, email);
        admins.add(admin);
        saveAdmins();
        nextId++;
    }

    public void deleteAdmin(int id) {
        admins.removeIf(admin -> admin.getId() == id);
        saveAdmins();
        nextId--;
    }

    public List<Admin> getAllAdmins() {
        return admins;
    }

    public Admin getAdminById(int id) {
        return admins.stream()
                .filter(admin -> admin.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private void loadAdmins() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String email = parts[2];
                Admin admin = new Admin(id, name, email);
                admins.add(admin);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveAdmins() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Admin admin : admins) {
                bw.write(admin.getId() + "," + admin.getName() + "," + admin.getEmail());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
