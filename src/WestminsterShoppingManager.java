import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.io.*;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {

    private ArrayList<Product> productList;

    public WestminsterShoppingManager() { //constructor for the westminster shopping manager class
        this.productList = new ArrayList<>();
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }
    public boolean Unique(String productId) { //method to check the validity of the product id
        for (Product product : productList) {
            if (product.getProductID().equals(productId)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void addNewProduct(Product product) { //method for add products
        if (productList.size() < 50 && Unique(product.getProductID())) {
            productList.add(product); // adding the product
            System.out.println(product.getProductID()+" Product added to the System");
        } else {
            System.out.println("You cant add more products maximum number of products reached");
        }
    }

    @Override
    public void deleteProduct(String productID) { //method for delete product
        boolean productFound=false;

        if (productList.isEmpty()) {  //checking if the list is empty
            System.out.println("The Shop is Empty");
        } else
        {
            for (Product product : productList) { // for loop fo check the product id
                if (product.getProductID().equals(productID)) {
                    String deletedProductID = product.getProductID() + " " + product.getProductName(); //assigning the name and the id for a variable
                    productList.remove(product); //removing the product
                    if (product instanceof Clothing){ //checking if the product is instance of clothing
                        System.out.println("Product Type: Clothing\n"+deletedProductID+" Product Successfully Deleted");
                    } else if (product instanceof Electronics) { //checking if the product is instance of electronics
                        System.out.println("Product Type: Electronics\n"+deletedProductID+" Product Successfully Deleted");
                    }
                    productFound=true; // assassin true if the product found
                    break;
                }
            }
            if (!productFound) { //checking if the user enter a valid id
                System.out.println(productID + " Please Enter valid ProductID, ProductId Not Found ");
            }
        }
    }

    @Override
    public void printProduct() { //method for print products
        Collections.sort(productList, (p1, p2) -> p1.getProductID().compareTo(p2.getProductID())); //sorting the arraylist
        for (Product product : productList) { // for loop for get product one by one
            System.out.println("\nProduct ID: " + product.getProductID());
            System.out.println("Product Name: " + product.getProductName());
            System.out.println("Available: " + product.getNumberOfItems());
            System.out.println("Price: £" + product.getPrice());
            if (product instanceof Electronics) { //checking if the product is type electronics
                System.out.println("Type: Electronics");
                System.out.println("Brand: " + ((Electronics) product).getBrand());
                System.out.println("Warranty Period In Weeks: " + ((Electronics) product).getWarrantyPeriod());
            } else if (product instanceof Clothing) { //checking if the product is type clothing
                System.out.println("Type: Clothing");
                System.out.println("Size: " + ((Clothing) product).getSize());
                System.out.println("Colour: " + ((Clothing) product).getColour());
            }
            System.out.println("-------------------------------------");
        }
    }

    @Override
    public void  file() { //creating a method to write products for the text file
        try {
            FileWriter writer= new FileWriter("Products.txt"); //creating a writer object
            boolean productsWrote=false;

            for (Product product : productList){
                if (product instanceof Electronics) {
                    writer.write("\nType: Electronics");
                    writer.write("\nProduct_ID: " + product.getProductID());
                    writer.write("\nProduct Name: " + product.getProductName());
                    writer.write("\nAvailable Number Of Products: " + product.getNumberOfItems());
                    writer.write("\nPrice: " + product.getPrice());
                    writer.write("\nBrand: " + ((Electronics) product).getBrand());
                    writer.write("\nWarranty Period In Weeks: " + ((Electronics) product).getWarrantyPeriod());
                } else if (product instanceof Clothing) {
                    writer.write("\nType: Clothing");
                    writer.write("\nProduct_ID: " + product.getProductID());
                    writer.write("\nProduct Name: " + product.getProductName());
                    writer.write("\nAvailable Number Of Products: " + product.getNumberOfItems());
                    writer.write("\nPrice: " + product.getPrice());
                    writer.write("\nSize: " + ((Clothing) product).getSize());
                    writer.write("\nColour: " + ((Clothing) product).getColour());
                }
                writer.write("\n-------------------------------------");
                productsWrote=true;
            }
            writer.close(); //closing the writer
            if(productsWrote){
                System.out.println("Data Wrote Successfully");
            }else{
                System.out.println("No Products available in the system");
            }
        }

        catch (Exception e) {
            System.out.println("Text file not Found");
    }
    }

    public void loadTextFile() {

        try (BufferedReader reader = new BufferedReader(new FileReader("Products.txt"))){

            String line;
            Product currentProduct = null;

            while ((line = reader.readLine()) != null) { //create a loop for loop through the lines in text files

                if (line.equals("-------------------------------------")) { //check the line separate
                    if (currentProduct != null) {
                        productList.add(currentProduct);
                        currentProduct = null;
                    }
                    continue;
                }
                String[] parts = line.split(": "); //split the line into two parts by ": "

                if (parts.length == 2) { //checks the parts of the line
                    String attributeName = parts[0].trim(); //assign fist part of the line as attribute name
                    String attributeValue = parts[1].trim();//assign second part of the line as attribute value

                    if (attributeName.equals("Type") && attributeValue.equals("Electronics") && currentProduct == null) {
                        //create a new electronic product
                        currentProduct = new Electronics(null, null, 0, 0.0, null, 0);
                    } else if (attributeName.equals("Type") && attributeValue.equals("Clothing") && currentProduct == null) {
                        //create a new clothing product
                        currentProduct = new Clothing(null, null, 0, 0.0, null, null);
                    }
                    switch (attributeName) { //checking the attribute
                        case "Type":
                            break;
                        case "Product_ID": //checking if the attribute is Product_ID
                            if (currentProduct != null) { //check if the product is null
                                currentProduct.setProductID(attributeValue);//set the product_Id with attribute value
                            }
                            break;
                        case "Product Name":
                            if (currentProduct != null) { //check if the product is null
                                currentProduct.setProductName(attributeValue);//set the product name with attribute value
                            }
                            break;
                        case "Available Number Of Products":

                            if (currentProduct != null) { //check if the product is null
                                currentProduct.setNumberOfItems(Integer.parseInt(attributeValue));//set the number of items with attribute value
                            }
                            break;
                        case "Price":

                            if (currentProduct != null) { //check if the product is null
                                currentProduct.setPrice(Double.parseDouble(attributeValue));//set the product price with attribute value
                            }
                            break;
                        case "Brand":
                            if (currentProduct != null) { //check if the product is null
                                assert currentProduct instanceof Electronics;
                                ((Electronics) currentProduct).setBrand(attributeValue);//set the product brand with the attribute value
                            }
                            break;
                        case "Warranty Period In Weeks":

                            if (currentProduct != null) { //check if the product is null
                                assert currentProduct instanceof Electronics;
                                ((Electronics) currentProduct).setWarrantyPeriod(Integer.parseInt(attributeValue));//set the product warranty with the attribute value
                            }
                            break;

                        case "Size":
                            if (currentProduct != null) { //check if the product is null
                                assert currentProduct instanceof Clothing;
                                ((Clothing) currentProduct).setSize(attributeValue);} //set the product size with the attribute
                            // with the attribute value

                            break;
                        case "Colour":
                            if (currentProduct != null) { //check if the product is null
                                assert currentProduct instanceof Clothing;
                                ((Clothing) currentProduct).setColour(attributeValue);//set the product color with the attribute value
                            }
                            break;
                    }
                }
            }
            System.out.println("Successfully Loaded to the system");
        } catch (IOException e) {
            System.out.println("Text File not Found");}
    }
    public void GUI(){
        new GUI(productList);

    }
}





