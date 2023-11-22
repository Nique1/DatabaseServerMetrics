package package1.efficiencyMetrics;


import java.sql.Timestamp;

public class ResponseTimeMeasure {
    private Timestamp startTime;
    private Timestamp endTime;

    public void startOperation() {
        startTime = new Timestamp(System.currentTimeMillis());
    }

    public void endOperation() {
        endTime = new Timestamp(System.currentTimeMillis());
    }

    public long getElapsedTime() {
        if (startTime != null && endTime != null) {
            return endTime.getTime() - startTime.getTime();
        }
        return -1;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }


    public void printMetrics() {
        System.out.println("\nDatabase Operation Metrics:");
        System.out.println("Start Time: " + startTime);
        System.out.println("End Time: " + endTime);
        System.out.println("Elapsed Time: " + getElapsedTime() + " nanoseconds");
        System.out.println();
    }


}
