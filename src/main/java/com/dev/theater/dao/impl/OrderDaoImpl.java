package com.dev.theater.dao.impl;

import com.dev.theater.dao.OrderDao;
import com.dev.theater.exception.CrudException;
import com.dev.theater.model.Order;
import com.dev.theater.model.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl extends DaoImpl<Order> implements OrderDao {
    @Autowired
    public OrderDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Order> getOrdersHistory(User user) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select distinct o from Order o inner join fetch o.tickets"
                    + " where o.user = :user", Order.class)
                    .setParameter("user", user)
                    .getResultList();
        } catch (Exception e) {
            throw new CrudException("Could not find history for: " + user, e);
        }
    }
}
