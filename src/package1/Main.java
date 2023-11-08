package package1;

import package1.databaseConnection.DataSourceSwitcher;
import package1.efficencyMetrics.ResponseTimeMeasure;
import package1.databaseOperations.QueryExecutor;
import package1.databaseOperations.QueryResultPrinter;

import java.sql.ResultSet;
import java.util.Scanner;

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
                ResultSet resultSet = queryExecutor.executeQueryOnDataSource(query);
                QueryResultPrinter.printResultSet(resultSet);
                responseTimeMeasure.measureDatabaseOperationEfficiency(resultSet);
            }
            break;
        }

//        //Print result set

//        //start response time measure
//        ResponseTimeMeasure responseTimeMeasure = new ResponseTimeMeasure();
//
//
//
//        // Execute a query on the first data source
//        ResultSet resultSet1 = queryExecutor.executeQueryOnDataSource1("SELECT * FROM Customers");
//
//

//
//
//        //Print metrics
//        responseTimeMeasure.measureDatabaseOperationEfficiency(resultSet1);


        dataSourceSwitcher.closeConnection();

    }
}

