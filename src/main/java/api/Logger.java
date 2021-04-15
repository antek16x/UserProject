package api;


public interface Logger {
    void setName(String name);

    void setVersion(String version);

    void log(String message);
}
