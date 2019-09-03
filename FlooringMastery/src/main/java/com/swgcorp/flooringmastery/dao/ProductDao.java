package com.swgcorp.flooringmastery.dao;

import com.swgcorp.flooringmastery.dto.*;
import java.util.List;
import java.math.BigDecimal;

public interface ProductDao {

    public List<Product> getAllProducts() throws PersistenceException;

    public List<String> getAllProductTypesAsStrings() throws PersistenceException;

    public BigDecimal getCostPerSquareFoot(String productType) throws PersistenceException;

    public BigDecimal getLaborCostPerSquareFoot(String productType) throws PersistenceException;

    public boolean isProductTypeValid(String stringToValidate) throws PersistenceException;
}
