package services;

import dao.OrdersDao;
import model.Orders;

import java.util.List;

public class OrderService {
    private static OrdersDao ordersDao;

    public OrderService() {
        ordersDao = new OrdersDao();
    }

    public OrdersDao ordersDao() {
        return ordersDao;
    }

    public void persist(Orders entity) {
        ordersDao.openCurrentSessionWithTransaction();
        ordersDao.persist(entity);
        ordersDao.closeCurrentSessionWithTransaction();
    }

    public List<Orders> findAll() {
        ordersDao.openCurrentSession();
        List<Orders> orders = ordersDao.findAllOrders();
        ordersDao.closeCurrentSession();
        return orders;
    }


    public List<Orders> findAllOrdersByClientId(String clientId) {
        ordersDao.openCurrentSession();
        List<Orders> orders = ordersDao.findAllOrdersByClientId(clientId);
        ordersDao.closeCurrentSession();
        return orders;
    }
}
