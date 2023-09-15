package ru.itmo.se.bl.lab3.entity;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;

@Entity
@Table(name = "international_passports")
@Data
@Getter
public class InternationalPassport implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String series;
	
	@Column(nullable = false)
	private String number;
	
	@Column(name = "birth_date", nullable = false)
	private Date birthDate;
	
	@Column(name = "expire_date", nullable = false)
	private Date expireDate;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Column(nullable = false)
	private String citizenship;
}
