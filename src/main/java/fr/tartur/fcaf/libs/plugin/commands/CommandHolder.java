package fr.tartur.fcaf.libs.plugin.commands;

import java.util.ArrayList;
import java.util.List;

public class CommandHolder {

    private final List<BaseCommand> commands;
    private final boolean permissionEnabled;

    public CommandHolder(List<BaseCommand> commands, boolean permissionEnabled) {
        this.commands = commands;
        this.permissionEnabled = permissionEnabled;
    }

    public CommandHolder(boolean permissionEnabled) {
        this(new ArrayList<>(), permissionEnabled);
    }

    public CommandHolder() {
        this(false);
    }

    public static class Builder {
        private final List<BaseCommand> commands;
        private boolean permissionEnabled;

        public Builder() {
            this.commands = new ArrayList<>();
            this.permissionEnabled = false;
        }

        public Builder addCommand(BaseCommand command) {
            this.commands.add(command);
            return this;
        }

        public Builder setPermissionEnabled(boolean permissionEnabled) {
            this.permissionEnabled = permissionEnabled;
            return this;
        }

        public CommandHolder build() {
            return new CommandHolder(this.commands, this.permissionEnabled);
        }
    }

    public List<BaseCommand> getCommands() {
        return commands;
    }

    public boolean isPermissionEnabled() {
        return permissionEnabled;
    }
}
