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

import com.ortizzurita.druggelp2.models.entities.Articulo;
import com.ortizzurita.druggelp2.models.entities.DetalleReserva;
import com.ortizzurita.druggelp2.models.services.IReservaService;
import com.ortizzurita.druggelp2.models.entities.Medicamento;
import com.ortizzurita.druggelp2.models.entities.Reserva;
import com.ortizzurita.druggelp2.models.services.IArticuloService;
/*
import com.ortizzurita.druggelp2.models.services.IFarmacoService;
import com.ortizzurita.druggelp2.models.reporting.RptReservaUsuario;
import com.ortizzurita.druggelp2.models.entities.Farmaco;*/


@Controller
@SessionAttributes("Reserva")
@RequestMapping(value="/reserva")
public class ReservaController {
	
	@Autowired
	private IReservaService srvReserva;
	
	/*@Autowired
	private IFarmacoService srvFarmaco;*/
	
	@Autowired
	private IArticuloService srvArticulo;
	
	@GetMapping(value="/create")
	public String create(Model model) {
		Reserva reserva = new Reserva();
		reserva.setMedicamentos(new ArrayList<Medicamento>());
		model.addAttribute("title", "Agrega medicamentos para reservarlos");
		model.addAttribute("Reserva", reserva);		
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
	
	@GetMapping(value="/list/{id}")
	public String list(@PathVariable(value="id") Integer id, Model model) {
		List<Reserva> reservas = this.srvReserva.findByUsuario(id);
		model.addAttribute("reservas", reservas);		
		return "reserva/list";
	}
	
	
	@PostMapping(value = "/save")
	public String save(@Validated Reserva reserva, BindingResult result, Model model, SessionStatus status, RedirectAttributes flash) {				
		try {														
			return "reserva/list";
		} catch (Exception ex) {			
			return "reserva/form";
		}		
	}
	
//////////////////////MAESTRO DETALLE/////////////////////
	
	@PostMapping(value="/add", produces="application/json")
	public @ResponseBody Object add(@RequestBody @Valid DetalleReserva detallereserva,
			BindingResult result, Model model, HttpSession session){
		try {
			Articulo articulo = this.srvArticulo.findById(detallereserva.getArticuloid());
			detallereserva.setArticulo(articulo);
			Reserva reserva = (Reserva) session.getAttribute("Reserva");
			reserva.getDetallereservas().add(detallereserva);
			return detallereserva;
		}
		catch(Exception ex){
			return ex;
		}
	}
	
	/*@PostMapping(value="/add", produces="application/json")
	public @ResponseBody Object add(@RequestBody @Valid Medicamento medicamento,
			BindingResult result, Model model, HttpSession session){
		try {
			Farmaco farmaco = this.srvFarmaco.findById(medicamento.getFarmacoid());
			medicamento.setFarmaco(farmaco);
			Reserva reserva = (Reserva) session.getAttribute("Reserva");
			reserva.getMedicamentos().add(medicamento);
			return medicamento;
		}
		catch(Exception ex){
			return ex;
		}
	}*/
	//////////////////////MAESTRO DETALLE/////////////////////
	@GetMapping(value="/pills")
	public String pills(Model model, HttpSession session) {
		Reserva reserva = (Reserva) session.getAttribute("Reserva");
		model.addAttribute("pills", reserva.getDetallereservas());
		model.addAttribute("title","Listado de articulos");
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
