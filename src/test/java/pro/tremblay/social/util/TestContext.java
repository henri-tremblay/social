package pro.tremblay.social.util;

/**
 * Context for tests.
 */
public class TestContext {

    private ConsoleTestingDSL console;

    public TestContext(){
        console = ConsoleTestingDSL.start();
    }

    public ConsoleTestingDSL console() {
        return console;
    }
}
