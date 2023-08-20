package pl.camp.it.book.store.database.hibernate;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.book.store.database.IUserRepository;
import pl.camp.it.book.store.exception.LoginAlreadyExistException;
import pl.camp.it.book.store.model.User;

import java.util.Optional;

@Repository
public class UserDAO implements IUserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Optional<User> getByLogin(String login) {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM pl.camp.it.book.store.model.User WHERE login =: login", User.class);
        query.setParameter("login", login);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return  Optional.empty();
        } finally {
            session.close();
        }
    }

    @Override
    public void persistUser(User user) throws LoginAlreadyExistException{
        Session session = this.sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new LoginAlreadyExistException();
        }
        finally {
            session.close();
        }
    }

    @Override
    public Optional<User> getById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM pl.camp.it.book.store.model.User WHERE id =: id", User.class);
        query.setParameter("id", id);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return  Optional.empty();
        } finally {
            session.close();
        }
    }
}
