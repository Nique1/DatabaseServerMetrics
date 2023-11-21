package package1.efficencyMetrics;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import package1.databaseConnection.DataSourceSwitcher;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


public class DMVInfoToExcelExport {

    public void exportDMVToExcel(ResultSet resultSet, String filePath, String metricName, DataSourceSwitcher dataSourceSwitcher) {
        Workbook workbook = null;

        String updateFilePath = getUpdatedFilePath(filePath, dataSourceSwitcher, metricName);


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

    private String getUpdatedFilePath(String filePath, DataSourceSwitcher dataSourceSwitcher, String metricName) {
        if ("local".equalsIgnoreCase(String.valueOf(dataSourceSwitcher))) {
            return filePath.replace("metrics" + metricName + ".xlsx", "metrics" + metricName + "Local.xlsx");
        } else if ("remote".equalsIgnoreCase(String.valueOf(dataSourceSwitcher))) {
            return filePath.replace("metrics" + metricName + ".xlsx", "metrics" + metricName + "Remote.xlsx");
        } else {
            return filePath; // Use the original path if the switcher doesn't match
        }


    }
}






