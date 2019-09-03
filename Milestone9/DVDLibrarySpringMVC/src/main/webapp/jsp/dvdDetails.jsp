<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>DVD Details</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>DVD Details</h1>
            <hr/>
                <p>
                    Name: <c:out value="${dvd.dvdTitle}"/>
                </p>
                <p>
                    Company: <c:out value="${dvd.releaseYear}"/>
                </p>
                <p>
                    Phone: <c:out value="${dvd.director}"/>
                </p>
                <p>
                    Email: <c:out value="${dvd.rating}"/>
                </p>
<a href="${pageContext.request.contextPath}/">
                <div class="btn btn-primary">Get outta here.</div>
</a>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>