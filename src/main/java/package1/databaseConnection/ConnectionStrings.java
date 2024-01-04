package package1.databaseConnection;

import lombok.Getter;

@Getter
public enum ConnectionStrings {
    LOCAL_CONNECTION("jdbc:sqlserver://DESKTOP-0DCQVME;Database=Northwind;integratedSecurity=true;encrypt=true;trustServerCertificate=true"),
    REMOTE_CONNECTION("jdbc:sqlserver://34.116.152.32;Database=Northwind;user=sqlserver;password=Pusia.3708;encrypt=true;trustServerCertificate=true");

    private String connectionString;

    ConnectionStrings(String connectionString) {
        this.connectionString = connectionString;
    }

}
