package effective.java.item17;

public class PhoneNumber {

    private String number;
    
    //普通构造器
    public PhoneNumber(String number) {
        this.number = number;
    }

    // 防御性拷贝的构造器
    public PhoneNumber(PhoneNumber phoneNumber) {
        this.number = phoneNumber.number; // 字符串的深拷贝
    }

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "PhoneNumber [number=" + number + "]";
	}
    
}
