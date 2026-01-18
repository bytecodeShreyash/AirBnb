package com.example.controller;

import com.example.dto.HotelDto;
import com.example.entity.Hotel;
import com.example.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/admin/hotels")
@RequiredArgsConstructor
@Slf4j
public class HotelController {
    private final HotelService hotelService;
    @PostMapping
    public ResponseEntity<HotelDto> createHotel(@RequestBody HotelDto hotelDto){
        log.info("Attempting to create a new hotel with name:"+hotelDto.getName());
        HotelDto hotel =hotelService.createNewHotel(hotelDto);
        return new ResponseEntity<>(hotel, HttpStatus.CREATED);
    }
    @PostMapping("/list")
    public ResponseEntity<List<HotelDto>> CreateAllHotels(@RequestBody List<HotelDto> hotelDtos){
        log.info("Attempting to create a List of hotel:");
        List<HotelDto> hotel=hotelService.createListofHotel(hotelDtos);
        return new ResponseEntity<>(hotel, HttpStatus.CREATED);

    }
    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable Long hotelId){
        HotelDto hotelDto=hotelService.getHotelById(hotelId);
        return ResponseEntity.ok(hotelDto);
    }
    @PutMapping("/{Id}")
    public ResponseEntity<HotelDto> updateHotel(@PathVariable Long Id,@RequestBody HotelDto hotelDto){
        HotelDto hotel=hotelService.updateHotel(Id,hotelDto);
        return new ResponseEntity<>(hotel,HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable Long id){
        if(hotelService.deleteHotel(id)){
            return new ResponseEntity<>("Hotel has been deleted",HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Hotel not found",HttpStatus.NOT_FOUND);

    }
}
