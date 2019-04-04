package org.hum.doublefish.model;

import java.io.Serializable;

public class Dept implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer deptId;

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	@Override
	public String toString() {
		return "Dept [deptId=" + deptId + "]";
	}
}
