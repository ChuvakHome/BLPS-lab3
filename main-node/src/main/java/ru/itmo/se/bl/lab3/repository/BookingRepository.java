package ru.itmo.se.bl.lab3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.itmo.se.bl.lab3.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
//	@PersistenceContext
//    void setEntityManager(EntityManager entityManager);
}
