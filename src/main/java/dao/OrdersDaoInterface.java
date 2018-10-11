package dao;

import java.io.Serializable;
import java.util.List;

public interface OrdersDaoInterface<T, Id extends Serializable> {
    public void persist(T entity);
    public List<T> findAllOrders();
    public List<T> findAllOrdersByClientId(String clientId);
}
