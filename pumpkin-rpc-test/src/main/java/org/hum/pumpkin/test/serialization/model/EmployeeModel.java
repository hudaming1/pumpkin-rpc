package org.hum.pumpkin.test.serialization.model;

import java.util.List;

public class EmployeeModel extends HumanModel {

	private static final long serialVersionUID = -8623726285525707225L;
	private String employeeNo;
	private Double salary;
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
		return "EmployeeModel [employeeNo=" + employeeNo + ", salary=" + salary + ", tasks=" + tasks
				+ "]";
	}
	
	public static EmployeeModel createSimple(String name, Boolean sex, Double salary) {
		EmployeeModel employee = new EmployeeModel();
		employee.setId(System.currentTimeMillis());
		employee.setName(name);
		employee.setSex(sex);
		employee.setEmployeeNo("EMP" + System.currentTimeMillis() + "");
		employee.setSalary(salary);
		return employee;
	}
}
