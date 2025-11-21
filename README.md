# Social Networking Kata

Implement a console-based social networking application (similar to Bluesky) satisfying the scenarios below.

It's a classical Kata created by [Sandro Mancuso](https://github.com/sandromancuso/twitter-kata-java).

I've tweaked it to my liking.

## Scenarios

Note: Below, `=>` is the command prompt. Whatever is on the right of it is typed by the user.

**Posting**: Users can publish messages to a personal timeline

```
=> Alice -> I love the weather today
=> Bob -> Damn! We lost!
=> Bob -> Good game though.
```

**Reading**: Users can see others' timelines

```
=> Alice
Alice - I love the weather today
=> Bob
Bob - Good game though.
Bob - Damn! We lost!
```

**Following**: Users can subscribe to others' timelines, and view an aggregated list of all subscriptions

```
=> Charlie -> I'm in New York today! Anyone wants to have a coffee?
=> Charlie follows Alice
=> Charlie wall
Charlie - I'm in New York today! Anyone wants to have a coffee?
Alice - I love the weather today
=> Charlie follows Bob
=> Charlie wall
Charlie - I'm in New York today! Anyone wants to have a coffee?
Bob - Good game though.
Bob - Damn! We lost!
Alice - I love the weather today
```

## General requirements

- Application must use the console for input and output
- User submits commands to the application:
    - posting: `<user name> -> <message>`
    - reading: `<user name>`
    - following: `<user name> follows <another user>`
    - wall: `<user name> wall`
    - exit: `exit`
- Don't worry about handling any exceptions or invalid commands. 
- Assume that the user will always type the correct commands. 
- Just focus on the sunny day scenarios.

**NOTE:** All commands start with the username except `exit` that exits the application.

## Acceptance tests

Your will find acceptance tests with Cucumber in `src/test/resources`.
When these tests pass, your job is done.
You should not modify them.
They also represent a more detailed specification compared to what's described above.
Please read and run them to correctly understand the requirements.

## Configuration

You need Java 25 to run test and application.

### Tooling

## To check dependencies and plugins versions

`mvn versions:display-dependency-updates versions:display-plugin-updates`

## To update the license

`mvn validate license:format`

## To see JaCoCo, Spotbugs and Checkstyle reports

`mvn test site -Dmaven.test.failure.ignore=true`
