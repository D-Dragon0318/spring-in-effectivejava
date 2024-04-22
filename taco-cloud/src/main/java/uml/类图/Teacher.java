package uml.类图;

import java.util.ArrayList;
import java.util.List;

// Teacher 类 继承自 Staff 类
public class Teacher extends Staff {
    private List<Course> taughtCourses; // 关联关系，教师教授的课程列表

    public Teacher(int staffID, String title) {
        super(staffID, title);
        this.taughtCourses = new ArrayList<>();
    }

    public void teach(Course course) {
        taughtCourses.add(course);
    }

    @Override
    public void evaluatePerformance() {

    }

    // 其他教师相关方法...
}
