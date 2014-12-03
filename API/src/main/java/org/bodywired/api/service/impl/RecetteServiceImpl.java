package org.bodywired.api.service.impl;

import org.bodywired.api.service.RecetteService;
import org.springframework.stereotype.Service;

@Service
public class RecetteServiceImpl implements RecetteService {

	@Override
	public Integer getTotalRecettes() {
		return 2;
	}

}
