<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Organizations</title>
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
                </ul>    
            </div>
            <div class="row">
                <div class="col-md-8">
                    <h2>List of Organizations</h2>
                    <table id="contactTable" class="table table-hover">
                        <tr>
                            <th width="2%">ID</th>
                            <th width="20%">Name</th>
                            <th width="32%">Description</th>
                            <th width="38%">Address</th>
                            <th width="4%"></th>
                            <th width="4%"></th>
                        </tr>
                        <c:forEach var="currentOrganization" items="${organizationList}">
                            <tr>
                                <td>
                                    <a href="displayOrganizationDetails?orgId=${currentOrganization.orgId}">
                                    <c:out value="${currentOrganization.orgId}"/> 
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${currentOrganization.orgName}"/>
                                </td>
                                <td>
                                    <c:out value="${currentOrganization.orgDescription}"/>
                                </td>
                                <td>
                                    <c:out value="${currentOrganization.orgAddress}"/>
                                </td>
                                <td>
                                    <a href="displayEditOrganizationForm?orgId=${currentOrganization.orgId}">
                                    Edit
                                    </a>
                                </td>
                                <td>
                                    <a href="deleteOrganization?orgId=${currentOrganization.orgId}">
                                    Delete
                                    </a>
                                </td>
                            </tr>  
                        </c:forEach>
                    </table>
                </div>
                <div class="col-md-4">
                    <h3>Add New Organization</h3>
                    <form class="form-horizontal" role="form" method="POST" action="createOrganization">
                        <div class="form-group">
                            <label for="add-org-name" class="col-md-4 control-label">Name:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="orgName" placeholder="Name of Organization" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-description" class="col-md-4 control-label">Description:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="orgDescription" placeholder="Description of Organization" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-address" class="col-md-4 control-label">Address:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" name="orgAddress" placeholder="Address of Organization" required/>
                            </div>
                        </div>
                       
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Create Organization"/>
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