package com.stock.mvc.dao;

import java.util.List;

import com.stock.mvc.entites.CommandeClient;
import com.stock.mvc.entites.LigneCommandeClient;

public interface ICommandeClientDao extends IGenericDao<CommandeClient> {
	
	public List<LigneCommandeClient> getByIdCommande (Long idCommandeClient);

}
