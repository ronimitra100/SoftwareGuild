package com.sg.factorizorjspservlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ronim_000
 */

@WebServlet(urlPatterns = {"/FactorizorServlet"})
public class FactorizorServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
           List<Integer> factorList = new ArrayList<>();
           int factorSum=0;
           boolean isPrime=false;
           boolean isPerfect=false;
           
           String input = request.getParameter("numberToFactor");
           int numberToFactor = Integer.parseInt(input);
           
           for (int i=1; i< numberToFactor; i++){
               if (numberToFactor%i==0){
                   factorList.add(i);
                   factorSum += i;
               }
           }
           
           if (factorSum==numberToFactor){
               isPerfect = true;
           }
           
           if (factorSum==1){
               isPrime = true;
           }
           
           request.setAttribute("numberToFactor",numberToFactor);
           request.setAttribute("factors",factorList);
           request.setAttribute("isPerfect",isPerfect);
           request.setAttribute("isPrime",isPrime);
           
           RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
           rd.forward(request,response);
    }
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }


}
