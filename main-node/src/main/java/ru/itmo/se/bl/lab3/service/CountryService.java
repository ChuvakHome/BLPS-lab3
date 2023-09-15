package ru.itmo.se.bl.lab3.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.itmo.se.bl.lab3.entity.Country;
import ru.itmo.se.bl.lab3.repository.CountryRepository;

@Service
@AllArgsConstructor
public class CountryService {
	private CountryRepository repo;
	
	public List<Country> getAll() {
		return repo.findAll();
	}
	
	public Country getCountryById(Integer id) {
		return repo.findById(id).orElse(null);
	}
	
	public Country getCountryByName(String name) {
		return repo.findByName(name).orElse(null);
	}
}
