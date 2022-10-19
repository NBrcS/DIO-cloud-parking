package one.digitalinnovation.parking.service;

import one.digitalinnovation.parking.exceptions.ParkingNotFoundException;
import one.digitalinnovation.parking.model.Parking;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.BiConsumer;

@Service
public class ParkingService {

    private static Map<String, Parking> parkingMap = new LinkedHashMap<>();

    static {
        String id = getUUID();
        Parking parking = new Parking(id,
                "AAA-1234",
                "FL",
                "FD MUSTANG",
                "PURPLE");

        String id2 = getUUID();
        Parking parking2 = new Parking(id2,
                "BBB-5678",
                "MS",
                "DG CHALLENGER",
                "BLACK");


        parkingMap.put(id, parking);
        parkingMap.put(id2, parking2);
    }


    public ArrayList<Parking> findAll(){
        return new ArrayList<>(parkingMap.values());
    }

    private static String getUUID(){
        return UUID.randomUUID().
                toString().replace("-", "");
    }

    public Parking findById(String id) {
        Parking parking = parkingMap.get(id);
        if(parking == null){
            throw new ParkingNotFoundException(id);
        }

        return parking;
    }

    public Parking create(Parking parkingCreate) {
        parkingCreate.setId(getUUID());
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingMap.put(parkingCreate.getId(), parkingCreate);

        return parkingCreate;
    }

    public Parking put(Parking putParking, String ID) {

        Parking parking = parkingMap.get(ID);
        if(parking == null){
            throw new ParkingNotFoundException(ID);
        }
        else parkingMap.put(ID, putParking);

        return putParking;
    }

    public Parking delete(String ID) {

        Parking parking = parkingMap.get(ID);
        if(parking == null){
            throw new ParkingNotFoundException(ID);
        }
        else parkingMap.remove(ID);


        return parking;
    }
}
