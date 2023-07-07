package fr.tartur.fcaf.libs.data.database;

import java.sql.DriverManager;
import java.sql.SQLException;

public class LocalDatabaseConnection extends BaseDatabaseConnection {

    public LocalDatabaseConnection(String dbName, String dbFilePath, String usersTableName) {
        super("jdbc:sqlite:" + dbFilePath + dbName + ".db", dbName, usersTableName, DatabaseService.SQLITE);
    }

    @Override
    protected void connect() throws SQLException {
        this.connection = DriverManager.getConnection(this.uri);
    }

}
