package dao;

import model.Orders;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class OrdersDao implements OrdersDaoInterface<Orders> {
    private Session currentSession;
    private Transaction currentTransaction;


    public OrdersDao() {
    }

    public void openCurrentSession() {
        currentSession = getSessionFactory().openSession();
    }

    public void openCurrentSessionWithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
    }


    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    /**
     * @return SessionFactory Configuration
     */
    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Orders.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(builder.build());
    }

    private Session getCurrentSession() {
        return currentSession;
    }

    public void persist(Orders entity) {
        getCurrentSession().save(entity);
    }

    /**
     * @return list with all orders
     */
    @SuppressWarnings("unchecked")
    public List<Orders> findAllOrders() {
        return (List<Orders>) getCurrentSession().createQuery("from Orders").list();
    }

    /**
     * this method return list with all orders by id
     *
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List<Orders> findAllOrdersByClientId(String inId) {
        return (List<Orders>) getCurrentSession().createQuery("from Orders where clientId = :inId")
                .setString("inId", inId).list();
    }
}
