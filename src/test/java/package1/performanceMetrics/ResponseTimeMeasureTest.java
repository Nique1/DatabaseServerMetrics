package package1.performanceMetrics;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class ResponseTimeMeasureTest {

    @Test
    void testElapsedTimeWhenNotStarted(){
        //given
        //when
        ResponseTimeMeasure measure = new ResponseTimeMeasure();
        //then
        assertThat(measure.getElapsedTime()).isEqualTo(-1);
    }

    @Test
    void testElapsedTimeWhenStartedButNotEnded(){
        //given
        ResponseTimeMeasure measure = new ResponseTimeMeasure();
        //when
        measure.startOperation();
        //then
        assertThat(measure.getElapsedTime()).isEqualTo(-1);
    }

    @Test
    void testElapsedTimeWhenMeasurementCompleted(){
        //given
        ResponseTimeMeasure measure = new ResponseTimeMeasure();
        //when
        measure.startOperation();
        try{
            Thread.sleep(1000);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        measure.endOperation();
        //then
        assertThat(measure.getElapsedTime()).isGreaterThanOrEqualTo(1000);
    }

    @Test
    void testStartAndEndTime(){
        //given
        ResponseTimeMeasure measure = new ResponseTimeMeasure();
        //when
        Instant startTime = Instant.now();
        measure.startOperation();
        Instant endTime = Instant.now();
        measure.endOperation();
        //then
        assertThat(measure.getStartTime()).isEqualTo(startTime);
        assertThat(measure.getEndTime()).isEqualTo(endTime);
    }

    @Test
    void testPrintMetrics(){
        //given
        ResponseTimeMeasure measure = new ResponseTimeMeasure();
        //when
        measure.startOperation();
        //delay simulation
        try{
            Thread.sleep(1000);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
        measure.endOperation();

        // Redirect System.out for testing
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        measure.printMetrics();

        String consoleOutput = outContent.toString();
        //then
        assertThat(consoleOutput).contains("Start Time:");
        assertThat(consoleOutput).contains("End Time:");
        assertThat(consoleOutput).contains("Elapsed Time:");
    }
}