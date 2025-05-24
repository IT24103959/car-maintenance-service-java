package com.service.carservice.repositories;

import com.service.carservice.cache.ReviewList;
import com.service.carservice.models.Review;
import org.springframework.stereotype.Repository;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Repository
public class ReviewRepository extends BaseRepository {
    private static final String FILE_PATH = "src/main/resources/data/reviews.json";
    private final ReviewList items = new ReviewList();

    public ReviewRepository() {
        super();
    }

    public void loadFromFile() {
        try {
            if (Files.exists(Paths.get(getFilePath()))) {
                Review[] data = objectMapper.readValue(
                        Files.readAllBytes(Paths.get(getFilePath())),
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
                objectMapper.writeValue(Paths.get(getFilePath()).toFile(), data);
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