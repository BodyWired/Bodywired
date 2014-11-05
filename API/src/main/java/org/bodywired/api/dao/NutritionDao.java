package org.bodywired.api.dao;

import org.apache.ibatis.annotations.Param;
import org.bodywired.api.model.nutriment.AlimentOligoElement;
import org.bodywired.api.model.nutriment.ApportCholesterol;
import org.bodywired.api.model.nutriment.ApportEau;
import org.bodywired.api.model.nutriment.ApportGlucide;
import org.bodywired.api.model.nutriment.ApportLipide;
import org.bodywired.api.model.nutriment.ApportMineral;
import org.bodywired.api.model.nutriment.ApportProteine;
import org.bodywired.api.model.nutriment.ApportVitamine;

public interface NutritionDao {

	public Integer sauvegarderLipide(@Param("lip") ApportLipide lipide);

	public Integer sauvegarderGlucide(@Param("glu") ApportGlucide glucide);

	public Integer sauvegarderProteine(@Param("pro") ApportProteine proteine);

	public Integer sauvegarderEau(@Param("eau") ApportEau eau);

	public Integer sauvegarderCholesterol(
			@Param("cho") ApportCholesterol cholesterol);

	public Integer sauvegarderVitamine(@Param("vit") ApportVitamine vitamine);

	public Integer sauvegarderMineral(@Param("apn") ApportMineral mineral);

	public Integer sauvegarderOligoElement(
			@Param("oel") AlimentOligoElement oligoElement);
}
