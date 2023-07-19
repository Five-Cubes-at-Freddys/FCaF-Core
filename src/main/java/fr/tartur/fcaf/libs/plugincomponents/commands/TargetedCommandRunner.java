package fr.tartur.fcaf.libs.plugincomponents.commands;

import fr.tartur.fcaf.user.FPlayer;
import fr.tartur.fcaf.user.FPlayerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * Class used to define a command intended to act on a FPlayer.
 * @author tarturr
 * @see fr.tartur.fcaf.libs.plugincomponents.commands.BaseCommand
 * @see org.bukkit.command.CommandExecutor
 */
public abstract class TargetedCommandRunner extends BaseCommand {

    protected final TargetedCommandRunner[] subCommands;
    protected final int playerNameIndex;

    protected FPlayer target;

    /**
     * Class constructor.
     * @param names           All command names, separated by a comma, especially used for subcommands to manage every case.
     * @param argIndex        The index where the command is supposed to be. If value is -1 (or another negative number), it means that the current command is a parent command.
     * @param playerNameIndex The index where the player name is supposed to be. The value cannot be negative. If target is already set, this field is useless.
     * @param players         The connected fcaf players list.
     * @param subCommands     The possible subcommands.
     */
    public TargetedCommandRunner(String names, int argIndex, int playerNameIndex, FPlayerManager players, BaseCommand... subCommands) {
        super(names, argIndex, players, subCommands);
        this.subCommands = new TargetedCommandRunner[super.subCommands.length];

        if (playerNameIndex < 0) {
            throw new IllegalArgumentException("The player name index cannot be negative.");
        }

        this.playerNameIndex = playerNameIndex;

        for (int i = 0; i < super.subCommands.length; i++) {
            if (!(super.subCommands[i] instanceof TargetedCommandRunner targetedCommandRunner)) {
                throw new IllegalArgumentException("Only targeted subcommands can be created for a targeted parent command.");
            }

            this.subCommands[i] = targetedCommandRunner;
        }
    }

    /**
     * Class constructor with playerNameIndex set to 0.
     * This constructor is used for targeted subcommands.
     * @see #TargetedCommandRunner(String, int, int, FPlayerManager, BaseCommand...)
     */
    public TargetedCommandRunner(String names, int argIndex, FPlayerManager players, BaseCommand... subCommands) {
        this(names, argIndex, 0, players, subCommands);
    }

    /**
     * TargetedCommandRunner implementation of onCommand() method.<br><br>
     *
     * This method will try to look for the targeted fcaf player and assign it to this.target if it is null.<br>
     * If the player was not found, the command is cancelled with a message to the command sender.<br><br>
     *
     * It will also look for a possible subcommand, and execute it if the index and name match these subcommand properties.
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used, in lowercase
     * @param args    Passed command arguments
     * @return true if the command was correctly executed, otherwise false.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            if (target == null && i == playerNameIndex) {
                var foundPlayer = super.players.findConnectedPlayer(arg);

                if (foundPlayer.isPresent()) {
                    target = foundPlayer.get();
                    continue;
                } else {
                    sender.sendMessage("§8[§4FCaF§8] §cLe joueur §e" + arg + "§c n'a pas pu être trouvé.");
                    return false;
                }
            }

            for (TargetedCommandRunner targetedCommand : this.subCommands) {
                if (i == targetedCommand.index && Arrays.stream(targetedCommand.names).anyMatch(name -> name.equalsIgnoreCase(arg))) {
                    targetedCommand.setTarget(target);
                    return targetedCommand.onCommand(sender, command, arg.toLowerCase(), tail(i, args));
                }
            }
        }

        return run(sender, label, args);
    }

    /**
     * This method assigns the new target to this command.
     * @param target The new command target.
     */
    protected void setTarget(FPlayer target) {
        this.target = target;
    }
}
