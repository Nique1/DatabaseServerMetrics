package package1.performanceMetrics;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.Instant;

@Getter
@Setter
public class ResponseTimeMeasure {
    private Instant startTime;
    private Instant endTime;

    public void startOperation() {
        startTime = Instant.now();
    }

    public void endOperation() {
        endTime = Instant.now();
    }

    public long getElapsedTime() {
        if (startTime != null && endTime != null) {
            return Duration.between(startTime,endTime).toMillis();
        }
        return -1;
    }

    public void printMetrics() {
        System.out.println("\nDatabase Operation Metrics:");
        System.out.println("Start Time: " + startTime);
        System.out.println("End Time: " + endTime);
        System.out.println("Elapsed Time: " + getElapsedTime() + " milliseconds");
        System.out.println();
    }

}
