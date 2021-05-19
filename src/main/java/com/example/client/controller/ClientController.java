package com.example.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.client.exception.ClientServiceException;
import com.example.client.model.Client;
import com.example.client.service.ClientService;

@RestController
public class ClientController {
	
	@Autowired
	private ClientService service;
	
	@PostMapping("/save-client")
	public Client addClient(@RequestBody Client client) throws ClientServiceException{
	System.out.println("Inside Controller:"+client);	
	return service.saveClient(client);
		
	}
	
	
	 @PutMapping("/update-client")
	 public Client updateClient (@RequestBody Client client) throws ClientServiceException {
	 return service.updateClient(client);
	 }
	 
	 
	 @GetMapping("/search-client")
	 public List<Client> searchClient(@RequestParam(required = false) Long id,@RequestParam(required = false) Long mobileNumber,@RequestParam(required=false)String firstName) {
	 return service.searchClient(id, mobileNumber, firstName);	 
		 
		 
	 }
	

}
