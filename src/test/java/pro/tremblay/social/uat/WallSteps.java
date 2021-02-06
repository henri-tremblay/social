package pro.tremblay.social.uat;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pro.tremblay.social.ConsoleTestingDSL;

import static org.assertj.core.api.Assertions.assertThat;

public class WallSteps {

	private ConsoleTestingDSL console;

	@Before
	public void initialise() {
		console = ConsoleTestingDSL.start();
	}

	@Given("^Bob posts a few messages$")
	public void bob_posts_a_few_messages() {
		console.sendUserCommand("Bob -> Hi, I'm Bob");
		console.sendUserCommand("Bob -> It's a lovely day today");
	}

	@Given("^Charlie posts a few messages$")
	public void charlie_posts_a_few_messages() {
		console.sendUserCommand("Charlie -> Hi, I'm Charlie");
		console.sendUserCommand("Charlie -> I'm in London today.");
	}

	@Given("^Charlie follows Bob$")
	public void charlie_follows_Bob() {
		console.sendUserCommand("Charlie follows Bob");
	}

	@When("^Charlie checks his wall$")
	public void charlie_checks_his_wall() {
		console.sendUserCommand("Charlie wall");
	}

	@Then("^messages from Bob and Charlie are displayed in reverse chronological order$")
	public void messages_from_Bob_and_Charlie_are_displayed_in_reverse_chronological_order() {
		String output = console.retrieveOutput();
		assertThat(output).isEqualTo("Start socializing\nCharlie - I'm in London today.\nCharlie - Hi, I'm Charlie\nBob - It's a lovely day today\nBob - Hi, I'm Bob\nbye!\n");
	}


}
