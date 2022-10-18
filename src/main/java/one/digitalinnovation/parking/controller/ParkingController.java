package one.digitalinnovation.parking.controller;

import one.digitalinnovation.parking.controller.dto.ParkingDTO;
import one.digitalinnovation.parking.controller.mapper.ParkingMapper;
import one.digitalinnovation.parking.model.Parking;
import one.digitalinnovation.parking.service.ParkingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/")
public class ParkingController {

    private final ParkingService PARKING_SERVICE;
    private final ParkingMapper PARKING_MAPPER;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper){
        this.PARKING_MAPPER = parkingMapper;
        this.PARKING_SERVICE = new ParkingService();
    }

    @GetMapping
    public ArrayList<ParkingDTO> findAll(){
        ArrayList<Parking> parkingArrayList = PARKING_SERVICE.findAll();
        ArrayList<ParkingDTO> parkingDTOArrayList = PARKING_MAPPER.toParkingDTOList(parkingArrayList);

        return parkingDTOArrayList;
    }


}
