package effective.java.item10.对称性;

public class Person {
    private String name;
    private int age;

    // 构造函数、getter和setter省略...

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person other = (Person) obj;
        // 违反对称性：如果调用对象的name为"Alice"，则总是返回true，无视其他条件
        if ("Alice".equals(this.name)) return true;
        return false; // 其他情况都视为不相等
    }

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

    // 注意：此处未展示hashCode方法的实现，但在实际应用中也需相应调整
}
