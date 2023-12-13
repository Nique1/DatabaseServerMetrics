package package1;

import package1.databaseConnection.DataSourceSwitcher;
import package1.databaseOperations.QueryExecutor;
import package1.databaseOperations.QueryResultPrinter;
import package1.performanceMetrics.*;
import package1.userInput.UserInput;


import java.sql.ResultSet;

public class Main {

    public static void main(String[] args) {
        UserInput userInput = new UserInput();

        String selectedDataSource = userInput.getUserInput("Choose database connection: 'local' or 'remote' or type 'exit'");

        DataSourceSwitcher dataSourceSwitcher = new DataSourceSwitcher(selectedDataSource);

        QueryExecutor queryExecutor = new QueryExecutor(dataSourceSwitcher);

        QueryResultPrinter queryResultPrinter = new QueryResultPrinter();
        PerformanceMetrics performanceMetrics = new PerformanceMetrics(dataSourceSwitcher);
        PerformanceMetricsExcelExport performanceMetricsExcelExport = new PerformanceMetricsExcelExport();


        while (true) {

            String selectedQuery = userInput.getUserInput("Enter your query or type 'exit'");
            String printResult = userInput.getUserInput("Do you want to print result to the console? Type 'yes' or 'no'");
            String takeMeasurements = userInput.getUserInput("Do you want to measure database server efficiency? Type 'yes' or 'no'");

            if (!"exit".equalsIgnoreCase(selectedQuery)) {

                ResponseTimeMeasure responseTimeMeasure = new ResponseTimeMeasure();
                responseTimeMeasure.startOperation();
                ResultSet resultSet = queryExecutor.executeQueryOnDataSource(selectedQuery);

                if(!"no".equalsIgnoreCase(printResult)){
                    System.out.println("Printing");
                    queryResultPrinter.printResultSet(resultSet);
                }

                if(!"no".equalsIgnoreCase(takeMeasurements)){
                    responseTimeMeasure.endOperation();
                    responseTimeMeasure.printMetrics();
                    performanceMetricsExcelExport.exportResponseTimeToExcel
                            (responseTimeMeasure,
                                    FilePaths.RESPONSE_TIME.getPath(),
                                    "ResponseTimeMeasure",
                                    selectedDataSource
                            );

                    ResultSet cpuUsage = performanceMetrics.retrieveCPUInfo();
                    performanceMetricsExcelExport.exportMetricsToExcel
                            (cpuUsage,
                                    FilePaths.CPU_INFO.getPath(),
                                    "CPU",
                                    selectedDataSource
                            );

                    ResultSet diskUsage = performanceMetrics.retrieveDiskUsage();
                    performanceMetricsExcelExport.exportMetricsToExcel
                            (diskUsage,
                                    FilePaths.DISK_USAGE.getPath(),
                                    "DiskUsage",
                                    selectedDataSource
                            );

                    ResultSet memoryUsage = performanceMetrics.retrieveMemoryUsage();
                    performanceMetricsExcelExport.exportMetricsToExcel
                            (memoryUsage,
                                    FilePaths.MEMORY_USAGE.getPath(),
                                    "MemoryUsage",
                                    selectedDataSource
                            );

                }

            }
            System.err.println("Application has ended its execution.");
            break;
        }
        dataSourceSwitcher.closeConnection();
        userInput.closeScanner();

    }
}

