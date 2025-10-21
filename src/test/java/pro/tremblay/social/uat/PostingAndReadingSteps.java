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
package pro.tremblay.social.uat;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pro.tremblay.social.util.ConsoleTestingDSL;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class PostingAndReadingSteps {

	private final ConsoleTestingDSL console;

	public PostingAndReadingSteps(ConsoleTestingDSL console) {
		this.console = console;
	}

	@Given("some messages are posted:")
	public void given_some_messages_are_posted(DataTable table) {
		List<Map<String, String>> rows = table.asMaps(String.class, String.class);
		for (Map<String, String> columns : rows) {
			console.sendUserCommand(columns.get("User") + " -> " + columns.get("Message"));
		}
	}

	@When("someone reads messages of {string}")
	public void when_someone_reads_messages_of_given_user(String user) {
		console.sendUserCommand(user);
	}

	@Then("^the messages are displayed in reverse chronological order:$")
	public void then_messages_are_displayed_in_reverse_chronological_order(String expected) {
		String output = console.retrieveOutput();
		assertThat(output).isEqualTo(expected);
	}
}