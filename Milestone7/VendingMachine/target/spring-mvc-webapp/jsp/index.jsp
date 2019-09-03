<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Vending Machine</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/vendingmachine.css" rel="stylesheet">
    </head>
    <body>
        <div class="container zoomed-and-centered">

            <div>
                <h1 class="bolded">Vending Machine</h1>
                <hr/>
                <div class="container-fluid">
                    <div class="row">
                        <div  class="col-lg-8 col-lg-70">

                            <div class="row enlarged-font" id="item-list">
                                <c:forEach var="currentItem" items="${itemList}">
                                    <a href="${pageContext.request.contextPath}/selectItem?id=${currentItem.itemId}">
                                        <div class="col-lg-4 border border-dark item-border">
                                            <div  class="item-box">

                                                <p style="text-align:left" id="item-id" name="select">&nbsp<c:out value="${currentItem.itemId}"/></p>
                                                <h3 class="bolded"><c:out value="${currentItem.itemName}"/></h3>
                                                <p>$<c:out value="${currentItem.itemCost}" /></p>
                                                <br>
                                                <br>
                                                <p>Quantity Left: <c:out value="${currentItem.itemQuantity}" /></p>
                                            </div>
                                        </div>
                                    </a>

                                </c:forEach>
                            </div>

                        </div>
                        <div id="user-interaction-forms" class="col-lg-4 col-lg-30">
                            <form id="make-a-deposit-form" class="form-horizontal" action="addMoney" method="POST" >
                                <div class="form-group ">
                                    <div class="col-lg-8 col-lg-offset-2 ">
                                        <h3 class="bolded"><label for="deposit" >Total $ In</label></h3>
                                        <input  class="form-control enlarged-font" id="deposit" value="${currentDeposit}" readonly />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="form-group">
                                        <input type="submit"  name="money" value="Add Dollar" id="add-dollar-button" class="btn btn-primary btn-lg col-lg-offset-1 left-button"/>

                                        <input type="submit" name="money" value="Add Quarter"  id="add-quarter-button" class="btn btn-primary btn-lg col-lg-offset-1 right-button"/>

                                    </div>
                                    <div class="form-group">
                                        <input type="submit" name="money" value="Add Dime"  id="add-dime-button" class="btn btn-primary btn-lg col-lg-offset-1 left-button"/>

                                        <input type="submit" name="money" value="Add Nickel" id="add-nickel-button" class="btn btn-primary btn-lg right-button"/>

                                    </div>
                                </div>
                                <hr/>
                            </form>
                            <form id="make-purchase-form"  class="form-horizontal" action="makePurchase" method="POST">
                                <div class="form-group">
                                    <h3><label for="message">Messages</label></h3>
                                    <input  value="${message}" class="form-control enlarged-font" id="message-box" readonly/>
                                </div>
                                <div class="form-group">
                                    <label for="item-id" class="col-lg-4 control-label enlarged-font bolded">
                                        Item:
                                    </label>
                                    <div class="col-lg-8">
                                        <input type="submit" class="form-control  enlarged-font" id="item-id" value="${itemSelection}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <input type="submit" id="make-purchase-button" value="Make Purchase" class="btn btn-primary btn-lg middle-button"/>

                                </div>

                            </form>
                            <hr/>
                            <form id="get-change-form"  class="form-horizontal" action="returnChange" method="GET">
                                <div class="form-group">
                                    <h3><label for="change" class="bolded">Change</label></h3>
                                    <input  value="${changeInfo}" class="form-control col-lg-offset-2  enlarged-font" id="change-box"  readonly>
                                </div>
                                <div class="form-group">
                                    <input value="Change Return" type="submit" id="return-change-button" class="btn btn-primary btn-lg middle-button"/>

                                </div>
                                
                            </form>
                                <hr/>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>