package uml.类图;

// 定义抽象类 Staff，包含基本信息和一个抽象方法
public abstract class Staff {
    // 基本属性
    protected int staffID;
    protected String title;

    public Staff() {
    }

    public Staff(int staffID, String title) {
    }

    // 抽象方法，用于评估工作人员的表现
    public abstract void evaluatePerformance();

    // 其他方法和属性...

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
