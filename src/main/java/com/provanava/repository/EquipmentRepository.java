package com.provanava.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.provanava.domain.Client;
import com.provanava.domain.Equipment;

@Repository
public class EquipmentRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/** Retorna uma Lista de Equipment com todos os equipamentos cadastrados no banco de dados */
	public List<Equipment> findAll(){
		String query = "select * from equipment";
		return jdbcTemplate.query(query,
				(rs, rowNum) -> new Equipment(
						 			rs.getInt("serial"),
									rs.getString("name"),
									rs.getString("ip"),
									rs.getDate("registered"),
									rs.getDate("disabled"),
									rs.getString("email"),
									rs.getString("phone"),
									rs.getBoolean("active"),
									new Client()));
	}
	
	/** Retorna um Equipment dado o número de cnpj de um cliente do banco de dados*/
	public List<Equipment> findByClient(String cnpj){
		String query =  "select * from equipment where client_cnpj = ?";
		return jdbcTemplate.query(query, new Object[] { cnpj },
				(rs, rowNum) -> new Equipment(
									rs.getInt("serial"),
									rs.getString("name"),
									rs.getString("ip"),
									rs.getDate("registered"),
									rs.getDate("disabled"),
									rs.getString("email"),
									rs.getString("phone"),
									rs.getBoolean("active"),
									new Client()));
	}
	
	/** Retorna uma lista de Equipments cadastrados no banco de dados que não possuem valor na chave clien_cnpj*/
	public List<Equipment> findNull(){
		String query =  "select * from equipment where client_cnpj is null";
		return jdbcTemplate.query(query,
				(rs, rowNum) -> new Equipment(
									rs.getInt("serial"),
									rs.getString("name"),
									rs.getString("ip"),
									rs.getDate("registered"),
									rs.getDate("disabled"),
									rs.getString("email"),
									rs.getString("phone"),
									rs.getBoolean("active"),
									new Client()));
	}
	
	/** Retorna um Equipment dado um número de serial do banco de dados*/
	public Equipment findBySerial(String serial) {
		String query = "select * from equipment where serial = ?";
		return jdbcTemplate.queryForObject(query, new Object[] { serial },
				(rs, rowNum) -> new Equipment(
									rs.getInt("serial"),
									rs.getString("name"),
									rs.getString("ip"),
									rs.getDate("registered"),
									rs.getDate("disabled"),
									rs.getString("email"),
									rs.getString("phone"),
									rs.getBoolean("active"),
									new Client()));
	}
	
	/** Retorna a contagem de equipamentos cadastrados no banco de dados com o status active dado um número de cnpj*/
	public int countActive(String cnpj) {
		String query = "select count(*) from equipment where client_cnpj = ? and active like 1";
		int count = jdbcTemplate.queryForObject(query, new Object[] { cnpj }, Integer.class);
		return count;
	}
	
	/** Realiza a inserção de um equipamento ao banco de dados*/
	public void insert(Equipment equip) {
		String query = "insert into equipment (serial, name, ip, registered, disabled, email, phone, active) values (?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(query, equip.getSerial(), equip.getName(), equip.getIp(), equip.getRegistered(), equip.getDisabled(), equip.getEmail(), equip.getPhone(), equip.isActive());
	}
	
	/** Realiza a troca de valores da coluna active do equipamento*/
	public void changeStatus(Equipment equip) {
		String query = "update equipment set active = ?, disabled = ? where serial = ?";
		jdbcTemplate.update(query, equip.isActive(), equip.getDisabled(), equip.getSerial());
	}
	
	/** Insere o valor de cnpj na coluna client_cnpj da tabela de equipamentos*/
	public void addClient(Equipment equip, String cnpj) {
		String query = "update equipment set client_cnpj = ? where serial = ?";
		jdbcTemplate.update(query, cnpj, equip.getSerial());
	}
	
	/** Remove o valor de cnpj na coluna client_cnpj da tabela de equipamentos*/
	public void removeClient(Equipment equip) {
		String query = "update equipment set client_cnpj = NULL where serial = ?";
		jdbcTemplate.update(query, equip.getSerial());
	}
	
	/** Realiza a exclusão de um equipamento do banco de dados */
	public void delete(Equipment equip) {
		String query = "delete from equipment where serial = ?";
		jdbcTemplate.update(query, equip.getSerial());
	}
}
