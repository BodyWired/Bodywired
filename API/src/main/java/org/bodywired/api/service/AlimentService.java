package org.bodywired.api.service;

import java.util.List;

import org.bodywired.api.model.Aliment;
import org.bodywired.api.model.Declinaison;
import org.bodywired.api.model.classement.CategorieAliment;
import org.bodywired.api.wrapper.RechercheWrapper;

public interface AlimentService {

	public boolean ajouterAlimentDansCategorie ( CategorieAliment categorieAliment, Aliment aliment );

	public boolean ajouterDeclinaisonAliment ( Aliment aliment, Declinaison declinaison );

	public void sauvegarderAliment ( Aliment aliment );

	public List <CategorieAliment> getCategoriesAliments ();

	public List <Aliment> getAliments ( RechercheWrapper wrapper );

	public int getTotal ();

}
