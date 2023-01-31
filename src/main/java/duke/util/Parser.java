package duke.util;

import duke.Duke;
import duke.command.*;
import duke.task.Event;

public class Parser {

    private String readCommand(String[] userCommand) {
        return userCommand[0];
    }

    private int queryInteger(String[] userCommand) throws DukeException {
        if (isInteger(userCommand)) {
            return Integer.parseInt(userCommand[1]);
        } else {
            throw new DukeException("error Invalid formatting for commands");
        }
    }

    private static boolean isInteger(String[] userCommand) throws DukeException {
        if (userCommand.length != 2) {
            throw new DukeException("error Invalid formatting for commands");
        }
        return isInteger(userCommand[1],10);
    }

    private static boolean isInteger(String s, int radix) {
        if (s.isEmpty()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) {
                    return false;
                } else {
                    continue;
                }
            }
            if (Character.digit(s.charAt(i), radix) < 0) {
                return false;
            }
        }
        return true;
    }

    public Command parse(String[] userCommand) throws DukeException {
        String name;
        switch(this.readCommand(userCommand)) {

            case "todo":
                if (userCommand.length == 1) {
                    throw new DukeException("error no arguments");
                }
                return new TodoCommand(userCommand[1]);

            case "deadline":
                if (userCommand.length == 1) {
                    throw new DukeException("error no arguments");
                }
                String[] splitBy = userCommand[1].split(" /by ", 2);
                if (splitBy.length != 2) {
                    throw new DukeException("error Invalid formatting for commands");
                }
                name = splitBy[0];
                String by = splitBy[1];
                return new DeadlineCommand(name, by);

            case "event":
                if (userCommand.length == 1) {
                    throw new DukeException("error no arguments");
                }
                String[] splitFrom = userCommand[1].split(" /from ", 2);
                if (splitFrom.length != 2) {
                    throw new DukeException("error Invalid formatting for commands");
                }
                String[] splitTo = splitFrom[1].split(" /by ", 2);
                if (splitTo.length != 2) {
                    throw new DukeException("error Invalid formatting for commands");
                }
                name = splitFrom[0];
                String from = splitTo[0];
                String to = splitTo[1];
                return new EventCommand(name, from, to);

            case "mark":
                return new MarkCommand(this.queryInteger(userCommand), true);

            case "unmark":
                return new MarkCommand(this.queryInteger(userCommand), false);

            case "list":
                if (userCommand.length > 1) {
                    throw new DukeException("error Invalid formatting for commands");
                }
                return new ListCommand();

            case "bye":
                if (userCommand.length > 1) {
                    throw new DukeException("error Invalid formatting for commands");
                }
                return new ExitCommand();

            case "delete":
                return new DeleteCommand(this.queryInteger(userCommand));
        }
        throw new DukeException("Cannot recognise command!");
    }
}