Feature: Posting and Reading

  In order to share my ideas with the world
  As a user
  I would like to post my messages so other people could read them

  Scenario: Reading posts from a user
    Only Alice's messages are displayed

    Given some messages are posted:
      | User  | Message                 |
      | Bob   | Hi, I'm Bob             |
      | Alice | Hello, my name is Alice |
      | Alice | It's a lovely day today |
    When someone reads messages of "Alice"
    Then the messages are displayed in reverse chronological order:
      """
      Start socializing
      Alice - It's a lovely day today
      Alice - Hello, my name is Alice
      bye!

      """
