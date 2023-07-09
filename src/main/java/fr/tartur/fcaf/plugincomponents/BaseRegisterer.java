package fr.tartur.fcaf.plugincomponents;

import fr.tartur.fcaf.Core;
import fr.tartur.fcaf.user.FPlayerManager;

public abstract class BaseRegisterer {

    protected final Core plugin;
    protected final FPlayerManager playerManager;

    public BaseRegisterer(Core plugin, FPlayerManager playerManager) {
        this.plugin = plugin;
        this.playerManager = playerManager;
    }

    public abstract void registerAll();

}
