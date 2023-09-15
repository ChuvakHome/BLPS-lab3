package ru.itmo.se.bl.lab3.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.itmo.se.bl.lab3.entity.Tour;
import ru.itmo.se.bl.lab3.repository.TourRepository;

@Service
@AllArgsConstructor
public class TourService {
	private TourRepository repo;
	
	public List<Tour> getAll() {
		return repo.findAll();
	}
	
	public Tour getById(Integer id) {
		return repo.findById(id).orElse(null);
	}
	
	public List<Tour> getByTourCityName(String tourCityName) {
		return repo.findByTourCityName(tourCityName);
	}

	public boolean removeTour(Integer id) {
		if (repo.findById(id).isPresent()) {
			repo.deleteById(id);

			return true;
		}
		else
			return false;
	}
}
