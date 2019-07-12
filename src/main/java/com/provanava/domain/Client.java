package com.provanava.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Client implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String cnpj;
	private String email;
	private String phone;
	private Date registred;
	private List<Equipment> equipments;

	public Client() {}
	
	public Client(String name, String cnpj, String email, String phone, Date registred) {
		this.name = name;
		this.cnpj = cnpj;
		this.email = email;
		this. phone = phone;
		this.registred = registred;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getRegistred() {
		return registred;
	}

	public void setRegistred(Date registred) {
		this.registred = registred;
	}

	public List<Equipment> getEquipments() {
		return equipments;
	}

	public void setEquipments(List<Equipment> equipments) {
		this.equipments = equipments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		return true;
	}
}
