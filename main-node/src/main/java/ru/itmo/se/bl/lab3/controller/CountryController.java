package ru.itmo.se.bl.lab3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.itmo.se.bl.lab3.entity.Country;
import ru.itmo.se.bl.lab3.service.CountryService;

@RestController
@RequestMapping("/api/country")
public class CountryController {
	@Autowired
	private CountryService service;
	
	@GetMapping(value = "/all", produces = "application/json; charset=UTF-8")
	public List<Country> getAllCountries() {
		return service.getAll();
	}
	
	@GetMapping(value = "/{country_id}", produces = "application/json; charset=UTF-8")
	public Country getCountryById(@PathVariable("country_id") String countryId) {
		return service.getCountryById(Integer.decode(countryId));
	}
	
	@GetMapping(value = "/get", produces = "application/json; charset=UTF-8")
	public Country getCountryByLocalName(@RequestParam("name") String name) {
		return service.getCountryByName(name);
	}
}
