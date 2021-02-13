/*
 * Copyright 2021-2021 the original author or authors.
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

import pro.tremblay.social.SocialConsole;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ConsoleTestingDSL {

	private static final String javaClasspath;
	private static final String javaExec;

	static {
		String javaHome = System.getProperty("java.home");
		javaExec = Paths.get(javaHome, "bin", "java").toString();
		javaClasspath = System.getProperty("java.class.path");
	}

	private static final String EXIT_COMMAND = "exit" + "\n";

	private final List<String> userCommands = new ArrayList<>();

	public static ConsoleTestingDSL start() {
		return new ConsoleTestingDSL();
	}

	public void sendUserCommand(String userCommand) {
		userCommands.add(userCommand + "\n");
	}

	public String retrieveOutput() {
		try {
			return normalizeLineSeparator(runSocialConsoleWith(userCommands));
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	private String normalizeLineSeparator(String message) {
		return message.replace(System.lineSeparator(), "\n");
	}

	private String runSocialConsoleWith(List<String> userCommands) throws IOException {
		Process process = execute(javaExec, "-cp", javaClasspath, SocialConsole.class.getName());

		sendUserCommandsToProcess(userCommands, process);

		return readOutputFrom(process);
	}

	private void sendUserCommandsToProcess(List<String> userCommands, Process process) {
		addExitCommandTo(userCommands);
		PrintWriter processWriter = writer(process);
		for(String userCommand: userCommands) {
			if (!isProcessAlive(process)) {
				throw new IllegalStateException("Application crashed with status " + process.exitValue());
			}
			processWriter.write(userCommand);
			processWriter.flush();
		}
	}

	private boolean isProcessAlive(Process process) {
		return process.isAlive();
	}

	private String readOutputFrom(Process process) throws IOException {
		BufferedReader processReader = reader(process);
		ByteArrayOutputStream bOut = new ByteArrayOutputStream();
		OutputStreamWriter out = new OutputStreamWriter(bOut);
		processReader.transferTo(out);
		out.flush();
		return bOut.toString();
	}

	private void addExitCommandTo(List<String> userCommands) {
		if (!userCommands.contains(EXIT_COMMAND)) {
			userCommands.add(EXIT_COMMAND);
		}
	}

	private PrintWriter writer(Process process) {
		return new PrintWriter( process.getOutputStream() );
	}

	private BufferedReader reader(Process process) {
		return new BufferedReader( new InputStreamReader(process.getInputStream()) );
	}

	private Process execute(String... command) throws IOException {
		return new ProcessBuilder(command).start();
	}

}
