package vn.order.shared.utils;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class LogHelper {
	
    public static void debug(Class cls, String message, Object... objects) {
        Logger log = LoggerFactory.getLogger(cls.getName());
        log.debug(message, objects);
    }

    public static void info(String category, String message, Object... objects) {
        Logger log = LoggerFactory.getLogger(category);
        log.info(message, objects);
    }

    public static void logAsJson(Class cls, Object message) {
        LoggerFactory.getLogger(cls).info(toJson(message));
    }

    private static String toJson(Object message) {
        try {
            return ObjectUtils.convertObjectToJson(message);
        } catch (Exception e) {
            return "Error in parsing object to json string";
        }
    }

    public static void info(Class cls, String message, Object... objects) {
        Logger log = LoggerFactory.getLogger(cls.getName());
        log.info(message, objects);
    }

    public static void warn(Class cls, String message, Object... objects) {
        Logger log = LoggerFactory.getLogger(cls.getName());
        log.warn(message, objects);
    }
    
    public static void error(Class cls, String message, Exception e) {
        Logger log = LoggerFactory.getLogger(cls.getName());
        log.error(message, e);
    }

    public static void error(Class cls, String message, Object... objects) {
        Logger log = LoggerFactory.getLogger(cls.getName());
        log.error(message, objects);
    }
    
    public static void trace(Class cls, String message) {
        Logger log = LoggerFactory.getLogger(cls.getName());
        log.trace(message);
    }
    
    public static void logException(Class cls, Exception ex, Object data) {
        
        Map<String, Object> log = new HashMap<String, Object>();
        log.put("logType", "EXCEPTION");
        log.put("data", data);
        log.put("message", ex.getMessage());
        log.put("stacktrace", ExceptionUtils.getStackTrace(ex));
        
        LogHelper.logAsJson(cls, log);
    }
}
