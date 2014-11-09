package org.bodywired.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.bodywired.api.model.Aliment;
import org.bodywired.api.model.classement.Categorie;

public interface ClassementAlimentDao {

	public Integer sauvegarderCategorie(@Param("cat") Categorie categorieAliment);

	public Integer ajouterAlimentDansCategorie(@Param("ali") Aliment aliment,
			@Param("cat") Categorie categorie);

	public List<Categorie> getCategories();

}
