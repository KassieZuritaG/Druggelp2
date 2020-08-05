package com.ortizzurita.druggelp2.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ortizzurita.druggelp2.models.entities.Farmacia;
import com.ortizzurita.druggelp2.models.entities.Farmaco;
import com.ortizzurita.druggelp2.models.services.IFarmaciaService;
import com.ortizzurita.druggelp2.models.services.IFarmacoService;

@Controller
@SessionAttributes("farmaco")
@RequestMapping(value="/farmaco")
public class FarmacoController {

	@Autowired
	private IFarmacoService srvFarmaco;
	
	@Autowired
	private IFarmaciaService srvFarmacia;
	
	@GetMapping(value="/create")
	public String create(Model model) {
		Farmaco farmaco = new Farmaco();
		List<Farmacia> farmacias = this.srvFarmacia.findAll();
		model.addAttribute("title", "Registro de un nueva fármaco");
		model.addAttribute("farmacias", farmacias); 
		model.addAttribute("farmaco", farmaco);
		return "farmaco/form";
	}
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		Farmaco farmaco = this.srvFarmaco.findById(id);
		model.addAttribute("title", farmaco.toString());
		model.addAttribute("farmaco", farmaco);
		return "farmaco/card";
	}
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id") Integer id, Model model) {
		Farmaco farmaco = this.srvFarmaco.findById(id);
		List<Farmacia> farmacias = this.srvFarmacia.findAll();
		model.addAttribute("farmacias", farmacias);
		model.addAttribute("farmaco", farmaco);
		model.addAttribute("title", "Actualizando el registro de "+ farmaco.toString());
		return "farmaco/form";
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model, RedirectAttributes flash) {
		try {
			flash.addFlashAttribute("success", "Eliminado correctamente");
			}
			catch(Exception ex) {
				flash.addFlashAttribute("error", "El fármaco no se puede eliminar");
			}
		this.srvFarmaco.delete(id);
		return "redirect:/farmaco/list";
	}
	
	@GetMapping(value= {"/","/list"})
	public String list(Model model) {
		List<Farmaco> farmacos = this.srvFarmaco.findAll();
		model.addAttribute("farmacos", farmacos);
		model.addAttribute("title", "Listado de fármacos");
		return "farmaco/list";
	}
	
	@PostMapping(value="/save") 
	public String save(@Validated Farmaco farmacos, BindingResult result, Model model,
			@RequestParam("photo") MultipartFile image,
			SessionStatus status, RedirectAttributes flash) {
		try {
			
			String message = "Fármaco agregado correctamente";
			String titulo = "Nuevo registro de fármaco";
			
			if(farmacos.getIdfarmaco() != null) {
				message = "Fármaco actualizado correctamente";
				titulo = "Actualizando el registro de " + farmacos;
			}
			
			if(result.hasErrors()) {
				model.addAttribute("title", titulo);
				model.addAttribute("farmacos", farmacos);
				return "farmaco/form";
			}
			if (!image.isEmpty()) {				
				Path dir = Paths.get("src//main//resources//static//photos");
				String rootPath = dir.toFile().getAbsolutePath();
				try {
					byte[] bytes = image.getBytes();
					Path rutaCompleta = Paths.get(rootPath + "//" + image.getOriginalFilename());
					Files.write(rutaCompleta, bytes);
					farmacos.setImagen(image.getOriginalFilename());

				} catch (IOException e) {
					e.printStackTrace();
				}
			}	
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		this.srvFarmaco.save(farmacos);
		return "redirect:/farmaco/list";
	}
}
