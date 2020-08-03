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

import com.ortizzurita.druggelp2.models.entities.Farmaco;
import com.ortizzurita.druggelp2.models.services.IFarmacoService;

@Controller
@RequestMapping(value="/farmaco")
public class FarmacoController {

	@Autowired
	private IFarmacoService srvFarmaco;
	
	@GetMapping(value="/create")
	public String create(Model model) {
		Farmaco farmaco = new Farmaco();
		model.addAttribute("title", "Registro de un nueva farmaco");
		model.addAttribute("farmaco", farmaco);
		return "farmaco/form";
	}
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		Farmaco farmaco = this.srvFarmaco.findById(id);
		model.addAttribute("farmaco", farmaco);
		return "farmaco/card";
	}
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id") Integer id, Model model) {
		Farmaco farmaco = this.srvFarmaco.findById(id);
		model.addAttribute("farmaco", farmaco);
		model.addAttribute("title", "Actualizando el registro de "+ farmaco);
		return "farmaco/form";
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model) {
		this.srvFarmaco.delete(id);
		return "redirect:/farmaco/list";
	}
	
	@GetMapping(value= {"/","/list"})
	public String list(Model model) {
		List<Farmaco> farmacos = this.srvFarmaco.findAll();
		model.addAttribute("farmacos", farmacos);
		model.addAttribute("title", "Listado de farmacos");
		return "farmaco/list";
	}
	
	@PostMapping(value="/save") 
	public String save(@Validated Farmaco farmacos, BindingResult result, Model model) {
		try {
			if(result.hasErrors()) {
				model.addAttribute("title", "Error al registrar una nuevo farmaco");
				model.addAttribute("farmacos", farmacos);
				return "farmaco/form";
			}
		}catch(Exception ex) {}
		return "redirect:/farmaco/list";
	}
}
