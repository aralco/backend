package dev.factory.dao;

import dev.factory.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void save(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.save(customer);
    }

    public void update(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.update(customer);
    }

    public Customer findOne(Long customerId) {
        Session session = sessionFactory.getCurrentSession();
        return (Customer)session.load(Customer.class, customerId);

    }

    @SuppressWarnings("unchecked")
    public List<Customer> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Customer").list();
    }

}
