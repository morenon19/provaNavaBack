package com.provanava.domain;

import java.io.Serializable;
import java.util.Date;

public class Equipment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer serial;
	private String name;
	private String ip;
	private Date registered;
	private Date disabled;
	private String email;
	private String phone;
	private boolean active;
	private Client client;
	
	public Equipment() {}

	public Equipment(Integer serial, String name, String ip, Date registered, Date disabled, String email, String phone, Boolean active, Client client) {
		this.serial = serial;
		this.name = name;
		this.ip = ip;
		this.registered = registered;
		this.disabled = disabled;
		this.email = email;
		this.phone = phone;
		this.active = active;
		this.client = client;
	}

	public Integer getSerial() {
		return serial;
	}

	public void setSerial(Integer serial) {
		this.serial = serial;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getRegistered() {
		return registered;
	}

	public void setRegistered(Date registered) {
		this.registered = registered;
	}

	public Date getDisabled() {
		return disabled;
	}

	public void setDisabled(Date disabled) {
		this.disabled = disabled;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((serial == null) ? 0 : serial.hashCode());
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
		Equipment other = (Equipment) obj;
		if (serial == null) {
			if (other.serial != null)
				return false;
		} else if (!serial.equals(other.serial))
			return false;
		return true;
	}
}
