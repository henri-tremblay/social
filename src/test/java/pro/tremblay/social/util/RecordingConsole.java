package pro.tremblay.social.util;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.assertj.core.api.Assertions.assertThat;

public class RecordingConsole implements Console {

    private final Deque<String> inputLines = new ArrayDeque<>();
    private final List<String> outputLines = new ArrayList<>();

    public RecordingConsole addLine(String line) {
        inputLines.addLast(line);
        return this;
    }

    @Override
    public String readline() {
        return inputLines.removeFirst();
    }

    @Override
    public void write(String output) {
        outputLines.add(output);
    }

    public void assertOutput(String... lines) {
        assertThat(outputLines).containsSequence(lines);
    }
}

