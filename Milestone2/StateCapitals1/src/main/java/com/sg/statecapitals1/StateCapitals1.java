package com.sg.statecapitals1;
//import java.util.Collections;
import java.util.Collection;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;

public class StateCapitals1 {

    public static void main(String[] args) {
        HashMap<String, String> stateCapitalPairs = new HashMap<String, String>();

        stateCapitalPairs.put("Alabama", "Montogomery");
        stateCapitalPairs.put("Alaska", "Juneau");
        stateCapitalPairs.put("Arizona", "Phoenix");
        stateCapitalPairs.put("Arkansas", "Little Rock");

        Set<String> states = stateCapitalPairs.keySet();
        Collection<String> capitals = stateCapitalPairs.values();

        System.out.println("STATES:");
        System.out.println("=======");
        for (String state : states) {
            System.out.println(state);
        }

        System.out.println("\nCAPITALS");
        System.out.println("=========");
        for (String capital : capitals) {
            System.out.println(capital);
        }

        System.out.println("\nSTATE/CAPITAL PAIRS");
        System.out.println("====================");
        for (String state : states) {
            System.out.println(state + " - " + stateCapitalPairs.get(state));
        }
    }
    
}