package fr.tartur.fcaf.plugin.commands;

import fr.tartur.fcaf.Core;
import fr.tartur.fcaf.libs.plugin.commands.CommandHolder;
import fr.tartur.fcaf.libs.plugin.commands.defaultcommands.HelpCommand;
import fr.tartur.fcaf.plugin.BaseRegisterer;
import fr.tartur.fcaf.user.FPlayerManager;

import java.util.Objects;

public class CommandRegisterer extends BaseRegisterer {

    private final CommandHolder holder;

    public CommandRegisterer(Core plugin, FPlayerManager playerManager, CommandHolder holder) {
        super(plugin, playerManager);
        this.holder = holder;
    }

    @Override
    public void registerAll() {
        this.holder.getCommands().forEach(command -> {
            command.initData(-1, super.playerManager); // Starting to -1 because parent commands are not considered as arguments
            Objects.requireNonNull(super.plugin.getCommand(command.getName())).setExecutor(command);
        });
    }

    public void registerHelpCommand(String commandName) {
        HelpCommand command = new HelpCommand(commandName, this.holder.getCommands());
        command.initData(-1, super.playerManager);
        this.holder.getCommands().add(command);
        Objects.requireNonNull(super.plugin.getCommand(commandName)).setExecutor(command);
    }

}
