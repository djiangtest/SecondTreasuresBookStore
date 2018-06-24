package com.secondtreasuresbookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.secondtreasuresbookstore.model.Book;
import com.secondtreasuresbookstore.ws.BookStoreWSClient;

/**
 * Servlet implementation class BookStoreServlet
 */
@WebServlet("/BookStoreServlet")
public class BookStoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookStoreServlet() {
        super();
        BookStoreWSClient client = new BookStoreWSClient();
        client.addBook("1111", "Java Foundamental","Java Expert", "The foundamental Knowledge of Java", 20);
        client.addBook("2222", "Advanced Java", "Java Expert", "The advance Knowledge of Java", 90);
        client.addBook("3333", "Java Foundamental", "Java Expert", "The foundamental Knowledge of Java", 20);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		BookStoreWSClient client = new BookStoreWSClient();
		
		if (action.equals("listAll")){ 
		    
		    List<Book> books = client.getAll();
		    request.setAttribute("booklist", books);
		    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/booklist.jsp");
		    dispatcher.forward(request, response);
		    
		} else if (action.equals("delete")){ 
			
			String isbn = request.getParameter("isbn");
			client.deleteBook(isbn);
			List<Book> books = client.getAll();
		    request.setAttribute("booklist", books);
		    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/booklist.jsp");
		    dispatcher.forward(request, response);
		    
		} else if (action.equals("edit")){ 
			
			String isbn = request.getParameter("isbn");
			Book book = client.findBookByID(isbn);
		    request.setAttribute("book", book);
		    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/editBook.jsp");
		    dispatcher.forward(request, response);
		    
		} else if (action.equals("update")){ 
			
			String isbn = request.getParameter("isbn");
			String author = request.getParameter("author");
			String genre = request.getParameter("genre");
			String title = request.getParameter("title");
			double price = 0;
			try {
				price = Double.parseDouble(request.getParameter("price"));
			} catch (Exception e) {
				price = 0;
			}
			if(author.isEmpty() || genre.isEmpty() 
					|| title.isEmpty() || price == 0) {
				request.setAttribute("errorMsg", "All fields are required.");
				request.setAttribute("book", new Book(isbn, title, genre, author, price));
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/editBook.jsp");
			    dispatcher.forward(request, response); 
			    return;
			}
			
			client.updateBook(isbn, title, author, genre, price);
			List<Book> books = client.getAll();
		    request.setAttribute("booklist", books);
		    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/booklist.jsp");
		    dispatcher.forward(request, response); 
		    
		} else if (action.equals("new")){ 
			
		    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/newBook.jsp");
		    dispatcher.forward(request, response);
		    
		} else if (action.equals("add")){ 
			
			String isbn = request.getParameter("isbn");
			String author = request.getParameter("author");
			String genre = request.getParameter("genre");
			String title = request.getParameter("title");
			double price = 0;
			try {
				price = Double.parseDouble(request.getParameter("price"));
			} catch (Exception e) {
				price = 0;
			}
			if(isbn.isEmpty() || author.isEmpty() || genre.isEmpty() 
					|| title.isEmpty() || price == 0) {
				request.setAttribute("errorMsg", "All fields are required.");
				request.setAttribute("book", new Book(isbn, title, genre, author, price));
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/newBook.jsp");
			    dispatcher.forward(request, response); 
			    return;
			}
			
			client.addBook(isbn, title, author, genre, price);
			List<Book> books = client.getAll();
		    request.setAttribute("booklist", books);
		    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/booklist.jsp");
		    dispatcher.forward(request, response); 
		    
		} else {
			
			response.getWriter().append("Unknown action: " + action + " at ").append(request.getContextPath());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
