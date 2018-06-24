package com.secondtreasuresbookstore.ws;

import static org.junit.Assert.*;

import com.secondtreasuresbookstore.model.Book;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BookStoreWSClientTest {
	
	BookStoreWSClient client = new BookStoreWSClient();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		client.addBook("2222", "Advanced Java", "Java Expert", "The advance Knowledge of Java", 90);
		client.deleteBook("3333");
	}

	@Test
	public void testAdd() {
		
		boolean isInserted = client.addBook("3333", "Java Foundamental", "Java Expert", "The foundamental Knowledge of Java", 20);
		assert(isInserted == true);
	}

	@Test
	public void testGetAll() {
		client.addBook("1111", "Java Foundamental", "Java Expert", "The foundamental Knowledge of Java", 20);
		List<Book> books = client.getAll();
		assert(books.size() > 0);
	}
	
	@Test
	public void testUpdate() {
		client.addBook("1111", "Java Foundamental", "Java Expert", "The foundamental Knowledge of Java", 20);
		boolean isUpdated = client.updateBook("1111", "Java Foundamental", "Java Expert", "The foundamental Knowledge of Java", 30);
		assert(isUpdated == true);
		
	}
	
	@Test
	public void testDelete() {
		boolean isDeleted = client.deleteBook("2222");
		assert(isDeleted);
	}
	
}
