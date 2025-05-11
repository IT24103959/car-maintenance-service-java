package com.service.carservice.util;

import com.service.carservice.models.ServiceRecord;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SelectionSort {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_DATE;

    public static void sortAscending(List<ServiceRecord> serviceRecords) {
        int n = serviceRecords.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                LocalDate dateJ = LocalDate.parse(serviceRecords.get(j).getDate(), DATE_FORMATTER);
                LocalDate dateMin = LocalDate.parse(serviceRecords.get(minIndex).getDate(), DATE_FORMATTER);
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
                LocalDate dateJ = LocalDate.parse(serviceRecords.get(j).getDate(), DATE_FORMATTER);
                LocalDate dateMax = LocalDate.parse(serviceRecords.get(maxIndex).getDate(), DATE_FORMATTER);
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