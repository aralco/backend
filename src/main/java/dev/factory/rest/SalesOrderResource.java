package dev.factory.rest;


import dev.factory.model.SalesOrder;
import dev.factory.rest.wrapper.CommonResponse;
import dev.factory.rest.wrapper.CreateSalesOrderRequest;
import dev.factory.service.SalesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/salesorders",
        consumes = "application/json",
        produces = "application/json")
public class SalesOrderResource {
    @Autowired
    private SalesOrderService salesOrderService;


    @RequestMapping(method = RequestMethod.POST)
    public SalesOrder createSalesOrder(@RequestBody CreateSalesOrderRequest salesOrderRequest) {
        return salesOrderService.createSalesOrder(salesOrderRequest);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<SalesOrder> listSalesOrders() {
        return salesOrderService.getSalesOrders();
    }

    @RequestMapping(value = "{orderNumber}", method = RequestMethod.GET)
    public SalesOrder viewSalesOrder(@PathVariable String orderNumber) {
        return salesOrderService.getSalesOrder(orderNumber);
    }

    @RequestMapping(value = "{orderNumber}", method = RequestMethod.DELETE)
    public CommonResponse deleteSalesOrder(@PathVariable String orderNumber) {
        String message = salesOrderService.deleteSalesOrder(orderNumber);
        return new CommonResponse(message);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public SalesOrder updateSalesOrder(@RequestBody SalesOrder salesorders) {
        return salesOrderService.updateSalesOrder(salesorders);
    }

}
