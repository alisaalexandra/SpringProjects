package com.productmanagement.ui;

import com.productmanagement.dao.ProductRepository;
import com.productmanagement.model.ManufacturerModel;
import com.productmanagement.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ProductUI {

    @Autowired
    private ProductRepository productDao;

    Scanner scanner = new Scanner(System.in);

    public void startApplication(){
        int option = -1;
        while(option != 0){
            System.out.println("1. Add new product");
            System.out.println("2. View all products");
            System.out.println("3. Update an existing product");
            System.out.println("4. Delete an existing product");
            System.out.println("5. Find product by ID");
            System.out.println("6. View products on page");
            System.out.println("7. Find product by manufacturer's name");
            option=scanner.nextInt();
            scanner.nextLine();
            if(option==1){
                addProduct();
            }else if(option==2){
                viewProducts();
            }else if(option==3){
                updateProduct();
            }else if(option==4){
                deleteProduct();
            }else if(option==5){
                findProductByID();
            }else if(option==6){
                viewProductsOnPage();
            }else if(option==7){
                findProductByManufacturerName();
            }
        }
    }

    public void addProduct(){
        ProductModel product = new ProductModel();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        product.setName(name);

        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        product.setDescription(description);

        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        product.setPrice(price);

        ManufacturerModel manufacturer = new ManufacturerModel();
        System.out.print("Enter manufacturer's name: ");
        String manufacturerName = scanner.next();
        manufacturer.setName(manufacturerName);

        System.out.print("Enter company name: ");
        String company = scanner.next();
        manufacturer.setCompany(company);

        System.out.print("Enter business name: ");
        String businessName = scanner.next();
        manufacturer.setBusinessName(businessName);

        System.out.print("Enter CUI: ");
        int cui = scanner.nextInt();
        manufacturer.setCompanyID(cui);

        product.setManufacturer(manufacturer);
        productDao.save(product);
    }

    public void viewProducts(){
        List<ProductModel> products = productDao.findAllByOrderByPrice();
        for (ProductModel product:products) {
            System.out.println("\n - PRODUCT ID: " + product.getId() + " - \nName: " +
                    product.getName() + "\nDescription: " + product.getDescription() + "\nPrice: " + product.getPrice());
            ManufacturerModel manufacturer = product.getManufacturer();
            if(manufacturer != null) {
                System.out.println("- Manufacturer -");
                System.out.println("Name: " +
                        manufacturer.getName() + "\nCompany: " + manufacturer.getCompany() + "\nBusiness name: " +
                        manufacturer.getBusinessName() + "\nCUI: " + manufacturer.getCompanyID());
                }else{
                    System.out.println();
                }
        }
    }

    public void updateProduct(){
        System.out.println("Enter product ID: ");
        int id = scanner.nextInt();
        ProductModel product = productDao.findById(id);
        System.out.println("Enter new price: ");
        double price = scanner.nextDouble();
        product.setPrice(price);
        productDao.save(product);
    }

    public void deleteProduct(){
        System.out.println("Enter product ID: ");
        int id = scanner.nextInt();
        productDao.deleteById(id);
    }

    public void findProductByID(){
        System.out.println("Enter product ID: ");
        int id = scanner.nextInt();
        ProductModel product = productDao.findById(id);
        System.out.println("\nName: " +
                product.getName() + "\nDescription: " + product.getDescription() + "\nPrice: " + product.getPrice());
        ManufacturerModel manufacturer = product.getManufacturer();
        if(manufacturer != null) {
            System.out.println("- Manufacturer -");
            System.out.println("Name: " +
                    manufacturer.getName() + "\nCompany: " + manufacturer.getCompany() + "\nBusiness name: " +
                    manufacturer.getBusinessName() + "\nCUI: " + manufacturer.getCompanyID());
        }else{
            System.out.println();
        }
    }

    public void viewProductsOnPage(){
        String nextPage = "N";
        int currentPage = 0;
        while (nextPage.equalsIgnoreCase("N")) {
            Pageable pageable = PageRequest.of(currentPage, 2);
            List<ProductModel> products = productDao.findAllByOrderByName(pageable);
            for (ProductModel product : products) {
                System.out.println("\n - PRODUCT ID: " + product.getId() + " - \nName: " +
                        product.getName() + "\nDescription: " + product.getDescription() + "\nPrice: " + product.getPrice());
                ManufacturerModel manufacturer = product.getManufacturer();
                if(manufacturer != null) {
                    System.out.println("- Manufacturer -");
                    System.out.println("Name: " +
                            manufacturer.getName() + "\nCompany: " + manufacturer.getCompany() + "\nBusiness name: " +
                            manufacturer.getBusinessName() + "\nCUI: " + manufacturer.getCompanyID());
                }else{
                    System.out.println();
                }
            }
            System.out.println("Enter N for next page: ");
            nextPage = scanner.nextLine();
            currentPage++;
        }
    }

    public void findProductByManufacturerName(){
        System.out.print("Enter manufacturer's name: ");
        String name = scanner.next();
        List<ProductModel> products = productDao.findAllByManufacturerName(name);
        for (ProductModel product:products) {
            System.out.println("(" + product.getId() + ") \nName: " +
                    product.getName() + "\nDescription: " + product.getDescription() + "\nPrice: " + product.getPrice());
        }

    }
}
