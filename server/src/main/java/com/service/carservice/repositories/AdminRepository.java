package com.service.carservice.repositories;

import org.springframework.stereotype.Repository;

import com.service.carservice.models.Admin;

@Repository
public class AdminRepository extends BaseRepository<Admin> {
    @Override
    protected String getFilePath() {
        return "src/main/resources/data/admins.json";
    }

    @Override
    protected Class<Admin> getTypeClass() {
        return Admin.class;
    }

    @Override
    protected int getId(Admin admin) {
        return admin.getId();
    }

}
