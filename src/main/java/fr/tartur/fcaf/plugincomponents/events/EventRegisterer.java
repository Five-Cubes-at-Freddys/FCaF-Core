package fr.tartur.fcaf.plugincomponents.events;

import fr.tartur.fcaf.Core;
import fr.tartur.fcaf.plugincomponents.BaseRegisterer;
import fr.tartur.fcaf.user.FPlayerManager;
import org.bukkit.plugin.PluginManager;

public class EventRegisterer extends BaseRegisterer {

    public EventRegisterer(Core plugin, FPlayerManager playerManager) {
        super(plugin, playerManager);
    }

    @Override
    public void registerAll() {
        PluginManager manager = plugin.getServer().getPluginManager();
        manager.registerEvents(new JoinEvents(this.playerManager), plugin);
    }
}
