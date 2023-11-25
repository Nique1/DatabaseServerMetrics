package package1.databaseConnection;

public enum ConnectionStrings {
    LOCAL_CONNECTION("jdbc:sqlserver://YourDatabaseServerName;Database=YourDatabaseName;integratedSecurity=true;encrypt=true;trustServerCertificate=true"),
    REMOTE_CONNECTION("jdbc:sqlserver://YourDatabaseServerName;Database=YourDatabaseName;user=YourUsername;password=YourPassword;encrypt=true;trustServerCertificate=true");

    private final String connectionString;

    ConnectionStrings(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getConnectionString() {
        return connectionString;
    }
}
