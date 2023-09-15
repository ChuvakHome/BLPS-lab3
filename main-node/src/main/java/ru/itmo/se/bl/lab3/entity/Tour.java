package ru.itmo.se.bl.lab3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Setter;

@Entity
@Table(name = "tours")
@Setter
@Data
public class Tour {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "tour_name", nullable = false)
	private String tourName;
	
	@Column(name = "tour_day_count", nullable = false)
	private int tourDays;
	
	@Column(name = "tour_travel_cost", nullable = false)
	private int tourTravelCost;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "tour_city_id", nullable = false)
	@JsonIgnore
	private City tourCity;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "tour_hotel_id", nullable = true)
	private Hotel tourHotel;
}
