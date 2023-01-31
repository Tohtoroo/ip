package duke.command;

import duke.Duke;
import duke.task.Task;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    private final int taskNumberToDelete;
    public DeleteCommand(int taskNumberToDelete) {
        this.taskNumberToDelete = taskNumberToDelete;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
        if (tl.isEmpty()) {
            throw new DukeException("How to delete an empty list of tasks meowww");
        }
        if (this.taskNumberToDelete > tl.size() || this.taskNumberToDelete < 1) {
            throw new DukeException("Out of range you can delete!");
        }
        Task t = tl.get(taskNumberToDelete - 1);
        tl.deleteTask(taskNumberToDelete - 1);
        String toShow = "Meowww. I've removed this task:\n" + t + "\n" + ui.stringOfTaskNumbers(tl);
        ui.showToUser(toShow);
        try {
            storage.update(tl);
        } catch (IOException e) {
            ui.showToUser(e.getMessage());
            throw new DukeException("Problem with updating in delete");
        }
    }
}