<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
                </ul>    
            </div>
            <div class="row">
                <div class="col-md-7">
                    <h2>List of Sightings</h2>
                    <table id="contactTable" class="table table-hover">
                        <tr>
                            <th width="10%">Sighting ID</th>
                            <th width="20%">Superhero/Supervillain</th>
                            <th width="25%">Sighting Location</th>
                            <th width="20%">Sighting Time</th>
                            <th width="10%"></th>
                            <th width="15%"></th>
                        </tr>
                        <c:forEach var="i" begin="0" end="${sightingList.size()-1}">
                            <tr>
                                <td>
                                    <a href="displaySightingDetails?sightingId=${sightingList[i].sightingId}">
                                    <c:out value="${sightingList[i].sightingId}"/> 
                                    </a>
                                </td>
   
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
                                    <a href="displayEditSightingForm?sightingId=${sightingList[i].sightingId}">
                                    Edit
                                    </a>
                                </td>
                                <td>
                                    <a href="deleteSighting?sightingId=${sightingList[i].sightingId}">
                                    Delete
                                    </a>
                                </td>
                            </tr>  
                        </c:forEach>
                    </table>
                </div>
                <div class="col-md-5">
                    <div>
                    <h3>Add New Sighting</h3>
                    <form class="form-horizontal" role="form" method="POST" action="createSighting">
                        <div class="form-group">
                            <label for="add-super-person" class="col-md-4 control-label">Superperson ID:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="heroId" placeholder="ID of Superperson" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-location" class="col-md-4 control-label">Location ID:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="locationId" placeholder="ID of Sighting Location" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-sighting-time" class="col-md-4 control-label">Sighting Date:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="sightingTime" placeholder="Date of Sighting in YYYY-MM-DD format" required/>
                            </div>
                        </div>
                       
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Create Sighting"/>
                            </div>
                        </div>
                    </form>
                    </div>
                    <div></div>
                    <hr>  
                        <div>
                            <div>
                    <h3>Search Sightings by Date</h3>
                    <form class="form-horizontal" role="form" method="GET" action="searchSightings">
                        
                        <div class="form-group">
                            <label for="search-sighting-date" class="col-md-4 control-label">Sighting Date:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="sightingTime" placeholder="Date of Sighting in YYYY-MM-DD format" required/>
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
