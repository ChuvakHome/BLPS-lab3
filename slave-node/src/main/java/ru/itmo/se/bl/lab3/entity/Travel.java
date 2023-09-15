package ru.itmo.se.bl.lab3.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.sql.Date;

@Entity
@Table(name = "travels")
@Data
@Getter
public class Travel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "travel_date")
	private Date travelDate;
	
	@Column(nullable = false)
	private int cost;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "start_city_id", nullable = false)
	private City startCity;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "end_city_id", nullable = false)
	private City endCity;
	
	@Column(name = "travel_duration", nullable = false)
	private long travelDuration; // travel duration in minutes
}
