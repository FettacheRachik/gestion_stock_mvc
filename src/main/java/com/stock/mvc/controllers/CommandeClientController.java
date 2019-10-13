package com.stock.mvc.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stock.mvc.entites.CommandeClient;
import com.stock.mvc.entites.LigneCommandeClient;
import com.stock.mvc.services.IArticleService;
import com.stock.mvc.services.IClientService;
import com.stock.mvc.services.ICommandeClientService;
import com.stock.mvc.services.ILigneCommandeClientService;

@Controller
@RequestMapping(value="/commandeclient")
public class CommandeClientController {
	
	@Autowired
	private ICommandeClientService commandeService;
	
	@Autowired
	private IClientService clientService;
	
	@Autowired
	private IArticleService articleService;
	
	@Autowired
	private ILigneCommandeClientService ligneCdeService;
	
	
	@RequestMapping(value="/")
	public String index(Model model) {
		
		List<CommandeClient> commandesClient = commandeService.selectAll();
		
		if (commandesClient.isEmpty()) {
			commandesClient = new ArrayList();
		}else {
			
			for (CommandeClient commandeClient: commandesClient) {
				List<LigneCommandeClient> ligneCdeClt = ligneCdeService.getByIdCommande(commandeClient.getIdCommandeClient());
				commandeClient.setLigneCommandeClients(ligneCdeClt);
			}
		}
		
		model.addAttribute("commandesClient", commandesClient);
		return "commandeclient/commandeclient";
	}

}
