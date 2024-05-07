package com.example.demo.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.UserOrder;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.OrderRepository;
import com.example.demo.model.persistence.repositories.UserRepository;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private com.example.demo.Logger.Logger2 Logger2;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	private static final Logger log = LogManager.getLogger(CartController.class);
	
	@PostMapping("/submit/{username}")
	public ResponseEntity<UserOrder> submit(@PathVariable String username) {
		log.info("OrderService: Submitting order..");
		Logger2.logToCsv(null, "Service: submit", "Submitting order", "200");
		User user = userRepository.findByUsername(username);
		if(user == null) {
			log.error("OrderService: User not found..");
			Logger2.logToCsv(null, "Service: submit", "User not found", "404");
			return ResponseEntity.notFound().build();
		}
		UserOrder order = UserOrder.createFromCart(user.getCart());
		log.info("OrderService: Order created..");
		orderRepository.save(order);
		log.info("OrderService: Order saved..");
		Logger2.logToCsv(null, "Service: submit", "Order saved", "200");
		return ResponseEntity.ok(order);
	}
	
	@GetMapping("/history/{username}")
	public ResponseEntity<List<UserOrder>> getOrdersForUser(@PathVariable String username) {
		log.info("OrderService: Fetching order history..");
		Logger2.logToCsv(null, "Service: getOrdersForUser", "Fetching order history", "200");
		User user = userRepository.findByUsername(username);

		if(user == null) {
			log.warn("OrderService: Order history not found..");
			Logger2.logToCsv(null, "Service: getOrdersForUser", "Order history not found", "404");
			return ResponseEntity.notFound().build();
		}
		Logger2.logToCsv(user.getId(), "Service: getOrdersForUser", "Order history found", "200");
		return ResponseEntity.ok(orderRepository.findByUser(user));
	}
}
