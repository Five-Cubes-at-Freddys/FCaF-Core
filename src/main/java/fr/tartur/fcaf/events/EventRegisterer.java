package fr.tartur.fcaf.events;

import fr.tartur.fcaf.Core;
import fr.tartur.fcaf.libs.data.database.BaseDatabaseConnection;
import fr.tartur.fcaf.user.FPlayerManager;
import org.bukkit.plugin.PluginManager;

public class EventRegisterer {

    private final Core plugin;
    private final FPlayerManager playerManager;

    public EventRegisterer(Core plugin, BaseDatabaseConnection usersDatabase) {
        this.plugin = plugin;
        this.playerManager = new FPlayerManager(usersDatabase);
    }

    public void registerAllEvents() {
        PluginManager manager = plugin.getServer().getPluginManager();
        manager.registerEvents(new JoinEvents(this.playerManager), plugin);
    }

}
