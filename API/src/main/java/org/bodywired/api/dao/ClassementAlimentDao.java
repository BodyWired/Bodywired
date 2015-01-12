package org.bodywired.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.bodywired.api.model.classement.CategorieAliment;

public interface ClassementAlimentDao {

	public Integer sauvegarderCategorieAliment(
			@Param("cat") CategorieAliment categorieAliment);

	public List<CategorieAliment> getCategories();

}
