package org.bodywired.api.service;

import java.util.List;

import org.bodywired.api.model.Declinaison;
import org.bodywired.api.model.classement.Etat;

public interface DeclinaisonService {

	Boolean sauvegarderDeclinaison(Declinaison declinaison);

	Boolean sauvegarderEtat(Etat etat);

	List<Etat> getEtats();

}
