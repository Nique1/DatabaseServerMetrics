package package1.databaseConnection;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceSwitcher {
    private Connection connection;

    public DataSourceSwitcher(String dataSource) {
        if ("local".equalsIgnoreCase(dataSource)) {
            String connectionStringDB1 = "jdbc:sqlserver://DESKTOP-0DCQVME;Database=Northwind;integratedSecurity=true;encrypt=true;trustServerCertificate=true";
            try {
                this.connection = DriverManager.getConnection(connectionStringDB1);
            } catch (SQLServerException e) {
                if (e.getMessage().contains("Connect timed out")) {
                    System.err.println("Database connection to host has failed. Try again");
                    System.exit(1);
                } else {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if ("remote".equalsIgnoreCase(dataSource)) {
            String connectionStringDB2 = "jdbc:sqlserver://34.116.152.32;Database=Northwind;user=sqlserver;password=Pusia.3708;encrypt=true;trustServerCertificate=true";
            try {
                this.connection = DriverManager.getConnection(connectionStringDB2);
            } catch (SQLServerException e) {
                if (e.getMessage().contains("Connect timed out")) {
                    System.err.println("Database connection to host has failed. Try again");
                    System.exit(1);
                } else {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No such connection. Try again");
            System.exit(1);
        }

    }

    public Connection getConnection() {
        return this.connection;
    }


    public void closeConnection() {
        try {
            if (connection != null) {
                this.connection.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
