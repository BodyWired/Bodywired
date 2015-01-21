package org.bodywired.api.service.impl;

import java.util.List;

import org.bodywired.api.dao.DeclinaisonDao;
import org.bodywired.api.dao.NutritionDao;
import org.bodywired.api.model.Declinaison;
import org.bodywired.api.model.WrapperListNutriment;
import org.bodywired.api.model.classement.Etat;
import org.bodywired.api.model.nutriment.Mineral;
import org.bodywired.api.model.nutriment.OligoElement;
import org.bodywired.api.model.nutriment.Vitamine;
import org.bodywired.api.service.DeclinaisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeclinaisonServiceImpl implements DeclinaisonService {

	@Autowired
	private DeclinaisonDao declinaisonDao;

	@Autowired
	NutritionDao nutritionDao;

	@Override
	public Boolean sauvegarderDeclinaison(Declinaison declinaison) {
		declinaisonDao.sauvegarderDeclinaison(declinaison);
		if (declinaison.getEtats() != null) {
			for (Etat etat : declinaison.getEtats()) {
				sauvegarderEtat(etat);
				declinaisonDao.ajouterEtatALaDeclinaison(declinaison, etat);
			}
		}
		WrapperListNutriment nutriments = declinaison.getNutriments();
		if (nutriments.getCalorie() != null)
			nutritionDao.ajouterNutriment(nutriments.getCalorie(), declinaison);
		if (nutriments.getCholesterol() != null)
			nutritionDao.ajouterNutriment(nutriments.getCholesterol(), declinaison);
		if (nutriments.getEau() != null)
			nutritionDao.ajouterNutriment(nutriments.getEau(), declinaison);
		if (nutriments.getProteine() != null)
			nutritionDao.ajouterNutriment(nutriments.getProteine(), declinaison);
		if (nutriments.getCalorie() != null)
			nutritionDao.ajouterNutriment(nutriments.getCalorie(), declinaison);

		if (nutriments.getMineraux() != null) {
			for (Mineral mineral : nutriments.getMineraux()) {
				nutritionDao.ajouterNutriment(mineral, declinaison);
			}
		}

		if (nutriments.getOligosElements() != null) {
			for (OligoElement oligoElement : nutriments.getOligosElements()) {
				nutritionDao.ajouterNutriment(oligoElement, declinaison);
			}
		}

		if (nutriments.getVitamines() != null) {
			for (Vitamine vitamine : nutriments.getVitamines()) {
				nutritionDao.ajouterNutriment(vitamine, declinaison);
			}
		}
		if (nutriments.getGlucide() != null) {
			nutritionDao.sauvegarderGlucide(nutriments.getGlucide());
		}
		if (nutriments.getLipide() != null) {
			nutritionDao.sauvegarderLipide(nutriments.getLipide());
		}
		return true;
	}

	@Override
	public Boolean sauvegarderEtat(Etat etat) {
		Etat etatBDD = declinaisonDao.getEtat(etat);
		if (etatBDD != null) {
			etat.setId(etatBDD.getId());
			return true;
		}
		return (declinaisonDao.sauvegarderEtat(etat) == 1);
	}

	@Override
	public List<Etat> getEtats() {
		return (declinaisonDao.getEtats());
	}

}
