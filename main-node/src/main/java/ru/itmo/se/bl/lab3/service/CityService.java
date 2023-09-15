package ru.itmo.se.bl.lab3.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.itmo.se.bl.lab3.entity.City;
import ru.itmo.se.bl.lab3.repository.CityRepository;

@Service
@AllArgsConstructor
public class CityService {
	private CityRepository repo;
	
	public List<City> getAll() {
		return repo.findAll();
	}
	
	public City getCityById(Integer id) {
		return repo.findById(id).orElse(null);
	}
	
	public List<City> getCitiesByName(String name) {
		return repo.findByName(name);
	}
	
	public List<City> getCitiesByCountryName(String countryName) {
		return repo.findAllByCountryName(countryName);
	}
}
