package effective.java.item6;

import java.util.*;

public class MapKeySetDemo {
	public static void main(String[] args) {
		// 创建一个Map来模拟员工名册
		Map<String, String> employeeRoster = new HashMap<>();
		employeeRoster.put("E001", "Alice");
		employeeRoster.put("E002", "Bob");
		employeeRoster.put("E003", "Charlie");

		// 第一次调用keySet，获取当前所有员工ID的集合视图
		Set<String> idsView1 = employeeRoster.keySet();
		System.out.println("Initial keySet: " + idsView1);

		// 显示地证明idsView1和随后调用keySet返回的是同一个实例
		Set<String> idsView2 = employeeRoster.keySet();
		System.out.println("idsView1 and idsView2 are the same instance? " + (idsView1 == idsView2));

		// 更新员工名册，模拟员工加入或离开
		employeeRoster.remove("E002"); // 员工Bob离职
		employeeRoster.put("E004", "David"); // 新员工David加入

		// 观察idsView1的变化，验证它是动态更新的
		System.out.println("Updated keySet (via idsView1): " + idsView1);
	}
}
