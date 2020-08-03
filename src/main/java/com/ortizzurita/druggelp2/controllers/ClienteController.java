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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ortizzurita.druggelp2.models.entities.Cliente;
import com.ortizzurita.druggelp2.models.services.IClienteService;

@Controller
@SessionAttributes("cliente")
@RequestMapping(value="/cliente")  
public class ClienteController {

	@Autowired 
	private IClienteService srvCliente;
	
	@GetMapping(value="/create") 
	public String create(Model model) {
		Cliente cliente = new Cliente();
		cliente.setSexo("M");
		model.addAttribute("title", "Nuevo registro de Cliente");
		model.addAttribute("cliente", cliente);
		return "cliente/form";
	}
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		Cliente cliente = srvCliente.findById(id);
		model.addAttribute("title", cliente.toString());
		model.addAttribute("cliente", cliente);		
		return "cliente/card";
	}
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id") Integer id, Model model) {
		Cliente cliente = srvCliente.findById(id);
		model.addAttribute("cliente",cliente);
		model.addAttribute("title", "Actualizando el registro de " + cliente);
		return "cliente/form";
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model) {
		srvCliente.delete(id);
		return "redirect:/cliente/list";		
	}
	
	@GetMapping(value="/list")
	public String list(Model model) {
		List<Cliente> clientes = srvCliente.findAll();
		model.addAttribute("clientes", clientes);
		model.addAttribute("title", "Listado de clientes");
		return "cliente/list";
	}
	
	@PostMapping(value="/save") 
	public String save(@Validated Cliente cliente, BindingResult result, Model model,
			SessionStatus status, RedirectAttributes flash) {
		try {
			
			String message = "Cliente agregado correctamente";
			String titulo = "Nuevo registro de Cliente";
			
			if(cliente.getIdcliente() != null) {
				message = "Cliente actualizado correctamente";
				titulo = "Actualizando el registro de " + cliente;
			}
						
			if(result.hasErrors()) {
				model.addAttribute("title", titulo);							
				return "cliente/form";				
			}
																
			srvCliente.save(cliente);	
			status.setComplete();
			flash.addFlashAttribute("success", message);
		}
		catch(Exception ex) {
			flash.addFlashAttribute("error", ex.getMessage());
		}				
		return "redirect:/cliente/list";
	}
}
