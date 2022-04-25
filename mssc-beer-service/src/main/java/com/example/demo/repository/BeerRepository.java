package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Beer;

public interface BeerRepository {
	
	public List<Beer> getAllBeer();
	
	public Beer getBeerById(String beerId);
	
	public Beer createBeer(Beer beer);

}
