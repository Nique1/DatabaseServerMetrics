package package1.databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceSwitcher {
    private Connection connection;

    public DataSourceSwitcher(String dataSource) {
        try {
            if("local".equalsIgnoreCase(dataSource)){
                String connectionStringDB1 = "jdbc:sqlserver://DESKTOP-0DCQVME;Database=Northwind;integratedSecurity=true;encrypt=true;trustServerCertificate=true";
                this.connection = DriverManager.getConnection(connectionStringDB1);
            }else if("remote".equalsIgnoreCase(dataSource)){
//                String connectionStringDB2 = "jdbc:sqlserver://DESKTOP-0DCQVME;Database=Northwind;integratedSecurity=true;encrypt=true;trustServerCertificate=true";
//                connection = DriverManager.getConnection(connectionStringDB2);
            }else{
                System.out.println("No such connection");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.connection;
    }


    public void closeConnection(){
        try {
            if (connection != null) {
                this.connection.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
