package effective.java.item10;

import java.util.Objects;

public class Employee {

	private int id;
	private String name;
	private String department;

	public Employee(int id, String name, String department) {
		this.id = id;
		this.name = name;
		this.department = department;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)// 自反性：检查是否是同一个对象
			return true;
		if (obj == null || getClass() != obj.getClass())// 非null性：确保比较对象不为null且类型相同
			return false;
		Employee other = (Employee) obj;// 安全转换
		// 比较所有关键域
		return Objects.equals(name, other.name);// 错误：仅比较姓名
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

}
