package Controller;

import Model.Order;
import java.util.List;

public class BookingHistoryController {

    // UC-2.1 (Sequence): View -> Controller -> Order.findOrdersByAccountId(accountId).
    // BR2.1-1: chỉ trả về order của chính accountId.
    public List<Order> findOrdersByAccountId(String accountId) {
        return Order.findOrdersByAccountId(accountId);
    }

    // UC-2.1 (Optional Flow): View -> Controller -> Order.findOrderById(orderId).
    public Order findOrderById(String orderId) {
        return Order.findOrderById(orderId);
    }
}
