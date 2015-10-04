package com.dev.backend.dao;

import com.dev.backend.model.OrderLine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderLineDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void save(OrderLine orderLine) {
        Session session = sessionFactory.getCurrentSession();
        session.save(orderLine);
    }

    public void update(OrderLine orderLine) {
        Session session = sessionFactory.getCurrentSession();
        session.update(orderLine);
    }

    public OrderLine findOne(Long orderLineId) {
        Session session = sessionFactory.getCurrentSession();
        return (OrderLine)session.load(OrderLine.class, orderLineId);

    }

    @SuppressWarnings("unchecked")
    public List<OrderLine> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from OrderLine").list();
    }

}
