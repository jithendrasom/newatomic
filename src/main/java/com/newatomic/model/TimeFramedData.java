package com.newatomic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class TimeFramedData {
    int proximityToMean;
    int proximityToMedian;
    double median;
    double mean;
    double ltp;
    double low;
    double high;
    int numberOfLowerLows;
    int numberOfHigherHighs;
    int previousHigh;
    int previousLow; // does this require or can we get it from previous time period and compare to take a decision
    boolean isMeanCrossedLower;  // decision if it going multiple times lower than mean
    boolean numberOfTimesPriceGoneLowerThanMean;
    LocalDateTime time;
    boolean overallTrend;
}
