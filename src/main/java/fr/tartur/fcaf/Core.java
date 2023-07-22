package fr.tartur.fcaf;

import fr.tartur.fcaf.libs.plugincomponents.commands.CommandHolder;
import fr.tartur.fcaf.libs.plugincomponents.commands.data.TargetedCommandData;
import fr.tartur.fcaf.plugincomponents.commands.CommandRegisterer;
import fr.tartur.fcaf.plugincomponents.commands.FPlayerCommand;
import fr.tartur.fcaf.plugincomponents.commands.TestCommand;
import fr.tartur.fcaf.plugincomponents.commands.playerdata.AddCommand;
import fr.tartur.fcaf.plugincomponents.events.EventRegisterer;
import fr.tartur.fcaf.libs.data.database.BaseDatabaseConnection;
import fr.tartur.fcaf.libs.data.database.DatabaseConnectionEstablisher;
import fr.tartur.fcaf.user.FPlayerManager;
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

        FPlayerManager playerManager = new FPlayerManager(this.usersDatabase);

        CommandRegisterer commands = new CommandRegisterer(this, playerManager, new CommandHolder.Builder()
                .addCommand(new FPlayerCommand()
                        .setData(new TargetedCommandData(
                                new CommandHolder.Builder()
                                        .addCommand(new AddCommand())
                                        .build()
                        ))
                )
                .addCommand(new TestCommand())
                .build()
        );
        commands.registerAll();

        EventRegisterer events = new EventRegisterer(this, playerManager);
        events.registerAll();
    }

    @Override
    public void onDisable() {
        this.usersDatabase.close();
    }
}
