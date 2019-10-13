package com.stock.mvc.dao.impl;

import java.util.List;

import javax.persistence.Query;

import com.stock.mvc.dao.ICommandeClientDao;
import com.stock.mvc.entites.CommandeClient;
import com.stock.mvc.entites.LigneCommandeClient;

public class CommandeClientDaoImpl extends GenericDaoImpl<CommandeClient> implements ICommandeClientDao {

	@Override
	public List<LigneCommandeClient> getByIdCommande(Long idCommandeClient) {
		// TODO Auto-generated method stub
		
		String queryString = "select lc from " + LigneCommandeClient.class.getSimpleName() + " lc where lc.commandeClient.idCommandeClient = :x";
		
		Query query = em.createQuery(queryString);
		query.setParameter("x",idCommandeClient);
		
		return query.getResultList();
	}

}
