public class MVCPatternTest {
    public static void main(String[] args) {
        Student model = new Student();
        model.setName("Ram Das");
        model.setId("12345");
        model.setGrade("A");
        StudentView view = new StudentView();
        StudentController controller = new StudentController(model, view);
        controller.updateView();
        controller.setStudentName("Shyam Kumar");
        controller.setStudentGrade("B");
        controller.updateView();
    }
}

