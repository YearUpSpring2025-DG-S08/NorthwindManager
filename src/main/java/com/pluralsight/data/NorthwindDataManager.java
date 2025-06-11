package com.pluralsight.data;

import com.pluralsight.models.Category;
import com.pluralsight.models.Product;
import com.pluralsight.models.Supplier;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NorthwindDataManager {
    private final BasicDataSource dataSource;

    public NorthwindDataManager(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public List<Category> getCategories(){
        // create a new array list to hold the results of the data retrieval 
        ArrayList<Category> result =  new ArrayList<Category>();
        
        // create a connection to the database, add a prepare statement for the query, and execute the query to get results
        try( Connection c = dataSource.getConnection();
             PreparedStatement s = c.prepareStatement("SELECT CategoryID, CategoryName FROM categories");
             ResultSet queryResults = s.executeQuery()){
            
            // loop through the results and retrieve the necessary information from categories
            while(queryResults.next()){
                int categoryID = queryResults.getInt(1);
                String categoryName = queryResults.getString(2);
                
                // create a new instance of the model Category and add to the array list to be returned
                Category category = new Category(categoryID, categoryName);
                result.add(category);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public List<Product> getProducts(){
        // create a new array list to hold the results of the data retrieval
        ArrayList<Product> result =  new ArrayList<Product>();

        String query = """
                SELECT ProductID,
                ProductName,
                SupplierID,
                CategoryID,
                UnitPrice
                FROM northwind.products;
                """;
        
        // create a connection to the database, add a prepare statement for the query, and execute the query to get results
        try( Connection c = dataSource.getConnection();
             PreparedStatement s = c.prepareStatement(query);
             ResultSet queryResults = s.executeQuery()){

            // loop through the results and retrieve the necessary information from categories
            while(queryResults.next()){
                int productID = queryResults.getInt(1);
                String productName = queryResults.getString(2);
                int supplierID = queryResults.getInt(3);
                int categoryID = queryResults.getInt(4);
                double price = queryResults.getInt(5);

                // create a new instance of the model Category and add to the array list to be returned
                Product product = new Product(productID, productName, supplierID, categoryID, price);
                result.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public List<Product> getProductsByCategory(Category category){
        return null;
    }
    
    public List<Product> getProductsByPrice(double minPrice, double maxPrice){
        return null;
    }

    public List<Supplier> getSuppliers(){
        return new ArrayList<Supplier>();
    }
    
    public List<Product> getProductsBySupplier(Supplier supplier){
        return null;
    }
    
}
