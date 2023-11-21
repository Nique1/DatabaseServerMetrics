package package1.efficiencyMetrics;

import package1.databaseConnection.DataSourceSwitcher;

import java.sql.*;


public class DMVSnapshot {
    DataSourceSwitcher dataSourceSwitcher;

    public DMVSnapshot(){};

    public DMVSnapshot(DataSourceSwitcher dataSourceSwitcher) {
        this.dataSourceSwitcher = dataSourceSwitcher;
    }

    public ResultSet retrieveCPUInfo(){
        return executeQuery("SELECT * FROM sys.dm_os_performance_counters WHERE counter_name LIKE '%CPU%'");
    }

    public ResultSet retrieveDiskUsage() {
        return executeQuery("SELECT name AS DatabaseName, " +
                "type_desc AS FileType, " +
                "size * 8 AS TotalSizeKB, " +
                "fileproperty(name, 'SpaceUsed') * 8 AS UsedSpaceKB, " +
                "(size - fileproperty(name, 'SpaceUsed')) * 8 AS AvailableSpaceKB " +
                "FROM sys.database_files");
    }

    public ResultSet retrieveIOMetrics() {
        return executeQuery("SELECT * FROM sys.dm_io_virtual_file_stats(NULL, NULL) WHERE database_id > 4");
    }

    public ResultSet retrieveMemoryUsage(){
        return executeQuery("SELECT type, name, SUM(pages_kb) AS TotalMemoryUsedKB " +
                "FROM sys.dm_os_memory_clerks " +
                "GROUP BY type, name " +
                "ORDER BY TotalMemoryUsedKB DESC");
    }

    public ResultSet retrieveWaitTimes() {
        return executeQuery("SELECT wait_type, " +
                "waiting_tasks_count AS WaitCount, " +
                        "wait_time_ms AS TotalWaitTime_MS, " +
                        "max_wait_time_ms AS MaxWaitTime_MS, " +
                        "signal_wait_time_ms AS SignalWaitTime_MS " +
                        "FROM sys.dm_os_wait_stats " +
                        "WHERE wait_type NOT IN ('SLEEP_TASK', 'REQUEST_FOR_DEADLOCK_SEARCH') " +
                        "AND waiting_tasks_count > 0 " +
                        "ORDER BY wait_time_ms DESC");
    }



    public ResultSet executeQuery(String query){
        Connection conn = dataSourceSwitcher.getConnection();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet;
        }
         catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
