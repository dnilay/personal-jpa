package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.OrderEntity;
@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Integer> {

	@Query
	public Optional<OrderEntity> findByOrderId(String orderId);
}
