package package1;

import package1.databaseConnection.DataSourceSwitcher;
import package1.efficencyMetrics.DMVSnapshot;
import package1.efficencyMetrics.DMVSnapshotPrinter;
import package1.efficencyMetrics.ResponseTimeMeasure;
import package1.databaseOperations.QueryExecutor;
import package1.databaseOperations.QueryResultPrinter;

import java.sql.ResultSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        //TODO dodac obsluge bledów przy scannerze
        System.out.println("Choose database connection: 'local' or 'remote':");
        String selectedDataSource = scanner.nextLine();

        DataSourceSwitcher dataSourceSwitcher = new DataSourceSwitcher(selectedDataSource);

        QueryExecutor queryExecutor = new QueryExecutor(dataSourceSwitcher);

        DMVSnapshot dmvSnapshot = new DMVSnapshot(dataSourceSwitcher);
        DMVSnapshotPrinter dmvSnapshotPrinter = new DMVSnapshotPrinter();

        while (true) {
            System.out.println("Enter your query:");
            //TODO dodac obsluge bledow przy scannerze
            String query = scanner.nextLine();

            if (!"exit".equalsIgnoreCase(query)) {

                ResponseTimeMeasure responseTimeMeasure = new ResponseTimeMeasure();
                responseTimeMeasure.startOperation();
                ResultSet resultSet = queryExecutor.executeQueryOnDataSource(query);
                QueryResultPrinter.printResultSet(resultSet);
                responseTimeMeasure.endOperation();
                responseTimeMeasure.printMetrics();

                ResultSet cpuUsage = dmvSnapshot.retrieveCPUInfo();
                dmvSnapshotPrinter.printDMVInfo("CPU usage info", cpuUsage);

                ResultSet ioMetrics = dmvSnapshot.retrieveIOMetrics();
                dmvSnapshotPrinter.printDMVInfo("IO metrics info", ioMetrics);

                ResultSet memoryUSage = dmvSnapshot.retrieveMemoryUsage();
                dmvSnapshotPrinter.printDMVInfo("Memory usage info", memoryUSage);

                ResultSet waitTime = dmvSnapshot.retrieveWaitTimes();
                dmvSnapshotPrinter.printDMVInfo("Wait time info", waitTime);
            }
            break;
        }
        dataSourceSwitcher.closeConnection();

    }
}

