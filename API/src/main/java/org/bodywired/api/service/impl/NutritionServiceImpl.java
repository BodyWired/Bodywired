package org.bodywired.api.service.impl;

import java.util.List;

import org.bodywired.api.dao.NutritionDao;
import org.bodywired.api.model.ApportNutritionnel;
import org.bodywired.api.model.Declinaison;
import org.bodywired.api.model.nutriment.Mineral;
import org.bodywired.api.model.nutriment.OligoElement;
import org.bodywired.api.model.nutriment.Vitamine;
import org.bodywired.api.service.AlimentService;
import org.bodywired.api.service.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NutritionServiceImpl implements NutritionService {

	@Autowired
	private NutritionDao	nutritionDao;

	@Autowired
	private AlimentService	alimentService;

	@Override
	public boolean sauvegarderApportNutritionnel ( ApportNutritionnel apportNutritionnel,
			Declinaison declinaison ) {
		if ( declinaison.getId() == null ) {
			alimentService.ajouterDeclinaisonAliment(declinaison.getAliment(), declinaison);
		}

		nutritionDao.sauvegarderCholesterol(apportNutritionnel.getCholesterol());
		nutritionDao.sauvegarderEau(apportNutritionnel.getEau());
		nutritionDao.sauvegarderGlucide(apportNutritionnel.getGlucide());
		nutritionDao.sauvegarderLipide(apportNutritionnel.getLipide());

		for ( Mineral mineral : apportNutritionnel.getMineraux() ) {
			nutritionDao.sauvegarderMineral(mineral);
		}
		for ( OligoElement ol : apportNutritionnel.getOligoElements() ) {
			nutritionDao.sauvegarderOligoElement(ol);
		}
		for ( Vitamine vit : apportNutritionnel.getVitamines() ) {
			nutritionDao.sauvegarderVitamine(vit);
		}

		return nutritionDao.sauvegarderApportNutritionnel(apportNutritionnel) == 1;
	}

	@Override
	public List <ApportNutritionnel> getNutriments () {
		// TODO Auto-generated method stub
		return null;
	}

}
