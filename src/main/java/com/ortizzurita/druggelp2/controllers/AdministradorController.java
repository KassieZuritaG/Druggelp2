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

import com.ortizzurita.druggelp2.models.entities.Administrador;
import com.ortizzurita.druggelp2.models.services.IAdministradorService;

@Controller
@SessionAttributes("administrador")
@RequestMapping(value="/administrador")  
public class AdministradorController {

	@Autowired 
	private IAdministradorService srvAdministrador;
	
	@GetMapping(value="/create") 
	public String create(Model model) {
		Administrador administrador = new Administrador();
		administrador.setSexo("M");
		model.addAttribute("title", "Nuevo registro de Administrador");
		model.addAttribute("administrador", administrador);
		return "administrador/form";
	}
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		Administrador administrador = srvAdministrador.findById(id);
		model.addAttribute("title", administrador.toString());
		model.addAttribute("administrador", administrador);		
		return "administrador/card";
	}
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id") Integer id, Model model) {
		Administrador administrador = srvAdministrador.findById(id);
		model.addAttribute("administrador",administrador);
		model.addAttribute("title", "Actualizando el registro de " + administrador);
		return "administrador/form";
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model) {
		srvAdministrador.delete(id);
		return "redirect:/administrador/list";		
	}
	
	@GetMapping(value="/list")
	public String list(Model model) {
		List<Administrador> administradores = srvAdministrador.findAll();
		model.addAttribute("administradores", administradores);
		model.addAttribute("title", "Listado de administradores");
		return "administrador/list";
	}
	
	@PostMapping(value="/save") 
	public String save(@Validated Administrador administrador, BindingResult result, Model model,
			@RequestParam("photo") MultipartFile image,
			SessionStatus status, RedirectAttributes flash) {
		try {
			
			String message = "Administrador agregado correctamente";
			String titulo = "Nuevo registro de Administrador";
			
			if(administrador.getIdadministrador() != null) {
				message = "Administrador actualizado correctamente";
				titulo = "Actualizando el registro de " + administrador;
			}
						
			if(result.hasErrors()) {
				model.addAttribute("title", titulo);							
				return "administrador/form";				
			}
			
			if (!image.isEmpty()) {				
				Path dir = Paths.get("src//main//resources//static//photos");
				String rootPath = dir.toFile().getAbsolutePath();
				try {
					byte[] bytes = image.getBytes();
					Path rutaCompleta = Paths.get(rootPath + "//" + image.getOriginalFilename());
					Files.write(rutaCompleta, bytes);
					administrador.setImagen(image.getOriginalFilename());

				} catch (IOException e) {
					e.printStackTrace();
				}
			}													
			srvAdministrador.save(administrador);	
			status.setComplete();
			flash.addFlashAttribute("success", message);
		}
		catch(Exception ex) {
			flash.addFlashAttribute("error", ex.getMessage());
		}				
		return "redirect:/administrador/list";
	}
}
