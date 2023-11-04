package package1.efficencyMetrics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RowCounter {
    private Connection connection;

    public RowCounter(Connection connection) {
        this.connection = connection;
    }

    public int getRecordCount() {
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery();

            // Przesuwamy się na koniec wyników, aby uzyskać liczbę rekordów
            resultSet.last();
            int rowCount = resultSet.getRow();

            // Przesuwamy się z powrotem na początek, jeśli planujesz przetwarzać wyniki
            resultSet.beforeFirst();

            resultSet.close();
            statement.close();

            return rowCount;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
