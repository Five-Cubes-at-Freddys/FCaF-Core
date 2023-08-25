package fr.tartur.fcaf.libs.plugin.commands.data;

public class CommandUsage {

    public static final String DEFAULT_USAGE = "§cUndefined command usage.";

    private static final String DEFAULT_VALUE = "§7None";

    private String commandName;
    private final String options;
    private final String description;

    public CommandUsage(String options, String description) {
        this.options     = options;
        this.description = description;
    }

    public static class Builder {
        private String options;
        private String description;

        public Builder() {
            this.options     = DEFAULT_VALUE;
            this.description = DEFAULT_VALUE;
        }

        public Builder setOptions(CommandOptions options) {
            this.options = options.toString();
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public CommandUsage build() {
            return new CommandUsage(options, description);
        }

    }

    private boolean isNone(String elem) {
        return elem.equals(DEFAULT_VALUE);
    }

    @Override
    public String toString() {
        return "§e/§c" + this.commandName +
                (isNone(this.options)     ? "" : " " + this.options) +
                (isNone(this.description) ? "" : " §7: §f" + this.description + ".");
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }
}
