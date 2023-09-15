package ru.itmo.se.bl.lab3.service;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.itmo.se.bl.lab3.entity.Travel;
import ru.itmo.se.bl.lab3.exception.TravelBookException;
import ru.itmo.se.bl.lab3.exception.TravelNotFoundException;
import ru.itmo.se.bl.lab3.repository.TravelRepository;

@Service
@AllArgsConstructor
public class TravelService {
	private TravelRepository repo;
	
	public Travel getById(Integer id) throws TravelNotFoundException {
		return repo.findById(id).orElseThrow(() -> new TravelNotFoundException(id));
	}
	
	public List<Travel> getAll() {
		return repo.findAll();
	}
	
	public List<Travel> getByCitiesNames(String startCityName, String endCityName) {
		return repo.findByStartCityNameAndEndCityName(startCityName, endCityName);
	}

	public void bookTravel(Integer id) throws TravelNotFoundException, TravelBookException {
		getById(id);
		Random random = new Random();

		for (int i = 0; i < 5; ++i) {
			if (random.nextInt(10) > 2)
				return;
		}

		throw new TravelBookException();
	}
}
