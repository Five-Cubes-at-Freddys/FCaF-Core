package fr.tartur.fcaf.libs.plugincomponents.commands;

import fr.tartur.fcaf.user.FPlayerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * Class used to define a simple command.
 * @author tarturr
 * @see fr.tartur.fcaf.libs.plugincomponents.commands.BaseCommand
 * @see org.bukkit.command.CommandExecutor
 */
public abstract class CommandRunner extends BaseCommand {

    /**
     * Class constructor.
     * @param names       All command names, separated by a comma, especially used for subcommands to manage every case.
     * @param argIndex    The index where the command is supposed to be. If value is -1 (or another negative number), it means that the current command is a parent command.
     * @param players     The connected fcaf players list.
     * @param subCommands The possible subcommands.
     */
    public CommandRunner(String names, int argIndex, FPlayerManager players, BaseCommand... subCommands) {
        super(names, argIndex, players, subCommands);
    }

    /**
     * CommandRunner implementation of onCommand() method.<br><br>
     *
     * It will look for a possible subcommand, and execute it if the index and name match these subcommand properties.
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used, in lowercase
     * @param args    Passed command arguments
     * @return true if the command was correctly executed, otherwise false.
     */
    @Override
    public final boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        for (int i = 0; i < args.length; i++) {
            for (BaseCommand baseCommand : subCommands) {
                String arg = args[i];

                if (Arrays.stream(baseCommand.names).anyMatch(name -> name.equalsIgnoreCase(arg))
                        && baseCommand.index == i) {
                    return baseCommand.onCommand(sender, command, label, tail(i, args));
                }
            }
        }

        return run(sender, label, args);
    }
}
