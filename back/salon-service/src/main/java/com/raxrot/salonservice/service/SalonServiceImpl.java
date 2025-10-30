package com.raxrot.salonservice.service;

import com.raxrot.salonservice.dto.SalonRequest;
import com.raxrot.salonservice.dto.SalonResponse;
import com.raxrot.salonservice.dto.UserDTO;
import com.raxrot.salonservice.exception.ApiException;
import com.raxrot.salonservice.mapper.SalonMapper;
import com.raxrot.salonservice.model.Salon;
import com.raxrot.salonservice.repository.SalonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalonServiceImpl implements SalonService {
    private final SalonRepository salonRepository;

    @Override
    public SalonResponse createSalon(SalonRequest salonRequest, UserDTO userDTO) {

        Salon salon = SalonMapper.mapToSalon(salonRequest);
        salon.setOwnerId(userDTO.getId());

        Salon savedSalon=salonRepository.save(salon);
        return SalonMapper.mapToSalonResponse(savedSalon);
    }

    @Override
    public List<SalonResponse> getAllSalons() {
        List<Salon>salons=salonRepository.findAll();
        List<SalonResponse>salonResponses=salons.stream()
                .map(salon->SalonMapper.mapToSalonResponse(salon))
                .toList();
        return salonResponses;
    }

    @Override
    public SalonResponse getSalonById(Long id) {
        Salon salon=salonRepository.findById(id)
                .orElseThrow(()->new ApiException("Salon not found", HttpStatus.NOT_FOUND));
        return SalonMapper.mapToSalonResponse(salon);
    }

    @Override
    public SalonResponse getSalonByOwnerId(Long ownerId) {
        Salon salon=salonRepository.findByOwnerId(ownerId)
                .orElseThrow(()->new ApiException("Salon not found", HttpStatus.NOT_FOUND));
        return SalonMapper.mapToSalonResponse(salon);
    }

    @Override
    public List<SalonResponse> searchSalonByCity(String city) {
        List<Salon>salons=salonRepository.findByCityContainingIgnoreCase(city);
        List<SalonResponse>salonResponses=salons.stream()
                .map(salon->SalonMapper.mapToSalonResponse(salon))
                .toList();
        return salonResponses;
    }

    @Override
    public SalonResponse updateSalon(Long id, SalonRequest salonRequest, UserDTO userDTO) {
        Salon salonToUpdate=salonRepository.findById(id)
                .orElseThrow(()->new ApiException("Salon not found", HttpStatus.NOT_FOUND));
        if (salonToUpdate.getOwnerId()!=userDTO.getId()){
            throw new ApiException("User is not authorized to update salon", HttpStatus.CONFLICT);
        }
        salonToUpdate.setName(salonRequest.getName());
        salonToUpdate.setAddress(salonRequest.getAddress());
        salonToUpdate.setPhoneNumber(salonRequest.getPhoneNumber());
        salonToUpdate.setEmail(salonRequest.getEmail());
        salonToUpdate.setCity(salonRequest.getCity());

        salonToUpdate.setOwnerId(userDTO.getId());

        Salon updatedSalon=salonRepository.save(salonToUpdate);
        return SalonMapper.mapToSalonResponse(updatedSalon);
    }
}
