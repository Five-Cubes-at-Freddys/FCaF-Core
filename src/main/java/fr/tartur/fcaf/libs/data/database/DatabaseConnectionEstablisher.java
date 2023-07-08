package fr.tartur.fcaf.libs.data.database;

import fr.tartur.fcaf.Core;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.logging.Level;

public class DatabaseConnectionEstablisher {

    private final FileConfiguration config;

    private final String service;
    private final String usersTableName;
    private final String dbName;

    private BaseDatabaseConnection usersDatabase;

    public DatabaseConnectionEstablisher(FileConfiguration config) {
        this.config = config;

        this.service = config.getString("database.service", BaseDatabaseConnection.SERVICE);
        this.usersTableName = config.getString("databse.users_table", BaseDatabaseConnection.USERS_TABLE_NAME);
        this.dbName = config.getString("database.name", BaseDatabaseConnection.DATABASE_NAME);
    }

    public void open() {
        if (service.equalsIgnoreCase("sqlite")) {
            connectToLocalDatabase(this.dbName, this.usersTableName);
        } else {
            connectToRemoteDatabase(this.dbName, this.service, this.usersTableName);
        }
    }

    private void connectToLocalDatabase(String dbName, String usersTableName) {
        String filePath = this.config.getCurrentPath() + "plugins/FCaF-Core/" + this.config.getString("database.sqlite_path", LocalDatabaseConnection.SQLITE_PATH);
        this.usersDatabase = new LocalDatabaseConnection(dbName, filePath, usersTableName);

        if (this.usersDatabase.open()) {
            Core.logger.log(Level.FINE, "The connection to the " + dbName + ".db database has successfully been " +
                    "established!");
        }
    }

    private void connectToRemoteDatabase(String dbName, String service, String usersTableName) {
        String host = this.config.getString("database.other.host");
        String user = this.config.getString("database.other.user");
        String pass = this.config.getString("database.other.pass");
        int port = this.config.getInt("database.other.port", -1);

        if (host == null || user == null || pass == null || port == -1) {
            Core.logger.log(Level.SEVERE, "The connection to the remote database could not be established " +
                    "because one or more of the information supplied is missing, of the wrong type or" +
                    "incorrect.\nTrying to establish a local SQLite database...");

            connectToLocalDatabase(dbName, usersTableName);
        } else {
            this.usersDatabase = new DatabaseConnection.Builder()
                    .database(dbName)
                    .service(service)
                    .usersTable(usersTableName)
                    .host(host)
                    .username(user)
                    .password(pass)
                    .port(port)
                    .build();
        }

        if (this.usersDatabase.open()) {
            Core.logger.log(Level.FINE, "The connection to the remote " + dbName + " database has successfully " +
                    "been established!");
        }
    }

    public BaseDatabaseConnection getUsersDatabase() {
        return this.usersDatabase;
    }
}
