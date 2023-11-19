package package1.efficencyMetrics;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DMVInfoToExcelExport {
    public void exportDMVToExcel(ResultSet resultSet, String filePath, String metricName) {
        Workbook workbook = null;

        try {
            workbook = (filePath.endsWith(".xlsx")) ? new XSSFWorkbook() : new HSSFWorkbook();
            Sheet sheet = workbook.createSheet(metricName);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            List<Object[]> rowData = new ArrayList<>();

            int rowNum = 0;
            for (Object[] row : rowData) {
                Row excelRow = sheet.createRow(rowNum++);
                for (int i = 0; i < columnCount; i++) {
                    Cell cell = excelRow.createCell(i);
                    if (row[i] != null) {
                        cell.setCellValue(row[i].toString());
                    }
                }
            }

            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
                System.out.println("Data appended to Excel successfully for metric: " + metricName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch ( SQLException e) {
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
}






