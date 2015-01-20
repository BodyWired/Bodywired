package org.bodywired.api.controller;

import org.bodywired.api.service.ParseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mangofactory.swagger.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class DumpController {

	@Autowired
	private ParseData parser;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String redirect() {
		return "redirect:index.html";
	}

	@RequestMapping(value = "/data/dump", method = RequestMethod.GET)
	public String main() {
		try {
			parser.run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:index.html";
	}

}
