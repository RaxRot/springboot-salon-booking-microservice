package com.raxrot.salonservice.mapper;

import com.raxrot.salonservice.dto.SalonRequest;
import com.raxrot.salonservice.dto.SalonResponse;
import com.raxrot.salonservice.model.Salon;

public class SalonMapper {

    public static Salon mapToSalon(SalonRequest salonRequest) {
        Salon salon = new Salon();
        salon.setName(salonRequest.getName());
        salon.setAddress(salonRequest.getAddress());
        salon.setPhoneNumber(salonRequest.getPhoneNumber());
        salon.setEmail(salonRequest.getEmail());
        salon.setCity(salonRequest.getCity());
        return salon;
    }

    public static SalonResponse mapToSalonResponse(Salon salon) {
        SalonResponse salonResponse = new SalonResponse();
        salonResponse.setId(salon.getId());
        salonResponse.setName(salon.getName());
        salonResponse.setAddress(salon.getAddress());
        salonResponse.setPhoneNumber(salon.getPhoneNumber());
        salonResponse.setEmail(salon.getEmail());
        salonResponse.setCity(salon.getCity());
        salonResponse.setOwnerId(salon.getOwnerId());
        return salonResponse;
    }
}
