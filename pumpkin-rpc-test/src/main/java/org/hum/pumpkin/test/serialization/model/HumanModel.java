package org.hum.pumpkin.test.serialization.model;

import java.io.Serializable;

public class HumanModel implements Serializable {

	private static final long serialVersionUID = -5190711525249912395L;

	private Long id;
	private String name;
	private Boolean sex;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "HumanModel [id=" + id + ", name=" + name + ", sex=" + sex + "]";
	}

}
