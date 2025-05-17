package com.service.carservice.util;

import com.service.carservice.models.ServiceRecord;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SelectionSort {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public static void sortAscending(List<ServiceRecord> serviceRecords) {
        int n = serviceRecords.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                LocalDateTime dateJ = LocalDateTime.parse(serviceRecords.get(j).getDate(), DATE_TIME_FORMATTER);
                LocalDateTime dateMin = LocalDateTime.parse(serviceRecords.get(minIndex).getDate(),
                        DATE_TIME_FORMATTER);
                if (dateJ.isBefore(dateMin)) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                ServiceRecord temp = serviceRecords.get(i);
                serviceRecords.set(i, serviceRecords.get(minIndex));
                serviceRecords.set(minIndex, temp);
            }
        }
    }

    public static void sortDescending(List<ServiceRecord> serviceRecords) {
        int n = serviceRecords.size();
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                LocalDateTime dateJ = LocalDateTime.parse(serviceRecords.get(j).getDate(), DATE_TIME_FORMATTER);
                LocalDateTime dateMax = LocalDateTime.parse(serviceRecords.get(maxIndex).getDate(),
                        DATE_TIME_FORMATTER);
                if (dateJ.isAfter(dateMax)) {
                    maxIndex = j;
                }
            }
            if (maxIndex != i) {
                ServiceRecord temp = serviceRecords.get(i);
                serviceRecords.set(i, serviceRecords.get(maxIndex));
                serviceRecords.set(maxIndex, temp);
            }
        }
    }
}