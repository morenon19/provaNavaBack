package com.provanava.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.provanava.domain.Client;
import com.provanava.services.ClientService;
/** Endpoint Restfull para trabalhar com clientes */
@RestController
@RequestMapping(value="/clients")
public class ClientResource {

	@Autowired
	private ClientService service;
	
	/** Endpoint para recuperar clientes */
	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<Client>> findAll(){
		List<Client> clients = service.findAll();
		return ResponseEntity.ok().body(clients);
	}
	
	/** Endpoint para recuperar cliente dado um número de cnpj */
	@CrossOrigin
	@GetMapping(value="/{cnpj}")
	public ResponseEntity<Client> findByCnpj(@PathVariable String cnpj){
		Client client = service.findByCnpj(cnpj);
		return ResponseEntity.ok().body(client);
	}
	
	/** Endpoint para inserir clientes */
	@CrossOrigin
	@PostMapping
	public ResponseEntity<Client> insert(@RequestBody Client client){
		service.insert(client);
		URI uri = ServletUriComponentsBuilder.
				fromCurrentRequest().path("/{id}").
				buildAndExpand(client.getCnpj()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	/** Endpoint para remover clientes */
	@CrossOrigin
	@DeleteMapping(value="/{cnpj}")
	public ResponseEntity<Client> delete(@PathVariable String cnpj){
		service.delete(cnpj);
		return ResponseEntity.ok().build();
	}
	
}
