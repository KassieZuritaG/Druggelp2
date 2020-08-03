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

import com.ortizzurita.druggelp2.models.entities.Reserva;
import com.ortizzurita.druggelp2.models.services.IReservaService;

@Controller
@RequestMapping(value="/reserva")
public class ReservaController {
	
	@Autowired
	private IReservaService srvReserva;
	
	@GetMapping(value="/create")
	public String create(Model model) {
		Reserva reserva = new Reserva();
		model.addAttribute("title", "Registro de nueva reserva");
		model.addAttribute("reserva", reserva);
		return "reserva/form";
	}
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		Reserva reserva = this.srvReserva.findById(id);
		model.addAttribute("reserva", reserva);
		return "reserva/card";
	}
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id") Integer id, Model model) {
		Reserva reserva = this.srvReserva.findById(id);
		model.addAttribute("reserva", reserva);
		model.addAttribute("title", "Actualizando el registro de "+ reserva);
		return "reserva/form";
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model) {
		this.srvReserva.delete(id);
		return "redirect:/reserva/list";
	}
	
	@GetMapping(value= {"/","/list"})
	public String list(Model model) {
		List<Reserva> reservas = this.srvReserva.findAll();
		model.addAttribute("reservas", reservas);
		model.addAttribute("title", "Listado de Reservas");
		return "reserva/list";
	}
	
	@PostMapping(value="/save") 
	public String save(@Validated Reserva reserva, BindingResult result, Model model) {
		try {
			if(result.hasErrors()) {
				model.addAttribute("title", "Error al registrar una nueva reserva");
				model.addAttribute("reserva",reserva);
				return "reserva/form";
			}
		}catch(Exception ex){}
		return "redirect:/reserva/list";}
}
