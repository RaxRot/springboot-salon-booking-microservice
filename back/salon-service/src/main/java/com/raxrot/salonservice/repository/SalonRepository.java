package com.raxrot.salonservice.repository;

import com.raxrot.salonservice.model.Salon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SalonRepository extends JpaRepository<Salon, Long> {
    Optional<Salon> findByOwnerId(Long ownerId);
    List<Salon> findByCityContainingIgnoreCase(String city);
}
