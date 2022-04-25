package com.example.demo.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ErrorResponseModel;
import com.example.demo.exception.OrderNotFoundException;
import com.example.demo.model.OrderEntity;
import com.example.demo.service.OrderService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class OrderController {
	private final OrderService orderService;
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponseModel> handleOrderNotFoundException(OrderNotFoundException e)
	{
		ErrorResponseModel responseModel=new ErrorResponseModel();
		responseModel.setErrorMessage(e.getMessage());
		responseModel.setErrorCode(HttpStatus.NOT_FOUND.value());
		responseModel.setErrorReportingTime(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseModel);
	}
	
	@PostMapping("/orders")
	public ResponseEntity<OrderEntity> createOrder(@RequestBody OrderEntity orderEntity)
	{
		return new ResponseEntity<OrderEntity>(orderService.createOrder(orderEntity),HttpStatus.CREATED);
	}
	
	@GetMapping("/orders")
	public ResponseEntity<?> fetchOrdes()
	{
		return ResponseEntity.ok(orderService.getAllOrders());
	}
	
	@GetMapping("/orders/{orderId}")
	public ResponseEntity<Optional<OrderEntity>> fetchOrderByOrderId(@PathVariable("orderId") String orderId)
	{
		Optional<OrderEntity> order=orderService.findOrderByOrderid(orderId);
		
		return ResponseEntity.ok(order);
		
	}
	@PutMapping("/orders/{orderId}")
	public ResponseEntity<?> updateOrderByOrderid(@PathVariable("orderId") String orderId,@RequestBody OrderEntity orderEntity)
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.updateOrderByOrderId(orderId, orderEntity));
	}
	@DeleteMapping("/orders/{orderId}")
	public ResponseEntity<?> deleteOrderByOrderId(@PathVariable("orderId") String orderId)
	{
		orderService.deleteOrderByOrderId(orderId);
		return ResponseEntity.status(HttpStatus.OK).body("deletion sucess.!!!");
	}

}
