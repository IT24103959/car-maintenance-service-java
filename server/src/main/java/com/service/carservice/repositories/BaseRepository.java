package com.service.carservice.repositories;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public abstract class BaseRepository {

    protected int nextId;
    protected String FILE_PATH;
    protected final ObjectMapper objectMapper = new ObjectMapper();

    protected String getFilePath(){
            return FILE_PATH;
    }

    public BaseRepository(){
        loadFromFile();
        setNextId();
    }

    public abstract void loadFromFile();
    public abstract void setNextId();

    public int getNextId(boolean increment){
        int id = nextId;
        if(increment){
            nextId++;
        }
        return id;
    }

}

