package package1;

import java.sql.ResultSet;

public class Main {

    public static void main(String[] args) {
        DataSourceSwitcher dataSourceSwitcher = new DataSourceSwitcher();

        QueryExecutor queryExecutor = new QueryExecutor(dataSourceSwitcher);

        ResponseTimeMeasure responseTimeMeasure = new ResponseTimeMeasure();

        ProcedureExecutor procedureExecutor = new ProcedureExecutor(dataSourceSwitcher.getDataSource1Connection());

        // Execute a query on the first data source
        ResultSet resultSet1 = queryExecutor.executeQueryOnDataSource1("WAITFOR DELAY '00:00:15'; SELECT * FROM Customers");


        //execute procedure


        procedureExecutor.executeProcedure();
        // Execute a query on the second data source
//        ResultSet resultSet2 = databaseOperations.executeQueryOnDataSource2("SELECT * FROM table2");

        //Print result set
//        ResultSetPrinter.printResultSet(resultSet1);

        //Print metrics
        responseTimeMeasure.printMetrics();
    }
}

