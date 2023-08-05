package pl.camp.it.book.store.database.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.book.store.database.IBookDAO;
import pl.camp.it.book.store.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDAO implements IBookDAO {

    @Autowired
    Connection connection;

    @Override
    public List<Book> getAllBooks() {
        List<Book> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tbook";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getString("isbn")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void persistBook(Book book) {
        try {
            String sql = "INSERT INTO tbook (title, author, price, quantity, isbn) VALUES (?,?,?,?,?)";
            PreparedStatement ps = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setDouble(3, book.getPrice());
            ps.setInt(4, book.getQuantity());
            ps.setString(5, book.getIsbn());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            book.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Book> getBookById(int id) {
        try {
            String sql = "SELECT * FROM tbook WHERE id = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(new Book(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getString("isbn")));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteBook(int id) {
        try {
            String sql = "DELETE FROM tbook WHERE id = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateBook(Book book) {
        try {
            String sql = "UPDATE tbook SET title = ?, author = ?, price = ?, quantity = ?, isbn = ? WHERE  id = ?;";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getTitle());
            ps.setDouble(3, book.getPrice());
            ps.setInt(4, book.getQuantity());
            ps.setString(5, book.getIsbn());
            ps.setInt(6, book.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> getByPattern(String pattern) {
        List<Book> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tbook WHERE title LIKE ? OR author LIKE ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, "%"+pattern+"%");
            ps.setString(2, "%"+pattern+"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                result.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getString("isbn")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
