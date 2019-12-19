package api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Приложение Spring
 *
 * @author obondar82@gmail.com
 */
@SpringBootApplication
@MapperScan("api.mappers")
public class Application {

    /**
     * Запуск приложения
     *
     * @param args Аргументы командной строки
     */
    public static void main(String[] args) {
        // Запускаем приложение Spring
        SpringApplication.run(Application.class, args);
    }
}