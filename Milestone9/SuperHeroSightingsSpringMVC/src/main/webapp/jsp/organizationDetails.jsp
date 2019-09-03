<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Organization Details</title>
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
                <h2>
                    <c:out value="${organization.orgName}"/> : Organization Details
                </h2>
                <hr>
                <p>
                    Description: <c:out value="${organization.orgDescription}"/>
                </p>
                <p>
                    Address: <c:out value="${organization.orgAddress}"/>
                </p>
                <p>
                    Superpersons affiliated with Organization: <c:forEach var="currentSuperhero" items="${superheroList}">
                           
                                              <c:out value="${currentSuperhero.heroName}"/> ,
                                
                                         </c:forEach>
                </p>
                <hr>
                <div class="row">
                    <sec:authorize access="hasRole('ROLE_SIDEKICK')">
                <div class="col-md-4">
                    <h3>Add a New Member</h3>
                    <hr>
                    <form class="form-horizontal" role="form" method="POST" action="addMemberToOrg?orgId=${organization.orgId}">
                        
                        <div class="form-group">
                            <label for="heroId" class="col-md-4 control-label">Superhero ID:</label>
                            <div class="col-md-8">
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
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Associate Member"/>
                            </div>
                        </div>
                    </form>
                    </div>
                    
                    <div class="col-md-4 col-md-offset-4">
                    <h3>Delete an Existing Member</h3>
                    <hr>
                    <form class="form-horizontal" role="form" method="POST" action="deleteMemberFromOrg?orgId=${organization.orgId}">
                        
                        <div class="form-group">
                            <label for="heroId" class="col-md-4 control-label">Superhero ID:</label>
                            <div class="col-md-8">
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
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Dissociate Member"/>
                            </div>
                        </div>
                    </form>
                    </div>
                    </sec:authorize>
                </div>
                
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
