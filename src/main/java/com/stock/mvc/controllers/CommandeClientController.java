package com.stock.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/commandeclient")
public class CommandeClientController {
	
	@RequestMapping(value="/")
	public String index() {
		
		return "commandeclient/commandeclient";
	}

}
