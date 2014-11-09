package org.bodywired.api.service.impl;

import java.util.List;

import org.bodywired.api.dao.AlimentDao;
import org.bodywired.api.model.Aliment;
import org.bodywired.api.model.classement.Categorie;
import org.bodywired.api.service.AlimentService;
import org.bodywired.api.service.CategorieService;
import org.bodywired.api.wrapper.RechercheWrapper;
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
		alimentDao.sauvegarderAliment(aliment);
		for (Categorie categorie : aliment.getCategories()) {
			if (categorie.getId() == null) {
				categorieService.sauvegarderCategorieAliment(categorie);
			}
			categorieService.ajouterAlimentDansCategorie(aliment, categorie);
		}
		return true;
	}

	@Override
	public Integer getTotalAliments() {
		return alimentDao.getTotalAliments();
	}

	@Override
	public List<Aliment> rechercherAliments(RechercheWrapper wrapper) {
		return alimentDao.rechercherAliments(wrapper);
	}

	@Override
	public Aliment getAliment(String nom) {
		return (alimentDao.getAliment(nom));
	}

}
