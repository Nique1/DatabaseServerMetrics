package package1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        DataSourceSwitcher dataSourceSwitcher = new DataSourceSwitcher();

        DatabaseOperations databaseOperations = new DatabaseOperations(dataSourceSwitcher);

        // Execute a query on the first data source
        ResultSet resultSet1 = databaseOperations.executeQueryOnDataSource1("SELECT * FROM table1");

        // Execute a query on the second data source
        ResultSet resultSet2 = databaseOperations.executeQueryOnDataSource2("SELECT * FROM table2");

        // Process and work with the result sets
        // ...
    }
}

