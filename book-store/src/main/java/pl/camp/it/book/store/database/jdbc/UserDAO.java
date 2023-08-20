package pl.camp.it.book.store.database.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.book.store.database.IUserRepository;
import pl.camp.it.book.store.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


public class UserDAO implements IUserRepository {

    @Autowired
    Connection connection;

    @Override
    public Optional<User> getByLogin(String login) {
        try {
            String sql = "SELECT * FROM tuser WHERE login = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(new User(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("email"),
                        User.Role.valueOf(rs.getString("role"))
                ));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void persistUser(User user) {
        try {
            String sql = "INSERT INTO tuser (login,password,name, surname,email,role) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, user.getSurname());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getRole().toString());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            user.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> getById(int id) {
        try {
            String sql = "SELECT * FROM tuser WHERE id = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(new User(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("email"),
                        User.Role.valueOf(rs.getString("role"))
                ));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
