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
import com.ortizzurita.druggelp2.models.entities.Farmacia;
import com.ortizzurita.druggelp2.models.entities.Farmaco;
import com.ortizzurita.druggelp2.models.reporting.RptFarmacoFarmacia;
import com.ortizzurita.druggelp2.models.services.IFarmaciaService;
import com.ortizzurita.druggelp2.models.services.IFarmacoService;



@Controller
@SessionAttributes("Farmacia")
//@SessionAttributes("farmacia")
@RequestMapping(value="/farmacia")
public class FarmaciaController {
	
	@Autowired
	private IFarmaciaService srvFarmacia;
	
	@Autowired
	private IFarmacoService srvFarmaco;
	
	@GetMapping(value = "/create") // https://localhost:8080/usuarios/create
	public String create(Model model) {
		Farmacia farmacia=new Farmacia();
		farmacia.setArticulos(new ArrayList<Articulo>());
		model.addAttribute("title","Registro de nueva farmacia");
		model.addAttribute("Farmacia",farmacia);
		return "farmacia/form";
	}
	
	/*@GetMapping(value="/create")
	public String create(Model model) {
		Farmacia farmacia = new Farmacia();
		farmacia.setArticulos(new ArrayList<Articulo>());
		model.addAttribute("title", "Registro de nueva farmacia");
		model.addAttribute("farmacia", farmacia);
		return "farmacia/form";
	}*/
	
	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable(value = "id") Integer id, Model model) {
		srvFarmacia.delete(id);
		return "redirect:/farmacia/list";
	}
	
	/*@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model) {
		srvFarmacia.delete(id);
		return "redirect:/farmacia/list";
	}*/
	
	
	@PostMapping(value = "/save") // https://localhost:8080/usuarios/save
	public String save(@Validated Farmacia farmacia, BindingResult result, Model model, 
			
			SessionStatus status, RedirectAttributes flash, HttpSession session) {
		try {
			
			String message = "Farmacia agregada con exito";
			String titulo = "Registro de un nueva Farmacia";
			
			if(farmacia.getIdfarmacia() != null) {
				message = "Farmacia actualizada con exito";
				titulo = "Actualizando Farmacia " + farmacia.getNombre();
			}
			
			if(result.hasErrors()) {
				model.addAttribute("title",titulo);
				model.addAttribute("error", "Error agregar farmacia");
				//List<Profesor> Profesor = srvprofesor.findAll();
				//model.addAttribute("Profesor",Profesor);
				return "farmacia/form";
			}
			
			Farmacia farmaciaSession = (Farmacia)  session.getAttribute("Farmacia");
			for(Articulo a : farmaciaSession.getArticulos()) {
				farmacia.getArticulos().add(a);
			}
			
			srvFarmacia.save(farmacia);
			status.setComplete();
			
			flash.addFlashAttribute("success", message);
		}
		catch(Exception ex) {
			flash.addFlashAttribute("success", ex.getMessage());
		}
		return "redirect:/farmacia/list";		
	}
	
	
	
	/*@PostMapping(value="/save") 
	public String save(@Validated Farmacia farmacia, SessionStatus status, BindingResult result, 
			Model model, RedirectAttributes flash, HttpSession session) {
		try {
			String message = "Farmacia agregada correctamente";
			String titulo = "Nuevo registro de Farmacia";
			
			if(farmacia.getIdfarmacia() != null) {
				message = "Farmacia actualizada correctamente";
				titulo = "Actualizando el registro de " + farmacia.getNombre();
			}
			
			if(result.hasErrors()) {
				
				model.addAttribute("title",titulo);
				model.addAttribute("error", "Error al registrar una nueva farmacia");
				return "farmacia/form";
				
				//model.addAttribute("title", "Error al registrar una nueva farmacia");
				//model.addAttribute("error", farmacia);
				//model.addAttribute("farmacia", farmacia);
				//return "farmacia/form";
				}
			
			Farmacia farmaciaSession = (Farmacia) session.getAttribute("Farmacia");
			for(Articulo ar : farmaciaSession.getArticulos()) {
				farmacia.getArticulos().add(ar);
				
			}
			
			srvFarmacia.save(farmacia);
			status.setComplete();
			flash.addFlashAttribute("success", message);
			
		}catch(Exception e) {
			flash.addFlashAttribute("error", e.getMessage());
		}
		
		return "redirect:/farmacia/list";
	}*/
	
	
	@GetMapping(value = "/update/{id}")
	public String update(@PathVariable(value = "id") Integer id, Model model) {
		Farmacia farmacia =srvFarmacia.findById(id);
		model.addAttribute("Farmacia",farmacia);
		model.addAttribute("title","Actualizando farmacia"+ farmacia.getNombre());
		return "farmacia/form";
	}
	
	/*@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id") Integer id, Model model) {
		Farmacia farmacia = srvFarmacia.findById(id);
		model.addAttribute("farmacia", farmacia);
		model.addAttribute("title", "Actualizando el registro de "+ farmacia.getIdfarmacia());
		return "farmacia/form";
	}*/
	
	@GetMapping(value = "/list")
	public String list(Model model) {
		List<Farmacia> farmacia=srvFarmacia.findAll();
		model.addAttribute("Farmacia", farmacia);
		model.addAttribute("title", "Listado de farmacias");
		return "farmacia/list";
	}
	
	/*@GetMapping(value= {"/","/list"})
	public String list(Model model) {
		List<Farmacia> farmacia =srvFarmacia.findAll();
		//List<Farmacia> farmacias = this.srvFarmacia.findAll();
		model.addAttribute("Farmacia", farmacia);
		model.addAttribute("title", "Listado de farmacias");
		return "farmacia/list";
	}*/
	
	@GetMapping(value = "/retrieve/{id}")
	public String retrieve(@PathVariable(value = "id") Integer id, Model model) {
		Farmacia farmacia = srvFarmacia.findById(id);
		model.addAttribute("farmacia", farmacia);
		return "farmacia/card";
	}
	
	/*@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		Farmacia farmacia = srvFarmacia.findById(id);
		model.addAttribute("title", farmacia.toString());
		model.addAttribute("farmacia", farmacia);
		return "farmacia/card";
	}*/
	
	
	@PostMapping(value = "/add", produces="application/json")
	public @ResponseBody Object add(@RequestBody @Valid Articulo articulo, 
			BindingResult result, Model model, HttpSession session) {				
		try {
			Farmaco farmaco=this.srvFarmaco.findById(articulo.getFarmacoid());
			articulo.setFarmaco(farmaco);
			Farmacia farmacia=(Farmacia) session.getAttribute("Farmacia");
			farmacia.getArticulos().add(articulo);
			return articulo;
		} catch (Exception ex) {			
			return ex;
		}		
	}
	
	
	/*@PostMapping(value = "/add", produces="application/json")
	public @ResponseBody Object add(@RequestBody @Valid Articulo articulo, 
			BindingResult result, Model model, HttpSession session) {				
		try {
			Farmaco farmaco =this.srvFarmaco.findById(articulo.getFarmacoid());
			articulo.setFarmaco(farmaco);
			Farmacia farmacia=(Farmacia) session.getAttribute("farmacia");
			farmacia.getArticulos().add(articulo);
			return articulo;
		} catch (Exception ex) {			
			return ex;
		}		
	}*/
	
	
	@GetMapping(value = "/items")
	public String items(Model model, HttpSession session) {
		Farmacia farmacia=(Farmacia) session.getAttribute("Farmacia");
		model.addAttribute("articulos",farmacia.getArticulos());	
		model.addAttribute("title", "Listado de articulos");
		return "articulo/list";
	}
	
	
	/*@GetMapping(value = "/items")
	public String items(Model model, HttpSession session) {
		Farmacia farmacia = (Farmacia) session.getAttribute("farmacia");
		//Farmacia farmacia = (Farmacia) session.getAttribute("Farmacia");
		model.addAttribute("articulos", farmacia.getArticulos());		
		model.addAttribute("title", "Listado de articulos");
		return "articulo/list";
	}*/
	
	
	///////////////////////////////////////////
	@GetMapping(value = "/rptFarmacoFarmacia")
	public String rptFarmacoFarmacia(Model model) {
		return "farmacia/rptFarmacoFarmacia";				
	}
	
	
	@GetMapping(value = "/dataRptFarmacoFarmacia", produces="application/json")
	public @ResponseBody List<RptFarmacoFarmacia> dataRptFarmacoFarmacia(Model model) {				
		try {
			return this.srvFarmacia.rptFarmacoFarmacia();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}		
	}
	
}
