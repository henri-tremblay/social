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

import java.nio.charset.Charset;
import java.util.Scanner;

public final class Console {

    private final Scanner scanner;

    public Console() {
        scanner = new Scanner(System.in, Charset.defaultCharset());
    }

    public String readline() {
        return scanner.nextLine();
    }

    public void write(String output) {
        System.out.println(output);
    }
}
