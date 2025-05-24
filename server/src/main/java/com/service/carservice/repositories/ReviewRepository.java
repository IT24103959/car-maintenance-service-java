package com.service.carservice.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.carservice.cache.ReviewList;
import com.service.carservice.models.Review;
import org.springframework.stereotype.Repository;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Repository
public class ReviewRepository {
    private static final String FILE_PATH = "src/main/resources/data/reviews.json";
    private final ReviewList items = new ReviewList();
    private int nextId;
    private final ObjectMapper objectMapper = new ObjectMapper();


    public ReviewRepository() {
        loadFromFile();
        setNextId();
    }

    public int getNextId(boolean increment){
        int id = nextId;
        if(increment){
            nextId++;
        }
        return id;
    }


    public void loadFromFile() {
        try {
            if (Files.exists(Paths.get(FILE_PATH))) {
                Review[] data = objectMapper.readValue(
                        Files.readAllBytes(Paths.get(FILE_PATH)),
                        objectMapper.getTypeFactory().constructArrayType(Review.class));

                for (Review record : data) {
                    items.insert(record);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void persistToFile(ReviewList items) {
        if (items != null && items.getSize() > 0) {
            try {
                Review[] data = items.toArray();
                objectMapper.writeValue(Paths.get(FILE_PATH).toFile(), data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setNextId() {
        int maxId = 0;
        Review[] data = items.toArray();
        for (Review record : data) {
            int id = record.getId();
            if (id > maxId) {
                maxId = id;
            }
        }
        nextId = maxId + 1;
    }

    public ReviewList getAll(){
        return items;
    }

}