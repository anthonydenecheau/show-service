package com.scc.onlinedogshow.template;

import java.util.List;
import java.util.Map;

import com.scc.onlinedogshow.model.Breeder;
import com.scc.onlinedogshow.model.Owner;
import com.scc.onlinedogshow.model.Pedigree;
import com.scc.onlinedogshow.model.Title;

public class ResponseObject {

	private int id;
	private String name;
	private String gender;
	private String birthDate;
	private String birthCountry;
	private List<Pedigree> pedigrees;
	private List<Map<String, Object>> tokens;
	private Breed breed;	
	private Map<String, Object> father;
	private Map<String, Object> mother;
	private Breeder breeder;
	private List<Owner> owners;
	private List<Title> titles;
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	
	public String getName() { return name;}
	public void setName(String name) { this.name = name;}
	
	public String getGender() { return gender;}
	public void setGender(String gender) { this.gender = gender; }
	
	public String getBirthDate() { return birthDate; }
	public void setBirthDate(String birthDate) { this.birthDate = birthDate; }
	
	public String getBirthCountry() { return birthCountry; }
	public void setBirthCountry(String birthCountry) { this.birthCountry = birthCountry; }
	
	public List<Pedigree> getPedigrees() { return pedigrees; }
	public void setPedigrees(List<Pedigree> pedigrees) { this.pedigrees = pedigrees; }
	
	public List<Map<String, Object>> getTokens() { return tokens; }
	public void setTokens(List<Map<String, Object>> tokens) { this.tokens = tokens; }
	
	public Breed getBreed() { return breed; }
	public void setBreed(Breed breed) { this.breed = breed; }
	
	public Map<String, Object> getFather() { return father; }
	public void setFather(Map<String, Object> father) { this.father = father; }
	
	public Map<String, Object> getMother() { return mother; }
	public void setMother(Map<String, Object> mother) { this.mother = mother; }
	
	public Breeder getBreeder() { return breeder; }
	public void setBreeder(Breeder breeder) { this.breeder = breeder; }
	
	public List<Owner> getOwners() { return owners; }
	public void setOwners(List<Owner> owners) { this.owners = owners; }
	
	public List<Title> getTitles() { return titles; }
	public void setTitles(List<Title> titles) { this.titles = titles; }
	
	public ResponseObject withId(int id){ this.setId( id ); return this; }
	public ResponseObject withName(String name){ this.setName( name ); return this; }
	public ResponseObject withGender(String gender){ this.setGender( gender ); return this; }
	public ResponseObject withBirthDate(String birthDate){ this.setBirthDate( birthDate ); return this; }
	public ResponseObject withBirthCountry(String birthCountry){ this.setBirthCountry( birthCountry ); return this; }
	public ResponseObject withTokens(List<Map<String, Object>> tokens){ this.setTokens(tokens); return this; }
	public ResponseObject withPedigrees(List<Pedigree> pedigrees){ this.setPedigrees( pedigrees); return this; }
	public ResponseObject withBreed(Breed breed){ this.setBreed( breed ); return this; }
	public ResponseObject withFather(Map<String, Object> father){ this.setFather( father ); return this; }
	public ResponseObject withMother(Map<String, Object> mother){ this.setMother( mother ); return this; }
	public ResponseObject withBreeder(Breeder breeder){ this.setBreeder( breeder ); return this; }
	public ResponseObject withOwners(List<Owner> owner){ this.setOwners( owner ); return this; }
	public ResponseObject withTitles(List<Title> titles){ this.setTitles( titles ); return this; }

}
