package duke.command;

import duke.task.Deadline;
import duke.task.Todo;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.IOException;

public class DeadlineCommand extends Command {
    private final Deadline task;

    public DeadlineCommand(String taskName, String by) {
        this.task = new Deadline(taskName, by);
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) throws DukeException {
        tl.add(task);
        String toShow = "Meowww, I've added this task:\nAdded: " + task + "\n";
        toShow += ui.stringOfTaskNumbers(tl);
        ui.showToUser(toShow);

        try {
            storage.update(tl);
        } catch (IOException e) {
            ui.showToUser(e.getMessage());
            throw new DukeException("Problem with updating in deadline");
        }
    }
}