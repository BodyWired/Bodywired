package org.bodywired.api.service;

import java.util.List;

import org.bodywired.api.model.Declinaison;

public interface DeclinaisonService {

	void sauvegarderDeclinaison ( Declinaison declinaison );

	List <Declinaison> getDeclinaison ();

	void sauvegarderEtat ( Etat etat );

	List <Etat> getEtats ();

}
