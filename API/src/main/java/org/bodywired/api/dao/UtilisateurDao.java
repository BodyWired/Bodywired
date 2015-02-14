package org.bodywired.api.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.bodywired.api.model.Planning;
import org.bodywired.api.model.Utilisateur;

public interface UtilisateurDao {
	
	public Integer creerUtilisateur(@Param("uti") Utilisateur utilisateur);
	
	public Integer modifierProfil(@Param("uti") Utilisateur utilisateur);
	
	public Utilisateur getUtilisateurByLogin(@Param("login") String login);
	
	public Utilisateur getUtilisateurById(@Param("uti_id") Integer userId);

	public Integer ajouterFavori(@Param("uti_id") Integer userId, @Param("rec_id") Integer recId);

	public Integer supprimerFavori(@Param("uti_id") Integer userId, @Param("rec_id") Integer recId);
	
	public Utilisateur getPlanningById(@Param("plan_id") Integer planId);
	
	public Integer ajouterPlanning(@Param("plan") Planning planning);

	public Integer modifierPlanning(@Param("plan_id") Integer planId, @Param("rec_id") Integer recId, @Param("date") Date date, @Param("repas") Integer repas);

	public Integer supprimerPlanning(@Param("plan_id") Integer planId);

	//	public List <Recette> favorisPourUnUtilisateur(@Param("uti") Utilisateur utilisateur);
//	
//	public List <Planning> planningPourUnUtilisateur(@Param("uti") Utilisateur utilisateur);

}
