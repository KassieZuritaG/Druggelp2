package com.ortizzurita.druggelp2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ortizzurita.druggelp2.models.entities.Medicamento;

@Controller
@RequestMapping(value="/medicamento")  
public class MedicamentoController {
	
	@GetMapping(value="/create") 
	public String create(Model model) {
		Medicamento medicamento = new Medicamento();
		model.addAttribute("medicamento", medicamento);
		return "medicamento/form"; 
	}
}
