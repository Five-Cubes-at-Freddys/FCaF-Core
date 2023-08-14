package fr.tartur.fcaf.libs.plugin.commands.data;

public class CommandOptionsBuilder {
    private final StringBuilder options;

    public CommandOptionsBuilder() {
        this.options = new StringBuilder();
    }

    private CommandOptionsBuilder addOptions(char lhs, char rhs, String... options) {
        StringBuilder result = new StringBuilder();

        for (String opt : options) {
            result.append(opt);

            if (!options[options.length - 1].equals(opt)) {
                result.append(" §b| §e");
            }
        }

        if (this.options.length() != 0) {
            this.options.append(' ');
        }

        this.options.append(String.format("§b%c§e%s§b%c", lhs, result, rhs));
        return this;
    }

    public CommandOptionsBuilder addOptions(String... options) {
        return addOptions('{', '}', options);
    }

    public CommandOptionsBuilder addRequiredOptions(String... options) {
        return addOptions('<', '>', options);
    }

    public CommandOptionsBuilder addOptionalOptions(String... options) {
        return addOptions('[', ']', options);
    }

    public String build() {
        return this.options.toString();
    }
}
