# My Kotlin Practices

Details:
- Java 17
- Kotlin 1.7.21
- Gradle 7.4.2
  - Kotlin DSL

## How to build

To build the whole project:
```
    gradle build
```
To build the specific practice:
```
    gradle practiceName:build
```

## How to run

To run the practice:
```
    gradle practiceName:run
```
`gradle practiceName:run -q --console=plain` may be useful as well, as it hides the Gradle output and makes the use of a program easier.