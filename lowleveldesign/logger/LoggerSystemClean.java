package lowleveldesign.logger;

// Chain of Responsibility pattern - if a type of request can't be handled by one handler, it can be passed to the next handler.

abstract class LoggerV1 {
    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;

    // Reference to the next logger in the chain
    protected LoggerV1 nextLogger;

    // Constructor to set the next logger in the chain
    protected LoggerV1(LoggerV1 nextLogger) {
        this.nextLogger = nextLogger;
    }

    // Method to log the message
    public void log(int logLevel, String message) {
        if (canHandle(logLevel)) {
            write(message); // Handle the message if this logger can handle it
        } else if (nextLogger != null) {
            nextLogger.log(logLevel, message); // Pass the log message to the next logger in the chain
        }
    }

    // Abstract methods to be implemented by subclasses
    protected abstract boolean canHandle(int logLevel);
    protected abstract void write(String message);
}

class InfoLoggerV1 extends LoggerV1 {

    // Constructor for InfoLoggerV1, which sets the next logger in the chain
    InfoLoggerV1(LoggerV1 nextLogger) {
        super(nextLogger);
    }

    // Check if this logger can handle INFO level messages
    @Override
    protected boolean canHandle(int logLevel) {
        return logLevel == INFO;
    }

    // Write the message for INFO level
    @Override
    protected void write(String message) {
        System.out.println("Handled by InfoLoggerV1: " + message);
    }
}

class DebugLoggerV1 extends LoggerV1 {

    // Constructor for DebugLoggerV1, which sets the next logger in the chain
    DebugLoggerV1(LoggerV1 nextLogger) {
        super(nextLogger);
    }

    // Check if this logger can handle DEBUG level messages
    @Override
    protected boolean canHandle(int logLevel) {
        return logLevel == DEBUG;
    }

    // Write the message for DEBUG level
    @Override
    protected void write(String message) {
        System.out.println("Handled by DebugLoggerV1: " + message);
    }
}

class ErrorLoggerV1 extends LoggerV1 {

    // Constructor for ErrorLoggerV1, which sets the next logger in the chain
    ErrorLoggerV1(LoggerV1 nextLogger) {
        super(nextLogger);
    }

    // Check if this logger can handle ERROR level messages
    @Override
    protected boolean canHandle(int logLevel) {
        return logLevel == ERROR;
    }

    // Write the message for ERROR level
    @Override
    protected void write(String message) {
        System.out.println("Handled by ErrorLoggerV1: " + message);
    }
}

public class LoggerSystemClean {
    public static void main(String[] args) {
        // Creating the chain of loggers: InfoLoggerV1 -> DebugLoggerV1 -> ErrorLoggerV1
        LoggerV1 logger = new InfoLoggerV1(new DebugLoggerV1(new ErrorLoggerV1(null)));

        // Logging messages of different levels
        logger.log(LoggerV1.INFO, "This is an info message.");
        logger.log(LoggerV1.DEBUG, "This is a debug message.");
        logger.log(LoggerV1.ERROR, "This is an error message.");
    }
}
