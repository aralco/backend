package com.dev.backend.service;

import com.dev.backend.dao.CustomerDao;
import com.dev.backend.dao.ProductDao;
import com.dev.backend.dao.SalesOrderDao;
import com.dev.backend.exception.InsufficientCreditException;
import com.dev.backend.exception.InsufficientStockException;
import com.dev.backend.model.Customer;
import com.dev.backend.model.OrderLine;
import com.dev.backend.model.Product;
import com.dev.backend.model.SalesOrder;
import com.dev.backend.rest.wrapper.SalesOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SalesOrderService {

    @Autowired
    private SalesOrderDao salesOrderDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private ProductDao productDao;

    @Transactional
    public SalesOrder createSalesOrder(SalesOrderRequest salesOrderRequest)  throws InsufficientCreditException, InsufficientStockException {
        Customer customer = customerDao.findByCode(salesOrderRequest.getCustomer());
        validateCustomerCredit(salesOrderRequest, customer);
        //Customer credit is updated
        customer.setCurrentCredit(customer.getCurrentCredit().add(salesOrderRequest.getTotalPrice()));

        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setOrderNumber(salesOrderRequest.getOrderNumber());
        salesOrder.setCustomer(customer);
        salesOrder.setTotalPrice(salesOrderRequest.getTotalPrice());
        List<OrderLine> orderLines = new ArrayList<>();

        for(SalesOrderRequest.OrderLine reqOrderLine : salesOrderRequest.getOrderLines()) {
            Product product = productDao.findByCode(reqOrderLine.getProduct());
            validateProductStock(reqOrderLine, product);
            //Stock Quantity is updated
            product.setQuantity(product.getQuantity()-reqOrderLine.getQuantity());

            OrderLine orderLine = new OrderLine();
            orderLine.setProduct(product);
            orderLine.setQuantity(reqOrderLine.getQuantity());
            orderLine.setUnitPrice(reqOrderLine.getPrice());
            orderLine.setTotalPrice(reqOrderLine.getTotal());
            orderLine.setSalesOrder(salesOrder);
            orderLines.add(orderLine);
        }
        salesOrder.setOrderLines(orderLines);
        salesOrderDao.save(salesOrder);
        return salesOrder;
    }

    private void validateProductStock(SalesOrderRequest.OrderLine reqOrderLine, Product product) {
        if(reqOrderLine.getQuantity()>product.getQuantity())    {
            throw new InsufficientStockException("Available stock is insufficient to process this sale order.");
        }
    }

    private void validateCustomerCredit(SalesOrderRequest salesOrderRequest, Customer customer) {
        BigDecimal availableCredit = customer.getCreditLimit().subtract(customer.getCurrentCredit());
        if(salesOrderRequest.getTotalPrice().compareTo(availableCredit)>0)   {
            throw new InsufficientCreditException("Not enough credit for this sales order.");
        }
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
        return "Sales order successfully deleted.";
    }

    @Transactional
    public SalesOrder updateSalesOrder(SalesOrder salesOrder) {
        salesOrderDao.update(salesOrder);
        return salesOrder;
    }

}
