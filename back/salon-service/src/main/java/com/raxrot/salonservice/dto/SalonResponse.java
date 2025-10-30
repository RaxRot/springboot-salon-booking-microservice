package com.raxrot.salonservice.dto;


import lombok.Data;

@Data
public class SalonResponse {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String city;
    private Long ownerId;
}
