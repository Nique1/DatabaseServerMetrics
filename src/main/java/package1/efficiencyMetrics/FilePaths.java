package package1.efficiencyMetrics;

public enum FilePaths {
    RESPONSE_TIME("YourPath"),
    CPU_USAGE("YourPath"),
    DISK_USAGE("YourPath"),
    MEMORY_USAGE("YourPath");

    private final String path;

    FilePaths(String filePaths) {
        this.path = filePaths;
    }

    public String getPath() {
        return path;
    }
}
