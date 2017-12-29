package com.scc.onlinedogshow.template;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.scc.onlinedogshow.model.Breeder;
import com.scc.onlinedogshow.model.Owner;
import com.scc.onlinedogshow.model.Title;

public class ResponseObject {

	private int id;
	private String name;
	private String gender;
	private String birthDate;
	private String birthCountry;
	private List<Pedigree> pedigrees;
	private List<Entry> tokens;
	private Breed breed;	
	private Parent father;
	private Parent mother;
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
	
	public List<Entry> getTokens() { return tokens; }
	public void setTokens(List<Entry> tokens) { this.tokens = tokens; }
	
	public Breed getBreed() { return breed; }
	public void setBreed(Breed breed) { this.breed = breed; }
	
	public Parent getFather() { return father; }
	public void setFather(Parent father) { this.father = father; }
	
	public Parent getMother() { return mother; }
	public void setMother(Parent mother) { this.mother = mother; }
	
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
	public ResponseObject withTokens(Map<String, Object> tokens){ this.setTokens( new ArrayList<Entry>(tokens.entrySet()) ); return this; }
	public ResponseObject withPedigrees(List<Pedigree> pedigrees){ this.setPedigrees( pedigrees); return this; }
	public ResponseObject withBreed(Breed breed){ this.setBreed( breed ); return this; }
	public ResponseObject withFather(Parent father){ this.setFather( father ); return this; }
	public ResponseObject withMother(Parent mother){ this.setMother( mother ); return this; }
	public ResponseObject withBreeder(Breeder breeder){ this.setBreeder( breeder ); return this; }
	public ResponseObject withOwners(List<Owner> owner){ this.setOwners( owner ); return this; }
	public ResponseObject withTitles(List<Title> titles){ this.setTitles( titles ); return this; }

}
