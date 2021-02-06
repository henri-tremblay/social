package pro.tremblay.social.uat;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pro.tremblay.social.ConsoleTestingDSL;

import static org.assertj.core.api.Assertions.assertThat;

public class StartAndStopSteps {

	private ConsoleTestingDSL console;

	@Before
	public void initialise() {
		console = ConsoleTestingDSL.start();
	}

	@Given("^the application receives an 'exit' command$")
	public void the_application_receives_an_exit_command() {
		console.sendUserCommand("some user command");
		console.sendUserCommand("exit");
	}

	@Then("^the application should terminate$")
	public void the_application_should_terminate() {
		String output = console.retrieveOutput();
		assertThat(output).isEqualTo("Start socializing\nbye!\n");
	}

}
