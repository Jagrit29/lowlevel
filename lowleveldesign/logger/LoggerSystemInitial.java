package lowleveldesign.logger;

// chain of responsibility - if a type of request can't be handled by 1, it can be handled by the nextHandler.
// So basically our abstract class should be able to handle the flow or the passage

abstract class Logger {
    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;

    // now this logger should be able to move to nextLogger to if the current can't handle it;
    Logger nextLogger;

    // if constructor of this is called, It will go to next. This constructor will come into play when the subclass is called;
    Logger(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void log(int logLevel, String message) {
        if(nextLogger != null) {
            nextLogger.log(logLevel, message); // pass it to next;
        }
    }
}

class InfoLogger extends Logger {

    // now this is constructor of the subclass, When this is called, We will call the parent class and set the next logger whichever is passed. so we need to pass the nextlogger for info
    InfoLogger(Logger nextLogger) {
        super(nextLogger);
    }

    // now there might a case we don't ened nextLogger;
    public void log(int logLevel, String message) {
        if(logLevel == INFO) {
            // this means it can handle here only;
            System.out.println("Handled by Log level"+" "+ message);
        } else {
            // now it can't be handled by InfoLogger so I will rollback to my parent class which can be done by calling super and then using it's log;
            super.log(logLevel, message);
        }
    }
}

class DebugLogger extends Logger {

    // now this is constructor of the subclass, When this is called, We will call the parent class and set the next logger whichever is passed. so we need to pass the nextlogger for info
    DebugLogger(Logger nextLogger) {
        super(nextLogger);
    }

    // now there might a case we don't ened nextLogger;
    public void log(int logLevel, String message) {
        if(logLevel == DEBUG) {
            // this means it can handle here only;
            System.out.println("Handled by Debug level"+" "+ message);
        } else {
            // now it can't be handled by InfoLogger so I will rollback to my parent class which can be done by calling super and then using it's log;
            super.log(logLevel, message);
        }
    }
}

public class LoggerSystemInitial {
    public static void main(String args[]) {
        // first I need a logger object of info only;
        Logger logger = new InfoLogger(new DebugLogger(null)); // now here what I have done is I created a instant of info logger only but depending the logmessage it can roll to next one;

        // now here comes the advantage, I have logger message of info but it will work for debug too;
        logger.log(2, "DebugMessage");

    }
}
