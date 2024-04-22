package uml.类图;

// 定义 Student 类，继承自 Staff 类，并实现 IStudentRecord 接口
public class Student extends Staff implements IStudentRecord {
    // 学生特有属性
    private int studentID;
    private String firstName;
    private String lastName;
    private int gradeLevel;

    public Student(int studentID, String firstName, String lastName, int gradeLevel) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gradeLevel = gradeLevel;
    }

    public void setFirstName(String newName) {
        this.firstName = newName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    // 实现抽象方法 evaluatePerformance
    @Override
    public void evaluatePerformance() {
        // 根据学生的成绩、出勤等因素评估表现
        System.out.println("Evaluating performance for student " + getFullName());
    }

    // 实现 IStudentRecord 接口方法
    @Override
    public void register() {
        // 注册学生逻辑...
        // 注册逻辑，此处为示例，实际应与数据库交互
        System.out.println("Registered student with ID: " + studentID);
    }

    @Override
    public void updateContactInfo(String phone, String address) {
        System.out.println("Contact info updated for student " + getFullName());
    }

}