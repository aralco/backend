package dev.factory.rest.wrapper;

import java.math.BigDecimal;
import java.util.List;

public class CreateSalesOrderRequest {
    private String orderNumber;
    private BigDecimal totalPrice;
    private String customer;
    private List<OrderLine> orderLines;

    public CreateSalesOrderRequest(String orderNumber, BigDecimal totalPrice, String customer, List<OrderLine> orderLines) {
        this.orderNumber = orderNumber;
        this.totalPrice = totalPrice;
        this.customer = customer;
        this.orderLines = orderLines;
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
        private BigDecimal total;
        private BigDecimal price;

        public OrderLine(String product, Integer quantity, BigDecimal total, BigDecimal price) {
            this.product = product;
            this.quantity = quantity;
            this.total = total;
            this.price = price;
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

        public BigDecimal getTotal() {
            return total;
        }

        public void setTotal(BigDecimal total) {
            this.total = total;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
    }
}
