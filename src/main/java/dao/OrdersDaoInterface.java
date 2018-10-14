package dao;

import java.util.List;

/**
 * DAO interface
 *
 * @param <T>
 */
public interface OrdersDaoInterface<T> {
    void persist(T entity);

    List<T> findAllOrders();

    List<T> findAllOrdersByClientId(String clientId);
}
