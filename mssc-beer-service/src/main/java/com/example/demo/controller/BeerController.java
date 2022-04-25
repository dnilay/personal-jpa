package com.example.demo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Beer;
import com.example.demo.service.BeerService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class BeerController {
	
	private final BeerService beerService;
	private final Environment environment;
	@RequestMapping
	public String getStatus()
	{
		return "beer-service is up and running on port:"+environment.getProperty("local.server.port");
	}
	
	@RequestMapping(method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE,value = "/beers")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Beer>> getAllBeer()
	{
		return new ResponseEntity<List<Beer>>(beerService.getAllBeer(),HttpStatus.OK);
	}
	
	@GetMapping("/beers/{beerId}")
	public ResponseEntity<Beer> getBeerById(@PathVariable("beerId") String beerid)
	{
		return ResponseEntity.ok(beerService.getBeerById(beerid));
	}
	
	
	@PostMapping("/beers")
	public ResponseEntity<Beer> createBeer(@RequestBody Beer beer)
	{
		beer.setBeerId(UUID.randomUUID().toString());
		return ResponseEntity.status(HttpStatus.CREATED).body(beerService.createBeer(beer));
	}
	

}
