package package1.databaseOperations;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import package1.databaseConnection.DataSourceSwitcher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryExecutor {
    private DataSourceSwitcher dataSourceSwitcher;

    public QueryExecutor(DataSourceSwitcher dataSourceSwitcher) {
        this.dataSourceSwitcher = dataSourceSwitcher;
    }

    public ResultSet executeQueryOnDataSource(String query) {
        Connection conn = dataSourceSwitcher.getConnection();
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            return statement.executeQuery();
        } catch (SQLServerException e) {
            System.err.println("Invalid query syntax. Try again ");
            System.exit(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
