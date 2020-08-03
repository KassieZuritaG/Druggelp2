package com.ortizzurita.druggelp2.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value="/") 
public class MainController {
	
	@GetMapping(value= {"/","/index.html"})
	public String index(Model model) {
		return "index";
	}
		
	@GetMapping(value="/tables.html")
	public String tables(Model model) {						
		return "tables";
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