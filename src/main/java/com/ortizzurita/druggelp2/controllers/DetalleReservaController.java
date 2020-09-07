package com.ortizzurita.druggelp2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.ortizzurita.druggelp2.models.entities.DetalleReserva;
import com.ortizzurita.druggelp2.models.services.IDetalleReservaService;

@Controller
@RequestMapping(value="/detallereserva")  
public class DetalleReservaController {
	
	@Autowired 
	private IDetalleReservaService srvDetalleReserva;
	
	@GetMapping(value="/create") 
	public String create(Model model) {
		DetalleReserva detalle = new DetalleReserva();
		model.addAttribute("detallereserva", detalle);
		return "detallereserva/form"; 
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model) {
		DetalleReserva detalle = this.srvDetalleReserva.findById(id);
		int idReserva = detalle.getReservaid();
		this.srvDetalleReserva.delete(id);
		return "redirect:/detallereserva/list/"+idReserva;		
	}
}
