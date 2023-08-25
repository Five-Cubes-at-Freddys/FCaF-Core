package fr.tartur.fcaf.libs.plugin.commands;

import fr.tartur.fcaf.libs.plugin.commands.data.CommandData;
import fr.tartur.fcaf.libs.plugin.commands.data.CommandUsage;
import fr.tartur.fcaf.libs.plugin.commands.data.TargetedCommandData;
import fr.tartur.fcaf.user.FPlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Class used to define more specific uses of a basic CommandExecutor.
 * Can contain subcommands in itself.
 *
 * @author tarturr
 * @see org.bukkit.command.CommandExecutor
 */
public abstract class BaseCommand implements TabExecutor {

    protected final String name;
    protected final String commandUsage;

    protected CommandData commandData;

    private BaseCommand correspondingCommand;

    /**
     * Default class constructor.
     *
     * @param name         This command name.
     * @param commandUsage This command usage.
     */
    public BaseCommand(String name, CommandUsage commandUsage) {
        this.name = name;

        this.commandData = null;
        commandUsage.setCommandName(name);
        this.commandUsage = commandUsage.toString();
    }

    /**
     * Default class constructor.
     *
     * @param name         This command name.
     */
    public BaseCommand(String name) {
        this.name = name;

        this.commandData = null;
        this.commandUsage = CommandUsage.DEFAULT_USAGE;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        this.resetCorrespondingCommand();
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        int length = args.length - 1;
        String commandName = args[length];

        if (length > 0) {
            Optional<BaseCommand> foundCommand = this.commandData.getCommandHolder().getCommands().stream()
                    .filter(baseCommand -> baseCommand.getName().equals(commandName))
                    .findAny();

            foundCommand.ifPresent(this::setCorrespondingCommand);
        }

        if (this.hasCorrespondingCommand()) {
            if (length < this.correspondingCommand.getData().getIndex() ||
                    length == this.correspondingCommand.getData().getIndex() && !commandName.equals(this.correspondingCommand.getName())) {
                this.resetCorrespondingCommand();
            } else {
                return this.correspondingCommand.onTabComplete(sender, command, label, args);
            }
        }

        List<BaseCommand> commands = this.commandData.getCommandHolder().getCommands().stream()
                .filter(targetedCommand -> targetedCommand.getData().getIndex() == length && targetedCommand.getName().startsWith(commandName))
                .toList();

        if (!commands.isEmpty()) {
            return commands.stream().map(BaseCommand::getName).toList();
        } else {
            return Bukkit.getOnlinePlayers().stream()
                    .map(Player::getName)
                    .toList();
        }
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

    public String getCommandUsage() {
        return commandUsage;
    }

    public BaseCommand setData(CommandData commandData) {
        this.commandData = commandData;
        return this;
    }

    // TODO: Split all specific data assignments into another specialized class.
    public void initData(int commandIndex, FPlayerManager playerManager) {
        if (!this.hasData()) {
            if (this instanceof TargetedCommandRunner targetedCommandRunner) {
                targetedCommandRunner.setData(new TargetedCommandData());

                if (commandIndex == targetedCommandRunner.getData().getPlayerIndex()) {
                    commandIndex++;
                }
            } else {
                this.setData(new CommandData());
            }
        }

        for (BaseCommand subCommand : this.getData().getCommandHolder().getCommands()) {
            subCommand.initData(commandIndex + 1, playerManager);
        }

        CommandData data = this.getData();
        data.setIndex(commandIndex);
        data.setPlayerManager(playerManager);
        this.setData(data);
    }

    public String getName() {
        return name;
    }

    public int getArgsStart() {
        return this.commandData.getIndex() + 1;
    }

    private boolean hasCorrespondingCommand() {
        return this.correspondingCommand != null;
    }

    public void setCorrespondingCommand(BaseCommand correspondingCommand) {
        this.correspondingCommand = correspondingCommand;
    }

    private void resetCorrespondingCommand() {
        if (this.hasCorrespondingCommand()) {
            this.correspondingCommand = null;
        }
    }

    public boolean hasCommandUsage() {
        return this.commandUsage.equals(CommandUsage.DEFAULT_USAGE);
    }
}
