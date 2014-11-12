package dev.factory.service;

import dev.factory.dao.SalesOrderDao;
import dev.factory.model.SalesOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesOrderService {
    @Autowired
    private SalesOrderDao salesOrderDao;

    public void updateSalesOrder(SalesOrder salesOrder) {
        salesOrderDao.update(salesOrder);
    }

    public void deleteSalesOrder(SalesOrder salesOrder) {
        salesOrderDao.delete(salesOrder);
    }

    public void createSalesOrder(SalesOrder salesOrder)  {
        salesOrderDao.save(salesOrder);
    }
}
