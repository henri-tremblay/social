Feature: Start and stop

  Scenario: Terminate on exit command

    When the application receives an 'exit' command
    Then the application should terminate:
      """
      Start socializing
      bye!

      """
