package package1.efficiencyMetrics;

public enum FilePaths {
    RESPONSE_TIME("C:/Users/domin/OneDrive/Pulpit/dbMetrics/metricsResponseTimeMeasure.xlsx"),
    CPU_INFO("C:/Users/domin/OneDrive/Pulpit/dbMetrics/metricsCPU.xlsx"),
    DISK_USAGE("C:/Users/domin/OneDrive/Pulpit/dbMetrics/metricsDiskUsage.xlsx"),
    MEMORY_USAGE("C:/Users/domin/OneDrive/Pulpit/dbMetrics/metricsMemoryUsage.xlsx");

    private final String path;

    FilePaths(String filePaths) {
        this.path = filePaths;
    }

    public String getPath() {
        return path;
    }
}
