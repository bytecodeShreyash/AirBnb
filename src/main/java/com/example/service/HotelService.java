package com.example.service;

import com.example.dto.HotelDto;
import com.example.entity.Hotel;

import java.util.List;

public interface HotelService {
    public HotelDto createNewHotel(HotelDto hotelDto);
    public HotelDto getHotelById(Long id);
    public List<HotelDto> createListofHotel(List<HotelDto> hotelDtos);
    public HotelDto updateHotel(Long Id, HotelDto hotelDto);
    public Boolean deleteHotel(Long Id);

}
