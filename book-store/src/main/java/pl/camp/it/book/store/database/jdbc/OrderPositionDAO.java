package pl.camp.it.book.store.database.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.book.store.database.IOrderPositionDAO;
import pl.camp.it.book.store.model.Book;
import pl.camp.it.book.store.model.OrderPosition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Repository
public class OrderPositionDAO implements IOrderPositionDAO {

    @Autowired
    Connection connection;

    @Autowired
    BookDAO bookDAO;

    @Autowired
    Supplier<Book> unknowBookSupplier;

    @Override
    public void persist(OrderPosition orderPosition, int orderId) {
        try{
            String sql = "INSERT INTO torderposition (quantity, order_id, book_id) VALUES (?,?,?)";
            PreparedStatement ps = this.connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, orderPosition.getQuantity());
            ps.setInt(2,orderId);
            ps.setInt(3,orderPosition.getBook().getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            orderPosition.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderPosition> getOrderId(int orderId) {
        List<OrderPosition> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM torderposition WHERE order_id = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = this.bookDAO.getBookById(rs.getInt("book_id"))
                        .orElseGet(this.unknowBookSupplier);
                result.add(new OrderPosition(
                        rs.getInt("id"),
                        book,
                        rs.getInt("quantity")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Optional<OrderPosition> getById(int id) {
        try{
            String sql = "SELECT * FROM torderposition WHERE id =?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs =ps.executeQuery();
            if(rs.next()){
                Book book = this.bookDAO.getBookById(rs.getInt("book_id"))
                        .orElseGet(this.unknowBookSupplier);
                return Optional.of(new OrderPosition(
                        rs.getInt("id"),
                        book,
                        rs.getInt("quantity")
                ));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
