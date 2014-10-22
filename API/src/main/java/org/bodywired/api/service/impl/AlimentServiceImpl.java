package org.bodywired.api.service.impl;

import java.util.List;

import org.bodywired.api.dao.AlimentDao;
import org.bodywired.api.dao.ClassementAlimentDao;
import org.bodywired.api.model.Aliment;
import org.bodywired.api.model.Declinaison;
import org.bodywired.api.model.classement.CategorieAliment;
import org.bodywired.api.service.AlimentService;
import org.bodywired.api.wrapper.RechercheWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlimentServiceImpl implements AlimentService {

	@Autowired
	private AlimentDao				alimentDao;

	@Autowired
	private ClassementAlimentDao	classementAlimentDao;

	@Override
	public boolean ajouterAlimentDansCategorie ( CategorieAliment categorieAliment, Aliment aliment ) {
		if ( categorieAliment.getId() == null ) {
			classementAlimentDao.sauvegarderCategorieAliment(categorieAliment);
		}
		aliment.setCategorieAliment(categorieAliment);
		return alimentDao.sauvegarderAliment(aliment) == 1;
	}

	@Override
	public boolean ajouterDeclinaisonAliment ( Aliment aliment, Declinaison declinaison ) {
		if ( aliment.getId() == null ) {
			ajouterAlimentDansCategorie(aliment.getCategorieAliment(), aliment);
		}
		declinaison.setAliment(aliment);
		return alimentDao.sauvegarderDeclinaison(declinaison) == 1;
	}

	@Override
	public void sauvegarderAliment ( Aliment aliment ) {
		alimentDao.sauvegarderAliment(aliment);
	}

	@Override
	public List <CategorieAliment> getCategoriesAliments () {
		return alimentDao.getCategoriesAliments();
	}

	@Override
	public int getTotal () {
		return alimentDao.getTotal();
	}

	@Override
	public List <Aliment> getAliments ( RechercheWrapper wrapper ) {
		return alimentDao.getAliments(wrapper);
	}

}
