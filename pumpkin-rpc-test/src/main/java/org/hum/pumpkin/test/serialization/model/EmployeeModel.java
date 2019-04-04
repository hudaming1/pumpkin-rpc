package org.hum.pumpkin.test.serialization.model;

import java.util.List;

public class EmployeeModel extends HumanModel {

	private static final long serialVersionUID = -8623726285525707225L;
	private Double salary;
	private String employeeNo;
	private List<WorkTaskModel> tasks;
	
	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public List<WorkTaskModel> getTasks() {
		return tasks;
	}

	public void setTasks(List<WorkTaskModel> tasks) {
		this.tasks = tasks;
	}

	@Override
	public String toString() {
		return "EmployeeModel [employeeNo=" + employeeNo + ", salary=" + salary + ", tasks=" + tasks + ", toString()="
				+ super.toString() + "]";
	}

	public static EmployeeModel createSimple(String name, Boolean sex, Double salary) {
		EmployeeModel employee = new EmployeeModel();
		employee.setId(1551170588749L);
		employee.setName(name);
		employee.setSex(sex);
//		employee.setBirthday("2019-02-26");
		employee.setEmployeeNo("EMP1551170588749");
		employee.setSalary(salary);
		return employee;
	}
}
