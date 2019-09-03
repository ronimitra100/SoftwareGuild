package com.swgcorp.flooringmastery.dao;

import com.swgcorp.flooringmastery.dto.*;
import java.util.List;
import java.math.BigDecimal;

public interface StateTaxDao {

    public List<Tax> getTaxesForAllStates() throws PersistenceException;

    public List<String> getAllStates() throws PersistenceException;

    public BigDecimal getTaxForGivenState(String stateAbbreviation) throws PersistenceException;

    public boolean isStateAbbrValid(String stringToValidate) throws PersistenceException;
}
