package com.sg.statecapitals2;

import java.util.Collection;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;

public class StateCapitals2 {
    public static void main(String[] args){
        HashMap<String,Capital> stateCapitalPairs = new HashMap<String,Capital>();
        defineStateCapitalMapping(stateCapitalPairs);
         
        displayStateCapitalPairs(stateCapitalPairs);
        
        UserInputOutput userInputOutput = new UserInputOutput();
        Integer minPopulation = userInputOutput.readInt("Please enter the lower limit for capital city population:");
        displayCapitalsWithPopulationExceeding(stateCapitalPairs, minPopulation);
    }
    
    public static void defineStateCapitalMapping(HashMap<String,Capital> stateCapitalPairs){
        stateCapitalPairs.put("Alabama", new Capital("Montogomery", 205000, 156));
        stateCapitalPairs.put("Alaska", new Capital("Juneau", 31000, 3255));
        stateCapitalPairs.put("Arizona", new Capital("Phoenix", 1445000, 517));
        stateCapitalPairs.put("Arkanas", new Capital("Little Rock", 193000, 116));
    }
    
    public static void displayStateCapitalPairs(HashMap<String,Capital> stateCapitalPairs){
        Set<String> states = stateCapitalPairs.keySet();
        
        System.out.println("STATE/CAPITAL PAIRS:");
        System.out.println("====================");
        for (String state: states){
            String capitalName = stateCapitalPairs.get(state).getName();
            Integer capitalPopulation = stateCapitalPairs.get(state).getPopulation();
            Integer capitalArea = stateCapitalPairs.get(state).getSquareMileage();
            System.out.println(state+" - "+ capitalName+" | Pop: "+capitalPopulation+" | Area: "+capitalArea+" sq mi");
        }
    }
    
    public static void displayCapitalsWithPopulationExceeding(HashMap<String,Capital> stateCapitalPairs, Integer minPopulation){
        Set<String> states = stateCapitalPairs.keySet();
        
        System.out.println("\nLISTING CAPITALS WITH POPULATIONS GREATER THAN "+minPopulation+":");
        
        for (String state: states){
            String capitalName = stateCapitalPairs.get(state).getName();
            Integer capitalPopulation = stateCapitalPairs.get(state).getPopulation();
            Integer capitalArea = stateCapitalPairs.get(state).getSquareMileage();
            if (capitalPopulation>minPopulation){
            System.out.println(state+" - "+ capitalName+" | Pop: "+capitalPopulation+" | Area: "+capitalArea+" sq mi");
            }
        }
    }
}
