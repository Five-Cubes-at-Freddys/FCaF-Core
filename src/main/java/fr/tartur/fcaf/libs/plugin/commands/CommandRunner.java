package fr.tartur.fcaf.libs.plugin.commands;

import fr.tartur.fcaf.libs.plugin.commands.data.CommandUsage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * Class used to define a simple command.
 * @author tarturr
 * @see BaseCommand
 * @see org.bukkit.command.CommandExecutor
 */
public abstract class CommandRunner extends BaseCommand {

    /**
     * Default class constructor.
     *
     * @param name         This command name.
     * @param commandUsage This command usage.
     */
    public CommandRunner(String name, CommandUsage commandUsage) {
        super(name, commandUsage);
    }

    /**
     * Default class constructor.
     *
     * @param name         This command name.
     */
    public CommandRunner(String name) {
        super(name);
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
        super.onCommand(sender, command, label, args);

        for (int i = getArgsStart(); i < args.length; i++) {
            for (BaseCommand baseCommand : super.getData().getCommandHolder().getCommands()) {
                String arg = args[i];

                if (baseCommand.getData().getIndex() == i && baseCommand.name.equalsIgnoreCase(arg)) {
                    return baseCommand.onCommand(sender, command, label, args);
                }
            }
        }

        if (!run(sender, tailFromCommandIndex(args))) {
            sender.sendMessage("§cTapez \"/aide\" pour obtenir de l'aide.");
            return false;
        }

        return true;
    }
}
