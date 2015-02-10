package org.bodywired.api.dao;

import org.apache.ibatis.annotations.Param;
import org.bodywired.api.model.Utilisateur;

public interface UtilisateurDao {
	
	public Integer creerUtilisateur(@Param("uti") Utilisateur utilisateur);
	
	public Integer modifierProfil(@Param("uti") Utilisateur utilisateur);
	
	public Utilisateur getUtilisateurByLogin(@Param("login") String login);
	
	public Utilisateur getUtilisateurById(@Param("uti_id") Integer id);
	
//	public List <Recette> favorisPourUnUtilisateur(@Param("uti") Utilisateur utilisateur);
//	
//	public List <Planning> planningPourUnUtilisateur(@Param("uti") Utilisateur utilisateur);

}
