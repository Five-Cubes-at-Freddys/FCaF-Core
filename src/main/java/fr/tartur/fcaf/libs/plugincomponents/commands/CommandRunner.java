package fr.tartur.fcaf.libs.plugincomponents.commands;

import fr.tartur.fcaf.user.FPlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public abstract class CommandRunner extends BaseCommand {

    public CommandRunner(String names, int argIndex, FPlayerManager players, BaseCommand... subCommands) {
        super(names, argIndex, players, subCommands);
    }

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

        return run(sender, args);
    }

    protected abstract boolean run(@NotNull CommandSender sender, @NotNull String[] args);
}
