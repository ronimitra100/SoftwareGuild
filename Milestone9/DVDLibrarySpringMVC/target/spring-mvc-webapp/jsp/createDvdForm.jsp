<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
    </head>
    <body>
<div class=container id="createDvdPage">
        <h1>Create DVD</h1>
        <hr/>
        <div class="form-group">
            <form class="form-horizontal" role="form" id="add-form" method ="POST" action="${pageContext.request.contextPath}/createDvd">
                <label for="add-dvd-title" class="col-md-4 control-label">Dvd Title</label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="dvdTitle" id="dvdTitleInput" required/>
                </div>
        </div>

        <div class="form-group">
            <label for="add-release-year" class="col-md-4 control-label">Release Year:</label>
            <div class="col-md-8">
                <input type="text" class="form-control" name="releaseYear" id="dvdReleaseYearInput" required/>
            </div>
        </div>

        <div class="form-group">
            <label for="add-director" class="col-md-4 control-label">Director:</label>
            <div class="col-md-8">
                <input type="text" class="form-control" name="director" id="dvdDirectorInput" required/>
            </div>
        </div>

        <div class="form-group">
            <label for="add-rating" class="col-md-4 control-label">Rating:</label>
            <div class="col-md-8">
                <!--<button class="dropbtn">Dropdown</button>-->
                <div>
            <select id="myList" name="rating">
               <option value = "1">G</option>
               <option value = "2">PG</option>
               <option value = "3">PG13</option>
               <option value = "4">R</option>
             </select>
                </div>
            </div>
        </div>

        <div class="form-group">
            <label for="add-notes" class="col-md-4 control-label">Notes:</label>
            <div class="form-group col-md-8">
                <input type="text" class="form-control" name="notes" id="dvdNotes" required/>
            </div>
        </div>

        <div class="form-group">
            <button type="button" id="cancelCreation">
                        Cancel
                    </button>
            <div class="form-group">
                <input type="submit" class="btn btn-default" value="Create Dvd"/>
            </div>
        </div>
        </form>
    </div>
    </body>
</html>