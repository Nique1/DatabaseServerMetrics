package package1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        DataSourceSwitcher dataSourceSwitcher = new DataSourceSwitcher();

        DatabaseOperations databaseOperations = new DatabaseOperations(dataSourceSwitcher);

        DatabaseOperationMetrics databaseOperationMetrics = new DatabaseOperationMetrics();

        // Execute a query on the first data source
        ResultSet resultSet1 = databaseOperations.executeQueryOnDataSource1("SELECT * FROM Customers");


        // Execute a query on the second data source
//        ResultSet resultSet2 = databaseOperations.executeQueryOnDataSource2("SELECT * FROM table2");

        //Print result set
        ResultSetPrinter.printResultSet(resultSet1);

        //Print metrics
        databaseOperationMetrics.printMetrics();
    }
}

