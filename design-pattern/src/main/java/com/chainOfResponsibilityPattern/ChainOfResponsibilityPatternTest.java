package com.chainOfResponsibilityPattern;

public class ChainOfResponsibilityPatternTest {
    public static void main(String[] args) {
        AbstractLogger loggerChain = getChainOfLoggers();

        loggerChain.logMessage(AbstractLogger.INFO, "this is an information");
        loggerChain.logMessage(AbstractLogger.DEBUG, "this is a debug level information");
        loggerChain.logMessage(AbstractLogger.ERROR, "this is a error level information");
    }

    private static AbstractLogger getChainOfLoggers() {
        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);

        return errorLogger;
    }
}
