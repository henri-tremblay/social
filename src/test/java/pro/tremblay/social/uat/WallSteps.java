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
import pro.tremblay.social.util.TestContext;

import static org.assertj.core.api.Assertions.assertThat;

public class WallSteps {

	TestContext testContext;

	public WallSteps(TestContext context) {
		testContext = context;
	}

	@Given("{string} follows {string}")
	public void given_user_follows_other(String user, String followed) {
		testContext.console()
				   .sendUserCommand(user + " follows " + followed);
	}

	@When("{string} checks his wall")
	public void when_user_checks_his_wall(String user) {
		testContext.console()
				   .sendUserCommand(user + " wall");
	}

}
