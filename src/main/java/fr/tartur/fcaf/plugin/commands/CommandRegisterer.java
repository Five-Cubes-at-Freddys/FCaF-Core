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
            command.initData(super.playerManager, this.holder.isPermissionEnabled());
            Objects.requireNonNull(super.plugin.getCommand(command.getName())).setExecutor(command);
        });
    }

    public void registerHelpCommand(String commandName) {
        HelpCommand command = new HelpCommand(commandName, this.holder.getCommands());
        command.initData(super.playerManager, false);
        this.holder.getCommands().add(command);
        Objects.requireNonNull(super.plugin.getCommand(commandName)).setExecutor(command);
    }

}
