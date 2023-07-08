package fr.tartur.fcaf.libs.data.database;

public enum DatabaseService {

    MYSQL("com.mysql.cj.jdbc.Driver"),
    POSTGRESQL("org.postgresql.Driver"),
    MARIADB("org.mariadb.jdbc.Driver");

    private final String driverPackage;

    DatabaseService(String driverPackage) {
        this.driverPackage = driverPackage;
    }

    public String getPackage() {
        return this.driverPackage;
    }

}
