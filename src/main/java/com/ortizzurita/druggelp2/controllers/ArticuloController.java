package com.ortizzurita.druggelp2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ortizzurita.druggelp2.models.entities.Articulo;



@Controller
@RequestMapping(value="/articulo")  
public class ArticuloController {

	@GetMapping(value="/create") 
	public String create(Model model) {
		Articulo articulo = new Articulo();
		model.addAttribute("articulo", articulo);
		return "articulo/form"; 
	}
}
