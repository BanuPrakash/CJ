<%--
  Created by IntelliJ IDEA.
  User: banuprakash
  Date: 14/07/25
  Time: 12:35 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product List</title>
</head>
<body>
  <h1>Product List Page</h1>
 <table border="1">
     <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
        </tr>
     </thead>
     <tbody>
     <c:forEach var="product" items="${products}">
         <tr>
             <td>${product.id}</td>
             <td>${product.name}</td>
             <td>${product.price}</td>
         </tr>
     </c:forEach>
     </tbody>
 </table>
 <a href="indx.jsp">Back</a>
</body>
</html>
