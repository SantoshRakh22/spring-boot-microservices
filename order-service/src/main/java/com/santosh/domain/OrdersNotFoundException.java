package com.santosh.domain;

public class OrdersNotFoundException extends RuntimeException {
    public OrdersNotFoundException(String message) {
        super(message);
    }

    public static OrdersNotFoundException forOrderNumber(String orderNumber) {
        return new OrdersNotFoundException("order with number " + orderNumber);
    }
}
