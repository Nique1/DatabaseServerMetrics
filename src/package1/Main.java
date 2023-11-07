package package1;

import package1.databaseConnection.DataSourceSwitcher;
import package1.efficencyMetrics.ResponseTimeMeasure;
import package1.databaseOperations.QueryExecutor;
import package1.databaseOperations.QueryResultPrinter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.SortedMap;

public class Main {

    public static void main(String[] args) {
        //connect to a database

        //choose database connection
        Scanner scanner = new Scanner(System.in);
        //TODO dodac obsluge bledów przy scannerze
        System.out.println("Choose database connection: 'local' or 'remote':");
        String selectedDataSource = scanner.nextLine();

        DataSourceSwitcher dataSourceSwitcher = new DataSourceSwitcher(selectedDataSource);


        //execute query
        QueryExecutor queryExecutor = new QueryExecutor(dataSourceSwitcher);

        while(true){
            System.out.println("Enter your query:");
            //TODO dodac obsluge bledow przy scannerze
            String query = scanner.nextLine();

            //start measuring
            if (!"exit".equalsIgnoreCase(query)) {
                ResponseTimeMeasure responseTimeMeasure = new ResponseTimeMeasure();
                //executeQuery
                ResultSet resultSet = queryExecutor.executeQueryOnDataSource1(query);
                responseTimeMeasure.measureDatabaseOperationEfficiency(resultSet);
            }
            break;
        }



//        //start response time measure
//        ResponseTimeMeasure responseTimeMeasure = new ResponseTimeMeasure();
//
//
//
//        // Execute a query on the first data source
//        ResultSet resultSet1 = queryExecutor.executeQueryOnDataSource1("SELECT * FROM Customers");
//
//
//        //Print result set
//        QueryResultPrinter.printResultSet(resultSet1);
//
//
//        //Print metrics
//        responseTimeMeasure.measureDatabaseOperationEfficiency(resultSet1);


        dataSourceSwitcher.closeConnection();

    }
}

