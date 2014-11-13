package dev.factory.service;

import dev.factory.dao.CustomerDao;
import dev.factory.dao.OrderLineDao;
import dev.factory.dao.ProductDao;
import dev.factory.dao.SalesOrderDao;
import dev.factory.model.OrderLine;
import dev.factory.model.Product;
import dev.factory.model.SalesOrder;
import dev.factory.rest.wrapper.CreateSalesOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SalesOrderService {

    @Autowired
    private SalesOrderDao salesOrderDao;
    @Autowired
    private OrderLineDao orderLineDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private ProductDao productDao;

    @Transactional
    public SalesOrder createSalesOrder(CreateSalesOrderRequest salesOrderRequest)  {
        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setOrderNumber(salesOrderRequest.getOrderNumber());
        salesOrder.setCustomer(customerDao.findByCode(salesOrderRequest.getCustomer()));
        System.out.println("1  "+salesOrder.getCustomer());
        salesOrder.setTotalPrice(salesOrderRequest.getTotalPrice());
        salesOrderDao.save(salesOrder);

        for(CreateSalesOrderRequest.OrderLine reqOrderLine : salesOrderRequest.getOrderLines()) {
            System.out.println("2  "+reqOrderLine.getProduct());
            Product product = productDao.findByCode(reqOrderLine.getProduct());
            System.out.println("3  "+salesOrder);
            OrderLine orderLine = new OrderLine();
            orderLine.setProduct(product);
            orderLine.setQuantity(reqOrderLine.getQuantity());
            orderLine.setUnitPrice(reqOrderLine.getPrice());
            orderLine.setTotalPrice(reqOrderLine.getTotal());
            orderLine.setSalesOrder(salesOrder);
            System.out.println("4  "+salesOrder);
            orderLineDao.save(orderLine);
        }
        System.out.println("5  "+salesOrder);
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
