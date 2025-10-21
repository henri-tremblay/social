/*
 * Copyright 2021-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
        console = new Console();
        String message = console.readline();
        assertThat(message).isEqualTo("Hello world");
    }

    @Test
    void write() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        console = new Console();
        console.write("Hello world");
        assertThat(out.toString()).isEqualTo("Hello world" + System.lineSeparator());
    }
}