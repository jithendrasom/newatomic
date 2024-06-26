package com.newatomic.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UtilService {

    public List<Double> getValuesInFileContent(MultipartFile fileConent) throws FileNotFoundException {
        try {
            BufferedReader csvReader = new BufferedReader
                    (new InputStreamReader(new ByteArrayInputStream(fileConent.getBytes ())));

            List<Double> closeList = csvReader.lines ().skip(2).map (a-> Double.valueOf(a.split(",")[0]))
                    .collect (Collectors.toList ());
            List<Double> closeSubList = closeList.subList (0, 600);
            csvReader.close ();
            return closeSubList;
        } catch (IOException ioe){ioe.printStackTrace ();}
        return new LinkedList<>();
    }


}
