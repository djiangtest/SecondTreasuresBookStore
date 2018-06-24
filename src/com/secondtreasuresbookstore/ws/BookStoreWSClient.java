/**
 * 
 */
package com.secondtreasuresbookstore.ws;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import com.secondtreasuresbookstore.model.Book;


/**
 * @author tony jiang
 *
 */
public class BookStoreWSClient {
	
    private static final String REST_URI 
    	= "http://localhost:8080/BookStoreWebServices/rest/services";

    private Client client = ClientBuilder.newClient();

	public Book findBookByID(String isbn) {
		WSResult result = client
		    .target(REST_URI)
		    .path("findBookByID/" + isbn)
		    .request(MediaType.TEXT_PLAIN_TYPE)
		    .accept(MediaType.APPLICATION_JSON)
		    .get(WSResult.class);
		return result.book;
	}
	
	public List<Book> getAll() {
		try {
			Book[] result = client
			    .target(REST_URI)
			    .path("getAll")
			    .request(MediaType.TEXT_PLAIN_TYPE)
			    .accept(MediaType.APPLICATION_JSON)
			    .get((new Book[1]).getClass());
			return Arrays.asList(result);
		} catch (Exception e) {
			return new ArrayList<Book>();
		}
	}
	
	public boolean addBook(String isbn, String title, String author, String genre, double price) {
		Book newBook = new Book(isbn, title, genre, author, price);
		try {
			WSResult result = client
				    .target(REST_URI)
				    .path("addBook")
				    .request(MediaType.APPLICATION_JSON)
				    .accept(MediaType.APPLICATION_JSON)
				    .post(Entity.json(newBook), WSResult.class);
			if(result.success)
				return true;
			else
				return false;
		} catch(Exception e) {
			return false;
		}
		
	}
	
	public boolean updateBook(String isbn, String title, String author, String genre, double price) {
		Book updatedBook = new Book(isbn, title, genre, author, price);
		try {
			WSResult result = client
				    .target(REST_URI)
				    .path("updateBook")
				    .request(MediaType.APPLICATION_JSON)
				    .accept(MediaType.APPLICATION_JSON)
				    .post(Entity.json(updatedBook), WSResult.class);
			if(result.success)
				return true;
			else
				return false;
		} catch(Exception e) {
			return false;
		}
		
	}
	
	public boolean deleteBook(String isbn) {
		try {
			WSResult result = client
			    .target(REST_URI)
			    .path("removeBook/" + isbn)
			    .request(MediaType.TEXT_PLAIN_TYPE)
			    .accept(MediaType.APPLICATION_JSON)
			    .get(WSResult.class);
			if(result.success)
				return true;
			else
				return false;
		} catch(Exception e) {
			return false;
		}
	
	}
	

}
