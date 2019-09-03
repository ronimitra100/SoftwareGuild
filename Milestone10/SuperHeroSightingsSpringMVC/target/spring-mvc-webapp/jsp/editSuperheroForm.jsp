<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Superperson Page</title>
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
                </ul>     
            </div>
                      
                    <h2>Edit Superperson</h2>
                    <hr>
                    <sf:form class="form-horizontal" role="form" method="POST" modelAttribute="superhero" action="editSuperhero">
                        <div class="form-group">
                            <label for="add-hero-name" class="col-md-4 control-label">Name:</label>
                            <div class="col-md-8">
                                <sf:input type="text" class="form-control" name="heroName" path="heroName" placeholder="Name of Super Person"/>
                                <sf:errors path="heroName" cssClass="error"></sf:errors>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-hero-description" class="col-md-4 control-label">Description:</label>
                            <div class="col-md-8">
                                <sf:input type="text" class="form-control" name="heroDescription" path="heroDescription" placeholder="Description of Super Person"/>
                                <sf:errors path="heroDescription" cssClass="error"></sf:errors>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-hero-superpower" class="col-md-4 control-label">Superpower:</label>
                            <div class="col-md-8">
                                <sf:input type="text" class="form-control" name="heroSuperPower" path="heroSuperPower" placeholder="Superpower of Super Person"/>
                                <sf:errors path="heroSuperPower" cssClass="error"></sf:errors>
                                <sf:hidden path="heroId"/>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Update Superperson"/>
                            </div>
                        </div>
                    </sf:form>
                
      
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>