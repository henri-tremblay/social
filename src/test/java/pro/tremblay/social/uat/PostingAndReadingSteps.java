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
package pro.tremblay.social.uat;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pro.tremblay.social.util.ConsoleTestingDSL;

import static org.assertj.core.api.Assertions.assertThat;

public class PostingAndReadingSteps {

	private ConsoleTestingDSL console;

	@Before
	public void initialise() {
		console = ConsoleTestingDSL.start();
	}

	@Given("^Alice posts a few messages$")
	public void alice_posts_a_few_messages() {
		console.sendUserCommand("Alice -> Hello, my name is Alice");
		console.sendUserCommand("Alice -> It's a lovely day today");
	}

	@When("^Bob reads Alice's messages$")
	public void bob_reads_Alice_s_messages() {
		console.sendUserCommand("Alice");
	}

	@Then("^Alice's messages are displayed in reverse chronological order$")
	public void alice_s_messages_are_displayed_in_reverse_chronological_order() {
		String output = console.retrieveOutput();
		assertThat(output).isEqualTo("Start socializing\nAlice - It's a lovely day today\nAlice - Hello, my name is Alice\nbye!\n");

	}
}