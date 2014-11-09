package org.bodywired.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.bodywired.api.model.Declinaison;
import org.bodywired.api.model.nutriment.AbstractNutriment;
import org.bodywired.api.model.nutriment.Glucide;
import org.bodywired.api.model.nutriment.Lipide;

public interface NutritionDao {

	public Integer sauvegarderLipide(@Param("lip") Lipide lipide);

	public Integer sauvegarderGlucide(@Param("glu") Glucide glucide);

	public Integer ajouterNutriment(@Param("nut") AbstractNutriment nutriment,
			@Param("dec") Declinaison declinaison);

	public List<AbstractNutriment> getNutriments(@Param("dec_id") Integer id);

}
