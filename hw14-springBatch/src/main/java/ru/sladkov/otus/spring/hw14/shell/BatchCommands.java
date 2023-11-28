package ru.sladkov.otus.spring.hw14.shell;

import org.springframework.batch.core.launch.JobOperator;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Properties;

@SuppressWarnings("unused")
@ShellComponent
public class BatchCommands {

    private final JobOperator jobOperator;

    public BatchCommands(JobOperator jobOperator) {
        this.jobOperator = jobOperator;
    }

    @ShellMethod(value = "migrateLibrary", key = "migrate")
    public void migrateLibrary() throws Exception {
        Long executionId = jobOperator.start("MigrateLibraryJob", new Properties());
        System.out.println(jobOperator.getSummary(executionId));
    }


}
