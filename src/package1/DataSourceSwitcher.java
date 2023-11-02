package package1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceSwitcher {
    private Connection dataSource1Connection;
    private Connection dataSource2Connection;
    //constructor

    public DataSourceSwitcher() {
        try {
            String connectionStringDB1 = "jdbc:sqlserver://DESKTOP-0DCQVME;Database=Northwind;integratedSecurity=true;encrypt=true;trustServerCertificate=true";
            dataSource1Connection = DriverManager.getConnection(connectionStringDB1);

//            String connectionStringDB2 = "jdbc:postgresql://postgres:5432/user=postgres&password=domi306";
//
//
//            dataSource2Connection = DriverManager.getConnection(connectionStringDB2);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public Connection getDataSource1Connection() {
        return dataSource1Connection;
    }

    public Connection getDataSource2Connection() {
        return dataSource2Connection;
    }

    public void closeConnection(){
        try {
            if (dataSource1Connection != null) {
                dataSource1Connection.close();
            }
            if (dataSource2Connection != null) {
                dataSource2Connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
