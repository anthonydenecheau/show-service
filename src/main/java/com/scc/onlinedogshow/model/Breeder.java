package com.scc.onlinedogshow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ods_eleveur")
public class Breeder {

	@Id
	@Column(name = "id", nullable = false)
	@JsonIgnore
	private int id;

	@Column(name = "nom")
	private String lastName;

	@Column(name = "prenom")
	private String firstName;

	@Column(name = "typ_profil")
	@JsonIgnore
	private String typeProfil;

	@Column(name = "professionnel_actif")
	@JsonIgnore
	private String professionnelActif;

	@Column(name = "raison_sociale")
	@JsonIgnore
	private String raisonSociale;

	@Column(name = "on_suffixe")
	@JsonIgnore
	private String onSuffixe;

	@Column(name = "id_chien")
	@JsonIgnore
	private int idDog;

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }

	public int getIdDog() { return idDog; }
	public void setIdDog(int idDog) { this.idDog = idDog; }
	
	public String getTypeProfil() { return typeProfil; }
	public void setTypeProfil(String typeProfil) { this.typeProfil = typeProfil;}
	
	public String getProfessionnelActif() { return professionnelActif; }
	public void setProfessionnelActif(String professionnelActif) { this.professionnelActif = professionnelActif; }
	
	public String getRaisonSociale() { return raisonSociale; }
	public void setRaisonSociale(String raisonSociale) { this.raisonSociale = raisonSociale; }

	public String getOnSuffixe() { return onSuffixe; }
	public void setOnSuffixe(String onSuffixe) { this.onSuffixe = onSuffixe; }

}
