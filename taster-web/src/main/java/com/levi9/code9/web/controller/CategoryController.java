package com.levi9.code9.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.levi9.code9.model.Category;
import com.levi9.code9.service.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(method = RequestMethod.GET)
	@ModelAttribute("categories")
	public List<Category> get() {
		return categoryService.findAll();
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String getNew(Model model) {
		model.addAttribute("category", new Category());
		return "addEditCategory";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String getEdit(@PathVariable Long id, Model model) {
		model.addAttribute("category", categoryService.findOne(id));
		return "addEditCategory";
	}
	
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public String remove(@PathVariable Long id) {
		categoryService.remove(id);
		return "redirect:..";
	}
	
	@RequestMapping(params = "save", method = RequestMethod.POST)
	public String post(@Valid Category category, BindingResult bindingResult, 
			Model model) {
		if (!bindingResult.hasErrors()) {
			categoryService.save(category);
			return "redirect:categories";
		} else {
			model.addAttribute("category", category);
			return "addEditCategory";
		}
	}
	
	@RequestMapping(params = "cancel", method = RequestMethod.POST)
	public String cancel() {
		return "redirect:categories";
	}
}
