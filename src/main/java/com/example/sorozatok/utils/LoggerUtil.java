package com.example.sorozatok.utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerUtil {
    private static final Logger logger = Logger.getLogger("AppLogger");

    static {
        try {
            FileHandler handler = new FileHandler("app.log", true);
            logger.addHandler(handler);
            logger.setLevel(Level.INFO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void log(String msg) {
        logger.info("LOG:" + msg);
    }
    public static void info(String msg) { logger.info("INFO:" + msg);}
    public static void warning(String msg) { logger.warning("WARNING:" + msg); }
    public static void error(String msg) { logger.info("ERROR:" + msg); }

}

