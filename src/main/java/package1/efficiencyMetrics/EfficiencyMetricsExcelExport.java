package package1.efficiencyMetrics;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import package1.databaseConnection.DataSourceType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


public class EfficiencyMetricsExcelExport {

    public void exportDMVToExcel(ResultSet resultSet, String filePath, String metricName, String selectedDataSource) {
        Workbook workbook = null;

        String updateFilePath = getUpdatedFilePath(filePath, selectedDataSource, metricName);


        try {
            workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet(metricName);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();


            Row headerRow = sheet.createRow(0);
            for (int i = 1; i <= columnCount; i++) {
                headerRow.createCell(i - 1).setCellValue(metaData.getColumnName(i));
            }

            int rowNum = 1;
            while (resultSet.next()) {
                Row row = sheet.createRow(rowNum++);
                for (int i = 1; i <= columnCount; i++) {
                    Cell cell = row.createCell(i - 1);
                    Object value = resultSet.getObject(i);
                    if (value != null) {
                        cell.setCellValue(value.toString());
                    } else {
                        cell.setCellValue("");
                    }
                }
            }

            try (FileOutputStream fileOut = new FileOutputStream(updateFilePath)) {
                workbook.write(fileOut);
                System.out.println("Data appended to Excel successfully for metric: " + metricName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void exportResponseTimeToExcel(ResponseTimeMeasure responseTimeMeasure,
                                          String filePath, String metricName,
                                          String selectedDataSource) {
        Workbook workbook = null;
        String updateFilePath = getUpdatedFilePath(filePath, selectedDataSource, metricName);

        try {
            workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet(metricName);
            Row row = sheet.createRow(0);
            row.createCell(0).setCellValue("Database Operation Metrics:");

            row = sheet.createRow(1);
            row.createCell(0).setCellValue("Start Time:");
            row.createCell(1).setCellValue(responseTimeMeasure.getStartTime().toString());

            row = sheet.createRow(2);
            row.createCell(0).setCellValue("End Time:");
            row.createCell(1).setCellValue(responseTimeMeasure.getEndTime().toString());

            row = sheet.createRow(3);
            row.createCell(0).setCellValue("Elapsed Time (milliseconds):");
            row.createCell(1).setCellValue(responseTimeMeasure.getElapsedTime());

            try (FileOutputStream fileOut = new FileOutputStream(updateFilePath)) {
                workbook.write(fileOut);
                System.out.println("Data appended to Excel successfully for metric: " + metricName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String getUpdatedFilePath(String filePath, String selectedDataSource, String metricName) {
        String updatedFilePath = filePath;

        if (DataSourceType.LOCAL.name().equalsIgnoreCase(selectedDataSource)) {
            updatedFilePath = filePath.replace
                    ("metrics" + metricName + ".xlsx",
                            "metrics" + metricName + "_Local.xlsx"
                    );
        } else if (DataSourceType.REMOTE.name().equalsIgnoreCase(selectedDataSource)) {
            updatedFilePath = filePath.replace
                    ("metrics" + metricName + ".xlsx",
                            "metrics" + metricName + "_Remote.xlsx"
                    );
        }

        File file = new File(updatedFilePath);
        if (file.exists()) {
            int i = 1;
            String fileName = file.getName();
            String fileNameWithoutExt = fileName.substring(0, fileName.lastIndexOf('.'));
            String fileExt = fileName.substring(fileName.lastIndexOf('.'));

            while (file.exists()) {
                updatedFilePath = file.getParent() + File.separator + fileNameWithoutExt + i + fileExt;
                file = new File(updatedFilePath);
                i++;
            }
        }

        return updatedFilePath;
    }
}






