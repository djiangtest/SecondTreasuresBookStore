<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="com.secondtreasuresbookstore.model.Book" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${pageContext.request.contextPath}/css/stacktable.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/jquery-1.7.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/stacktable.js" type="text/javascript"></script>
<title>Book List</title>
</head>
<body>
<% 
	List<Book> books = (List<Book>)request.getAttribute("booklist");
%>	
	<h2>Second Treasures Bookstore</h2>

<div class="container">
  <div  class="row"><a href="${pageContext.request.contextPath}/book?action=new">Add new book</a></div>
  <div class="row">
    <div class="col-xs-12">
      <table summary="This table shows all the books" class="table table-bordered table-hover">
       
        <thead>
          <tr>
            <th>ISBN</th>
            <th>Title</th>
            <th>Author</th>
            <th>Genre</th>
            <th>Price</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
 
<% 
	for(Book book: books) {
%>
		  <tr>
            <td><a href="${pageContext.request.contextPath}/book?action=edit&isbn=<%=book.getIsbn() %>"> <%=book.getIsbn() %></a></td>
            <td><%=book.getTitle() %></td>
            <td><%=book.getAuthor() %></td>
            <td><%=book.getGenre() %></td>
            <td><%=book.getPrice() %></td>
            <td><input type="button" value="Delete" onClick="javascript:window.location='${pageContext.request.contextPath}/book?action=delete&isbn=<%=book.getIsbn() %>';"> </td>
          </tr>
<% 
	}
%>
        </tbody>
        
      </table>
    </div>
  </div>
</div>
<script>
$('table').stacktable();
</script>
</body>
</html>
