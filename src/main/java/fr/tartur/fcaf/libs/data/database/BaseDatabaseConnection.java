package fr.tartur.fcaf.libs.data.database;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class BaseDatabaseConnection {

    protected final String uri;
    protected final String dbName;
    protected final String usersTableName;
    protected final DatabaseService service;
    protected Connection connection;

    public BaseDatabaseConnection(String uri, String dbName, String usersTableName, DatabaseService service) {
        this.uri = uri;
        this.dbName = dbName;
        this.usersTableName = usersTableName;
        this.service = service;
    }

    protected abstract void connect() throws SQLException;

    public boolean open() {
        try {
            connect();
            return true;
        } catch (SQLException e) {
            System.err.println("Une erreur est survenue lors de la tentative de connexion à la base de données " +
                    "d'adresse " + this.uri + ": " + e.getMessage() + "\nPlus d'informations: " + e);
            return false;
        }
    }

    public Connection get() {
        return this.connection;
    }

    public void close() {
        if (this.connection == null) {
            return;
        }

        try {
            if (!this.connection.isClosed()) {
                this.connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDatabaseName() {
        return this.dbName;
    }

    public String getUsersTableName() {
        return usersTableName;
    }

    public DatabaseService getService() {
        return this.service;
    }
}
