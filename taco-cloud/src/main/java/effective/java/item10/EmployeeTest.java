package effective.java.item10;

import java.util.HashSet;
import java.util.Set;

public class EmployeeTest {
	public static void main(String[] args) {
		Set<Employee> employeeSet = new HashSet<>();

		// 创建第一个员工，ID为1，姓名为"John Doe"
		Employee employee1 = new Employee(1, "John Doe", "IT");
		employeeSet.add(employee1);
		System.out.println("添加员工1后集合大小: " + employeeSet.size()); // 应输出 1

		// 创建第二个员工，ID为2，但姓名同样为"John Doe"
		Employee employee2 = new Employee(2, "John Doe", "HR");
		employeeSet.add(employee2);
		System.out.println("尝试添加员工2后集合大小: " + employeeSet.size()); // 因错误的equals实现，可能仍输出 1

		// 验证集合内容
		for (Employee emp : employeeSet) {
			System.out.println(
					"集合中的员工: ID=" + emp.getId() + ", Name=" + emp.getName() + ", Department=" + emp.getDepartment());
		}
		// 如果equals实现错误，这里可能只打印出一个员工的信息，表明第二个员工的信息被错误地覆盖了。
	}
}
