package com.example.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.client.exception.ClientServiceException;
import com.example.client.model.Client;
import com.example.client.service.ClientService;

@SpringBootTest
class ClientRegistrationAppApplicationTests {

	//@Test
	public void saveClientSuccessTest() throws ClientServiceException  {
		
		Client client = new Client();
		client.setId(9202204720082L);
		client.setFirstName("Daniel");
		client.setLastName("Harris");
		client.setMobileNumber(9831039938L);
		client.setAddress("INDIA");
		
		try {
		ClientService service = new ClientService();
		Client obj = service.saveClient(client);
		
		assertEquals(client,obj);
		
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
	}
	
	//@Test
	public void saveClientFailureTest() throws ClientServiceException {
		
		String expectedMessage = "ID is not a South African Id....";
		
		Client client = new Client();
		client.setId(1238L);
		client.setFirstName("Daniel");
		client.setLastName("Harris");
		client.setMobileNumber(9831039938L);
		client.setAddress("INDIA");	
		
		
		try {
			ClientService service = new ClientService();
			service.saveClient(client);
			}catch(Exception e) {
				
				String actualMessage = e.getMessage();
				assertTrue(actualMessage.contains(expectedMessage));
			}
		
	}
	
	@Test
	public void updateClientSuccessTest() throws ClientServiceException {
		
		Client aClient = new Client();
		aClient.setId(9202204720082L);
		aClient.setFirstName("Zartab");
		aClient.setLastName("Harris");
		aClient.setMobileNumber(9831039938L);
		aClient.setAddress("INDIA");	
		
		ClientService service = new ClientService();
		try {
		Client obj = service.updateClient(aClient);
		assertEquals(obj.getFirstName(),aClient.getFirstName());
		
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
	}
	
	@BeforeAll
	public static void createDummyClient() {
		
		Client client = new Client();
		client.setId(9202204720082L);
		client.setFirstName("Daniel");
		client.setLastName("Harris");
		client.setMobileNumber(9831039938L);
		client.setAddress("INDIA");
		
		try {
			ClientService service = new ClientService();
			Client obj = service.saveClient(client);
			
			assertEquals(client,obj);
			
			}catch(Exception e) {
				
				
				
			}
		
	}
	
	
  @Test
  public void searchClientSuccessTest() throws ClientServiceException {
	  
	  Long id = 9202204720082L;
	  try {
		  ClientService service = new ClientService();
		  List<Client> clients=service.searchClient(id, null, null);
		  
		  for(Client aClient : clients) {
			  
			  assertEquals(id,aClient.getId());
		  }
		  
	  }catch(Exception e) {
		  
		  e.printStackTrace(); 
	  }
	  
	  
  }
	

}
