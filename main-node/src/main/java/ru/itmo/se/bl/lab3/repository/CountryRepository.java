package ru.itmo.se.bl.lab3.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.itmo.se.bl.lab3.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
	Optional<Country> findByName(String localName);
}
