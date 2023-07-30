package pl.camp.it.book.store.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.camp.it.book.store.model.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.function.Supplier;

@Configuration
@ComponentScan("pl.camp.it.book.store")
public class AppConfigutarion {

    @Bean
    public Connection connection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bookstore", "root", "");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException();}
    }

    @Bean
    public Supplier<Book> unknownBookSupplier(){
        return () -> new Book(0,"title unknown","author unkown",0, 0,"isbn unknown");
    }
}
