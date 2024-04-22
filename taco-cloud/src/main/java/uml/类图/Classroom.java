package uml.类图;

import java.util.ArrayList;
import java.util.List;

// Classroom 类
public class Classroom {
    private List<Student> students= new ArrayList<>();; // 聚合关系，班级内的学生集合
    private Room room;

    public Classroom() {
    }

    public Classroom(Room room) {
        this.room = room;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    // 其他教室相关方法...

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
