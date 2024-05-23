package com.newatomic.controllers;

import com.newatomic.model.TickData;
import com.newatomic.model.TimeFramedData;
import com.newatomic.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
//import com.google.common.collect.Lists;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

// TODO TEST WITH C:\Users\jithe\PycharmProjects\pythonProject\data\AUGUST
// TODO now just use C:\D-folder\temp\myjavatestdata

@Controller
public class InsightController {
    @Autowired
    private UtilService utilService;
    @PostMapping("/processFile")
    public ResponseEntity<?> postUser(@RequestPart(name = "fileContent") MultipartFile fileContent){
        try{
            List<Double> ltps = utilService.getValuesInFileContent(fileContent);  
            //got from C:\D-folder\data\weekdaywise-data\Thursday\2023-06-09
            //TODO now process the ltps and build logic for in trade management
            System.out.println(ltps.size());
            // group them with size of 3
            List<List<Double>> subLists = new ArrayList<>();
            for(int i =0; i<ltps.size(); i = i+3){
                subLists.add(ltps.subList(i, i+3));
            }
            System.out.println(subLists.size());

            // now that sub lists are formed for each 3 seconds, we can invoke some logic on it
            int minuteCounter = 0;
            TickData tickData = TickData.builder().minute(new ArrayList<>()).threeMinute(new ArrayList<>())
                    .fifteenMinute(new ArrayList<>())
                    .fiveMinute(new ArrayList<>()).tenMinute(new ArrayList<>()).symbolName("BankNifty").build();
            tickData.setLtp(ltps.get(0));  // first price set
            List<Double> minuteLtps = new ArrayList<>();
            List<Double> threeMinLtps = new ArrayList<>();
            List<Double> fiveMinLtps = new ArrayList<>();
            List<Double> fifteenMinLtps = new ArrayList<>();

            for(int j =0; j<subLists.size(); j++){
                for(int i=0; i<3; i++){
                    Double ltpNow = subLists.get(j).get(i);
                    minuteLtps.add(ltpNow);
                    threeMinLtps.add(ltpNow);
                    fiveMinLtps.add(ltpNow);
                    fifteenMinLtps.add(ltpNow);

                    // in trade logic here using tickdata object and sublits ltps
                    // TODO just if it is having a free fall
                   /* if(minuteLtps.get(minuteLtps.size()-1)/tickData.getLtp() < 96){
                        System.out.println("Exit @ "+ minuteLtps.get(minuteLtps.size()-1));
                    }*/
                }
                if(minuteLtps.size() == 21){  // i.e minute completed  20*3=60 seconds
                    addMinuteSummaryData(minuteLtps, tickData, 1);
                    minuteLtps.clear();
                }
                if(threeMinLtps.size() == 60){  // i.e minute completed  60*3=180 seconds
                    addMinuteSummaryData(threeMinLtps, tickData, 3);
                    threeMinLtps.clear();
                }
                if(fifteenMinLtps.size() == 300){  // i.e minute completed  300*3=900 seconds
                    addMinuteSummaryData(fifteenMinLtps, tickData, 15);
                    fifteenMinLtps.clear();
                }

            }
            return new ResponseEntity<>("", HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }
    }

    private void addMinuteSummaryData(List<Double> minuteLtps, TickData tickData, Integer mins) {
        System.out.println(" ---> "+ minuteLtps);
        DoubleSummaryStatistics stats = minuteLtps.stream().collect(Collectors.summarizingDouble(e->e));
        // calculate median
        Collections.sort(minuteLtps);
        int len = minuteLtps.size();
        Double median = (len % 2 == 1)?minuteLtps.get(len / 2):
                (0.5 * (minuteLtps.get(len / 2) + minuteLtps.get(len / 2 - 1)));
        System.out.println("Median: ->"+ median);
        // Calculate numberOfTimes
        Integer numberOfHigherHighs = getNumberOfHigherHighs(minuteLtps, stats.getMax());
        Integer numberOfLowerLows = getNumberOfLowerLows(minuteLtps, stats.getMin());
        //Integer numberOfHigherHighs = getnumberOfHigherHighs(tempMinuteList, stats.getMin());
        Integer numberOfTimesPriceGoneLowerThanMean = getTimesPriceGoneLowerThanMean(minuteLtps, stats.getAverage());

        TimeFramedData tfm = TimeFramedData.builder().
                ltp(minuteLtps.get(minuteLtps.size()-1)).low(stats.getMin()).high(stats.getMax())
                .mean(stats.getAverage()).median(median).numberOfLowerLows(1)
                .numberOfHigherHighs(numberOfHigherHighs).isMeanCrossedLower(true)
                .numberOfTimesPriceGoneLowerThanMean(true).time(LocalDateTime.now())
                .build();
        System.out.println();
        if(mins == 1){
            tickData.getMinute().add(tfm);
            System.out.println(" minute ltp "+ tfm);
        } else if( mins == 3){
            tickData.getThreeMinute().add(tfm);
            System.out.println(" 3 minute ltp "+ tfm);
        } else if( mins == 15){
            tickData.getFifteenMinute().add(tfm);
            System.out.println(" 15 minute ltp "+ tfm);
        }
    }

    private Integer getNumberOfHigherHighs(List<Double> tempMinuteList, double min) {

        return 0;
    }

    private Integer getNumberOfLowerLows(List<Double> tempMinuteList, double min) {

        return 0;
    }

    private Integer getTimesPriceGoneLowerThanMean(List<Double> tempMinuteList, double min) {

        return 0;
    }


}
