package fr.tartur.fcaf.libs.data.database;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection extends BaseDatabaseConnection {

    private final String user;
    private final String pass;

    public static class Builder {

        private DatabaseService service;
        private String host;
        private int port;
        private String dbName;
        private String usersTableName;
        private String user;
        private String pass;

        public Builder() {
            this.service = DatabaseService.MYSQL;
            this.host = "localhost";
            this.dbName = "undefined";
            this.user = "root";
            this.pass = "";
            this.port = 3306;
        }

        public Builder service(String serviceName) {
            boolean found = false;

            for (DatabaseService service : DatabaseService.values()) {
                if (service.name().equalsIgnoreCase(serviceName)) {
                    this.service = service;
                    found = true;
                }
            }

            if (!found) {
                System.err.println("The database service \"" + serviceName + "\" could not be found. Please verify it.");
            }

            return this;
        }

        public Builder host(String host) {
            this.host = host;
            return this;
        }

        public Builder port(int port) {
            this.port = port;
            return this;
        }

        public Builder database(String dbName) {
            this.dbName = dbName;
            return this;
        }

        public Builder usersTable(String tableName) {
            this.usersTableName = tableName;
            return this;
        }

        public Builder username(String user) {
            this.user = user;
            return this;
        }

        public Builder password(String pass) {
            this.pass = pass;
            return this;
        }

        public DatabaseConnection build() {
            return new DatabaseConnection(this.service, this.host, this.port, this.dbName, this.usersTableName, this.user, this.pass);
        }

    }

    public DatabaseConnection(DatabaseService service, String host, int port, String dbName, String usersTableName, String user, String pass) {
        super("jdbc:" + service.name().toLowerCase() + "://" + host + (port >= 0 ? ":" + port : "") + "/" + dbName, dbName, usersTableName);

        this.user = user;
        this.pass = pass;

        try {
            Class.forName(service.getPackage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void connect() throws SQLException {
        this.connection = DriverManager.getConnection(this.uri, this.user, this.pass);
    }
}
