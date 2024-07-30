// Model
class Student {
    private String name;
    private String id;
    private String grade;

    public Student(String name, String id, String grade) {
        this.name = name;
        this.id = id;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}// View
class StudentView {
    public void displayStudentDetails(String name, String id, String grade) {
        System.out.println("Student Details:");
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Grade: " + grade);
    }
}
// Controller
class StudentController {
    private Student model;
    private StudentView view;

    public StudentController(Student model, StudentView view) {
        this.model = model;
        this.view = view;
    }

    public void setStudentName(String name) {
        model.setName(name);
    }

    public String getStudentName() {
        return model.getName();
    }

    public void setStudentId(String id) {
        model.setId(id);
    }

    public String getStudentId() {
        return model.getId();
    }

    public void setStudentGrade(String grade) {
        model.setGrade(grade);
    }

    public String getStudentGrade() {
        return model.getGrade();
    }

    public void updateView() {
        view.displayStudentDetails(model.getName(), model.getId(), model.getGrade());
    }
}
public class MVCPattern {
    public static void main(String[] args) {
        // Fetch student record based on his roll no from the database
        Student model = retrieveStudentFromDatabase();

        // Create a view to write student details on console
        StudentView view = new StudentView();

        // Create a controller
        StudentController controller = new StudentController(model, view);

        // Display student details
        System.out.println("Initial Student Details:");
        controller.updateView();

        // Update model data
        System.out.println("\nUpdating student grade...");
        controller.setStudentGrade("A+");

        // Display updated student details
        System.out.println("\nUpdated Student Details:");
        controller.updateView();
    }

    private static Student retrieveStudentFromDatabase() {
        // This is a mock method to simulate fetching a student from a database
        return new Student("John Doe", "10001", "B+");
    }
}
