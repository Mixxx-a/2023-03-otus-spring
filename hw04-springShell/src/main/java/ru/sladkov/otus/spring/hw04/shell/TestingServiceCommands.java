package ru.sladkov.otus.spring.hw04.shell;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.sladkov.otus.spring.hw04.model.Student;
import ru.sladkov.otus.spring.hw04.service.TestingService;

@ShellComponent
public class TestingServiceCommands {

    private final TestingService testingService;

    private Student student;

    public TestingServiceCommands(TestingService testingService) {
        this.testingService = testingService;
    }

    @ShellMethod(value = "Login command", key = {"l", "login"})
    public String login(@ShellOption String userName, @ShellOption String userSurname) {
        this.student = new Student(userName, userSurname);
        return "Hello, " + userName + " " + userSurname;
    }

    @ShellMethod(value = "Perform testing", key = {"run", "run-test"})
    @ShellMethodAvailability(value = "isTestingCommandAvailable")
    public String runTest() {
        testingService.performTesting(student);
        return "";
    }

    private Availability isTestingCommandAvailable() {
        return student == null ? Availability.unavailable("Please log in!") : Availability.available();
    }

}
