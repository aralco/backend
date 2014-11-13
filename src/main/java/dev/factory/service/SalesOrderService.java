package dev.factory.service;

import dev.factory.dao.SalesOrderDao;
import dev.factory.model.SalesOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SalesOrderService {

    @Autowired
    private SalesOrderDao salesOrderDao;

    @Transactional
    public SalesOrder createSalesOrder(SalesOrder salesOrder)  {
        salesOrderDao.save(salesOrder);
        return salesOrder;
    }

    @Transactional
    public List<SalesOrder> getSalesOrders() {
        return salesOrderDao.findAll();
    }

    @Transactional
    public SalesOrder getSalesOrder(String orderNumber) {
        return salesOrderDao.findByOrderNumber(orderNumber);
    }

    @Transactional
    public String deleteSalesOrder(String orderNumber) {
        SalesOrder salesOrder = salesOrderDao.findByOrderNumber(orderNumber);
        salesOrderDao.delete(salesOrder);
        return "Success delete of a sales order";
    }

    @Transactional
    public SalesOrder updateSalesOrder(SalesOrder salesOrder) {
        salesOrderDao.update(salesOrder);
        return salesOrder;
    }

}
