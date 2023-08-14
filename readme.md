# My Kotlin Practices

Details:
- Java 17
- Kotlin 1.9.0
- Ktor 2.3.3
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

Alternatively, build the project using `gradle build`, then use `gradle installDist`, and after the task has been done, the executable files can be found at `practiceName/build/install/bin`. They can be run with simple `./practiceName`.

To stop the web server, open [http://localhost:8080/shutdown](http://localhost:8080/shutdown) directory.