package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.*;

public class Main {
    private static final Logger logger = Logger.getLogger(""); // это глобальный логгер, название у глобального логгера пустая строка
    private static final Logger log = Logger.getLogger(Main.class.getName()); // это логгер пакета как и нужно делать! а не глобальный заводить

    public static void main(String[] args) {
        logger.log(Level.SEVERE, "Error");
        logger.log(Level.OFF, "ABC");
        logger.log(Level.CONFIG, "11111");

        // Есть уровень у логгера
        logger.setLevel(Level.ALL);
        logger.log(Level.CONFIG, "222222");
        // А есть уровень у хендлера, который обрабатывает и фильтрует сообщения после логгера
        // logger.getHandlers()[0].setLevel(Level.ALL);
        try {
            int a = 10 / 0;
        } catch (ArithmeticException e) {
            // logger.log(Level.SEVERE, Arrays.toString(e.getStackTrace()));
            logger.log(Level.SEVERE, "Olol: ");
            logger.log(Level.SEVERE, "Exception: ", e);
        }

        logger.log(Level.CONFIG, "333333");

        logger.setFilter(new Filter() {
            @Override
            public boolean isLoggable(LogRecord record) {
                return record.getMessage().contains("java");
            }
        });

        logger.log(Level.CONFIG, "444444");
        logger.log(Level.CONFIG, "4444java44");

        // Выходной формат тоже можно настраивать
        logger.getHandlers()[0].setFormatter(new XMLFormatter());
        logger.log(Level.CONFIG, "5555java");
        // А можно свой формат сделать
        logger.getHandlers()[0].setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return record.getMessage() + "\n";
            }
        });
        logger.log(Level.CONFIG, "666666java");

        try {
            Handler h2 = new FileHandler("program.log");
            h2.setFormatter(new XMLFormatter());
            h2.setLevel(Level.ALL);
            logger.addHandler(h2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.log(Level.CONFIG, "77777java");

        System.out.println("Глобальный логгер: " + log.getParent().getName());
        System.out.println("Пакета логгер: " + log.getName());
        // Можно отключить родителькие логгеры, чтобы к нему наверх не кидать сообщения, чтобы его хендлеры их не брались
        log.setUseParentHandlers(false);
        // Но тогда ничего не будет т.к. у нашего пакетного логгера нет пока хендлеров
        log.log(Level.SEVERE, "8888");

        // МОжно подцепить файл с конфигурацией логгирования
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("logging.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
