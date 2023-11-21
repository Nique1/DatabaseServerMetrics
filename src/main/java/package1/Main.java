package package1;

import package1.databaseConnection.DataSourceSwitcher;
import package1.databaseOperations.QueryExecutor;
import package1.databaseOperations.QueryResultPrinter;
import package1.efficencyMetrics.DMVInfoToExcelExport;
import package1.efficencyMetrics.DMVSnapshot;
import package1.efficencyMetrics.DMVSnapshotPrinter;
import package1.efficencyMetrics.ResponseTimeMeasure;
import package1.userInput.UserInput;

import java.sql.ResultSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        UserInput userInput = new UserInput();
        String selectedDataSource = userInput.getUserInput("Choose database connection: 'local' or 'remote'");

        DataSourceSwitcher dataSourceSwitcher = new DataSourceSwitcher(selectedDataSource);

        QueryExecutor queryExecutor = new QueryExecutor(dataSourceSwitcher);

        DMVSnapshot dmvSnapshot = new DMVSnapshot(dataSourceSwitcher);
        DMVSnapshotPrinter dmvSnapshotPrinter = new DMVSnapshotPrinter();
        DMVInfoToExcelExport dmvInfoToExcelExport = new DMVInfoToExcelExport();

        while (true) {

            String selectedQuery = userInput.getUserInput("Enter your query or type 'exit'");

            if (!"exit".equalsIgnoreCase(selectedQuery)) {

                ResponseTimeMeasure responseTimeMeasure = new ResponseTimeMeasure();
                responseTimeMeasure.startOperation();
                ResultSet resultSet = queryExecutor.executeQueryOnDataSource(selectedQuery);
                QueryResultPrinter.printResultSet(resultSet);
                responseTimeMeasure.endOperation();
                responseTimeMeasure.printMetrics();

                ResultSet cpuUsage = dmvSnapshot.retrieveCPUInfo();
                dmvInfoToExcelExport.exportDMVToExcel(cpuUsage, "C:/Users/domin/OneDrive/Pulpit/dbMetrics/metricsCPU.xlsx", "CPU", userInput);
                dmvSnapshotPrinter.printDMVInfo("CPU usage info", cpuUsage);

                ResultSet diskUsage = dmvSnapshot.retrieveDiskUsage();
                dmvInfoToExcelExport.exportDMVToExcel(diskUsage, "C:/Users/domin/OneDrive/Pulpit/dbMetrics/metricsDiskUsage.xlsx", "DiskUsage", userInput);
                dmvSnapshotPrinter.printDMVInfo("Disk usage info", diskUsage);

                ResultSet ioMetrics = dmvSnapshot.retrieveIOMetrics();
                dmvInfoToExcelExport.exportDMVToExcel(ioMetrics, "C:/Users/domin/OneDrive/Pulpit/dbMetrics/metricsIO.xlsx", "IO", userInput);
                dmvSnapshotPrinter.printDMVInfo("IO metrics info", ioMetrics);

                ResultSet memoryUsage = dmvSnapshot.retrieveMemoryUsage();
                dmvInfoToExcelExport.exportDMVToExcel(memoryUsage, "C:/Users/domin/OneDrive/Pulpit/dbMetrics/metricsMemoryUsage.xlsx", "MemoryUsage", userInput);
                dmvSnapshotPrinter.printDMVInfo("Memory usage info", memoryUsage);

                ResultSet waitTime = dmvSnapshot.retrieveWaitTimes();
                dmvInfoToExcelExport.exportDMVToExcel(waitTime, "C:/Users/domin/OneDrive/Pulpit/dbMetrics/metricsWaitTime.xlsx", "WaitTime", userInput);
                dmvSnapshotPrinter.printDMVInfo("Wait time info", waitTime);


            }
            break;
        }
        dataSourceSwitcher.closeConnection();

    }
}

