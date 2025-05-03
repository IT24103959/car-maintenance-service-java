package com.service.carservice.util;


import com.service.carservice.models.ServiceRecord;

import java.util.LinkedList;

public class SelectionSort {

    public static void sort(LinkedList<ServiceRecord> serviceRecords) {
        int n = serviceRecords.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (serviceRecords.get(j).getDate().isBefore(serviceRecords.get(minIndex).getDate())) {
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
}