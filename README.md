# Social Networking Kata

Implement a console-based social networking application (similar to Twitter) satisfying the scenarios below.

It's a classical Kata created by [Sandro Mancuso](https://github.com/sandromancuso/twitter-kata-java).

I've tweaked it to my liking.

## Scenarios

Note: `=>` is the command prompt. Whatever is on the right it typed by the user.

**Posting**: Alice can publish messages to a personal timeline

> => Alice -> I love the weather today    
> => Bob -> Damn! We lost!  
> => Bob -> Good game though.

**Reading**: Bob can view Alice’s timeline

> => Alice
> I love the weather today    
> => Bob
> Good game though.     
> Damn! We lost!

**Following**: Charlie can subscribe to Alice’s and Bob’s timelines, and view an aggregated list of all subscriptions

> => Charlie -> I'm in New York today! Anyone wants to have a coffee?     
> => Charlie follows Alice    
> => Charlie wall
> Charlie - I'm in New York today! Anyone wants to have a coffee?    
> Alice - I love the weather today

> => Charlie follows Bob
> => Charlie wall
> Charlie - I'm in New York today! Anyone wants to have a coffee?     
> Bob - Good game though.
> Bob - Damn! We lost!     
> Alice - I love the weather today

## General requirements

- Application must use the console for input and output
- User submits commands to the application:
    - posting: \<user name> -> \<message>
    - reading: \<user name>
    - following: \<user name> follows \<another user>
    - wall: \<user name> wall
- Don't worry about handling any exceptions or invalid commands. 
- Assume that the user will always type the correct commands. 
- Just focus on the sunny day scenarios.

**NOTE:** "posting:", "reading:", "following:" and "wall:" are not part of the command. All commands start with the user name.

## Configuration

You need Java 11 to run test and application.

### Tooling

## To check dependencies and plugins versions

`mvn versions:display-dependency-updates versions:display-plugin-updates`

## To update the license

`mvn validate license:format`
