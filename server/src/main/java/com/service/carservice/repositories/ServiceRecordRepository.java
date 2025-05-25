package com.service.carservice.repositories;

import org.springframework.stereotype.Repository;

import com.service.carservice.models.ServiceRecord;

@Repository
public class ServiceRecordRepository extends BaseRepository<ServiceRecord> {
    protected String getFilePath() {
        return "src/main/resources/data/service-history.json";
    }

    protected Class<ServiceRecord> getTypeClass() {
        return ServiceRecord.class;
    }

    protected int getId(ServiceRecord record) {
        return record.getId();
    }

}