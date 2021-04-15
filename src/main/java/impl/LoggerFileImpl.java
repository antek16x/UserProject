package impl;

import api.Logger;

import java.io.*;

public class LoggerFileImpl implements Logger {
    private String name;
    private String version;
    private String fileName;
    private PrintWriter printWriter;

    public LoggerFileImpl(String fileName) {
        try {
            printWriter = new PrintWriter(new FileWriter(fileName, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public void log(String message) {
        printWriter.append(name).append(", ").append(version).append(": ").append(message).append("\n");
        printWriter.flush();
    }
}
