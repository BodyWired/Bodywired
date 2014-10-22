package org.bodywired.api.dao;

import org.apache.ibatis.annotations.Param;
import org.bodywired.api.model.ApportNutritionnel;
import org.bodywired.api.model.nutriment.Cholesterol;
import org.bodywired.api.model.nutriment.Eau;
import org.bodywired.api.model.nutriment.Glucide;
import org.bodywired.api.model.nutriment.Lipide;
import org.bodywired.api.model.nutriment.Mineral;
import org.bodywired.api.model.nutriment.OligoElement;
import org.bodywired.api.model.nutriment.Proteine;
import org.bodywired.api.model.nutriment.Vitamine;

public interface NutritionDao {
	
	public Integer sauvegarderApportNutritionnel(@Param("apn") ApportNutritionnel apportNutritionnel);
	public Integer sauvegarderLipide(@Param("lip") Lipide lipide);

	public Integer sauvegarderGlucide(@Param("glu") Glucide glucide);
	public Integer sauvegarderProteine(@Param("pro") Proteine proteine);
	public Integer sauvegarderEau(@Param("eau") Eau eau);
	public Integer sauvegarderCholesterol(@Param("cho") Cholesterol cholesterol);
	public Integer sauvegarderVitamine(@Param("vit") Vitamine vitamine);
	public Integer sauvegarderMineral(@Param("apn") Mineral mineral);
	public Integer sauvegarderOligoElement(@Param("oel") OligoElement oligoElement);
}
