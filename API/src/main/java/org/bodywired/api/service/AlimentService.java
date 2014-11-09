package org.bodywired.api.service;

import java.util.List;

import org.bodywired.api.model.Aliment;
import org.bodywired.api.wrapper.RechercheWrapper;

public interface AlimentService {

	public Boolean sauvegarderAliment(Aliment aliment);

	public List<Aliment> rechercherAliments(RechercheWrapper wrapper);

	public Integer getTotalAliments();

	public Aliment getAliment(String nom);

}
