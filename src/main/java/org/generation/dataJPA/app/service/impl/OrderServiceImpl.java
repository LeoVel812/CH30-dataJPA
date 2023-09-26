package org.generation.dataJPA.app.service.impl;

import java.util.List;

import org.generation.dataJPA.app.entity.OrderProduct;
import org.generation.dataJPA.app.repository.OrderRepository;
import org.generation.dataJPA.app.service.OrderService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Override
	public OrderProduct createOrder(OrderProduct order) {
		return saveOrder(order);
	}

	// this method will be used in other classes
	public OrderProduct saveOrder(OrderProduct order) {
		return orderRepository.save(order);
	}

	@Override
	public OrderProduct getOrderById(Long id) {
		 return orderRepository.findById(id).orElseThrow(() -> new
		 IllegalStateException("Order doesn't exist " + id));
		//return new OrderProduct();
	}

	@Override
	public List<OrderProduct> getAllOrders() {
		return (List<OrderProduct>) orderRepository.findAll();
	}

	@Override
	public OrderProduct updateOrder(OrderProduct order, Long id) {
		OrderProduct existingOrder = getOrderById(id);
		existingOrder.setTotalAmount(order.getTotalAmount());
		return saveOrder(existingOrder);
	}

	@Override
	public void deleteOrder(Long id) {
		OrderProduct existingOrder = getOrderById(id);
		orderRepository.delete(existingOrder);
	}

}
