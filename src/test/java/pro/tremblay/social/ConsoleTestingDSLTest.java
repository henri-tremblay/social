package pro.tremblay.social;

import org.junit.Test;

public class ConsoleTestingDSLTest {

    @Test
    public void test() {
        ConsoleTestingDSL test = ConsoleTestingDSL.start();
        test.retrieveOutput();
    }
}
