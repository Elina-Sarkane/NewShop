package controller;

import entity.Product;
import entity.User;
import types.UserType;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    Shop shop = new Shop();
    public void showHomeScreen(){
        String[] showMenu = {"Add user", "View users", "Remove user", "Add product", "View products", "Buy product", "Exit shop" };

        ImageIcon shop = new ImageIcon("shop.jpg");
        String chooseOption = (String) JOptionPane.showInputDialog(
            null,
            "Choose option:",
            "Welcome to the shop!",
            JOptionPane.INFORMATION_MESSAGE,
            shop,
            showMenu,
            showMenu[0]
        );

        if (chooseOption == showMenu[0]){
            collectUserInfoAndAddUser();
            showHomeScreen();
        }else if (chooseOption == showMenu[1]){
            showAllUsers();
            showHomeScreen();
        }else if (chooseOption == showMenu[2]){
            deleteUser();
            showHomeScreen();
        }else if (chooseOption == showMenu[3]){
            collectProductInfoAndCreate();
            showHomeScreen();
        }else if (chooseOption == showMenu[4]){
            showAllProducts();
            showHomeScreen();
        }else if (chooseOption == showMenu[5]){
            sellProduct();
            showHomeScreen();
        }else{
            System.exit(0);
        }
    }

    private void collectUserInfoAndAddUser() {
        System.out.println("Enter User Name: ");
        String userName = scanner.nextLine();
        System.out.println("Enter User Email: ");
        String userEmail = scanner.nextLine();
        System.out.println("Enter balance e.g 10.50: ");
        float balance = Float.parseFloat(scanner.nextLine());
        System.out.println("Enter User type (owner / buyer): ");
        UserType type = (scanner.nextLine().trim().toUpperCase().equals("OWNER"))
                ? UserType.OWNER : UserType.BUYER;

        LocalDate createdAt = LocalDate.now();

        User user = new User(userName, userEmail, balance, type, createdAt);
        System.out.println(shop.createUser(user));
    }
    private void showAllUsers(){
        ArrayList<User> users = shop.getUsers();
        System.out.println("\t\tAll users\n");
        for (User currentUser: users){
            System.out.println(currentUser.getUserName() +
                    " | " + currentUser.getUserEmail() +
                    " | " + currentUser.getBalance() +
                    " | " + currentUser.getType() +
                    " | " + currentUser.getCreatedAt());
        }

    }
    private void deleteUser(){
        System.out.println("\nRemove user\n");
        System.out.println("Enter user ID: ");

        int userId = scanner.nextInt();
        String message = shop.removeUser(userId);
        System.out.println(message);

    }
    private void collectProductInfoAndCreate(){
        Product product = new Product();
        System.out.println("\nEnter product Name: ");
        product.setName(scanner.nextLine());

        System.out.println("Product price e.g 10.50:");
        product.setPrice(Float.parseFloat(scanner.nextLine()));

        System.out.println("Enter quantity: ");
        product.setQuantity(Integer.parseInt(scanner.nextLine()));

        product.setId(UUID.randomUUID());
        product.setCreatedAt(LocalDate.now());

        System.out.println(shop.createProduct(product));
    }
    private void showAllProducts(){
        ArrayList<Product> products = shop.getAllProduct();
        System.out.println("\t\tAll products\n");
        for (Product currentProduct: products){
            System.out.println(currentProduct.getId() +
                    " | " + currentProduct.getName() +
                    " | " + currentProduct.getPrice() +
                    " | " + currentProduct.getQuantity() +
                    " | " + currentProduct.getCreatedAt());
        }
    }
    private void sellProduct(){
        System.out.println("\nChoose product:\n");
        showAllProducts();
        System.out.println("\nEnter product ID: ");
        int productId = Integer.parseInt(scanner.nextLine());
        Product product = shop.getSingleProduct(productId);

        System.out.println("Enter your balance: ");
        float balance = Float.parseFloat(scanner.nextLine());

        System.out.println("How many " + product.getName() + " do you want?");
        Integer amount = scanner.nextInt();

        System.out.println("The sum of " + amount + " " + product.getName() + " is " + (amount* product.getPrice()) + " euros");
        System.out.println("Your balance now is " + (balance-amount*product.getPrice() + " euros!"));
    }
}
