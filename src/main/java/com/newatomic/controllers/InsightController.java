package com.newatomic.controllers;

import com.newatomic.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class InsightController {
    @Autowired
    private UtilService utilService;
    @PostMapping("/processFile")
    public ResponseEntity<?> postUser(@RequestParam MultipartFile fileContent){
        try{
            List<Double> ltps = utilService.getValuesInFileContent(fileContent);  
            //got from C:\D-folder\data\weekdaywise-data\Thursday\2023-06-09
            //TODO now process the ltps and build logic for in trade management

            return new ResponseEntity<>("", HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }
    }


}
