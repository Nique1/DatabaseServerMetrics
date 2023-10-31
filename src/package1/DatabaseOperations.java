package package1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseOperations {
    private DataSourceSwitcher dataSourceSwitcher;

    public DatabaseOperations(DataSourceSwitcher dataSourceSwitcher) {
        this.dataSourceSwitcher = dataSourceSwitcher;
    }

    public ResultSet executeQueryOnDataSource1(String query) {
        Connection conn = dataSourceSwitcher.getDataSource1Connection();
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        }
    }

    public ResultSet executeQueryOnDataSource2(String query) {
        Connection conn = dataSourceSwitcher.getDataSource2Connection();
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
