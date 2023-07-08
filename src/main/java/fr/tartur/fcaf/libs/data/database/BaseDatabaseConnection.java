package fr.tartur.fcaf.libs.data.database;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class BaseDatabaseConnection {

    public static final String SERVICE = "sqlite";
    public static final String DATABASE_NAME = "minecraft_server";
    public static final String USERS_TABLE_NAME = "users";

    protected final String uri;
    protected final String dbName;
    protected final String usersTableName;

    protected Connection connection;

    public BaseDatabaseConnection(String uri, String dbName, String usersTableName) {
        this.uri = uri;
        this.dbName = dbName;
        this.usersTableName = usersTableName;
    }

    protected abstract void connect() throws SQLException;

    public boolean open() {
        try {
            connect();
            return true;
        } catch (SQLException e) {
            System.err.println("Une erreur est survenue lors de la tentative de connexion à la base de données " +
                    "d'adresse " + this.uri + ".\nPlus d'informations: " + e);
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

    public String getUsersTableName() {
        return this.usersTableName;
    }
}
