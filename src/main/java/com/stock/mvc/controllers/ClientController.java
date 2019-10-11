package com.stock.mvc.controllers;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.stock.mvc.entites.Client;
import com.stock.mvc.services.IClientService;

@Controller
@RequestMapping(value="/client")
public class ClientController {
	
	
	@Autowired
	private IClientService clientService;
	@RequestMapping(value="/")
	public String client(Model model) {
		
		List<Client>clients = clientService.selectAll();
		
		if (clients ==null)
			clients = new ArrayList<Client> ();
		
		model.addAttribute("clients", clients);
		return "client/client";
		
	}
	
	@RequestMapping(value="/nouveau",method=RequestMethod.GET)
	public String ajouterClient (Model model) {
		Client client = new Client();
		model.addAttribute("client",client);
		
		return "client/ajouterClient";
		
	}
	
	@RequestMapping(value="/enregistrer",method=RequestMethod.POST)
	public String enregistrerClient (Model model,Client client , MultipartFile file) throws Exception {
		
		if (client!=null) {
		if (file!=null && !file.isEmpty()) {
			
				InputStream stream = file.getInputStream();
				
		}
				
				if (client.getIdClient()!=null) {
					clientService.update(client);
					System.err.println("client a id " + client.getIdClient() + " " + client.getNom());
				}else {
					clientService.save(client);
					System.err.println("client a pas  id " + client.getIdClient() + " " + client.getNom());
				}
			
		
		
		
		}
		
		return "redirect:/client/";
		
	}
	
	@RequestMapping(value="/modifier/{idClient}")
	public String modifierClient(Model model,@PathVariable("idClient") Long idClient) {
		
		Client client = clientService.getById(idClient);
		
		model.addAttribute("client", client);
		return "client/ajouterClient";
	}
	
	@RequestMapping(value="/supprimer/{idClient}")
	public String supprimerClient(Model model , @PathVariable("idClient") Long idClient) {
		
		if (idClient!=null) {
			Client client = clientService.getById(idClient);
			
			if (client !=null) {
				
				//Verifier si client n a pas de dépendance
				clientService.remove(idClient);
			}
		}
		
		
		return "redirect:/client/";
		
	}
	
	
	


}
