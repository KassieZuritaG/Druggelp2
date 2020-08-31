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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ortizzurita.druggelp2.models.entities.Farmaceutico;
import com.ortizzurita.druggelp2.models.services.IFarmaceuticoService;

@Controller
@RequestMapping(value="/farmaceutico")  
public class FarmaceuticoController {

	@Autowired 
	private IFarmaceuticoService srvFarmaceutico;
	
	@GetMapping(value="/create") 
	public String create(Model model) {
		Farmaceutico farmaceutico = new Farmaceutico();
		farmaceutico.setSexo("M");
		model.addAttribute("title", "Nuevo registro de farmacéutic@");
		model.addAttribute("farmaceutico", farmaceutico);
		return "farmaceutico/form";
	}
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		Farmaceutico farmaceutico = srvFarmaceutico.findById(id);
		model.addAttribute("farmaceutico", farmaceutico);
		return "farmaceutico/card";		
	}
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id") Integer id, Model model) {
		Farmaceutico farmaceutico = srvFarmaceutico.findById(id);
		model.addAttribute("farmaceutic", farmaceutico);
		model.addAttribute("title", "Actualizando el registro de " + farmaceutico);
		return "farmaceutico/form";
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model) {
		srvFarmaceutico.delete(id);
		return "redirect:/farmaceutico/list";
	}
	
	@GetMapping(value="/list")
	public String list(Model model) {
		List<Farmaceutico> farmaceuticos = srvFarmaceutico.findAll();
		model.addAttribute("farmaceuticos", farmaceuticos);
		model.addAttribute("title", "Listado de farmaceuticos");
		return "farmaceutico/list";
	}
	
	@PostMapping(value="/save") 
	public String save(@Validated Farmaceutico farmaceutico, BindingResult result, Model model,
			@RequestParam("photo") MultipartFile image,
			SessionStatus status, RedirectAttributes flash) {
		try {
			String message = "Farmaceutico agregado correctamente";
			String titulo = "Nuevo registro de farmaceuticos";
			
			if(farmaceutico.getIdfarmaceutico()!= null) {
				message = "Farmaceutico actualizado correctamente";
				titulo = "Actualizando el registro de " + farmaceutico;
			}
			if(result.hasErrors()) {
				model.addAttribute("title", titulo);							
				return "farmaceutico/form";				
			}
			if (!image.isEmpty()) {				
				Path dir = Paths.get("src//main//resources//static//photos");
				String rootPath = dir.toFile().getAbsolutePath();
				try {
					byte[] bytes = image.getBytes();
					Path rutaCompleta = Paths.get(rootPath + "//" + image.getOriginalFilename());
					Files.write(rutaCompleta, bytes);
					farmaceutico.setImagen(image.getOriginalFilename());

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			this.srvFarmaceutico.save(farmaceutico);
			status.setComplete();
			flash.addFlashAttribute("success", message);
			
		}catch(Exception ex){
			flash.addFlashAttribute("error", ex.getMessage());
		}
		return "redirect:/farmaceutico/list";
	}
}
