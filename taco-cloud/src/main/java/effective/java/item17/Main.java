package effective.java.item17;

public class Main {
	public static void main(String[] args) {
		
		// 创建PhoneNumber实例
		PhoneNumber phoneNumber = new PhoneNumber("12345678");

		// 使用PhoneNumber实例创建ContactInfo对象
		ContactInfo contact = new ContactInfo("John Doe", "代码悦读@bilibili.com", phoneNumber);

		// 打印ContactInfo信息  
        System.out.println("ContactInfo Information: " + contact);  
        
        //尝试修改phoneNumber的状态，看是否影响我们的contact
        phoneNumber.setNumber("123");
        
        // 再次打印ContactInfo信息以验证状态是否改变
        System.out.println("ContactInfo Information: " + contact); 

		// 获取并输出电话号码，这里使用getPhoneNumberCopy()以展示方法调用
		PhoneNumber copiedPhoneNumber = contact.getPhoneNumberCopy();
		//尝试修改copiedPhoneNumber的状态，看是否影响我们的contact
		copiedPhoneNumber.setNumber("456");
		
		// 再次打印ContactInfo信息以验证状态是否改变
        System.out.println("ContactInfo Information: " + contact); 

		// 注意：由于PhoneNumber被设计为不可变，此处的“拷贝”更多是出于演示目的。
		// 在实际不可变设计中，直接返回引用也是安全的，因为原始数据不会被修改。
	}
}
