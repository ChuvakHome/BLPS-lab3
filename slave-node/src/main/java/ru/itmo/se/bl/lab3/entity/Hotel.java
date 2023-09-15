package ru.itmo.se.bl.lab3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

@Entity
@Table(name = "hotels")
@Setter
@Data
public class Hotel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "hotel_name", nullable = false)
	private String hotelName;
	
	@Column(name = "cost_per_night", nullable = false)
	private int nightCost;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "country_id", nullable = false)
	@JsonIgnore
	private Country country;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "city_id", nullable = false)
	private City city;
}
