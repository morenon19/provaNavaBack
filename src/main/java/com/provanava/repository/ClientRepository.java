package com.provanava.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.provanava.domain.Client;

@Repository
public class ClientRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/** Retorna uma Lista de Client com todos os clientes cadastrados no banco de dados */
	public List<Client> findAll(){
		String query = "select * from client order by name";
		return jdbcTemplate.query(query, 
				(rs, rowNum) -> new Client(
									rs.getString("name"),
									rs.getString("cnpj"),
									rs.getString("email"),
									rs.getString("phone"),
									rs.getDate("registered")));
	}
	
	/** Retorna um Client dado um número de cnpj do banco de dados*/
	public Client findByCnpj(String cnpj) {
		String query = "select * from client where cnpj = ?";
		return jdbcTemplate.queryForObject(query, new Object[] { cnpj },
				(rs, rowNum) -> new Client(
									rs.getString("name"),
									rs.getString("cnpj"),
									rs.getString("email"),
									rs.getString("phone"),
									rs.getDate("registered")));
	}
	
	/** Retorna um Client dado um nome do banco de dados*/
	public Client findByName(String name) {
		String query = "select * from client where name = ?";
		return jdbcTemplate.queryForObject(query, new Object[] { name },
				(rs, rowNum) -> new Client(
									rs.getString("name"),
									rs.getString("cnpj"),
									rs.getString("email"),
									rs.getString("phone"),
									rs.getDate("registered")));
	}
	
	/** Realiza a inserção de um cliente ao banco de dados*/
	public void insert(Client client) {
		String query = "insert into client (name, cnpj, registered, email, phone) VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(query, 
				client.getName(), client.getCnpj(), client.getRegistred(), client.getEmail(), client.getPhone());
	}
	
	/** Realiza a exclusão de um cliente do banco de dados */
	public void delete(String cnpj) {
		String query = "delete from client where cnpj = ?";
		jdbcTemplate.update(query, cnpj);
	}
	
}
