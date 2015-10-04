package com.dev.backend.rest.wrapper;

import java.math.BigDecimal;
import java.util.List;

public class SalesOrderRequest {
    private String orderNumber;
    private BigDecimal totalPrice;
    private String customer;
    private List<OrderLine> orderLines;

    public SalesOrderRequest(String orderNumber, BigDecimal totalPrice, String customer, List<OrderLine> orderLines) {
        this.orderNumber = orderNumber;
        this.totalPrice = totalPrice;
        this.customer = customer;
        this.orderLines = orderLines;
    }

    public SalesOrderRequest() {
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public static class OrderLine   {
        private String product;
        private Integer quantity;
        private BigDecimal price;
        private BigDecimal total;

        public OrderLine(String product, Integer quantity, BigDecimal total, BigDecimal price) {
            this.product = product;
            this.quantity = quantity;
            this.price = price;
            this.total = total;
        }

        public OrderLine() {
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public BigDecimal getTotal() {
            return total;
        }

        public void setTotal(BigDecimal total) {
            this.total = total;
        }

    }
}
