package com.revature.order.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.order.model.OrderLineItems;

public interface OrderLineItemsRepository extends JpaRepository<OrderLineItems, Long> {
}

