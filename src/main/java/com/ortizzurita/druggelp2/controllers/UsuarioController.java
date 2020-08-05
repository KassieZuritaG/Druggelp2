package com.ortizzurita.druggelp2.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ortizzurita.druggelp2.models.entities.Rol;
import com.ortizzurita.druggelp2.models.entities.Usuario;
import com.ortizzurita.druggelp2.models.services.UsuarioService;

@Controller
@RequestMapping(value="/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@GetMapping(value="/create")
	public String registro(Model model) {	
		Usuario usuario = new Usuario();
		usuario.setSexo("M");
		model.addAttribute("usuario", usuario);
		model.addAttribute("title", "Registro de nuevo usuario");				
		return "usuario/form";
	}
	
	/*@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		Usuario usuario = 
	}*/
	
	@PostMapping(value="/save")
	public String save(@Validated Usuario usuario, BindingResult result, Model model,
			@RequestParam("photo") MultipartFile image, RedirectAttributes flash) {
		try {
			if(result.hasErrors())
			{	
				model.addAttribute("title", "Registro de nuevo usuario");
				model.addAttribute("usuario", usuario);
				return "usuario/form";
			}
			if (!image.isEmpty()) {				
				Path dir = Paths.get("src//main//resources//static//photos");
				String rootPath = dir.toFile().getAbsolutePath();
				try {
					byte[] bytes = image.getBytes();
					Path rutaCompleta = Paths.get(rootPath + "//" + image.getOriginalFilename());
					Files.write(rutaCompleta, bytes);
					usuario.setImagen(image.getOriginalFilename());

				} catch (IOException e) {
					e.printStackTrace();
				}
			}	
			String pass = usuario.getPassword();
			usuario.setPassword(encoder.encode(pass));			
			usuario.getRoles().add(new Rol("ROLE_USER"));
			usuario.setHabilitado(true);
			service.save(usuario);
			flash.addFlashAttribute("success", "El usuario fue agregado con éxito.");
		}
		catch(Exception ex) {
			flash.addFlashAttribute("error", "El usuario no pudo ser agregado.");
		}
		return "redirect:/login";		
	} 
	
	@GetMapping(value="/list")
	public String list(Model model) {
		List<Usuario> usuarios = service.findAll();
		model.addAttribute("usuarios", usuarios);
		model.addAttribute("title", "Listado de usuarios");
		return "usuario/list";
	}
}
