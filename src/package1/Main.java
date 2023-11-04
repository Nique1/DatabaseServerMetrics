package package1;

import package1.databaseConnection.DataSourceSwitcher;
import package1.efficencyMetrics.ResponseTimeMeasure;
import package1.efficencyMetrics.RowCounter;
import package1.databaseOperations.QueryExecutor;
import package1.databaseOperations.QueryResultPrinter;

import java.sql.ResultSet;

public class Main {

    public static void main(String[] args) {
        //connect to a database
        DataSourceSwitcher dataSourceSwitcher = new DataSourceSwitcher();

        //execute query
        QueryExecutor queryExecutor = new QueryExecutor(dataSourceSwitcher);

        ResponseTimeMeasure responseTimeMeasure = new ResponseTimeMeasure();

        RowCounter rowCounter = new RowCounter(dataSourceSwitcher.getDataSource1Connection());

        // Execute a query on the first data source
        ResultSet resultSet1 = queryExecutor.executeQueryOnDataSource1("SELECT * FROM Customers");



        // Execute a query on the second data source
//        ResultSet resultSet2 = databaseOperations.executeQueryOnDataSource2("SELECT * FROM table2");

        //Print result set
        QueryResultPrinter.printResultSet(resultSet1);


        //Print metrics
        responseTimeMeasure.measureDatabaseOperationEfficiency(resultSet1);

        //print number of rows

    }
}

