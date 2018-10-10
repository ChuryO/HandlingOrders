package dao;

import model.Orders;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class OrdersDao implements OrdersDaoInterface<Orders, String> {
    private Session currentSession;
    private Transaction currentTransaction;

    public OrdersDao() {
    }

    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionWithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    private static SessionFactory getSessionFactory() {

        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Orders.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(builder.build());
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public void persist(Orders entity) {
        getCurrentSession().save(entity);
    }

    @SuppressWarnings("unchecked")
    public List<Orders> findAllOrders() {
        return (List<Orders>) getCurrentSession().createQuery("from Orders").list();
    }

    @SuppressWarnings("unchecked")
    public List<Orders> findAllOrdersByClientId(String inId) {
        return (List<Orders>) getCurrentSession().createQuery("from Orders where clientId = :inId")
                .setString("inId", inId).list();
    }
}
