<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Organization Form</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/superherosightings.css" rel="stylesheet">         
    </head>
    <body>
        <div class="container">
            <h1>Superhero Wiki</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}">Home</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displaySuperheroesPage">Superheroes & Supervillains</a></li>
                        <li role="presentation"  class="active"><a href="${pageContext.request.contextPath}/displayOrganizationsPage">Organizations</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displayLocationsPage">Locations</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displaySightingsPage">Sightings</a></li>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/displayUserList">
                                User Management
                            </a>
                        </li>                        
                    </sec:authorize>
                </ul>     
            </div>
            <c:choose>
                <c:when test="${pageContext.request.userPrincipal.name != null}">
                    <p>Hello : ${pageContext.request.userPrincipal.name}
                        | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                    </p>
                </c:when>    
                <c:otherwise>
                    <a href="<c:url value="/login" />"> Sign In</a>
                </c:otherwise>
            </c:choose>
                      
                    <h2>Edit Organization</h2>
                    <hr>
                    <sf:form class="form-horizontal" role="form" method="POST" modelAttribute="organization" action="editOrganization">
                        <div class="form-group">
                            <label for="add-org-name" class="col-md-4 control-label">Name:</label>
                            <div class="col-md-8">
                                <sf:input type="text" class="form-control" name="orgName" path="orgName" placeholder="Name of Organization"/>
                                <sf:errors path="orgName" cssClass="error"></sf:errors>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-org-description" class="col-md-4 control-label">Description:</label>
                            <div class="col-md-8">
                                <sf:input type="text" class="form-control" name="orgDescription" path="orgDescription" placeholder="Description of Organization"/>
                                <sf:errors path="orgDescription" cssClass="error"></sf:errors>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-org-address" class="col-md-4 control-label">Address:</label>
                            <div class="col-md-8">
                                <sf:input type="text" class="form-control" name="orgAddress" path="orgAddress" placeholder="Address of Organization"/>
                                <sf:errors path="orgAddress" cssClass="error"></sf:errors>
                                <sf:hidden path="orgId"/>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Update Organization"/>
                            </div>
                        </div>
                    </sf:form>
                    
                    <form class="form-horizontal" role="form" method="GET" action="displayOrganizationsPage">
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Back"/>
                            </div>
                        </div>
                    </form>
                
      
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
