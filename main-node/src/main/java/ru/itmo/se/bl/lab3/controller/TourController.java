package ru.itmo.se.bl.lab3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.itmo.se.bl.lab3.dto.TourDTO;
import ru.itmo.se.bl.lab3.service.TourService;

@RestController
@RequestMapping("/api/tour")
public class TourController {
	@Autowired
	private TourService service;
	
	@GetMapping("/all")
	public List<TourDTO> getAllTours() {
		return service.getAll().stream().map(TourDTO::fromEntity).toList();
	}
	
	@GetMapping("/{tour_id}")
	public TourDTO getTourById(@PathVariable("tour_id") Integer tourId) {
		return TourDTO.fromEntity(service.getById(tourId));
	}
	
	@GetMapping("/find")
	public List<TourDTO> getToursByCityName(@RequestParam(name = "tour_city") String tourCityName) {
		return service.getByTourCityName(tourCityName).stream().map(TourDTO::fromEntity).toList();
	}

	@DeleteMapping("/")
	public boolean removeHotel(@RequestBody int id) {
		return service.removeTour(id);
	}
}
