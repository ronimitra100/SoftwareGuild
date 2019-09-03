<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lucky Sevens</title>
    </head>
    <body>
        <h1>Lucky Sevens</h1>
        <p>Game Rules: Enter some money to start playing. 
            In every round, the computer will roll two dice virtually. 
            If the sum of dice turns out to be 4, you win $4,
            otherwise you lose $1.
        </p>
        <p>Please enter the amount of money you want to bet: </p>
        <form method="post" action="LuckySevensServlet">
            <input type="text" name="bettingAmount"/><br>
            <input type="submit" value="Start Playing!!!"/>
        </form>
    </body>
</html>
