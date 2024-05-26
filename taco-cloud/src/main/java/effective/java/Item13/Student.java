package effective.java.Item13;

public class Student implements Cloneable {
    private String name;
    private Address address; // 假设Address是一个简单的类，包含街道、城市等信息

    public Student(String name, Address address) {
        this.name = name;
        // 这里应该是防御性拷贝，以确保传入的address不会被外部修改影响到本对象
        // 但为简化示例，这里直接赋值
        this.address = address;
    }

    // 实现深拷贝的 clone 方法
    @Override
    protected Student clone() throws CloneNotSupportedException {
        // 首先，调用super.clone()完成基本字段的浅拷贝
        Student clonedStudent = (Student) super.clone();

        // 然后，处理引用类型的字段，执行深拷贝，以确保原始对象和克隆对象的引用字段相互独立
        clonedStudent.address = new Address(this.address.getStreet(), this.address.getCity());

        return clonedStudent;
    }

    // 省略getter和setter以及Address类的定义...

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Address originalAddress = new Address("123 Elm Street", "Springfield");
        Student originalStudent = new Student("Alice", originalAddress);

        // 克隆学生对象
        Student clonedStudent = originalStudent.clone();

        // 修改克隆对象的地址，原始对象的地址不应受到影响
        clonedStudent.getAddress().setStreet("456 Oak Avenue");

        System.out.println("Original Student's Address: " + originalStudent.getAddress()); // 应保持为"123 Elm Street"
        System.out.println("Cloned Student's Address: " + clonedStudent.getAddress()); // 应更新为"456 Oak Avenue"
    }
}
