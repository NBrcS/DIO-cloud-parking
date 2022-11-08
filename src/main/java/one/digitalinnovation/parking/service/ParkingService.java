package one.digitalinnovation.parking.service;

import one.digitalinnovation.parking.exceptions.ParkingNotFoundException;
import one.digitalinnovation.parking.model.Parking;
import one.digitalinnovation.parking.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ParkingService {

    @Autowired
    private final ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Transactional
    public ArrayList<Parking> findAll(){
        return new ArrayList<>(parkingRepository.findAll());
    }

    private static String getUUID(){
        return UUID.randomUUID().
                toString().replace("-", "");
    }

    @Transactional
    public Parking findById(String id) {
        return parkingRepository.findById(id).orElseThrow(() ->
            new ParkingNotFoundException(id));
    }

    @Transactional
    public Parking create(Parking parkingCreate) {
        parkingCreate.setId(getUUID());
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingRepository.save(parkingCreate);

        return parkingCreate;
    }

    @Transactional
    public Parking update(Parking putParking, String ID) {

        Parking parking = findById(ID);
        parkingRepository.save(parking);

        return putParking;
    }

    @Transactional
    public Parking delete(String ID) {
        Parking parking = parkingRepository.getReferenceById(ID);
        parkingRepository.deleteById(ID);

        return parking;
    }

    @Transactional
    public Parking checkout(String ID){
        Parking parking = findById(ID);
        parking.setExitDate(LocalDateTime.now());
        parking.setBill(ParkingCheckOut.getBill(parking));
        parkingRepository.save(parking);

        return parking;
    }
}
