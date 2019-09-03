<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Home</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>Superhero Wiki</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"  class="active"><a href="${pageContext.request.contextPath}">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displaySuperheroesPage">Superheroes & Supervillains</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayOrganizationsPage">Organizations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayLocationsPage">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displaySightingsPage">Sightings</a></li>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displayUserList">User Management</a></li>                        
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
                <div class="col-md-6">
                    <h2>Welcome to Superhero Wiki !!!</h2>
                    <hr>
                    <p>
                        Welcome to Superhero Wiki, the authoritative source of information about all Superheroes and Supervillains.
                    </p>
                    <p>
                        Check out the following on this site:
                    <ul>
                        <li>List of Superheroes and Supervillains</li>
                        <li>List of Organizations that various Superheroes and Supervillains are affiliated with</li>
                        <li>List of Locations visited by Superheroes and Supervillains</li>
                        <li>List of Sightings of Superheroes and Supervillains</li>
                    </ul>
                    </p>
                    

                </div>
                <div class="col-md-6">
                    <h2>News Feed </h2>
                    <hr>
                    <h3>Check out the Recent Sightings ...</h3>
                    <table id="contactTable" class="table table-hover">
                        <tr>

                            <th width="20%">Superhero/Supervillain</th>
                            <th width="25%">SightingLocation</th>
                            <th width="20%">SightingTime</th>
                            <th width="20%"></th>

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
