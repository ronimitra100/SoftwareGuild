<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sightings</title>
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
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displayOrganizationsPage">Organizations</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displayLocationsPage">Locations</a></li>
                        <li role="presentation"  class="active"><a href="${pageContext.request.contextPath}/displaySightingsPage">Sightings</a></li>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/displayUserList">
                                User Admin
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
                <div class="col-md-8">
                    <h2>List of Sightings</h2>
                    <table id="contactTable" class="table table-hover">
                        <tr>
                            
                            <th width="20%">Superhero/Supervillain</th>
                            <th width="25%">Sighting Location</th>
                            <th width="20%">Sighting Time</th>
                            <th width="10%"></th>
                            <th width="10%"></th>
                            <th width="15%"></th>
                        </tr>
                        <c:forEach var="i" begin="0" end="${sightingList.size()-1}">
                            <tr>
                                
   
                                <td>
                                    <c:out value="${superheroList[i].heroName}"/>
                                </td>
                                <td>
                                    <c:out value="${locationList[i].locationName}"/>
                                </td>
                                <td>
                                    <c:out value="${sightingList[i].sightingTime}"/>
                                </td>
                                <td>
                                    <a href="displaySightingDetails?sightingId=${sightingList[i].sightingId}">
                                    View Details 
                                    </a>
                                </td>
                                <td>
                                    <sec:authorize access="hasRole('ROLE_SIDEKICK')">
                                    <a href="displayEditSightingForm?sightingId=${sightingList[i].sightingId}">
                                    Edit
                                    </a>
                                    </sec:authorize>
                                </td>
                                <td>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <a href="deleteSighting?sightingId=${sightingList[i].sightingId}">
                                    Delete
                                    </a>
                                    </sec:authorize>
                                </td>
                            </tr>  
                        </c:forEach>
                    </table>
                </div>
                <div class="col-md-4">
                    <div>
                    <sec:authorize access="hasRole('ROLE_SIDEKICK')">    
                    <h3>Add New Sighting</h3>
                    <form class="form-horizontal" role="form" method="POST" action="createSighting">
                        <div class="form-group">
                            <label for="add-super-person" class="col-md-5 control-label">Superperson ID:</label>
                            <div class="col-md-7">
                                <div class="dropdown">
                                    <select name="heroId" required>
                                        <option value="no-option-selected" selected disabled> -- Select -- </option>
                                        <c:forEach var="currentHero" items="${allSuperheroes}">
                                            <option value="${currentHero.heroId}">
                                                <c:out value="${currentHero.heroName}"/>
                                        </c:forEach>
                                    </select>                    
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-location" class="col-md-5 control-label">Location ID:</label>
                            <div class="col-md-7">
                                <div class="dropdown">
                                    <select name="locationId" required>
                                        <option value="no-option-selected" selected disabled> -- Select -- </option>
                                        <c:forEach var="currentLocation" items="${allLocations}">
                                            <option value="${currentLocation.locationId}">
                                                <c:out value="${currentLocation.locationName}"/>
                                        </c:forEach>
                                    </select>                    
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-sighting-time" class="col-md-5 control-label">Sighting Date:</label>
                            <div class="col-md-5">
                                <input type="date" class="form-control" name="sightingTime" required/>
                            </div>
                        </div>
                       
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Create Sighting"/>
                            </div>
                        </div>
                    </form>
                    </sec:authorize>
                    </div>
                    <div></div>
                    <hr>  
                        <div>
                            <div>
                    <h3>Search Sightings by Date</h3>
                    <form class="form-horizontal" role="form" method="GET" action="searchSightings">
                        
                        <div class="form-group">
                            <label for="search-sighting-date" class="col-md-5 control-label">Sighting Date:</label>
                            <div class="col-md-5">
                                <input type="date" class="form-control" name="sightingTime"  required/>
                            </div>
                        </div>
                       
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Search Sightings"/>
                            </div>
                        </div>
                    </form>
                    </div>
                            <hr>
                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
