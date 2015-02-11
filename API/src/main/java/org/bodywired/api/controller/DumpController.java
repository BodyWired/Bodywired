package org.bodywired.api.controller;

import org.apache.log4j.Logger;
import org.bodywired.api.service.ParseAliments;
import org.bodywired.api.service.ParseRecettes;
import org.bodywired.api.service.impl.AlimentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mangofactory.swagger.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class DumpController {

	public static boolean RUN = false;

	@Autowired
	private ParseAliments parserAliment;
	@Autowired
	private ParseRecettes parserRecette;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String redirect() {
		return "redirect:index.html";
	}

	private static final Logger LOGGER = Logger.getLogger(DumpController.class);

	@RequestMapping(value = "/data/dump", method = RequestMethod.GET)
	public String main() {
		try {
			// parserAliment.run();
			if (!RUN) {
				long start = System.currentTimeMillis();
				RUN = true;
				parserRecette.run();
				RUN = false;
				LOGGER.debug("\nDurée : " + (System.currentTimeMillis() - start));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:index.html";
	}

}
