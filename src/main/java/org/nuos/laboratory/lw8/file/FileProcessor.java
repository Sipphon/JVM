package org.nuos.laboratory.lw8.file;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.nuos.laboratory.lw8.houses.House;
import org.nuos.laboratory.lw8.logic.Houses;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {
    private final String fileName = "houses";
    private final String fileExtension = ".json";
    private final String directory = "userfiles/";
    private final String filePath = directory + fileName + fileExtension;


    public void writeFile(Houses houses) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(filePath), houses.getApartments());

        } catch (IOException ex) {

            ex.printStackTrace();
        }

    }

    public List<House> readFile() {

        File file = new File(filePath);
        List<House> houses = new ArrayList<>();

        if (file.length() == 0) {

            return houses;
        }

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            houses = objectMapper.readValue(new File(filePath), new TypeReference<List<House>>() {
            });

        } catch (IOException err) {

            err.printStackTrace();
        }

        return houses;


    }

}