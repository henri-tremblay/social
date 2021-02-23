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

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pro.tremblay.social.util.ConsoleTestingDSL;

public class WallSteps {

	private final ConsoleTestingDSL console;

	public WallSteps(ConsoleTestingDSL console) {
		this.console = console;
	}

	@Given("{string} follows {string}")
	public void given_user_follows_other(String user, String followed) {
		console.sendUserCommand(user + " follows " + followed);
	}

	@When("{string} checks his wall")
	public void when_user_checks_his_wall(String user) {
		console.sendUserCommand(user + " wall");
	}

}
