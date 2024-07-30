class Task {
    private int taskId;
    private String taskName;
    private String status;

    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    public int getTaskId() {
        return taskId;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
class Node {
    Task task;
    Node next;

    public Node(Task task) {
        this.task = task;
        this.next = null;
    }
}

class TaskManagementSystem {
    private Node head;
    private int size;

    public TaskManagementSystem() {
        head = null;
        size = 0;
    }

    public void addTask(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public Task searchTask(int taskId) {
        Node current = head;
        while (current != null) {
            if (current.task.getTaskId() == taskId) {
                return current.task;
            }
            current = current.next;
        }
        return null;
    }

    public void traverseTasks() {
        Node current = head;
        while (current != null) {
            System.out.println(current.task);
            current = current.next;
        }
    }

    public boolean deleteTask(int taskId) {
        if (head == null) {
            return false;
        }
        if (head.task.getTaskId() == taskId) {
            head = head.next;
            size--;
            return true;
        }
        Node current = head;
        Node prev = null;
        while (current != null && current.task.getTaskId() != taskId) {
            prev = current;
            current = current.next;
        }
        if (current == null) {
            return false;
        }
        prev.next = current.next;
        size--;
        return true;
    }

    public int getSize() {
        return size;
    }
}
public class TaskManagement {
    public static void main(String[] args) {
        TaskManagementSystem tms = new TaskManagementSystem();

        // Add tasks
        tms.addTask(new Task(1, "Implement login system", "In Progress"));
        tms.addTask(new Task(2, "Design database schema", "Not Started"));
        tms.addTask(new Task(3, "Write unit tests", "Not Started"));

        System.out.println("All Tasks:");
        tms.traverseTasks();

        // Search for a task
        Task foundTask = tms.searchTask(2);
        System.out.println("\nFound Task: " + foundTask);

        // Delete a task
        boolean deleted = tms.deleteTask(1);
        System.out.println("\nTask deleted: " + deleted);

        System.out.println("\nTasks after deletion:");
        tms.traverseTasks();

        System.out.println("\nTotal tasks: " + tms.getSize());
    }
}
