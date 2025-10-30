package com.raxrot.salonservice.controller;

import com.raxrot.salonservice.dto.SalonRequest;
import com.raxrot.salonservice.dto.SalonResponse;
import com.raxrot.salonservice.dto.UserDTO;
import com.raxrot.salonservice.service.SalonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/salons")
@RestController
public class SalonController {
    private final SalonService salonService;

    @PostMapping
    public ResponseEntity<SalonResponse> createSalon(@Valid @RequestBody SalonRequest salonRequest) {
       UserDTO userDTO=new UserDTO();
       userDTO.setId(1L);
       SalonResponse salonResponse=salonService.createSalon(salonRequest,userDTO);
       return new ResponseEntity<>(salonResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SalonResponse>> getAllSalons() {
        return ResponseEntity.ok(salonService.getAllSalons());
    }

    @GetMapping("/{salonId}")
    public ResponseEntity<SalonResponse> getSalonById(@PathVariable Long salonId) {
        return ResponseEntity.ok(salonService.getSalonById(salonId));
    }

    @GetMapping("/owner")
    public ResponseEntity<SalonResponse> getSalonByOwnerId() {
        UserDTO userDTO=new UserDTO();
        userDTO.setId(1L);
        return ResponseEntity.ok(salonService.getSalonByOwnerId(userDTO.getId()));
    }

    @GetMapping("/search")
    public ResponseEntity<List<SalonResponse>> searchSalonByCity(@RequestParam String city) {
        return ResponseEntity.ok(salonService.searchSalonByCity(city));
    }


    @PutMapping("/{salonId}")
    public ResponseEntity<SalonResponse> updateSalon(@PathVariable Long salonId, @Valid @RequestBody SalonRequest salonRequest) {
        UserDTO userDTO=new UserDTO();
        userDTO.setId(1L);
        return ResponseEntity.ok(salonService.updateSalon(salonId, salonRequest,userDTO));
    }
}
