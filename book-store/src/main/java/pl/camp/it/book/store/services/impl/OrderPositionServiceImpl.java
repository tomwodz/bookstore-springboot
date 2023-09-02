package pl.camp.it.book.store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.book.store.database.IOrderPositionDAO;
import pl.camp.it.book.store.model.OrderPosition;
import pl.camp.it.book.store.services.IOrderPositionService;

import java.util.List;
import java.util.Optional;

@Service
public class OrderPositionServiceImpl implements IOrderPositionService {

    @Autowired
    IOrderPositionDAO orderPositionDAO;

    @Override
    public List<OrderPosition> getOrderById(int orderId) {
        return this.orderPositionDAO.getOrderId(orderId);
    }

    @Override
    public Optional<OrderPosition> getById(int id) {
        return this.orderPositionDAO.getById(id);
    }
}
