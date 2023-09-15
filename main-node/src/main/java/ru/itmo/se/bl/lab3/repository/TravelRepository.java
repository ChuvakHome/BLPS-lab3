package ru.itmo.se.bl.lab3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.itmo.se.bl.lab3.entity.Travel;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Integer> {
//	List<Travel> findByStartCityIdAndEndCityId(Integer startCityId, Integer endCityId);
	
	List<Travel> findByStartCityNameAndEndCityName(String startCityName, String endCityName);
}
