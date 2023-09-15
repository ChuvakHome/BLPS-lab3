package ru.itmo.se.bl.lab3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.itmo.se.bl.lab3.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
	List<Hotel> findByCountryId(Integer countryId);
	
	List<Hotel> findByCityId(Integer cityId);
}
