package com.sg.luckysevensjspservlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "LuckySevensServlet", urlPatterns = {"/LuckySevensServlet"})
public class LuckySevensServlet extends HttpServlet {

    int playerBalance;
    int currentRollNo;
    int maxPlayerBalance;
    int rollNumLinkedToMaxPlayerBalance;
    int diceSumAfterRollingTwice = 0;
    int totalNumberOfRolls = 0;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int bettingAmount = Integer.parseInt(request.getParameter("bettingAmount"));

        maxPlayerBalance = bettingAmount;
        playerBalance = bettingAmount;
        currentRollNo = 1;
        while (playerBalance >= 1) {
            if (getDiceSumAfterRollingTwice() == 7) {
                playerBalance += 4;
                if (playerBalance > maxPlayerBalance) {
                    maxPlayerBalance = playerBalance;
                    rollNumLinkedToMaxPlayerBalance = currentRollNo;
                }
            } else {
                playerBalance -= 1;
            }
            currentRollNo++;
        }
        
        totalNumberOfRolls = currentRollNo -1 ;
        

        request.setAttribute("bettingAmount", bettingAmount);
        request.setAttribute("totalNumberOfRolls", totalNumberOfRolls);
        request.setAttribute("maxPlayerBalance", maxPlayerBalance);
        request.setAttribute("rollNumLinkedToMaxPlayerBalance", rollNumLinkedToMaxPlayerBalance);

        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    int getDiceSumAfterRollingTwice() {
        Random randomizer = new Random();
        int dice1 = randomizer.nextInt(6) + 1;
        int dice2 = randomizer.nextInt(6) + 1;
        return dice1 + dice2;
    }

}
