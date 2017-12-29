package com.scc.onlinedogshow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ods_titres")
public class Title {

	@Id
	@Column(name = "id", nullable = false)
	@JsonIgnore
	private int id;

	@Column(name = "id_chien", nullable = false)
	@JsonIgnore
	private int idDog;

	@Column(name = "code")
	private String title;

	@Column(name = "nom")
	private String name;

	@Column(name = "categorie")
	private String type;

	@Column(name = "pays")
	private String country;

	@Column(name = "date_obtention")
	private String obtentionDate;

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public int getIdDog() { return idDog; }
	public void setIdDog(int idDog) { this.idDog = idDog; }

	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getType() { return type; }
	public void setType(String type) { this.type = type; }

	public String getCountry() { return country; }
	public void setCountry(String country) { this.country = country; }

	public String getObtentionDate() { return obtentionDate; }
	public void setObtentionDate(String obtentionDate) { this.obtentionDate = obtentionDate; }

}
