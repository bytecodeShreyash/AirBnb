package com.example.service;

import com.example.Exception.ResourceNotFoundException;
import com.example.dto.HotelDto;
import com.example.entity.Hotel;
import com.example.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService{

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    @Override
    public HotelDto createNewHotel(HotelDto hotelDto) {
        log.info("Creating a new hotel with name:{}",hotelDto.getName());
        Hotel hotel=modelMapper.map(hotelDto,Hotel.class);
        hotel.setActive(false);
        hotel=hotelRepository.save(hotel);
        log.info("Created a new hotel with ID:{}",hotelDto.getId());
        return modelMapper.map(hotel,HotelDto.class);

    }

    @Override
    public HotelDto getHotelById(Long id) {
        log.info("Getting the hotel with ID:{}",id);
        Hotel hotel=hotelRepository
                .findById(id)
                .orElseThrow(
                        ()->new ResourceNotFoundException("Not found with ID:"+id)
                );
        return modelMapper.map(hotel,HotelDto.class);
    }

    @Override
    public List<HotelDto> createListofHotel(List<HotelDto> hotelDtos) {
        List<HotelDto> savedHotels = new ArrayList<>();

        for (HotelDto hotelDto : hotelDtos) {
            Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
            hotelRepository.save(hotel);
            savedHotels.add(hotelDto);
        }
        return savedHotels;
    }

    @Override
    public HotelDto updateHotel(Long Id, HotelDto hotelDto) {
        log.info("updating the hotel with ID:{}",Id);
        Hotel hotel=hotelRepository
                .findById(Id)
                .orElseThrow(
                        ()->new ResourceNotFoundException("Not found with ID:"+Id)
                );
        modelMapper.map(hotelDto,hotel);
        hotel.setId(Id);
        hotel=hotelRepository.save(hotel);
        return modelMapper.map(hotel,HotelDto.class);
    }

    @Override
    public Boolean deleteHotel(Long Id) {
        boolean exist=hotelRepository.existsById(Id);
        if(!exist){
            throw new ResourceNotFoundException("Not found with ID:"+Id);
        }
        hotelRepository.deleteById(Id);
        // TODO: Delete the future inventories for this hotel
        return true;
    }

}
