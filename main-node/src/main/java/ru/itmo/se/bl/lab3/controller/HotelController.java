package ru.itmo.se.bl.lab3.controller;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.se.bl.lab3.dto.HotelDTO;
import ru.itmo.se.bl.lab3.exception.HotelNotFoundException;
import ru.itmo.se.bl.lab3.service.HotelService;

import java.util.List;

@RestController
@RequestMapping("/api/hotel")
@Setter
public class HotelController {
	@Autowired
	private HotelService service;
	
	@GetMapping("/all")
	public List<HotelDTO> getAllHotels() {
		return service.getAll().stream().map(HotelDTO::fromEntity).toList();
	}
	
	@GetMapping("/{hotel_id}")
	public ResponseEntity<HotelDTO> getById(@PathVariable("hotel_id") Integer id) {
		try {
			return ResponseEntity.ok(HotelDTO.fromEntity(service.getById(id)));
		} catch (HotelNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/get")
	public List<HotelDTO> getByCountryId(
			@RequestParam("country_id") Integer countryId, 
			@RequestParam(name = "city_id", required = false) Integer cityId) {
		return (cityId != null ? service.getByCityId(cityId) : service.getByCountryId(countryId))
				.stream().map(HotelDTO::fromEntity).toList();
	}

	@DeleteMapping("/{hotel_id}")
	public boolean removeHotel(@PathVariable("hotel_id") Integer id) {
		return service.removeHotel(id);
	}
}
