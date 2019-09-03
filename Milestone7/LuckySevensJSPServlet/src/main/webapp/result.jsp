<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result</title>
    </head>
    <body>
        <p>You bet $${bettingAmount}.</p>
        <p>You are broke after ${totalNumberOfRolls} rolls.</p>
        <p>You should have quit after ${rollNumLinkedToMaxPlayerBalance} rolls when you had $${maxPlayerBalance}.</p>
    </body>
</html>
