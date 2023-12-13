package package1.databaseConnection;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Getter
public class DataSourceSwitcher {

    private Connection connection;

    public DataSourceSwitcher(String dataSource) {

        if (DataSourceType.LOCAL.name().equalsIgnoreCase(dataSource)) {

            try {
                this.connection = DriverManager.getConnection(ConnectionStrings.LOCAL_CONNECTION.getConnectionString());
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
        } else if (DataSourceType.REMOTE.name().equalsIgnoreCase(dataSource)) {
            try {
                this.connection = DriverManager.getConnection(ConnectionStrings.REMOTE_CONNECTION.getConnectionString());
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
        }else if(dataSource.equalsIgnoreCase("exit")){
            System.err.println("Application has ended its execution");
            System.exit(1);
        }
        else {
            System.err.println("No such connection. Try again");
            System.exit(1);
        }

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
