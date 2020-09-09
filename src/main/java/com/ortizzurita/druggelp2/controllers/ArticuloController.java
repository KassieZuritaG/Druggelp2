package com.ortizzurita.druggelp2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ortizzurita.druggelp2.models.entities.Articulo;
import com.ortizzurita.druggelp2.models.services.IArticuloService;




@Controller
@RequestMapping(value="/articulo")  
public class ArticuloController {
	
	@Autowired
	private IArticuloService srvArticulo;

	@GetMapping(value="/create") 
	public String create(Model model) {
		Articulo articulo = new Articulo();
		model.addAttribute("articulo", articulo);
		return "articulo/form"; 
	}
	
	@GetMapping(value="/search/{criteria}", produces="application/json")
	public @ResponseBody List<Articulo> search(@PathVariable(value="criteria") String criteria, Model model) {
		List<Articulo> lista=this.srvArticulo.findByNombre(criteria);
		return lista;		
	}
}
