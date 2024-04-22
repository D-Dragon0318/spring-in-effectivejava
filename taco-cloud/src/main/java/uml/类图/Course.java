package uml.类图;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Course 类
public class Course {
    private int courseId;
    private String name;
    private Teacher mainTeacher; // 一对一关系，课程的主要负责人
    private List<Teacher> assistantTeachers; // 多对多关系，课程的助理教师列表
    private Set<Student> enrolledStudents; // 多对多关系，报名本课程的学生集合

    public Course(int courseId, String name, Teacher mainTeacher) {
        this.courseId = courseId;
        this.name = name;
        this.mainTeacher = mainTeacher;
        this.assistantTeachers = new ArrayList<>();
        this.enrolledStudents = new HashSet<>();
    }

    public void enroll(Student student) {
        enrolledStudents.add(student);
    }

    // 其他课程相关方法...

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getMainTeacher() {
        return mainTeacher;
    }

    public void setMainTeacher(Teacher mainTeacher) {
        this.mainTeacher = mainTeacher;
    }

    public List<Teacher> getAssistantTeachers() {
        return assistantTeachers;
    }

    public void setAssistantTeachers(List<Teacher> assistantTeachers) {
        this.assistantTeachers = assistantTeachers;
    }

    public Set<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(Set<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }
}
