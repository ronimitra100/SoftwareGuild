<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>DVD Library Edit</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <sf:form class="form-horizontal" role="form" modelAttribute="dvd"
                 action="editDvd" method="POST">
            <div class="form-group">
                <label for="add-dvd-title" class="col-md-2 control-label">DVD Title</label>
                <div class="col-md-8">
                    <sf:input type="text" class="form-control" id="add-dvd-title"
                              path="dvdTitle" placeholder="DVD Title"/>
                    <sf:errors path="dvdTitle" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-release-year" class="col-md-2 control-label">Release Year:</label>
                    <div class="col-md-8">
                    <sf:input type="text" class="form-control" id="add-release-year"
                              path="releaseYear" placeholder="Release Year"/>
                    <sf:errors path="releaseYear" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-director" class="col-md-2 control-label">Director:</label>                          
                    <div class="col-md-8">
                    <sf:input type="text" class="form-control" id="add-director"
                              path="director" placeholder="Director"/>
                    <sf:errors path="director" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-rating" class="col-md-2 control-label">Rating:</label>
                    <div class="col-md-8">
                        <select id="rating" name="rating" placeholder="Rating">
                            <option value="NONE">--- Select ---</option>
                            <option value="G">G</option>
                            <option value="PG">PG</option>
                            <option value="PG13">PG13</option>
                            <option value="R">R</option>
                        </select>
                    <%--<sf:errors path="rating" cssclass="error"></sf:errors>--%>
                </div>
            </div>
            <div class="form-group">
                <label for="add-notes" class="col-md-2 control-label">Notes:</label>
                <div class="col-md-8">
                    <sf:input type="notes" class="form-control" id="add-notes"
                              path="notes" placeholder="Notes"/>
                    <sf:errors path="notes" cssclass="error"></sf:errors>
                    </div>
                </div>

            <sf:hidden path="dvdId"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-md-offset-2 col-md-1">
            <input type="submit" class="btn btn-default" value="Update DVD"/>
        </div>
    </div>
</sf:form>                
</div>
<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>