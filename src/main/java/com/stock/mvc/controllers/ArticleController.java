package com.stock.mvc.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.stock.mvc.entites.Article;
import com.stock.mvc.entites.Category;
import com.stock.mvc.services.IArticleService;
import com.stock.mvc.services.ICategoryService;


@Controller
@RequestMapping(value="/article")
public class ArticleController {
	
	
		@Autowired
		private IArticleService artService;
		
		@Autowired
		private ICategoryService catService;
		
		
		@RequestMapping(value="/")
		public String article (Model model) {
			
			List<Article> articles = artService.selectAll();
			
			
			
			model.addAttribute("articles", articles);

			
			
			return "article/article";

}
		
		@RequestMapping(value="/nouveau")
		public String ajouterArticle (Model model) {
			
			Article art = new Article();
			model.addAttribute("article", art);
			
			
			List<Category>categories = catService.selectAll();
			
			if (categories ==null) categories = new ArrayList();
			
			model.addAttribute("categories", categories);
			
			return "article/ajouterArticle";
		}
		
		@RequestMapping(value = "/enregistrer")
		public String enregistrerArticle(Model model, Article article, MultipartFile file) {
			String photoUrl = null;
			if (article != null) {
				if (file != null && !file.isEmpty()) {
					InputStream stream = null;
					try {
						/*stream = file.getInputStream();
						photoUrl = flickrService.savePhoto(stream, article.getCodeArticle());
						article.setPhoto(photoUrl);*/
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							stream.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				if (article.getIdArticle() != null) {
					artService.update(article);
				} else {
					artService.save(article);
				}
			}
			return "redirect:/article/";
		}
		
		@RequestMapping(value = "/modifier/{idArticle}")
		public String modifierArticle(Model model, @PathVariable Long idArticle) {
			if (idArticle != null) {
				Article article = artService.getById(idArticle);
				List<Category> categories = catService.selectAll();
				if (categories == null) {
					categories = new ArrayList<Category>();
				}
				model.addAttribute("categories", categories);
				if (article != null) {
					model.addAttribute("article", article);
				}
			}
			return "article/ajouterArticle";
		}
		
		@RequestMapping(value = "/supprimer/{idArticle}")
		public String supprimerArticle(Model model, @PathVariable Long idArticle) {
			if (idArticle != null) {
				Article Article = artService.getById(idArticle);
				if (Article != null) {
					//TODO Verification avant suppression
					artService.remove(idArticle);
				}
			}
			return "redirect:/article/";
		}
		
		
		
}