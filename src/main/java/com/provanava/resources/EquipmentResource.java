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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.provanava.domain.Equipment;
import com.provanava.services.EquipmentService;
/** Endpoint Restfull para trabalhar com equipamentos */
@RestController
@RequestMapping(value="/equipments")
public class EquipmentResource {

	@Autowired
	private EquipmentService service;
	
	/** Endpoint para recuperar equipamentos*/
	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<Equipment>> findAll(){
		List<Equipment> equips = service.findAll();
		return ResponseEntity.ok().body(equips);
	}
	
	/** Endpoint para recuperar equipamentos com coluna client_cnpj vazia */
	@CrossOrigin
	@GetMapping(value="/null")
	public ResponseEntity<List<Equipment>> findNull(){
		List<Equipment> equips = service.findNull();
		return ResponseEntity.ok().body(equips);
	}
	
	/** Endpoint para recuperar equipamentos dado um número de cnpj */
	@CrossOrigin
	@GetMapping(value="/client/{cnpj}")
	public ResponseEntity<List<Equipment>> findByClient(@PathVariable String cnpj){
		List<Equipment> equips = service.findByClient(cnpj);
		return ResponseEntity.ok().body(equips);
	}
	
	/** Endpoint para recuperar equipamentos dado um número de serial */
	@CrossOrigin
	@GetMapping(value="/{serial}")
	public ResponseEntity<Equipment> findBySerial(@PathVariable String serial){
		Equipment equip = service.findBySerial(serial);
		return ResponseEntity.ok().body(equip);
	}
	
	/** Endpoint para inserir equipamentos */
	@CrossOrigin
	@PostMapping
	public ResponseEntity<Equipment> insert(@RequestBody Equipment equip){
		service.insert(equip);
		URI uri = ServletUriComponentsBuilder.
				fromCurrentRequest().path("/{id}").
				buildAndExpand(equip.getSerial()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	/** Endpoint para adicionar valor à coluna client_cnpj de equipamentos */
	@CrossOrigin
	@PostMapping(value="/addclients/{cnpj}")
	public ResponseEntity<Equipment> addClient(@RequestBody List<Equipment> equips, @PathVariable String cnpj){
		service.addClients(equips, cnpj);
		return ResponseEntity.ok().build(); 
	}
	
	/** Endpoint para remover valor da coluna client_cnpj de equipamentos */
	@CrossOrigin
	@PostMapping(value="/removeclients")
	public ResponseEntity<Equipment> addClient(@RequestBody List<Equipment> equips){
		service.removeClients(equips);
		return ResponseEntity.ok().build(); 
	}
	
	/** Endpoint para alterar valor da coluna active de equipamentos */
	@CrossOrigin
	@PutMapping(value="/{serial}/cst")
	public ResponseEntity<Equipment> changeStatus(@RequestBody Equipment equip){
		service.changeStatus(equip);
		return ResponseEntity.ok().build();
	}
	
	/** Endpoint para remover equipamentos */
	@CrossOrigin
	@DeleteMapping(value="/{serial}")
	public ResponseEntity<Equipment> delete(@RequestBody Equipment equip){
		service.delete(equip);
		return ResponseEntity.ok().build();
	}
}
