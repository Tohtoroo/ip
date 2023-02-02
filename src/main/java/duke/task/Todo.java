package duke.task;

<<<<<<< HEAD
/*
=======
/**
>>>>>>> branch-A-CodingStandard
 * Represents a task called todo.
 * A todo is a task without any date/time attached to it.
 */
public class Todo extends Task {

    /**
     * Constructor for a new Todo object.
     * @param taskName Name of the task.
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Constructor for a new Todo object used for loading from save file.
     * @param taskName Name of the task.
     * @param isDone Status of the task.
     */
    public Todo(String taskName, boolean isDone) {
        super(taskName);
        this.isDone = isDone;
    }
    @Override
    public String[] encode() {
        String[] res = new String[]{"T", this.getStatusIcon(), this.taskName};
        return res;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
