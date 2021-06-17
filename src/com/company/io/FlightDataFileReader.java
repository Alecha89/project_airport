package com.company.io;


import com.company.entity.Flight;
import com.company.exception.EmptySourceFile;
import com.company.util.FlightUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FlightDataFileReader {
    private String filepath;

    public FlightDataFileReader(String filepath) {
        this.filepath = filepath;
    }

    public List<Flight> read() {
        List<Flight> flights = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            checkFile();

            String line;
            while ((line = reader.readLine()) != null) {
                Flight flight = FlightUtil.toObject(line);
                flights.add(flight);
            }

        } catch (IOException | EmptySourceFile e) {
            e.printStackTrace();
        }

        return flights;
    }

    private void checkFile() throws EmptySourceFile {
        File sourceFile = new File(filepath);

        if (sourceFile.length() == 0) {
            throw new EmptySourceFile("Файл пуст");
        }
    }
}

