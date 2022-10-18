package one.digitalinnovation.parking.service;

import one.digitalinnovation.parking.model.Parking;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private static Map<String, Parking> parkingMap = new HashMap<>();

    static {
        String id = getUUID();
        Parking parking = new Parking(id,
                "AAA-1234",
                "FL",
                "FD MUSTANG",
                "PURPLE");

        parkingMap.put(id, parking);
    }


    public ArrayList<Parking> findAll(){
        return new ArrayList<>(parkingMap.values());
    }

    private static String getUUID(){
        return UUID.randomUUID().
                toString().replace("-", "");
    }
}
