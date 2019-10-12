package com.stock.mvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stock.mvc.entites.Article;
import com.stock.mvc.entites.Category;
import com.stock.mvc.services.ICategoryService;

@Controller
@RequestMapping(value="/category")
public class CategoryController {
	
	
	@Autowired
	private ICategoryService catService;
	
	
	@RequestMapping(value="/")
	public String category (Model model) {
		
		List<Category> categories = catService.selectAll();
		
		model.addAttribute("categories", categories);
		
		return "category/category";
		
		
	}
	
	@RequestMapping(value="/nouveau")
	public String ajouterClient (Model model) {
		Category cat = new Category ();
		model.addAttribute("category", cat);
		
		return "category/ajouterCategory";
		
	}

	
	@RequestMapping(value="/enregistrer")
	public String enregisterClient (Model model , Category category) {
		
		if (category!=null) {
			
			if (category.getIdCategory() !=null) {
				catService.update(category);
			}else {
				catService.save(category);
			}
		}
		
		
		return "redirect:/category/";
		
	}
	
	
	
	@RequestMapping(value = "/modifier/{idCategory}")
	public String modifierClient(Model model, @PathVariable Long idCategory) {
		if (idCategory != null) {
			Category category = catService.getById(idCategory);
			if (category != null) {
				model.addAttribute("category", category);
			}
		}
		return "category/ajouterCategory";
	}
	
	
	@RequestMapping(value = "/supprimer/{idCategory}")
	
	public String supprimerClient(Model model, @PathVariable Long idCategory) {
		String errorMsg="";
		if (idCategory != null) {
			Category category = catService.getById(idCategory);
			if (category != null) {
				List<Article> articleCat = catService.selectAllArticlesByCategory(idCategory);
				if (articleCat.isEmpty() ) {
					catService.remove(idCategory);
				} else {
					errorMsg="Impossible de supprimer cette catégprie, car elle est affecté à des articles";
					model.addAttribute("message", errorMsg);
					return "Impossible de supprimer cette catégprie, car elle est affecté à des articles";
				}
			}
		}
		return "redirect:/category/";
	}
}
