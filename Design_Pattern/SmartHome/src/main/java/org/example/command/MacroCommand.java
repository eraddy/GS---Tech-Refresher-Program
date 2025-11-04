package org.example.command;

import java.util.ArrayList;
import java.util.List;

public class MacroCommand implements Command{
    private final List<Command> commands;
    public MacroCommand(List<Command> commands) { this.commands = new ArrayList<>(commands); }
    @Override public void execute() { commands.forEach(Command::execute); }
}
