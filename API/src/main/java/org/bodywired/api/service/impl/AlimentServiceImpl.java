package org.bodywired.api.service.impl;

import org.apache.log4j.Logger;
import org.bodywired.api.dao.AlimentDao;
import org.bodywired.api.model.Aliment;
import org.bodywired.api.model.classement.Categorie;
import org.bodywired.api.service.AlimentService;
import org.bodywired.api.service.CategorieService;
import org.bodywired.api.wrapper.RechercheWrapper;
import org.bodywired.api.wrapper.ResultatRechercheWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlimentServiceImpl implements AlimentService {

	@Autowired
	private AlimentDao alimentDao;

	@Autowired
	private CategorieService categorieService;

	@Override
	public Boolean sauvegarderAliment(Aliment aliment) {
		Aliment existant = rechercherAlimentParHref(aliment.getHref());
		if (existant != null) {
			aliment.setId(existant.getId());
		}
		alimentDao.sauvegarderAliment(aliment);
		for (Categorie categorie : aliment.getCategories()) {
			categorieService.ajouterAlimentDansCategorie(aliment, categorie);
		}
		return true;
	}

	@Override
	public ResultatRechercheWrapper rechercherAliments(RechercheWrapper wrapper) {
		return alimentDao.rechercherAliments(wrapper);
	}

	@Override
	public Aliment getAliment(String nom) {
		return (alimentDao.getAliment(nom));
	}

	@Override
	public Aliment rechercherAlimentParHref(String href) {
		LOGGER.debug("Recherche A : ["+href+"]");
		return alimentDao.rechercherAlimentParHref(href);
	}
	
	private static final Logger LOGGER = Logger.getLogger(AlimentServiceImpl.class);

}
