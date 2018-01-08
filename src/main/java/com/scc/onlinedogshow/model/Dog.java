package com.scc.onlinedogshow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ods_chien")
public class Dog{

	@Id
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "nom")
	private String nom;

	@Column(name = "affixe")
	private String affixe;

	@Column(name = "sexe")
	private String sexe;
	
	@Column(name = "date_naissance")
	private String dateNaissance;

	@Column(name = "pays")
	private String pays;

	@Column(name = "tatouage")
	private String tatouage;

	@Column(name = "transpondeur")
	private String transpondeur;

	@Column(name = "codefci")
	private String codeFci;

	@Column(name = "idrace")
	private int idRace;

	@Column(name = "idvariete")
	private int idVariete;

	@Column(name = "race")
	private String race;

	@Column(name = "variete")
	private String variete;

	@Column(name = "couleur")
	private String couleur;

	@Column(name = "id_etalon")
	private int idEtalon;

	@Column(name = "id_lice")
	private int idLice;

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getNom() { return nom; }
	public void setNom(String nom) { this.nom = nom; }

	public String getAffixe() { return affixe; }
	public void setAffixe(String affixe) { this.affixe = affixe; }

	public String getSexe() { return sexe; }
	public void setSexe(String sexe) { this.sexe = sexe; }

	public String getDateNaissance() { return dateNaissance; }
	public void setDateNaissance(String dateNaissance) { this.dateNaissance = dateNaissance; }

	public String getPays() { return pays; }
	public void setPays(String pays) { this.pays = pays; }
	/*
	public String getNumlof() { return numlof;	}
	public void setNumlof(String numlof) { this.numlof = numlof; }
	
	public String getNumconfirmation() { return numconfirmation; }
	public void setNumconfirmation(String numconfirmation) { this.numconfirmation = numconfirmation; }
	
	public String getDateConfirmation() { return dateConfirmation; }
	public void setDateConfirmation(String dateConfirmation) { this.dateConfirmation = dateConfirmation; }
	*/
	public String getTatouage() { return tatouage; }
	public void setTatouage(String tatouage) { this.tatouage = tatouage; }
  
	public String getTranspondeur() { return transpondeur; }
	public void setTranspondeur(String transpondeur) { this.transpondeur = transpondeur; }

	public String getCodeFci() { return codeFci; }
	public void setCodeFci(String codeFci) { this.codeFci = codeFci; }
	
	public int getIdRace() { return idRace; }
	public void setIdRace(int idRace) { this.idRace = idRace; }
	
	public int getIdVariete() { return idVariete; }
	public void setIdVariete(int idVariete) { this.idVariete = idVariete; }

	public String getRace() { return race; }
	public void setRace(String race) { this.race = race; }

	public String getVariete() { return variete; }
	public void setVariete(String variete) { this.variete = variete; }

	public String getCouleur() { return couleur; }
	public void setCouleur(String couleur) { this.couleur = couleur; }

	public int getIdEtalon() { return idEtalon;}
	public void setIdEtalon(int idEtalon) { this.idEtalon = idEtalon; }
	
	public int getIdLice() { return idLice; }
	public void setIdLice(int idLice) { this.idLice = idLice; }

}
