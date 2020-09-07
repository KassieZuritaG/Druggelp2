package com.ortizzurita.druggelp2.controllers;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ortizzurita.druggelp2.models.entities.DetalleReserva;
import com.ortizzurita.druggelp2.models.entities.Farmaco;
import com.ortizzurita.druggelp2.models.entities.Reserva;
import com.ortizzurita.druggelp2.models.reporting.RptReservaUsuario;
import com.ortizzurita.druggelp2.models.services.IFarmacoService;
import com.ortizzurita.druggelp2.models.services.IReservaService;

@Controller
@SessionAttributes("Reserva")
@RequestMapping(value="/reserva")
public class ReservaController {
	
	@Autowired
	private IReservaService srvReserva;
	
	@Autowired
	private IFarmacoService srvFarmaco;
	
	@GetMapping(value="/create")
	public String create(Model model) {
		Reserva reserva = new Reserva();
		reserva.setFarmacos(new ArrayList<DetalleReserva>());
		model.addAttribute("title", "Agrega medicamentos para reservarlos");
		model.addAttribute("Reserva", reserva);		
		return "reserva/form";
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model) {
		this.srvReserva.delete(id);
		return "redirect:/reserva/list";
	}
	
	@PostMapping(value = "/save") // https://localhost:8080/usuarios/save
	public String save(@Validated Reserva reserva, BindingResult result, Model model, SessionStatus status, RedirectAttributes flash, HttpSession session) {
		try {
			
			String message = "Reserva agregada con exito";
			String titulo = "Registro de un nueva Reserva";
			
			if(reserva.getIdreserva() != null) {
				message = "Reserva actualizada con exito";
				titulo = "Actualizando Reserva N°" + reserva.getIdreserva();
			}
			
			if(result.hasErrors()) {
				model.addAttribute("title",titulo);
				model.addAttribute("error", "Error agregar reserva");
				return "reserva/form";
			}
			
			Reserva reservaSession = (Reserva) session.getAttribute("Reserva");
			for(DetalleReserva dr : reservaSession.getFarmacos()) {
				reserva.getFarmacos().add(dr);
			}
			this.srvReserva.save(reserva);
			status.setComplete();
			flash.addFlashAttribute("success", message);
		}
		catch(Exception ex) {
			flash.addFlashAttribute("success", ex.getMessage());
		}
		return "redirect:/reserva/create";		
	}
	
	
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id") Integer id, Model model) {
		Reserva reserva = this.srvReserva.findById(id);
		model.addAttribute("Reserva", reserva);
		model.addAttribute("title", "Actualizando el registro de "+ reserva.getIdreserva());
		return "reserva/form";
	}
	
	@GetMapping(value = "/list")
	public String list(Model model) {
		List<Reserva> reserva = srvReserva.findAll();
		model.addAttribute("Reserva", reserva);
		model.addAttribute("title", "Listado de Reservas");
		return "reserva/list";
	}
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		Reserva reserva = this.srvReserva.findById(id);
		model.addAttribute("Reserva", reserva);
		return "reserva/card";
	}
	
	@PostMapping(value = "/add", produces="application/json")
	public @ResponseBody Object add(@RequestBody @Valid DetalleReserva 
			detalle,
			BindingResult result, Model model, HttpSession session){
		try {
			Farmaco farmaco = this.srvFarmaco.findById(detalle.getMedicamentoid());
			detalle.setMedicamento(farmaco);
			Reserva reserva = (Reserva) session.getAttribute("Reserva");
			reserva.getFarmacos().add(detalle);
			return detalle;
		}
		catch(Exception ex){
			return ex;
		}
	}	
	
	@GetMapping(value="/pills")
	public String pills(Model model, HttpSession session) {
		Reserva reserva = (Reserva) session.getAttribute("Reserva");
		model.addAttribute("farmacos", reserva.getFarmacos());
		model.addAttribute("title","Listado de fármacos");
		return "detallereserva/list";
	}
	
	//comentar
	/*@GetMapping(value = "/rptReservaUsuario")
	public String rptReservaUsuario(Model model) {
		return "reserva/rptReservaUsuario";				
	}
	
	
	@GetMapping(value = "/dataRptReservaUsuario", produces="application/json")
	public @ResponseBody List<RptReservaUsuario> dataRptReservaUsuario(Model model) {				
		try {
			return  this.srvReserva.rptReservaUsuario();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}		
	}*/

	
	
	
}
