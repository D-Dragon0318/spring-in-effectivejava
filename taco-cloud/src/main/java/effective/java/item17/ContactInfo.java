package effective.java.item17;

//规则2: 使类成为final，防止子类化  
public final class ContactInfo {

	// 规则3、4: 声明所有字段为final、私有
	private final String name;
	private final String email; // 假设Email地址不可变

	// 规则5: 确保对于任何可变组件的访问也是不可变的
	private final PhoneNumber phoneNumber; // 假设PhoneNumber是一个可变类，需要特别处理

	// 规则1：构造函数初始化状态，之后状态不可变
	public ContactInfo(String name, String email, PhoneNumber phoneNumber) {
		// 对可变参数进行防御性拷贝
		this.name = name;
		this.email = email.toLowerCase(); // 转换为小写，统一格式
		this.phoneNumber = new PhoneNumber(phoneNumber); // 假设PhoneNumber类有一个拷贝构造器
	}

	// 工厂方法，用于创建ContactInfo实例，同时进行必要的验证和处理
	public static ContactInfo of(String name, String email, PhoneNumber phoneNumber) {
		return new ContactInfo(name, email, phoneNumber);
	}

	// Getter方法，没有Setter
	// 规则1：不提供修改状态的方法
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	// 规则5：确保对于任何可变组件的访问也是不可变的
	// 返回PhoneNumber的拷贝或返回PhoneNumber的不可变视图
	public PhoneNumber getPhoneNumberCopy() {
		return new PhoneNumber(this.phoneNumber);
	}

	@Override
	public String toString() {
		return "ContactInfo [name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
	}
}
