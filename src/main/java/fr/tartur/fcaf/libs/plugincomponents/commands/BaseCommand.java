package fr.tartur.fcaf.libs.plugincomponents.commands;

import fr.tartur.fcaf.libs.plugincomponents.commands.data.CommandData;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * Class used to define more specific uses of a basic CommandExecutor.
 * Can contain subcommands in itself.
 *
 * @author tarturr
 * @see org.bukkit.command.CommandExecutor
 */
public abstract class BaseCommand implements CommandExecutor {

    protected final String name;

    protected CommandData commandData;

    /**
     * Default class constructor.
     *
     * @param name        This command name.
     */
    public BaseCommand(String name) {
        this.name = name;

        this.commandData = null;
    }

    /**
     * The command to be runned if no subcommands provided by the player have been set or found.
     * @param sender The basic command sender.
     * @param args   The args entered by the player.
     * @return true if the command was correctly executed, false otherwise.
     */
    protected abstract boolean run(@NotNull CommandSender sender, @NotNull String[] args);

    /**
     * Returns the range of args data between currentIndex + 1 and args.length.
     * @param args         The string array from which to take the tail.
     * @return The new array.
     */
    protected String[] tailFromCommandIndex(String[] args) {
        return args.length - this.commandData.getIndex() > 1
                ? Arrays.copyOfRange(args, getArgsStart(), args.length)
                : new String[]{};
    }

    public boolean hasData() {
        return commandData != null;
    }

    public CommandData getData() {
        return commandData;
    }

    public BaseCommand setData(CommandData commandData) {
        this.commandData = commandData;
        return this;
    }

    public String getName() {
        return name;
    }

    public int getArgsStart() {
        return this.commandData.getIndex() + 1;
    }
}
