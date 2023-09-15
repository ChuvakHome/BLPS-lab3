package ru.itmo.se.bl.lab3.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

@Entity
@Table(name = "countries")
@Setter
@Data
public class Country {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "country_name", nullable = false)
	private String name;
	
	@Column(name = "country_local_name", nullable = false)
	private String localName;
	
//	@OneToMany(mappedBy = "country", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<City> cities;
}
