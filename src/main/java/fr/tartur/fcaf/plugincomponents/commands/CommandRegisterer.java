package fr.tartur.fcaf.plugincomponents.commands;

import fr.tartur.fcaf.Core;
import fr.tartur.fcaf.plugincomponents.BaseRegisterer;
import fr.tartur.fcaf.user.FPlayerManager;

import java.util.Objects;

public class CommandRegisterer extends BaseRegisterer {

    public CommandRegisterer(Core plugin, FPlayerManager playerManager) {
        super(plugin, playerManager);
    }

    @Override
    public void registerAll() {
        Objects.requireNonNull(super.plugin.getCommand("fplayer")).setExecutor(new FPlayerCommand(super.playerManager));
    }
}
