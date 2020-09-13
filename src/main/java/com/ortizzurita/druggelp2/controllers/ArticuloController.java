package com.ortizzurita.druggelp2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ortizzurita.druggelp2.models.entities.Articulo;
import com.ortizzurita.druggelp2.models.services.IArticuloService;



@Controller
@SessionAttributes("articulo")
@RequestMapping(value="/articulo")  
public class ArticuloController {
	
	@Autowired 
	private IArticuloService srvArticulo;
	
	
	@GetMapping(value="/create") 
	public String create(Model model) {
		Articulo articulo = new Articulo();
		model.addAttribute("title", "Nuevo registro de Articulo");
		model.addAttribute("articulo", articulo);
		return "articulo/form"; 
	}
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		Articulo articulo = srvArticulo.findById(id);
		model.addAttribute("title", articulo.toString());
		model.addAttribute("articulo", articulo);		
		return "articulo/card"; 
	}
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id") Integer id, Model model) {
		Articulo articulo = srvArticulo.findById(id);
		model.addAttribute("articulo", articulo);
		model.addAttribute("title", "Actualizando el registro de " + articulo);
		return "articulo/form"; 
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model) {
		srvArticulo.delete(id);
		return "redirect:/articulo/list";		
	}
	
	@GetMapping(value="/list")
	public String list(Model model) {
		List<Articulo> articulos = srvArticulo.findAll();
		model.addAttribute("articulo", articulos);
		model.addAttribute("title", "Listado de articulos");
		return "articulo/list";
	}
	
	@PostMapping(value="/save") 
	public String save(@Validated Articulo articulo, BindingResult result, Model model,
			SessionStatus status, RedirectAttributes flash) {
		try {
			
			String message = "Articulos en Farmacia agregado correctamente";
			String titulo = "Nuevo registro de Articulos en Farmacia";
			
			if(articulo.getIdarticulo() != null) {
				message = "Articulos de farmacia actualizado correctamente";
				titulo = "Actualizando el registro de " + articulo;
			}
						
			if(result.hasErrors()) {
				model.addAttribute("title", titulo);							
				return "articulo/form";				
			}
			
			srvArticulo.save(articulo);
			status.setComplete();
			flash.addFlashAttribute("success", message);
		}
		catch(Exception ex) {
			flash.addFlashAttribute("error", ex.getMessage());
		}				
		return "redirect:/articulo/list";
	}
}
