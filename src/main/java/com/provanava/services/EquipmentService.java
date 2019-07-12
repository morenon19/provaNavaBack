package com.provanava.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.provanava.domain.Equipment;
import com.provanava.exceptions.ObjectNotFoundException;
import com.provanava.repository.EquipmentRepository;

@Service
public class EquipmentService {
	
	@Autowired
	private EquipmentRepository repo;
	
	/** Retorna uma lista de equipamentos */
	public List<Equipment> findAll(){
		return repo.findAll();
	}
	
	/** Retorna uma lista de dado um número de cnpj */
	public List<Equipment> findByClient(String cnpj){
		return repo.findByClient(cnpj);
	}
	
	/** Retorna uma lista de equipamentos dado um número serial */
	public Equipment findBySerial(String serial){
		try {
			Equipment equip = repo.findBySerial(serial);
			return equip;
		}catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException("Objeto não encontrado! CNPJ: " + serial + " Tipo: " + Equipment.class.getName());
		}
	}
	
	/** Retorna uma lista de equipamentos com o valor da coluna client_cnpj vazios */
	public List<Equipment> findNull(){
		try {
			List<Equipment> equips = repo.findNull();
			return equips;
		}catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException("Objetos não encontrados");
		}
	}
	
	/** Repassa um objeto Equipment para ser inserido ao banco de dados */
	public void insert(Equipment equip) {
		repo.insert(equip);
	}
	
	/** Retorna a contagem de equipamentos cadastrados no banco de dados com o status active dado um número de cnpj*/
	public Integer countActive(String cnpj) {
		return repo.countActive(cnpj);
	}

	/** Realiza a troca de valores da coluna active do equipamento*/
	public void changeStatus(Equipment equip) {
		repo.changeStatus(equip);
	}
	
	/** Insere o valor de cnpj na coluna client_cnpj da tabela de equipamentos*/
	public void addClients(List<Equipment> equips, String cnpj) {
		equips.forEach(equip -> repo.addClient(equip, cnpj));
	}
	
	/** Remove o valor de cnpj na coluna client_cnpj da tabela de equipamentos*/
	public void removeClients(List<Equipment> equips) {
		equips.forEach(equip -> repo.removeClient(equip));
	}
	
	/** Realiza a exclusão de um equipamento do banco de dados */
	public void delete(Equipment equip) {
		repo.delete(equip);
	}
}
