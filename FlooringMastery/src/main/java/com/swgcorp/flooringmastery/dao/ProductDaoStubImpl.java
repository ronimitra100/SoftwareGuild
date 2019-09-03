package com.swgcorp.flooringmastery.dao;

import com.swgcorp.flooringmastery.dto.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class ProductDaoStubImpl implements ProductDao {

    private Map<String, Product> products = new HashMap<String, Product>();
    public static final String PRODUCTS_FILE = "Products.txt";
    public static final String DELIMITER = ",";

    @Override
    public List<Product> getAllProducts() throws PersistenceException {
        List<Product> allProducts = new ArrayList<Product>();
        
        allProducts.add(new Product("Carpet",new BigDecimal("2.25"),new BigDecimal("2.10")));
        allProducts.add(new Product("Laminate",new BigDecimal("1.75"),new BigDecimal("2.10")));
        allProducts.add(new Product("Tile",new BigDecimal("3.50"),new BigDecimal("4.15")));
        allProducts.add(new Product("Wood",new BigDecimal("5.15"),new BigDecimal("4.75")));
        return allProducts;
    }

    @Override
    public List<String> getAllProductTypesAsStrings() throws PersistenceException {
        String[] allProductTypes = new String[]{"Carpet", "Laminate", "Tile", "Wood"};
        return Arrays.asList(allProductTypes);
    }

    @Override
    public BigDecimal getCostPerSquareFoot(String productType) throws PersistenceException {
         loadProductsFile();
        return products.get(productType).getCostPerSqFt();
    }

    @Override
    public BigDecimal getLaborCostPerSquareFoot(String productType) throws PersistenceException {
        loadProductsFile();
        return products.get(productType).getLaborCostPerSqFt();
    }

    @Override
    public boolean isProductTypeValid(String stringToValidate) throws PersistenceException {
        loadProductsFile();
        return products.keySet().contains(stringToValidate);
    }

    public void loadProductsFile() throws PersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(PRODUCTS_FILE)));

        } catch (FileNotFoundException e) {
            throw new PersistenceException("-_- Could not load products data into memory.", e);
        }

        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Product currentProduct = new Product(currentTokens[0], new BigDecimal(currentTokens[1]), new BigDecimal(currentTokens[2]));

            products.put(currentProduct.getProductType(), currentProduct);
        }

    }

}
