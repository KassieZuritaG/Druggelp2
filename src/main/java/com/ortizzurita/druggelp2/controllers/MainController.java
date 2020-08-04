package com.ortizzurita.druggelp2.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ortizzurita.druggelp2.models.entities.Farmaco;
import com.ortizzurita.druggelp2.models.services.IFarmaciaService;
import com.ortizzurita.druggelp2.models.services.IFarmacoService;

@Controller
@RequestMapping(value="/") 
public class MainController {
	
	@Autowired
	private IFarmacoService srvFarmaco;
	
	@Autowired
	private IFarmaciaService srvFarmacia;
	
	@GetMapping(value= {"/","/index.html"})
	public String index(Model model) {
		return "index";
	}
		
	@GetMapping(value="/shop.html")
	public String tables(Model model) {	
		List<Farmaco> farmacos = this.srvFarmaco.findAll();
		model.addAttribute("farmacos", farmacos);
		return "shop";
	}
	
	@GetMapping(value="/register.html")
	public String register(Model model) {						
		return "register";
	}
	
	@GetMapping(value="/login")
	public String login(@RequestParam(value="error", required=false) String error, 
			Model model, Principal principal, RedirectAttributes flash) {
		
		if(principal != null) {
			flash.addFlashAttribute("info", "El usuario ya tiene una sesión activa.");
			return "redirect:/";
		}		
		if(error != null) {
			model.addAttribute("error", "Usuario o contraseña incorrectas");
		}				
		return "login";
	}

}