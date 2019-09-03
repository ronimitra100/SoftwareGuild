<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Sighting Search Results</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
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
                <div class="col-md-10">
                    <h2>Search Results for Sightings on Date <c:out value="${sightingTime}"/></h2>
                    <table id="contactTable" class="table table-hover">
                        <tr>
                            <th width="10%">SightingID</th>
                            <th width="20%">Superhero/Supervillain</th>
                            <th width="25%">SightingLocation</th>
                            <th width="20%">SightingTime</th>
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
                            </tr>  
                        </c:forEach>
                    </table>
               </div>
                
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
