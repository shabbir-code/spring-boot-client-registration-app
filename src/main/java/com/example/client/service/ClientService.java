package com.example.client.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.example.client.exception.ClientServiceException;
import com.example.client.model.Client;

@Service
public class ClientService {
	public static HashMap<Long,Client> map = new HashMap<>();
	
	/**
	 * This method is used to save client object
	 * @param client
	 * @return
	 * @throws ClientServiceException
	 */
	public Client saveClient(Client client) throws  ClientServiceException{
		
		System.out.println("Inside Client Service"+client);
		
		validateById(client);
		
		if (map.size() == 0) {
			map.put(client.getId(), client);
     }
	 else {
			
			for(Client aclient : map.values()) {
				
				if(validateMobileNumber(aclient.getMobileNumber(),client.getMobileNumber()))
					map.put(client.getId(), client);
				
				
			}
			
			
		}
		
		
		
		System.out.println("Client Objects are:"+map);
		return client;
		
	}
	
	/**
	 * This method is used to update the Client object
	 * @param client
	 * @return
	 * @throws ClientServiceException
	 */
	public Client updateClient(Client client) throws  ClientServiceException {
		
		Client aClient = map.get(client.getId());
		aClient.setFirstName(client.getFirstName());
		aClient.setLastName(client.getLastName());
		aClient.setAddress(client.getAddress());
		//validate mobile Number
		
		for(Client clint : map.values()) {
			
			if(client.getId().longValue()== clint.getId().longValue()) {
				aClient.setMobileNumber(client.getMobileNumber());
				continue;
				
			}
			
			if(validateMobileNumber(clint.getMobileNumber(),client.getMobileNumber())) {
				//update mobile number
				aClient.setMobileNumber(client.getMobileNumber());
				
			}
			
		}
		
		return client;
	}
	
	
	/**
	 * This method is used to search Client Object
	 * 
	 * @param id
	 * @param mobileNumber
	 * @param firstName
	 * @return
	 */
	public List<Client> searchClient(Long id,Long mobileNumber,String firstName) {
		
		List<Client> clients = new ArrayList<>();
		
		Client client = null;
		
		if(id!=null) {
		client = searchClientById(id);
		clients.add(client);
			
		}
		
		if(mobileNumber != null) {
			System.out.println("==Inside Search with Mobile call=====");
			
			for(Client aclient: map.values()) {
				
				if(aclient.getMobileNumber().longValue() == mobileNumber.longValue()) {
					Client clint = new Client();
					clint.setId(aclient.getId());
					clint.setFirstName(aclient.getFirstName());
					clint.setLastName(aclient.getLastName());
					clint.setAddress(aclient.getAddress());
					clint.setMobileNumber(aclient.getMobileNumber());
					
					System.out.println("getting client using phone:"+clint);
					
					clients.add(clint);
				}
				
				break;
			}
			
		}
		
		
		if(firstName!= null) {

			for(Client aclient: map.values()) {
				
				if(aclient.getFirstName().equalsIgnoreCase(firstName)) {
					Client clint = new Client();
					clint.setId(aclient.getId());
					clint.setFirstName(aclient.getFirstName());
					clint.setLastName(aclient.getLastName());
					clint.setAddress(aclient.getAddress());
					clint.setMobileNumber(aclient.getMobileNumber());
					
					clients.add(clint);
				}
				
				
			}
			
			
		}
		
		
		return clients;
	}
	
	
	/**
	 * This method is used to search client by ID
	 * @param id
	 * @return
	 */
	private Client searchClientById(Long id) {
		
		Client client = map.get(id);
		
		return client;
		
		
	}
	
	/**
	 * This method is used to validate duplicate mobileNumber 
	 * 
	 * @param mobileNumber
	 * @param clientMobileNumber
	 * @return
	 * @throws ClientServiceException
	 */
	private boolean validateMobileNumber(Long mobileNumber, Long clientMobileNumber) throws ClientServiceException {
				boolean valid = true;
				if(mobileNumber!=null && clientMobileNumber!=null && (mobileNumber.longValue() == clientMobileNumber.longValue())) {
			    valid = false;	
				throw new ClientServiceException("Mobile number already exist");
				
				}
				
				return valid;
		
	}
	
	/**
	 * This method is used to validate duplicate id
	 * @param client
	 * @throws ClientServiceException
	 */
	private void validateById(Client client) throws ClientServiceException {
		System.out.println("==Inside validateById method====");
		
		if(!isValidSouthAfricanId(client)) {
			
			throw new ClientServiceException("ID is not a South African Id....");
			
		}
		
		if(map.size()>0) {
			
			for(Long id : map.keySet()) {
				
				if(id.longValue() == client.getId().longValue())
				throw new ClientServiceException("ID already exist...Please provide another ID for the Client....");
				
				
			}
			
		}
		
		
		
	}
	
	/**
	 * This method is used to validate whether id 
	 * is a south african Id
	 * @param client
	 * @return
	 */
	private boolean isValidSouthAfricanId(Client client){
		
		
		String regex = "(((\\d{2}((0[13578]|1[02])(0[1-9]|[12]\\d|3[01])|(0[13456789]|1[012])(0[1-9]|[12]\\d|30)|02(0[1-9]|1\\d|2[0-8])))|([02468][048]|[13579][26])0229))(( |-)(\\d{4})( |-)(\\d{3})|(\\d{7}))";
		
		 Pattern p = Pattern.compile(regex);
		 
		 if(client.getId()== null) {
			 
			 return false;
		 }
		 
		 Matcher m = p.matcher(String.valueOf(client.getId().longValue()));
		 
		 return m.matches();
		 
	
	}

}
