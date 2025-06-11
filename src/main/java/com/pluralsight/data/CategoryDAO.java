package com.pluralsight.data;

import com.pluralsight.models.Category;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private BasicDataSource dataSource;

    public CategoryDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Category> getCategories(){
        // create a new array list to hold the results of the data retrieval
        ArrayList<Category> result =  new ArrayList<Category>();

        // create a connection to the database, add a prepare statement for the query, and execute the query to get results
        try(Connection c = dataSource.getConnection();
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

    public Category getCategoryByName(String categoryName){
        Category result = null;

        String query = """
                SELECT
                CategoryID,
                CategoryName
                FROM
                categories
                WHERE CategoryName = ?
                """;

        try (Connection c = dataSource.getConnection();
             PreparedStatement s = c.prepareStatement(query))
        {
            s.setString(1, categoryName);

            try(ResultSet queryResults = s.executeQuery()){
                if(queryResults.next()){
                    int categoryID = queryResults.getInt(1);

                    return new Category(categoryID, categoryName);
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
