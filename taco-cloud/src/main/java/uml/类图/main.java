package uml.类图;

public class main {
    public static void main(String[] args) {
        // 创建一个学生实例
        Student student = new Student(12345, "Alice", "Johnson", 10);
        System.out.println("Student's full name: " + student.getFullName());
        student.setFirstName("Alicia");
        System.out.println("Updated student's full name: " + student.getFullName());

        // 创建教师和课程实例
        Teacher teacher = new Teacher(98765, "Dr. Smith");
        Course course = new Course(101, "Mathematics", teacher);

        // 教师教授课程，并让学生选课
        teacher.teach(course);
        course.enroll(student);

        // 创建一个教室和学生群体类
        Classroom classroom = new Classroom();
        classroom.addStudent(student);

        // 创建一个学校建筑和房间
        SchoolBuilding schoolBuilding = new SchoolBuilding();
        Room room = new Room();
        schoolBuilding.addRoom(room);

        // 显示简要信息
        System.out.println("Teacher teaching " + course.getName() + ": " + teacher.getTitle());
        System.out.println("Student " + student.getFullName() + " is enrolled in " + course.getName());
        System.out.println("Classroom contains student: " + classroom.getStudents().contains(student));
        System.out.println("Room belongs to school building: " + (room.getBelongsTo() != null));

        // 注意：此处仅为演示类之间的关系和方法调用，实际应用中会有更多的业务逻辑和验证过程
    }
}
