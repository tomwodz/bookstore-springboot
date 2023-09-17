package pl.camp.it.book.store.services.impl;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.camp.it.book.store.configuration.TestConfiguration;
import pl.camp.it.book.store.database.IBookDAO;
import pl.camp.it.book.store.database.IOrderDAO;
import pl.camp.it.book.store.database.IOrderPositionDAO;
import pl.camp.it.book.store.database.IUserRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class})
public class ServiceGenericTest {


    @MockBean
    IBookDAO bookDAO;

    @MockBean
    IUserRepository userRepository;

    @MockBean
    IOrderDAO orderDAO;

    @MockBean
    IOrderPositionDAO orderPositionDAO;


}
