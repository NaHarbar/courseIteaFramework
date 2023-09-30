package helpers;

import org.apache.logging.log4j.Logger;

import static helpers.ConsoleColors.*;

public class PrinterColors {

    public static void printMessageInBlue(String message) {
        System.out.println(BLUE + message + RESET);
    }

    public static void printColorMessage(String message, Logger logger, Level level) {
        switch (level) {
            case INFO:
                logger.info(YELLOW + message + RESET);
                break;
            case DEBUG:
                logger.debug(CYAN + message + RESET);
                break;
            case ERROR:
                logger.error(PURPLE + message + RESET);
        }
    }
}


