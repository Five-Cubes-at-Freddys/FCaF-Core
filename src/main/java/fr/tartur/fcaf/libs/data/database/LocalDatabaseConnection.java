package fr.tartur.fcaf.libs.data.database;

import java.io.File;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LocalDatabaseConnection extends BaseDatabaseConnection {

    public static final String SQLITE_PATH = "database/";

    private final String dbFilePath;

    public LocalDatabaseConnection(String dbName, String dbFilePath, String usersTableName) {
        super("jdbc:sqlite:" + dbFilePath + dbName + ".db", dbName, usersTableName);

        this.dbFilePath = dbFilePath;
    }

    @Override
    protected void connect() throws SQLException {
        File pathFolder = new File(this.dbFilePath);

        if (!pathFolder.exists()) {
            if (!pathFolder.mkdirs()) {
                System.err.println("An error occurred while creating parent folders for " + super.dbName + ".db file.");
            }
        }

        this.connection = DriverManager.getConnection(this.uri);

        PreparedStatement usersTableCreation = this.connection.prepareStatement("""
                CREATE TABLE IF NOT EXISTS %s (
                    uuid text NOT NULL PRIMARY KEY,
                    pseudo varchar(16) NOT NULL UNIQUE,
                    fazcoins int,
                    fazbadges int,
                    experience int,
                    lastconnexion datetime,
                    firstconnexion datetime
                );""".formatted(super.usersTableName));

        usersTableCreation.executeUpdate();
    }

}
