package fr.tartur.fcaf.libs.plugincomponents.commands;

import fr.tartur.fcaf.user.FPlayerManager;
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

    protected final String[] names;
    protected final int index;
    protected final FPlayerManager players;
    protected final BaseCommand[] subCommands;

    /**
     * Default class constructor.
     *
     * @param names       All command names, separated by a comma, especially used for subcommands to manage every case.
     * @param argIndex    The index where the command is supposed to be located, starting from the parent command.
     *                    If value is -1 (or another negative number), it means that the current command is a parent command.
     * @param players     The connected fcaf players list.
     * @param subCommands The possible subcommands.
     */
    public BaseCommand(String names, int argIndex, FPlayerManager players, BaseCommand... subCommands) {
        this.names = names.split(",");
        this.index = argIndex;
        this.players = players;
        this.subCommands = subCommands;
    }

    /**
     * Class constructor with argIndex set to -1.
     * This constructor is used for parent commands.
     * @see #BaseCommand(String, int, FPlayerManager, BaseCommand...) 
     */
    public BaseCommand(String names, FPlayerManager players, BaseCommand... subCommands) {
        this(names, -1, players, subCommands);
    }

    /**
     * Returns the range of args data between currentIndex + 1 and args.length.
     * @param currentIndex The provided current index.
     * @param args         The string array from which to take the tail.
     * @return The new array.
     */
    protected String[] tail(int currentIndex, String[] args) {
        return args.length - currentIndex > 1
                ? Arrays.copyOfRange(args, currentIndex + 1, args.length)
                : new String[]{};
    }

    /**
     * The command to be runned if no subcommands provided by the player have been set or found.
     * @param sender The basic command sender.
     * @param label  The command label.
     * @param args   The args entered by the player.
     * @return true if the command was correctly executed, false otherwise.
     */
    protected abstract boolean run(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args);
}
