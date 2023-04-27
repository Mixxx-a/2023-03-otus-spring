package ru.sladkov.otus.spring.hw02.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.sladkov.otus.spring.hw02.exception.PrintException;
import ru.sladkov.otus.spring.hw02.exception.ScanException;
import ru.sladkov.otus.spring.hw02.service.IOService;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class IOServiceStreamsImpl implements IOService {

    private final Scanner input;

    private final PrintStream out;

    public IOServiceStreamsImpl(@Value("#{T(System).in}") InputStream input,
                                @Value("#{T(System).out}") OutputStream output) {
        this.input = new Scanner(input);
        this.out = new PrintStream(output);
    }

    @Override
    public void print(String string) throws PrintException {
        try {
            out.print(string);
        } catch (Exception e) {
            throw new PrintException("Exception during printing " + string, e);
        }
    }

    @Override
    public void println(String string) throws PrintException {
        try {
            out.println(string);
        } catch (Exception e) {
            throw new PrintException("Exception during printing " + string, e);
        }
    }

    @Override
    public void println() throws PrintException {
        try {
            out.println();
        } catch (Exception e) {
            throw new PrintException("Exception during printing new line", e);
        }
    }

    @Override
    public String scanNext() throws ScanException {
        String next;
        try {
            next = input.next();
        } catch (Exception e) {
            throw new ScanException("Exception during scanning", e);
        }
        return next;
    }
}
