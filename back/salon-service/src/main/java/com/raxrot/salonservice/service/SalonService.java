package com.raxrot.salonservice.service;

import com.raxrot.salonservice.dto.SalonRequest;
import com.raxrot.salonservice.dto.SalonResponse;
import com.raxrot.salonservice.dto.UserDTO;

import java.util.List;

public interface SalonService {
    SalonResponse createSalon(SalonRequest salonRequest, UserDTO userDTO);
    List<SalonResponse> getAllSalons();
    SalonResponse getSalonById(Long id);
    SalonResponse getSalonByOwnerId(Long ownerId);
    List<SalonResponse> searchSalonByCity(String city);
    SalonResponse updateSalon(Long id,SalonRequest salonRequest, UserDTO userDTO);
}
