package com.service.carservice.util;

import com.service.carservice.cache.ServiceRecordList;
import com.service.carservice.models.ServiceRecord;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SelectionSort {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public static void sortAscending(ServiceRecordList serviceRecords) {
        int n = serviceRecords.getSize();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                LocalDateTime dateJ = LocalDateTime.parse(serviceRecords.getByIndex(j).getDate(), DATE_TIME_FORMATTER);
                LocalDateTime dateMin = LocalDateTime.parse(serviceRecords.getByIndex(minIndex).getDate(),
                        DATE_TIME_FORMATTER);
                if (dateJ.isBefore(dateMin)) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                ServiceRecord temp = serviceRecords.getByIndex(i);
                serviceRecords.setByIndex(i, serviceRecords.getByIndex(minIndex));
                serviceRecords.setByIndex(minIndex, temp);
            }
        }
    }

    public static void sortDescending(ServiceRecordList serviceRecords) {
        int n = serviceRecords.getSize();
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                LocalDateTime dateJ = LocalDateTime.parse(serviceRecords.getByIndex(j).getDate(), DATE_TIME_FORMATTER);
                LocalDateTime dateMax = LocalDateTime.parse(serviceRecords.getByIndex(maxIndex).getDate(),
                        DATE_TIME_FORMATTER);
                if (dateJ.isAfter(dateMax)) {
                    maxIndex = j;
                }
            }
            if (maxIndex != i) {
                ServiceRecord temp = serviceRecords.getByIndex(i);
                serviceRecords.setByIndex(i, serviceRecords.getByIndex(maxIndex));
                serviceRecords.setByIndex(maxIndex, temp);
            }
        }
    }
}