package ru.itmo.se.bl.lab3.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.itmo.se.bl.lab3.entity.TouristInfo;

public interface TouristInfoRepository extends JpaRepository<TouristInfo, Integer> {
	Optional<TouristInfo> findByPassportId(Integer passportId);
	
	Optional<TouristInfo> findByInternationalPassportId(Integer internationalPassportId);
}
