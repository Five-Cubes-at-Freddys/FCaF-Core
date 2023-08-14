package fr.tartur.fcaf.libs.plugin.commands.data;

public class CommandUsageBuilder {

    private String commandName;
    private String options;
    private String description;

    public CommandUsageBuilder() {
        this.commandName = "<unknowncmd>";
    }

    public CommandUsageBuilder setCommandName(String commandName) {
        this.commandName = commandName;
        return this;
    }

    public CommandUsageBuilder setOptions(String options) {
        this.options = options;
        return this;
    }

    public CommandUsageBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public String build() {
        return "§e/§c" + this.commandName +
                (this.options     == null ? "" : " " + this.options) +
                (this.description == null ? "" : " §7: §f" + this.description + ".");
    }

}
