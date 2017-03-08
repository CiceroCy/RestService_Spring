package com.api.web.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.expression.spel.CodeFlow.ClinitAdder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.web.model.Cliente;

@RestController
public class ClienteController {
	
	
	Map<Integer, Cliente> clientes=new HashMap<>();
	Integer proximoId=1;
	
	
	
	//Negocios
	
	private Cliente cadastrar(Cliente cliente){
	
		cliente.setId(proximoId);
		proximoId++;
		
		clientes.put(cliente.getId(), cliente);
	
		
		return cliente;
		
	}
	
	
	private Collection<Cliente> listAll(){
		
		 return clientes.values();
		
	}
	
	//EndPont
	
	@RequestMapping(value="/Clientes" ,
			method=RequestMethod.POST, 
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> CadastrarCliente(@RequestBody Cliente cliente){
	
		Cliente clientesSalvo= cadastrar(cliente);
		
		return new ResponseEntity<>(clientesSalvo,HttpStatus.OK);
	}
	
	@RequestMapping(value="/Clientes", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Cliente>> buscarTodos(){
		
		Collection<Cliente> clientesEncontrados= listAll();
		
		return new ResponseEntity<>(clientesEncontrados,HttpStatus.OK);
		
	}
	
}
