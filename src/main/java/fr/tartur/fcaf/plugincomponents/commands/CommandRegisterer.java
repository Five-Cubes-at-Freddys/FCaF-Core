package fr.tartur.fcaf.plugincomponents.commands;

import fr.tartur.fcaf.Core;
import fr.tartur.fcaf.libs.plugincomponents.commands.*;
import fr.tartur.fcaf.libs.plugincomponents.commands.data.CommandData;
import fr.tartur.fcaf.libs.plugincomponents.commands.data.TargetedCommandData;
import fr.tartur.fcaf.plugincomponents.BaseRegisterer;
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
            assignData(command, -1); // Starting to -1 because parent commands are not considered as arguments
            Objects.requireNonNull(super.plugin.getCommand(command.getName())).setExecutor(command);
        });
    }

    // TODO: Split all specific data assignments into another specialized class.
    private void assignData(BaseCommand command, int commandIndex) {
        if (!command.hasData()) {
            if (command instanceof TargetedCommandRunner targetedCommandRunner) {
                targetedCommandRunner.setData(new TargetedCommandData());

                if (commandIndex == targetedCommandRunner.getData().getPlayerIndex()) {
                    commandIndex++;
                }
            } else {
                command.setData(new CommandData());
            }
        }

        for (BaseCommand subCommand : command.getData().getCommandHolder().getCommands()) {
            assignData(subCommand, commandIndex + 1);
        }

        CommandData data = command.getData();
        data.setIndex(commandIndex);
        data.setPlayerManager(super.playerManager);
        command.setData(data);
    }

}
