package com.provanava.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.provanava.domain.Client;
import com.provanava.domain.Equipment;
import com.provanava.exceptions.DataIntegrityException;
import com.provanava.exceptions.ObjectNotFoundException;
import com.provanava.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repo;
	
	@Autowired
	private EquipmentService equipService;
	
	/** Retorna uma lista de clientes */	
	public List<Client> findAll(){
		return repo.findAll();
		
	}
	
	/** Retorna uma lista de clientes dado um número de cnpj */
	public Client findByCnpj(String cnpj) {
		Client client = new Client();
		try {
			client = repo.findByCnpj(cnpj);
			return client;
		}catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException("Objeto não encontrado! CNPJ: " + cnpj + " Tipo: " + Client.class.getName());
		}
		
	}
	
	/** Repassa um objeto Client para ser inserido ao banco de dados */
	public void insert(Client client){
		repo.insert(client);
	}
	
	/** Repassa um objeto Client para ser removido do banco de dados */
	public void delete(String cnpj) {
		Integer count = equipService.countActive(cnpj);
		if(count==0) {
			List<Equipment> equips = equipService.findByClient(cnpj);
			equips.forEach(equip -> equipService.delete(equip));
			repo.delete(cnpj);
		}else {
			throw new DataIntegrityException("Não é possível excluir clientes com equipamentos ATIVOS");
		}
	}
}
