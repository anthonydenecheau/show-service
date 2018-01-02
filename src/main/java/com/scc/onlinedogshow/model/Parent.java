package com.scc.onlinedogshow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ods_geniteurs")
public class Parent {

	@Id
	@Column(name = "id", nullable = false)
	@JsonIgnore
	private int id;

	@Column(name = "nom")
	private String name;

	@Column(name = "affixe")
	private String affixe;

	@Column(name = "on_suffixe")
	private String onSuffixe;

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public String getAffixe() { return affixe; }
	public void setAffixe(String affixe) { this.affixe = affixe; }
	
	public String getOnSuffixe() { return onSuffixe; }
	public void setOnSuffixe(String onSuffixe) { this.onSuffixe = onSuffixe; }
	
}
