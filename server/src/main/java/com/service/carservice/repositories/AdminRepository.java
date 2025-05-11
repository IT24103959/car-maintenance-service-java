package com.service.carservice.repositories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.carservice.models.Admin;

@Repository
public class AdminRepository {

    private static final String JSON_FILE_PATH = "src/main/resources/data/admins.json";
    private ObjectMapper objectMapper = new ObjectMapper();
    private int nextId;

    public AdminRepository() {
        List<Admin> admins = loadAdmins(); // Directly load admins from JSON
        this.nextId = admins.stream()
                .mapToInt(Admin::getId)
                .max()
                .orElse(0) + 1;
    }

    private List<Admin> loadAdmins() {
        try {
            return objectMapper.readValue(Files.readAllBytes(Paths.get(JSON_FILE_PATH)),
                    objectMapper.getTypeFactory().constructCollectionType(LinkedList.class, Admin.class));
        } catch (IOException e) {
            return new LinkedList<>();
        }
    }

    public void addAdmin(Admin admin) {
        admin.setId(nextId++); // Use nextId and increment
        List<Admin> admins = loadAdmins();
        admins.add(admin);
        persistToFile(admins);
    }

    public List<Admin> getAllAdmins() {
        try {
            return objectMapper.readValue(Files.readAllBytes(Paths.get(JSON_FILE_PATH)),
                    objectMapper.getTypeFactory().constructCollectionType(LinkedList.class, Admin.class));
        } catch (IOException e) {
            return new LinkedList<>();
        }
    }

    public Admin getAdminById(int id) {
        return getAllAdmins().stream()
                .filter(admin -> admin.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void deleteAdmin(int id) {
        List<Admin> admins = getAllAdmins();
        admins.removeIf(admin -> admin.getId() == id);
        persistToFile(admins);
    }

    public void persistToFile(List<Admin> admins) {
        try {
            objectMapper.writeValue(Paths.get(JSON_FILE_PATH).toFile(), admins);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getNextId() {
        return nextId;
    }
}
