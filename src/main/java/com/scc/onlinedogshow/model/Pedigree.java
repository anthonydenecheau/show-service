package com.scc.onlinedogshow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ods_livres")
public class Pedigree {

	@Id
	@Column(name = "id", nullable = false)
	@JsonIgnore
	private int id;

	@Column(name = "id_chien", nullable = false)
	@JsonIgnore
	private int idDog;

	@Column(name = "pays")
	private String country;

	@Column(name = "livre")
	private String type;

	@Column(name = "numero")
	private String number;

	@Column(name = "date_obtention")
	private String obtentionDate;

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public int getIdDog() { return idDog; }
	public void setIdDog(int idDog) { this.idDog = idDog; }

	public String getType() { return type; }
	public void setType(String type) { this.type = type; }

	public String getNumber() { return number; }
	public void setNumber(String number) { this.number= number; }

	public String getCountry() { return country; }
	public void setCountry(String country) { this.country = country; }

	public String getObtentionDate() { return obtentionDate; }
	public void setObtentionDate(String obtentionDate) { this.obtentionDate = obtentionDate; }

}
