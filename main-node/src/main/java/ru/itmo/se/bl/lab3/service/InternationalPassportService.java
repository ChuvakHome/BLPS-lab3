package ru.itmo.se.bl.lab3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.itmo.se.bl.lab3.entity.InternationalPassport;
import ru.itmo.se.bl.lab3.repository.InternationalPassportRepository;

@Service
public class InternationalPassportService {
	@Autowired
	private InternationalPassportRepository repo;
	
	public InternationalPassport getById(Integer id) {
		return repo.findById(id).orElse(null);
	}
	
	public InternationalPassport save(InternationalPassport internationalPassport) {
		return repo.save(internationalPassport);
	}
	
	public void saveAll(List<InternationalPassport> internationalPassports) {
		repo.saveAll(internationalPassports);
	}
	
	public InternationalPassport getBySeriesAndNumber(String series, String number) {
		return repo.findBySeriesAndNumber(series, number).orElse(null);
	}
}
