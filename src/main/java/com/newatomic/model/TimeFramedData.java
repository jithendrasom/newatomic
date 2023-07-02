package com.newatomic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TimeFramedData {
    int proximityToMean;
    int proximityToMedian;
    double median;
    double mean;
    double low;
    double high;


}
