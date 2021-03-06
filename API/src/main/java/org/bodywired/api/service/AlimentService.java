package org.bodywired.api.service;

import org.bodywired.api.model.Aliment;
import org.bodywired.api.wrapper.RechercheWrapper;
import org.bodywired.api.wrapper.ResultatRechercheWrapper;

public interface AlimentService {

	public Boolean sauvegarderAliment(Aliment aliment);

	public ResultatRechercheWrapper rechercherAliments(RechercheWrapper wrapper);

	public Aliment getAliment(String nom);

	public Aliment getAlimentById(Integer id);
	
	public Boolean supprimerAliment(Integer id);
	
	public Boolean modifierAliment(Aliment aliment);

	public Aliment rechercherAlimentParHref(String href);

}
