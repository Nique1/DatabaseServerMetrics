package package1.efficencyMetrics;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DMVSnapshotPrinter {
    public void printDMVInfo(String data, ResultSet resultSet) {
        System.out.println(data);
        //TODO wydzielic ten kod do jakiejs metody
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                System.out.print(metaData.getColumnName(i) + "\t");
            }
            System.out.println();

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + "\t");
                }
                System.out.println();
            }
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
