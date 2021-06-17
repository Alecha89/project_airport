package com.company.util;

import com.company.entity.Pilot;
import com.company.entity.Rang;

import static com.company.io.IOConstants.DELIMITER;

public class PilotUtil {
    public static Pilot toObject(String line) {
        String[] pilotArr = line.split(DELIMITER);

        int id = Integer.parseInt(pilotArr[0]);
        String firstname = pilotArr[1];
        String lastname = pilotArr[2];
        Rang rang = Rang.valueOf(pilotArr[3]);
        String code = pilotArr[4];

        return new Pilot(id, firstname, lastname, rang, code);
    }
}
