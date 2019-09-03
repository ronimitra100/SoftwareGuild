<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <title>Dvd Library</title>
    <link href="css/bootstrap.css" rel="stylesheet">
</head>

<body>
    <div class=container id="homePage">
        <h1 id="homeHeading">DVD Library</h1>
        <div class="col-md-3" id="createButton"><a class="button" href="${pageContext.request.contextPath}/jsp/createDvdForm.jsp">Create</a></button></div>
        <form action="/search/dvds" method="POST">
       <div class="col-md-3" id="createButton"><a class="button" href="${pageContext.request.contextPath}/search.jsp">Search</a></button></div>
        <div class="col-md-3">
            <label>Search Category</label>
            <select name="search-category">
            <option value="no-option-selected" selected disabled>Search Category</option>
            <option value="title">Title</option>
            <option value="releaseYear">Release Year</option>
            <option value="director">Director Name</option>
            <option value="rating">Rating</option>
            </select>
        </div>
        <div class="col-md-3">
            Search Term <input type="text" name="search-term">          
        </div>
        </form>
        <div id="mainTableContainer">
            <table id="mainTable" style="border: 1px solid black" class="col-md-12 table-bordered table-striped">
                <tr>
                    <th>
                        Title
                    </th>
                    <th>
                        Release Date
                    </th>
                    <th>
                        Director
                    </th>
                    <th>
                        Rating
                    </th>
                    <th>
                        Options
                    </th>
                    <th></th>
                </tr>
                        <c:forEach var="currentDvd" items="${dvdList}">
                            <tr>
                                <td>
                                    <a href="displayDvdDetails?dvdId=${currentDvd.dvdId}">
                                        <c:out value="${currentDvd.dvdTitle}"/>
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${currentDvd.releaseYear}"/>
                                </td>
                                <td>
                                    <c:out value="${currentDvd.director}"/>
                                </td>
                                <td>
                                    <c:out value="${currentDvd.rating}"/>
                                </td>
                                <td>
                                    <a href="displayEditDvdForm?dvdId=${currentDvd.dvdId}">
                                        Edit
                                    </a>
                                </td>
                                <td>
                                    <a href="deleteDvd?dvdId=${currentDvd.dvdId}">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
            </table>
        </div>

    </div>


    <script src="js/jquery-3.1.1.min.js"></script>
    <script src="js/dvdList.js"></script>
</body>

</html>