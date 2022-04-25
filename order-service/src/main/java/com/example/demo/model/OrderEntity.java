package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "order_id",unique = true,nullable = false)
	private String orderId;
	@Column(name = "order_name",nullable = false)
	private String orderName;
	@Column(name = "order_value",nullable = false)
	private double orderValue;
	@Embedded
	private Customer customerDetails;

}
