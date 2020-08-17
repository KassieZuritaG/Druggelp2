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

import com.ortizzurita.druggelp2.models.entities.Farmacia;
import com.ortizzurita.druggelp2.models.services.IFarmaciaService;

@Controller
@SessionAttributes("farmacia")
@RequestMapping(value="/farmacia")
public class FarmaciaController {
	
	@Autowired
	private IFarmaciaService srvFarmacia;
	
	@GetMapping(value="/create")
	public String create(Model model) {
		Farmacia farmacia = new Farmacia();
		model.addAttribute("title", "Registro de nueva farmacia");
		model.addAttribute("farmacia", farmacia);
		return "farmacia/form";
	}
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		Farmacia farmacia = this.srvFarmacia.findById(id);
		model.addAttribute("title", farmacia.toString());
		model.addAttribute("farmacia", farmacia);
		return "farmacia/card";
	}
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id") Integer id, Model model) {
		Farmacia farmacia = this.srvFarmacia.findById(id);
		model.addAttribute("farmacia", farmacia);
		model.addAttribute("title", "Actualizando el registro de "+ farmacia);
		return "farmacia/form";
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model) {
		srvFarmacia.delete(id);
		return "redirect:/farmacia/list";
	}
	
	@GetMapping(value= {"/","/list"})
	public String list(Model model) {
		List<Farmacia> farmacias = this.srvFarmacia.findAll();
		model.addAttribute("farmacias", farmacias);
		model.addAttribute("title", "Listado de farmacias");
		return "farmacia/list";
	}
	
	@PostMapping(value="/save") 
	public String save(@Validated Farmacia farmacia, SessionStatus status, BindingResult result, Model model, RedirectAttributes flash) {
		try {
			String message = "Farmacia agregada correctamente";
			String titulo = "Nuevo registro de Farmacia";
			
			if(farmacia.getIdfarmacia() != null) {
				message = "Farmacia actualizada correctamente";
				titulo = "Actualizando el registro de " + farmacia;
			}
			
			if(result.hasErrors()) {
				model.addAttribute("title", "Error al registrar una nueva farmacia");
				model.addAttribute("farmacia", farmacia);
				return "farmacia/form";
				}
			srvFarmacia.save(farmacia);
			status.setComplete();
			flash.addFlashAttribute("success", message);
			
		}catch(Exception e) {
			flash.addFlashAttribute("error", e.getMessage());
		}
		
		return "redirect:/farmacia/list";
	}
}
