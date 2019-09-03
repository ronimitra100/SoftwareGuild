<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                </ul>    
            </div>
            <div class="row">
                <div class="col-md-8">
                    <h2>List of Superheroes & Supervillains</h2>
                    <table id="contactTable" class="table table-hover">
                        <tr>
                            <th width="5%">ID</th>
                            <th width="15%">Name</th>
                            <th width="30%">Description</th>
                            <th width="30%">Superpower</th>
                            <th width="5%"></th>
                            <th width="5%"></th>
                        </tr>
                        <c:forEach var="currentSuperhero" items="${superheroList}">
                            <tr>
                                <td>
                                    <a href="displaySuperheroDetails?heroId=${currentSuperhero.heroId}">
                                    <c:out value="${currentSuperhero.heroId}"/> 
                                    </a>
                                </td>
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
                                    <a href="displayEditSuperheroForm?heroId=${currentSuperhero.heroId}">
                                    Edit
                                    </a>
                                </td>
                                <td>
                                    <a href="deleteSuperhero?heroId=${currentSuperhero.heroId}">
                                    Delete
                                    </a>
                                </td>
                            </tr>  
                        </c:forEach>
                    </table>
                </div>
                <div class="col-md-4">
                    <h2>Add New Superperson</h2>
                    <form class="form-horizontal" role="form" method="POST" action="createSuperhero">
                        <div class="form-group">
                            <label for="add-hero-name" class="col-md-4 control-label">Name:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="heroName" placeholder="Name of Superperson"/>
                                <errors path="heroName" cssClass="error"></errors>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-description" class="col-md-4 control-label">Description:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="heroDescription" placeholder="Description of Superperson"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-superpower" class="col-md-4 control-label">Superpower:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="heroSuperPower" placeholder="Superpower of Superperson"/>
                            </div>
                        </div>
                       
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Create Superperson"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>