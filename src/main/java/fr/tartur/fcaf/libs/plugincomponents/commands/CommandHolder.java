package fr.tartur.fcaf.libs.plugincomponents.commands;

import java.util.ArrayList;
import java.util.List;

public class CommandHolder {

    private final List<BaseCommand> commands;

    public CommandHolder(List<BaseCommand> commands) {
        this.commands = commands;
    }

    public CommandHolder() {
        this(new ArrayList<>());
    }

    public static class Builder {
        private final List<BaseCommand> commands;

        public Builder() {
            this.commands = new ArrayList<>();
        }

        public Builder addCommand(BaseCommand command) {
            this.commands.add(command);
            return this;
        }

        public CommandHolder build() {
            return new CommandHolder(this.commands);
        }
    }

    public List<BaseCommand> getCommands() {
        return commands;
    }

}
