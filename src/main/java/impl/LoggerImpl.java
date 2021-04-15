package impl;

import api.Logger;

public class LoggerImpl implements Logger {

    private String name;
    private String version;

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
        System.out.println(name + ", " + version + ": " + message);
    }
}
