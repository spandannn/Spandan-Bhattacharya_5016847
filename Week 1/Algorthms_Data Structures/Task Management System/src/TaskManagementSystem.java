public class TaskManagementSystem {
    public static void main(String[] args) {
        TaskList taskList = new TaskList();

        // Add tasks
        taskList.addTask(new Task("T001", "Design Database", "In Progress"));
        taskList.addTask(new Task("T002", "Develop API", "Not Started"));
        taskList.addTask(new Task("T003", "Test Application", "Completed"));

        // Traverse tasks
        System.out.println("All Tasks:");
        taskList.traverseTasks();

        // Search for a task
        Task task = taskList.searchTask("T002");
        if (task != null) {
            System.out.println("Found Task: " + task.getTaskName());
        } else {
            System.out.println("Task not found.");
        }

        // Delete a task
        taskList.deleteTask("T002");
        System.out.println("After Deletion:");
        taskList.traverseTasks();
    }
}