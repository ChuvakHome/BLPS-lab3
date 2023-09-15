package ru.itmo.se.bl.lab3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.itmo.se.bl.lab3.entity.Travel;
import ru.itmo.se.bl.lab3.exception.TravelNotFoundException;
import ru.itmo.se.bl.lab3.service.TravelService;

@RestController
@RequestMapping("/api/travel")
public class TravelController {
	@Autowired
	private TravelService service;
	
	@GetMapping("/all")
	public List<Travel> getAllTours() {		
		return service.getAll();
	}
	
	@GetMapping("/{travel_id}")
	public ResponseEntity<Travel> getTourById(@PathVariable("travel_id") Integer tourId) {
		try {
			return ResponseEntity.ok(service.getById(tourId));
		} catch (TravelNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/find")
	public List<Travel> getByStartCityAndEndCity(@RequestParam(name = "start_city") String startCityName, @RequestParam(name = "end_city") String endCityName) {
		return service.getByCitiesNames(startCityName, endCityName);
	}
}
