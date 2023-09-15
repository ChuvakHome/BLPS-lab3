package ru.itmo.se.bl.lab3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.itmo.se.bl.lab3.dto.CityDTO;
import ru.itmo.se.bl.lab3.service.CityService;

@RestController
@RequestMapping("/api/city")
public class CityController {
	@Autowired
	private CityService service;
	
	@GetMapping(value = "/all", produces = "application/json; charset=UTF-8")
	public List<CityDTO> getAllCities() {
		return service.getAll().stream().map(CityDTO::fromEntity).toList();
	}
	
	@GetMapping(value = "/{city_id}", produces = "application/json; charset=UTF-8")
	public CityDTO getCityById(@PathVariable("city_id") String cityId) {
		return CityDTO.fromEntity(service.getCityById(Integer.decode(cityId)));
	}
	
	@GetMapping(value = "/get", produces = "application/json; charset=UTF-8")
	public List<CityDTO> getCitiesByName(@RequestParam("city_name") String name) {
		return service.getCitiesByName(name).stream().map(CityDTO::fromEntity).toList();
	}
	
	@GetMapping(value = "/get-by-country", produces = "application/json; charset=UTF-8")
	public List<CityDTO> getCitiesByCountryName(@RequestParam("country_name") String countryName) {
		return service.getCitiesByCountryName(countryName).stream().map(CityDTO::fromEntity).toList();
	}
}
