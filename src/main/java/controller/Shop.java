package controller;

import entity.Product;
import entity.User;

import java.util.ArrayList;

public class Shop {
    private ArrayList<Product> products = new ArrayList<Product>();
    private ArrayList<User> users = new ArrayList<User>();

   public String createUser(User newUser){
       users.add(newUser);
        return "User created successfully!\n";
    }
    public ArrayList<User> getUsers(){
       return users;
    }

    public String removeUser(int userId){
        try {
            users.remove(userId);
        }catch (Exception ex){
            return "Unable to remove user";
        }
        return "User removed successfully!\n";
    }

    public String createProduct(Product product){
       products.add(product);
       return "Product added successfully!\n";
    }
    public ArrayList<Product> getAllProduct(){
       return products;
    }
    public Product getSingleProduct(int productId){
        return this.products.get(productId);
    }

}
