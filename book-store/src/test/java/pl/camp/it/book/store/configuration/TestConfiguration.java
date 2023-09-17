package pl.camp.it.book.store.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.camp.it.book.store.database.*;

@Configuration
@ComponentScan(basePackages = {
        "pl.camp.it.book.store.services",
        "pl.camp.it.book.store.session"
})
public class TestConfiguration {

  @Bean
  public BookDAO bookDAO(){
      return Mockito.mock(BookDAO.class);
  }

    @Bean
    public OrderDAO orderDAO(){
        return Mockito.mock(OrderDAO.class);
    }

    @Bean
    public UserDAO userDAO(){
        return Mockito.mock(UserDAO.class);
    }

    @Bean
    public OrderPositionDAO orderPositionDAO(){
        return Mockito.mock(OrderPositionDAO.class);
    }

}
