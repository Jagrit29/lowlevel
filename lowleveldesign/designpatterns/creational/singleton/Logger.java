package lowleveldesign.designpatterns.creational.singleton;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private static Logger instance;
    private Logger() {}

    public static synchronized Logger getInstance() {
        if(instance == null) {
            instance = new Logger();
        }

        return instance;
    }

    public void info(String message) {
        log("INFO", message);
    }

    public void debug(String message) {
        log("DEBUG", message);
    }

    public void warn(String message) {
        log("WARN", message);
    }

    public void log(String level, String message) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println(String.format("%s [%s]: %s", timestamp, level, message));
    }
}

class LoggerDemo {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.info("Inform Jagrit");
        logger.warn("Warn Jagrit");
        logger.debug("Debug Jagrit");
    }
}
