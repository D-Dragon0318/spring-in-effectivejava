package uml.类图;

public interface IStudentRecord {
    // 注册学生的方法
    void register();

    // 更新学生联系信息
    void updateContactInfo(String phone, String address);
}
