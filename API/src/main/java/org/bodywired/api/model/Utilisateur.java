package org.bodywired.api.model;

import java.util.LinkedHashSet;
import java.util.Set;

import org.bodywired.api.model.menu.Recette;

public class Utilisateur extends AbstractBaseModel {

	private String	login;
	private String	pwd;
	private String	mail;
	private Integer	sexe;
	private Integer	taille;
	private Integer	poids;
	
	private Set <Recette> favoris;
	private Set <Planning> plannings;
	
	public Utilisateur() {
		super();
		favoris = new LinkedHashSet <Recette> ();
		plannings = new LinkedHashSet <Planning> ();
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Integer getSexe() {
		return sexe;
	}
	public void setSexe(Integer sexe) {
		this.sexe = sexe;
	}
	public Integer getTaille() {
		return taille;
	}
	public void setTaille(Integer taille) {
		this.taille = taille;
	}
	public Integer getPoids() {
		return poids;
	}
	public void setPoids(Integer poids) {
		this.poids = poids;
	}
	public Set <Recette> getFavoris() {
		return favoris;
	}
	public void setFavoris(Set <Recette> favoris) {
		this.favoris = favoris;
	}
	public Set <Planning> getPlannings() {
		return plannings;
	}
	public void setPlannings(Set <Planning> plannings) {
		this.plannings = plannings;
	}
	
	
}
