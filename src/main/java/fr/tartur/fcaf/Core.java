package fr.tartur.fcaf;

import fr.tartur.fcaf.plugincomponents.commands.CommandRegisterer;
import fr.tartur.fcaf.plugincomponents.events.EventRegisterer;
import fr.tartur.fcaf.libs.data.database.BaseDatabaseConnection;
import fr.tartur.fcaf.libs.data.database.DatabaseConnectionEstablisher;
import fr.tartur.fcaf.user.FPlayerManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Core extends JavaPlugin {

    private BaseDatabaseConnection usersDatabase;
    private FPlayerManager playerManager;
    public static Logger logger;

    @Override
    public void onEnable() {
        logger = super.getLogger();
        saveDefaultConfig();

        DatabaseConnectionEstablisher establisher = new DatabaseConnectionEstablisher(this.getConfig());
        establisher.open();
        this.usersDatabase = establisher.getUsersDatabase();

        this.playerManager = new FPlayerManager(this.usersDatabase);

        EventRegisterer events = new EventRegisterer(this, this.playerManager);
        events.registerAll();
        CommandRegisterer commands = new CommandRegisterer(this, this.playerManager);
        commands.registerAll();
    }

    @Override
    public void onDisable() {
        this.usersDatabase.close();
    }
}
