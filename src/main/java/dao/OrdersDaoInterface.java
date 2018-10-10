package dao;

import java.io.Serializable;
import java.util.List;

public interface OrdersDaoInterface<T, Id extends Serializable> {
    public void persist(T entity);
    public List<T> findAllOrders();
    public List<T> findAllOrdersByClientId(String clientId);

    //    public List<T> findOrders();
    //    public List<T> findOrdersByClientId(String clientId);
    //    public List<T> findTotalPurchaseValue();
    //    public List<T> findTotalPurchaseValueByClientId(String clientId);
//    public List<T> averageOrders(String clientId);
//    public List<T> averageOrdersByClientId(String clientId);
}
