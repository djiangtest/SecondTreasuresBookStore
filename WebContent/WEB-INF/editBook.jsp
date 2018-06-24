<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

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
<title>Edit Book</title>
</head>
<body>
<% 
	Book booktoupdate = (Book)request.getAttribute("book");
	String msg = (String) request.getAttribute("errorMsg");
%>	
<h2>Second Treasures Bookstore</h2>

<div class="container">
<% if(msg != null) {%>
	<div class="row"  align="center" style="color:red"><%=msg %></div>
<% } %>
<div class="row">
<div class="col-xs-12" align="center">
<form name="frm" action="${pageContext.request.contextPath}/book?action=update" method="POST">
      <input type="hidden" name="isbn" value="<%=booktoupdate.getIsbn() %>">
                  
     
  <table class="table  table-hover">
   
    <tbody>

	  <tr>
	  	<td width="60">ISBN</td>
        <td width="200"><%=booktoupdate.getIsbn() %></td>
        <td></td>
      </tr>
      <tr>
      	<td>Title</td>
        <td><input type="text" name="title" value="<%= booktoupdate.getTitle()%>" size="60"></td>
        <td></td>
      </tr>
      <tr>
      	<td>Author</td>
        <td><input type="text" name="author" value="<%=booktoupdate.getAuthor() %>"></td>
        <td></td>
      </tr>
      <tr>
      	<td>Genre</td>
        <td><input type="text" name="genre" value="<%=booktoupdate.getGenre() %>" size="60"></td>
        <td></td>
      </tr>
      <tr>
      	<td>Price</td>
        <td><input type="text" name="price" value="<%=booktoupdate.getPrice() %>" size="4"></td>
        <td></td>
      </tr>
      <tr>
      <td colspan="2" align="center">
		<input type="submit" value="Submit"> 
		<input type="button" value="Cancel" onClick="javascript:window.location='${pageContext.request.contextPath}/book?action=listAll';">   
		</td>
		<td></td>
	  </tr> 
    </tbody>
    
  </table>
  </form>
</div>
</div>
</div>

<script>
//$('table').stacktable();
</script>
</body>
</html>