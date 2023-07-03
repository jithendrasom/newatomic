package com.newatomic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class TickData {
    List<TimeFramedData> minute;
    List<TimeFramedData> fiveMinute;
    List<TimeFramedData> tenMinute;
    List<TimeFramedData> fifteenMinute;
    String symbolName;
}
