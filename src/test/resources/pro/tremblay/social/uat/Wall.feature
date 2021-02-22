Feature: User's wall

  In order to see what all my friends are up to
  As a user
  I would like to follow them and see all their messages

  Scenario: User sees messages from friends on their own wall - one friend
    If Charlie follows Bob, on Charlie's wall there are messages from Bob and Charlie

    Given some messages are posted:
      | User    | Message                 |
      | Alice   | Hello, my name is Alice |
      | Bob     | Hi, I'm Bob             |
      | Bob     | It's a lovely day today |
      | Charlie | Hi, I'm Charlie         |
      | Charlie | I'm in London today.    |
    And "Charlie" follows "Bob"
    When "Charlie" checks his wall
    Then the messages are displayed in reverse chronological order:
      """
      Start socializing
      Charlie - I'm in London today.
      Charlie - Hi, I'm Charlie
      Bob - It's a lovely day today
      Bob - Hi, I'm Bob
      bye!

      """

  Scenario: User sees messages from friends on their own wall - two friends
    If Charlie follows Bob and Alice, on Charlie's wall there are messages from Bob, Alice and Charlie

    Given some messages are posted:
      | User    | Message                 |
      | Alice   | Hello, my name is Alice |
      | Bob     | Hi, I'm Bob             |
      | Bob     | It's a lovely day today |
      | Charlie | Hi, I'm Charlie         |
      | Charlie | I'm in London today.    |
    And "Charlie" follows "Bob"
    And "Charlie" follows "Alice"
    When "Charlie" checks his wall
    Then the messages are displayed in reverse chronological order:
      """
      Start socializing
      Charlie - I'm in London today.
      Charlie - Hi, I'm Charlie
      Bob - It's a lovely day today
      Bob - Hi, I'm Bob
      Alice - Hello, my name is Alice
      bye!

      """
