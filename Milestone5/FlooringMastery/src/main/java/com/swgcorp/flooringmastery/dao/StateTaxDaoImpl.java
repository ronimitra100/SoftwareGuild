package com.swgcorp.flooringmastery.dao;

import com.swgcorp.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StateTaxDaoImpl implements StateTaxDao {

    private Map<String, Tax> stateTaxes = new HashMap<String, Tax>();
    public static final String TAXES_FILE = "Taxes.txt";
    public static final String DELIMITER = ",";

    @Override
    public List<Tax> getTaxesForAllStates() throws PersistenceException {
        loadStateTaxes();
        return new ArrayList<Tax>(stateTaxes.values());
    }

    @Override
    public List<String> getAllStates() throws PersistenceException {
        loadStateTaxes();
        return new ArrayList<String>(stateTaxes.keySet());
    }

    @Override
    public BigDecimal getTaxForGivenState(String stateAbbreviation) throws PersistenceException {
        loadStateTaxes();
        return stateTaxes.get(stateAbbreviation).getTaxRate();
    }

    @Override
    public boolean isStateAbbrValid(String stringToValidate) throws PersistenceException {
        loadStateTaxes();
        return stateTaxes.containsKey(stringToValidate);
    }

    public void loadStateTaxes() throws PersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(TAXES_FILE)));

        } catch (FileNotFoundException e) {
            throw new PersistenceException("-_- Could not load state taxes data into memory.", e);
        }

        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Tax currentStateTax = new Tax(currentTokens[0], new BigDecimal(currentTokens[1]));

            stateTaxes.put(currentStateTax.getState(), currentStateTax);
        }
        scanner.close();
    }

}
