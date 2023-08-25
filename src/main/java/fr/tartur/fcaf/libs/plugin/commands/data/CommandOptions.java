package fr.tartur.fcaf.libs.plugin.commands.data;

public class CommandOptions {

    private final String options;

    public CommandOptions(String options) {
        this.options = options;
    }

    public static class Builder {
        private final StringBuilder options;

        public Builder() {
            this.options = new StringBuilder();
        }

        private Builder addOptions(char lhs, char rhs, String... options) {
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

        public Builder addOptions(String... options) {
            return addOptions('{', '}', options);
        }

        public Builder addOptions(CommandOptions options) {
            return addOptions('{', '}', options.toString());
        }

        public Builder addRequiredOptions(String... options) {
            return addOptions('<', '>', options);
        }

        public Builder addRequiredOptions(CommandOptions options) {
            return addOptions('<', '>', options.toString());
        }

        public Builder addOptionalOptions(String... options) {
            return addOptions('[', ']', options);
        }

        public Builder addOptionalOptions(CommandOptions options) {
            return addOptions('[', ']', options.toString());
        }

        public CommandOptions build() {
            return new CommandOptions(this.options.toString());
        }

    }

    @Override
    public String toString() {
        return this.options;
    }

}