package com.ortizzurita.druggelp2.controllers;

import java.util.List;

/*import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.ortizzurita.druggelp2.models.entities.Articulo;
import com.ortizzurita.druggelp2.models.services.IDetalleReservaService;*/
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ortizzurita.druggelp2.models.entities.DetalleReserva;



@Controller
@SessionAttributes("detallereserva")
@RequestMapping(value="/detallereserva")  

public class DetalleReservaController {
	
	
	@GetMapping(value="/create") 
	public String create(Model model) {
		DetalleReserva detallereserva = new DetalleReserva();
		model.addAttribute("detallereserva", detallereserva);
		return "detallereserva/form"; 
	}
	
	/*@Autowired 
	private IDetalleReservaService srvDetalleReserva;
	
	@GetMapping(value="/create") 
	public String create(Model model) {
		DetalleReserva detallereserva = new DetalleReserva();
		model.addAttribute("title", "Nuevo registro de Detalle");
		model.addAttribute("detallereserva", detallereserva);
		return "detallereserva/form";
	}
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		DetalleReserva detallereserva = srvDetalleReserva.findById(id);
		model.addAttribute("title", detallereserva.toString());
		model.addAttribute("detallereserva", detallereserva);		
		return "detallereserva/card";
	}
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id") Integer id, Model model) {
		DetalleReserva detallereserva = srvDetalleReserva.findById(id);
		model.addAttribute("detallereserva",detallereserva);
		model.addAttribute("title", "Actualizando el registro de " + detallereserva);
		return "detallereserva/form";
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model) {
		srvDetalleReserva.delete(id);
		return "redirect:/detallereserva/list";		
	}
	
	@GetMapping(value="/list")
	public String list(Model model) {
		List<DetalleReserva> detalles = srvDetalleReserva.findAll();
		model.addAttribute("detalles", detalles);
		model.addAttribute("title", "Listado de detalles");
		return "detallereserva/list";
	}
	
	@PostMapping(value="/save") 
	public String save(@Validated DetalleReserva detallereserva, BindingResult result, Model model,
			SessionStatus status, RedirectAttributes flash) {
		try {
			
			String message = "Detalle de Reserva agregado correctamente";
			String titulo = "Nuevo registro de Detalle de Reserva";
			
			if(detallereserva.getIddetalle() != null) {
				message = "Detalle de Reserva actualizado correctamente";
				titulo = "Actualizando el registro de " + detallereserva;
			}
						
			if(result.hasErrors()) {
				model.addAttribute("title", titulo);							
				return "detallereserva/form";				
			}
																
			srvDetalleReserva.save(detallereserva);	
			status.setComplete();
			flash.addFlashAttribute("success", message);
		}
		catch(Exception ex) {
			flash.addFlashAttribute("error", ex.getMessage());
		}				
		return "redirect:/detallereserva/list";
	}*/
}
