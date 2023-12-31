package fr.tartur.fcaf.libs.plugin.commands;

import fr.tartur.fcaf.common.log.MessageSender;
import fr.tartur.fcaf.common.log.MessageType;
import fr.tartur.fcaf.libs.plugin.commands.data.CommandUsage;
import fr.tartur.fcaf.libs.plugin.commands.data.TargetedCommandData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * Class used to define a command intended to act on a FPlayer.
 * @author tarturr
 * @see BaseCommand
 * @see org.bukkit.command.CommandExecutor
 */
public abstract class TargetedCommandRunner extends BaseCommand {

    protected TargetedCommandData commandData;

    /**
     * Default class constructor.
     *
     * @param name         This command name.
     * @param commandUsage This command usage.
     */
    public TargetedCommandRunner(String name, CommandUsage commandUsage) {
        super(name, commandUsage);
        this.commandData = (TargetedCommandData) super.commandData;
    }

    /**
     * Class constructor.
     * @param name This command name.
     */
    public TargetedCommandRunner(String name) {
        super(name);
        this.commandData = (TargetedCommandData) super.commandData;
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
        super.onCommand(sender, command, label, args);

        for (int i = getArgsStart(); i < args.length; i++) {
            String arg = args[i];

            if (i == this.commandData.getPlayerIndex() && !this.commandData.hasTarget()) {
                var foundPlayer = super.getData().getPlayerManager().findConnectedPlayer(arg);

                if (foundPlayer.isPresent()) {
                    this.commandData.setTarget(foundPlayer.get());
                    super.setData(this.commandData);

                    continue;
                } else {
                    MessageSender.tell(MessageType.ERROR, sender, "Le joueur �e" + arg, "n'a pas pu �tre trouv�");
                    return false;
                }
            }

            for (BaseCommand baseCommand : super.getData().getCommandHolder().getCommands()) {
                TargetedCommandRunner targetedCommand = (TargetedCommandRunner) baseCommand;

                if (i == targetedCommand.getData().getIndex() && targetedCommand.getName().equalsIgnoreCase(arg)) {
                    targetedCommand.getData().setTarget(this.commandData.getTarget());
                    return targetedCommand.onCommand(sender, command, arg, args);
                }
            }
        }

        if (!run(sender, tailFromCommandIndex(args))) {
            if (super.hasCommandUsage()) {
                MessageSender.tell(MessageType.ERROR, sender, "Erreur de l'utilisation de la commande. " +
                        "Usage correct : " + getCommandUsage());
            } else {
                sender.sendMessage("�cTapez \"/aide\" pour obtenir de l'aide.");
            }

            return false;
        }

        return true;
    }

    public TargetedCommandRunner setData(TargetedCommandData commandData) {
        super.setData(commandData);
        this.commandData = commandData;
        return this;
    }

    public TargetedCommandData getData() {
        return this.commandData;
    }
}
