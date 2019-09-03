<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Superheroes & Supervillains</title>
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
                        <li role="presentation"  class="active"><a href="${pageContext.request.contextPath}/displaySuperheroesPage">Superheroes & Supervillains</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displayOrganizationsPage">Organizations</a></li>
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
            <div class="row">
                <div class="col-md-9">
                    <h2>List of Superheroes & Supervillains</h2>
                    <table id="contactTable" class="table table-hover">
                        <tr>                           
                            <th width="13%">Name</th>
                            <th width="26%">Description</th>
                            <th width="26%">Superpower</th>
                            <th width="11%"></th>
                            <th width="5%"></th>
                            <th width="5%"></th>
                        </tr>
                        <c:forEach var="currentSuperhero" items="${superheroList}">
                            <tr>                              
                                <td>
                                    <c:out value="${currentSuperhero.heroName}"/>
                                </td>
                                <td>
                                    <c:out value="${currentSuperhero.heroDescription}"/>
                                </td>
                                <td>
                                    <c:out value="${currentSuperhero.heroSuperPower}"/>
                                </td>
                                <td>
                                    <a href="displaySuperheroDetails?heroId=${currentSuperhero.heroId}">
                                    View Details
                                    </a>
                                </td>
                                <td>
                                    <sec:authorize access="hasRole('ROLE_SIDEKICK')">
                                    <a href="displayEditSuperheroForm?heroId=${currentSuperhero.heroId}">
                                    Edit
                                    </a>
                                    </sec:authorize>
                                </td>
                                <td>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <a href="deleteSuperhero?heroId=${currentSuperhero.heroId}">
                                    Delete
                                    </a>
                                    </sec:authorize>
                                </td>
                            </tr>  
                        </c:forEach>
                    </table>
                </div>
                <div class="col-md-3">
                    <sec:authorize access="hasRole('ROLE_SIDEKICK')">
                    <h3>Add New Superperson</h3>
                    <form class="form-horizontal" role="form" method="POST" action="createSuperhero">
                        <div class="form-group">
                            <label for="add-hero-name" class="col-md-4 control-label">Name:</label>
                            <div class="col-md-8">
                                <input type="text" id="add-hero-name" class="form-control" name="heroName" placeholder="Name" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-description" class="col-md-4 control-label">Description:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="heroDescription" placeholder="Description" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-superpower" class="col-md-4 control-label">Superpower:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="heroSuperPower" placeholder="Superpower" required/>
                            </div>
                        </div>
                       
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Create Superperson"/>
                            </div>
                        </div>
                    </form>
                    </sec:authorize>
                </div>
                
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>