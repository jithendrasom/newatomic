package com.newatomic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TickData {
    List<TimeFramedData> minute;
    List<TimeFramedData> threeMinute;
    List<TimeFramedData> fiveMinute;
    List<TimeFramedData> tenMinute;
    List<TimeFramedData> fifteenMinute;
    String symbolName;
    Double ltp;
}
