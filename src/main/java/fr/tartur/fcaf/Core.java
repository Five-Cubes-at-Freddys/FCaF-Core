package fr.tartur.fcaf;

import fr.tartur.fcaf.events.EventRegisterer;
import fr.tartur.fcaf.libs.data.database.BaseDatabaseConnection;
import fr.tartur.fcaf.libs.data.database.DatabaseConnectionEstablisher;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Core extends JavaPlugin {

    private BaseDatabaseConnection usersDatabase;
    public static Logger logger;

    @Override
    public void onEnable() {
        logger = super.getLogger();
        saveDefaultConfig();

        DatabaseConnectionEstablisher establisher = new DatabaseConnectionEstablisher(this.getConfig());
        establisher.open();
        this.usersDatabase = establisher.getUsersDatabase();

        EventRegisterer events = new EventRegisterer(this, this.usersDatabase);
        events.registerAllEvents();
    }

    @Override
    public void onDisable() {
        this.usersDatabase.close();
    }
}
