package pl.camp.it.book.store.database.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.book.store.database.IOrderDAO;
import pl.camp.it.book.store.database.IOrderPositionDAO;
import pl.camp.it.book.store.database.IUserRepository;
import pl.camp.it.book.store.model.Order;
import pl.camp.it.book.store.model.OrderPosition;
import pl.camp.it.book.store.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class OrderDAO implements IOrderDAO {

    @Autowired
    Connection connection;

    @Autowired
    IOrderPositionDAO orderPositionDAO;

    @Autowired
    IUserRepository userRepository;

    @Override
    public void persistOrder(Order order) {
        try {
            String sql = "INSERT INTO torder (status, total, datetime, user_id) VALUES (?,?,?,?)";
            PreparedStatement ps = this.connection.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, order.getStatus().toString());
            ps.setDouble(2, order.getTotal());
            ps.setTimestamp(3, Timestamp.valueOf(order.getDateTime()));
            ps.setInt(4, order.getUser().getId());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            order.setId(rs.getInt(1));

            for(OrderPosition orderPosition: order.getOrderPositions()){
                this.orderPositionDAO.persist(orderPosition,order.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        List<Order> result = new ArrayList<>();
        try{
            String sql = "SELECT * FROM torder WHERE user_id = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            Optional<User> userBox = this.userRepository.getById(userId);
            while (rs.next()){
                int orderId = rs.getInt("id");
                List<OrderPosition> orderPositions= this.orderPositionDAO.getOrderId(orderId);
                Order order = new Order(
                        orderId,
                        userBox.get(),
                        Order.Status.valueOf(rs.getString("status")),
                        rs.getDouble("total"),
                        rs.getTimestamp("datetime").toLocalDateTime()
                );
                order.getOrderPositions().addAll(orderPositions);
                result.add(order);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Order> getOrderById(int id) {
       try{
           String sql = "SELECT * FROM torder WHERE id = ?";
           PreparedStatement ps = this.connection.prepareStatement(sql);
           ps.setInt(1, id);
           ResultSet rs = ps.executeQuery();
           if(rs.next()){
               Optional<User> userBox = this.userRepository.getById( rs.getInt("id"));
               Order order = new Order(
                       rs.getInt("id"),
                       userBox.get(),
                       Order.Status.valueOf(rs.getString("status")),
                       rs.getDouble("total"),
                       rs.getTimestamp("datetime").toLocalDateTime());
           List<OrderPosition> orderPositions = this.orderPositionDAO.getOrderId(order.getId());
           order.getOrderPositions().addAll(orderPositions);
           return Optional.of(order);
           }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
        return Optional.empty();
    }
}
