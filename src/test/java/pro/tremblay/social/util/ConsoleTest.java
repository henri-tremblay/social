package pro.tremblay.social.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

class ConsoleTest {

    private Console console;
    private InputStream sin;
    private PrintStream sout;

    @BeforeEach
    void setUp() {
        sin = System.in;
        sout = System.out;
    }

    @AfterEach
    void tearDown() {
        System.setIn(sin);
        System.setOut(sout);
    }

    @Test
    void readline() {
        System.setIn(new ByteArrayInputStream("Hello world".getBytes()));
        console = new SystemConsole();
        String message = console.readline();
        assertThat(message).isEqualTo("Hello world");
    }

    @Test
    void write() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        console = new SystemConsole();
        console.write("Hello world");
        assertThat(out.toString()).isEqualTo("Hello world" + System.lineSeparator());
    }
}