package org.bodywired.api.service.impl;

import java.util.List;

import org.bodywired.api.dao.DeclinaisonDao;
import org.bodywired.api.dao.NutritionDao;
import org.bodywired.api.model.Declinaison;
import org.bodywired.api.model.classement.Etat;
import org.bodywired.api.model.nutriment.AbstractNutriment;
import org.bodywired.api.model.nutriment.Glucide;
import org.bodywired.api.model.nutriment.Lipide;
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
		for (Etat etat : declinaison.getEtats()) {
			sauvegarderEtat(etat);
			declinaisonDao.ajouterEtatALaDeclinaison(declinaison, etat);
		}
		for (AbstractNutriment nutriment : declinaison.getApportsNutriment()) {
			nutritionDao.ajouterNutriment(nutriment, declinaison);
			switch (nutriment.getCodeBDD()) {
			case CAL:
			case CHO:
			case EAU:
			case PRO:
			case MIN:
			case OEL:
			case VIT:
				break;
			case GLU:
				nutritionDao.sauvegarderGlucide((Glucide) nutriment);
				break;
			case LIP:
				nutritionDao.sauvegarderLipide((Lipide) nutriment);
				break;
			default:
				break;
			}
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
