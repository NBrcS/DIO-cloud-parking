package one.digitalinnovation.parking.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import one.digitalinnovation.parking.controller.dto.ParkingCreateDTO;
import one.digitalinnovation.parking.controller.dto.ParkingDTO;
import one.digitalinnovation.parking.controller.mapper.ParkingMapper;
import one.digitalinnovation.parking.model.Parking;
import one.digitalinnovation.parking.service.ParkingService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/parking")
@Api(tags = "Parking Controller")
public class ParkingController {

    private final ParkingService PARKING_SERVICE;
    private final ParkingMapper PARKING_MAPPER;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper){
        this.PARKING_MAPPER = parkingMapper;
        this.PARKING_SERVICE = new ParkingService();
    }

    @GetMapping
    @ApiOperation("Find all parking's")
    public ResponseEntity<ArrayList<ParkingDTO>> findAll(){
        ArrayList<Parking> parkingArrayList = PARKING_SERVICE.findAll();
        ArrayList<ParkingDTO> parkingDTOArrayList = PARKING_MAPPER.toParkingDTOList(parkingArrayList);

        return ResponseEntity.ok(parkingDTOArrayList);
    }

    @GetMapping("/{id}")
    @ApiOperation("Find a parking by ID")
    public ResponseEntity<ParkingDTO> finByID(@PathVariable String id){
        Parking parking = PARKING_SERVICE.findById(id);

        ParkingDTO parkingDTO = PARKING_MAPPER.toParkingDTO(parking);
        return ResponseEntity.ok(parkingDTO);
    }

   @PostMapping
   @ApiOperation("Create a new parking")
   public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO cDto){

        Parking parkingCreate = PARKING_MAPPER.toParking(cDto);
        Parking parking = PARKING_SERVICE.create(parkingCreate);

        ParkingDTO parkingDTO = PARKING_MAPPER.toParkingDTO(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingDTO);
   }


    @PutMapping("/{id}")
    @ApiOperation("Insert a parking into an ID")
    public ResponseEntity<ParkingDTO> put(@PathVariable String id,
                                          @RequestBody ParkingCreateDTO cDto){

        Parking create = PARKING_MAPPER.toParking(cDto);
        Parking parking = PARKING_SERVICE.update(create, id);

        ParkingDTO dto = PARKING_MAPPER.toParkingDTO(parking);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("delete a parking with the ID")
    public ResponseEntity<ParkingDTO> delete(@PathVariable String id){

        Parking parking = PARKING_SERVICE.delete(id);

        ParkingDTO dto = PARKING_MAPPER.toParkingDTO(parking);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
    }

}
