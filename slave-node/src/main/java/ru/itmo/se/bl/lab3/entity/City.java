package ru.itmo.se.bl.lab3.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

@Entity
@Table(name = "cities")
@Setter
@Data
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "city_name", nullable = false)
	private String name;
	
	@Column(name = "city_local_name", nullable = false)
	private String localName;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "city_country_id", nullable = false)
	private Country country;
}
