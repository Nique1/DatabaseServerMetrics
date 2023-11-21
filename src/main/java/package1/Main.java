package package1;

import package1.databaseConnection.DataSourceSwitcher;
import package1.databaseOperations.QueryExecutor;
import package1.databaseOperations.QueryResultPrinter;
import package1.efficiencyMetrics.*;
import package1.userInput.UserInput;

import java.sql.ResultSet;

public class Main {

    public static void main(String[] args) {
        UserInput userInput = new UserInput();
        String selectedDataSource = userInput.getUserInput("Choose database connection: 'local' or 'remote'");

        DataSourceSwitcher dataSourceSwitcher = new DataSourceSwitcher(selectedDataSource);

        QueryExecutor queryExecutor = new QueryExecutor(dataSourceSwitcher);

        DMVSnapshot dmvSnapshot = new DMVSnapshot(dataSourceSwitcher);
        EfficiencyMetricsExcelExport efficiencyMetricsExcelExport = new EfficiencyMetricsExcelExport();


        while (true) {

            String selectedQuery = userInput.getUserInput("Enter your query or type 'exit'");

            if (!"exit".equalsIgnoreCase(selectedQuery)) {

                ResponseTimeMeasure responseTimeMeasure = new ResponseTimeMeasure();
                responseTimeMeasure.startOperation();
                ResultSet resultSet = queryExecutor.executeQueryOnDataSource(selectedQuery);
                QueryResultPrinter.printResultSet(resultSet);
                responseTimeMeasure.endOperation();
                responseTimeMeasure.printMetrics();
                efficiencyMetricsExcelExport.exportResponseTimeToExcel
                        (responseTimeMeasure,
                        FilePaths.RESPONSE_TIME.getPath(),
                        "ResponseTimeMeasure",
                        selectedDataSource
                        );

                ResultSet cpuUsage = dmvSnapshot.retrieveCPUInfo();
                efficiencyMetricsExcelExport.exportDMVToExcel
                        (cpuUsage,
                        FilePaths.CPU_INFO.getPath(),
                        "CPU",
                        selectedDataSource
                         );

                ResultSet diskUsage = dmvSnapshot.retrieveDiskUsage();
                efficiencyMetricsExcelExport.exportDMVToExcel
                        (diskUsage,
                        FilePaths.DISK_USAGE.getPath(),
                        "DiskUsage",
                        selectedDataSource
                        );
                ResultSet ioMetrics = dmvSnapshot.retrieveIOMetrics();
                efficiencyMetricsExcelExport.exportDMVToExcel
                        (ioMetrics,
                        "C:/Users/domin/OneDrive/Pulpit/dbMetrics/metricsIO.xlsx",
                        "IO",
                        selectedDataSource
                        );

                ResultSet memoryUsage = dmvSnapshot.retrieveMemoryUsage();
                efficiencyMetricsExcelExport.exportDMVToExcel
                        (memoryUsage,
                        FilePaths.MEMORY_USAGE.getPath(),
                        "MemoryUsage",
                        selectedDataSource
                );

                ResultSet waitTime = dmvSnapshot.retrieveWaitTimes();
                efficiencyMetricsExcelExport.exportDMVToExcel
                        (waitTime,
                        FilePaths.WAIT_TIME.getPath(),
                        "WaitTime",
                        selectedDataSource
                        );
            }
            break;
        }
        dataSourceSwitcher.closeConnection();
        userInput.closeScanner();

    }
}

