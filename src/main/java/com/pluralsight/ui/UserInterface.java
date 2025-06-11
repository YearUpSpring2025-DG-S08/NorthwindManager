package com.pluralsight.ui;

import com.pluralsight.data.CategoryDAO;
import com.pluralsight.data.ProductDAO;
import com.pluralsight.data.SuppliersDAO;
import com.pluralsight.models.Category;
import com.pluralsight.models.Employee;
import com.pluralsight.models.Product;

import java.util.List;

public class UserInterface {

    private Employee currentEmployee;
    private final Console console;
    private final CategoryDAO categoryDAO;
    private final ProductDAO productDAO;
    private final SuppliersDAO suppliersDAO;
    

    public UserInterface(CategoryDAO categoryDAO, ProductDAO productDAO, SuppliersDAO suppliersDAO) {
        this.console = new Console();
        this.categoryDAO = categoryDAO;
        this.productDAO = productDAO;
        this.suppliersDAO = suppliersDAO;
    }

    public  void display(){
        System.out.println("Welcome to the Northwind Manager!");
        currentEmployee = loginUser();
        System.out.println("Welcome, " + this.currentEmployee.getFirstName() + "!");


        showHomeMenu();
    }


    private Employee loginUser(){
        console.promptForString("Just hit enter to login as Damian", true);
        return new Employee(1, "Damian", "Garcia");
    }

    private void showHomeMenu(){
        while(true){
            String[] menuOptions = {
                "list product categories",
                "List all products",
                "list products by category",
                "list products by price",
                "List all suppliers",
                "List products by supplier", 
                "exit"
            };
            
        int userChoice = console.promptForOption(menuOptions);
        switch(userChoice){
            case 1:
                listAllCategories();
                break;
            case 2:
                listAllProducts();
                break;
            case 3:
                listProductsByCategory();
                break;
            case 4:
                listProductsByPrice();
                break;
            case 5:
                listAllSuppliers();
                break;
            case 6:
                listProductsBySupplier();
                break;
            case 7:
                System.exit(0);
                
        }

            console.promptForString("Press <ENTER> to continue...", true);
        
        }
        
    }

    private void listAllCategories() {
        List<Category> categories = categoryDAO.getCategories();
        if(categories.stream().count() <= 0){
            System.out.println("There are no categories found");
        } else{
            categories.forEach(c -> System.out.println(c.getCategoryName()));
        }
    }

    private void listAllProducts() {
        List<Product> products = productDAO.getProducts();
        displayProducts(products);
        
    }

    private void listProductsByCategory() {
        String categoryName = console.promptForString("Enter a category name: ");
        Category category = categoryDAO.getCategoryByName(categoryName);
        if(category != null){
//            System.out.println("You selected category ID: " + category.getId());
            List<Product> productsByCategory = productDAO.getProductsByCategory(category);
            displayProducts(productsByCategory);
        } else {
            System.out.println("There is no record of this category");
        }
    }

    private void listProductsByPrice() {
        double minPrice = console.promptForDouble("Enter a minimum price: ");
        double maxPrice = console.promptForDouble("Enter a maximum price: ");
        
        List<Product> productsByPrice = productDAO.getProductsByPrice(minPrice, maxPrice);
        displayProducts(productsByPrice);
    }

    private void listAllSuppliers() {
        
    }

    private void listProductsBySupplier() {
        
    }
    
    private void displayProducts(List<Product> products){
        if(products.stream().count() <= 0){
            System.out.println("There are no products found");
        } else{
            products.forEach(System.out::println);
        }
    }
}
