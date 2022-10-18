package one.digitalinnovation.parking.controller.mapper;

import one.digitalinnovation.parking.controller.dto.ParkingDTO;
import one.digitalinnovation.parking.model.Parking;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class ParkingMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();
    public ParkingDTO parkingDTO(Parking parking){
        return MODEL_MAPPER.map(parking, ParkingDTO.class);
    }

    public ArrayList<ParkingDTO> toParkingDTOList(ArrayList<Parking> parkingList){
        return (ArrayList<ParkingDTO>) parkingList.stream()
                .map(this::parkingDTO)
                .collect(Collectors.toList());
    }
}
