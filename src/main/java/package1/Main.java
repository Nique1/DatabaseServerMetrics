package package1;

import package1.databaseConnection.DataSourceSwitcher;
import package1.efficencyMetrics.DMVInfoToExcelExport;
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

        DMVInfoToExcelExport dmvInfoToExcelExport = new DMVInfoToExcelExport();

        while (true) {
            System.out.println("Enter your query or type 'exit'");
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
                dmvInfoToExcelExport.exportDMVToExcel(cpuUsage, "C:/Users/domin/OneDrive/Pulpit/dbMetrics/metricsCPU.xlsx", "CPUUsage");
                dmvSnapshotPrinter.printDMVInfo("CPU usage info", cpuUsage);

                ResultSet diskUsage = dmvSnapshot.retrieveDiskUsage();
                dmvInfoToExcelExport.exportDMVToExcel(diskUsage, "C:/Users/domin/OneDrive/Pulpit/dbMetrics/metricsDisk.xlsx", "DiskUsage");
                dmvSnapshotPrinter.printDMVInfo("Disk usage info", diskUsage);

                ResultSet ioMetrics = dmvSnapshot.retrieveIOMetrics();
                dmvInfoToExcelExport.exportDMVToExcel(ioMetrics, "C:/Users/domin/OneDrive/Pulpit/dbMetrics/metricsIO.xlsx", "IOMetrics");
                dmvSnapshotPrinter.printDMVInfo("IO metrics info", ioMetrics);

                ResultSet memoryUsage = dmvSnapshot.retrieveMemoryUsage();
                dmvInfoToExcelExport.exportDMVToExcel(memoryUsage, "C:/Users/domin/OneDrive/Pulpit/dbMetrics/metricsMemory.xlsx", "MemoryUsage");
                dmvSnapshotPrinter.printDMVInfo("Memory usage info", memoryUsage);

                ResultSet waitTime = dmvSnapshot.retrieveWaitTimes();
                dmvInfoToExcelExport.exportDMVToExcel(waitTime, "C:/Users/domin/OneDrive/Pulpit/dbMetrics/metricsWaitTime.xlsx", "waitTime");
                dmvSnapshotPrinter.printDMVInfo("Wait time info", waitTime);






            }
            break;
        }
        dataSourceSwitcher.closeConnection();

    }
}

