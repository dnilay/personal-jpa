package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Beer;
import com.example.demo.repository.BeerRepository;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class BeerServiceImpl implements BeerService{
	
	private final BeerRepository beerRepository;
	
	@Override
	public List<Beer> getAllBeer() {
		// TODO Auto-generated method stub
		return beerRepository.getAllBeer();
	}

	@Override
	public Beer getBeerById(String beerId) {
		
		return beerRepository.getBeerById(beerId);
	}

	@Override
	public Beer createBeer(Beer beer) {
		
		return beerRepository.createBeer(beer);
	}

}
