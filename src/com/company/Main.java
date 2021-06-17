package com.company;

import com.company.db.joiner.DataJoiner;
import com.company.db.writer.AirplaneDBWriter;
import com.company.db.writer.FlightDBWriter;
import com.company.db.writer.PilotDBWriter;
import com.company.entity.Airplane;
import com.company.entity.Flight;
import com.company.entity.JoinDataItem;
import com.company.entity.Pilot;
import com.company.io.AirplaneDataFileReader;
import com.company.io.FlightDataFileReader;
import com.company.io.JoinDataFileWriter;
import com.company.io.PilotDataFileReader;
import com.company.util.NotifyToConsoleUtil;

import java.util.List;

import static com.company.io.IOConstants.*;

public class Main {

    public static void main(String[] args) {
        //READ FROM CSV
	    PilotDataFileReader pilotFileReader = new PilotDataFileReader(PILOTS_SOURCE_FILE);
        List<Pilot> pilots = pilotFileReader.read();
        AirplaneDataFileReader airplaneFileReader = new AirplaneDataFileReader(AIRPLANES_SOURCE_FILE);
        List<Airplane> airplanes = airplaneFileReader.read();
        FlightDataFileReader flightFileReader = new FlightDataFileReader(FLIGHTS_SOURCE_FILE);
        List<Flight> flights = flightFileReader.read();

        //WRITE TO DB
        NotifyToConsoleUtil.start(Pilot.class.getSimpleName());
        System.out.println(pilots);
        PilotDBWriter.insert(pilots);
        NotifyToConsoleUtil.finish(Pilot.class.getSimpleName());

        NotifyToConsoleUtil.start(Airplane.class.getSimpleName());
        System.out.println(airplanes);
        AirplaneDBWriter.insert(airplanes);
        NotifyToConsoleUtil.finish(Airplane.class.getSimpleName());

        NotifyToConsoleUtil.start(Flight.class.getSimpleName());
        System.out.println(flights);
        FlightDBWriter.insert(flights);
        NotifyToConsoleUtil.finish(Flight.class.getSimpleName());

        //JOIN AND WRITE TO FILE
        List<JoinDataItem> items  = DataJoiner.getJoinedData();

        System.out.println(items);

        JoinDataFileWriter dataFileWriter = new JoinDataFileWriter(RESULT_FILE);
        dataFileWriter.write(items);

    }
}
