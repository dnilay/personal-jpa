package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Beer;

public interface BeerService {
	
	public List<Beer> getAllBeer();
	public Beer getBeerById(String beerId);
	public Beer createBeer(Beer beer);

}
