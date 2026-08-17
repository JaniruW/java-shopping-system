import java.util.*;
public class Main {
    public static void main(String[]args){

        Scanner input= new Scanner(System.in);//creating a scanner object
        //creating a instance of shopping manager
        WestminsterShoppingManager westminsterShoppingManager=new WestminsterShoppingManager();
        boolean run =true;


        while(run){
            try{
                System.out.println("\n            Menu"); //printing the menu options
                System.out.println("       1.Add a new Product");
                System.out.println("       2.Delete a Product");
                System.out.println("       3.Print the list of the products");
                System.out.println("       4.save File");
                System.out.println("       5.Load TextFile Data to the Program");
                System.out.println("       6.Load GUI");
                System.out.println("       7.Exit");
                System.out.print("\nEnter the Menu Option : ");

                int option=input.nextInt();//getting the user menu input
                switch (option){
                    case 1:
                        addProduct(input,westminsterShoppingManager);//passing a scanner input and westminster shopping manager instance
                        break;
                    case 2:
                        deleteProduct(input,westminsterShoppingManager);
                        break;
                    case 3:
                        printProducts(westminsterShoppingManager);
                        break;
                    case 4:
                        fileProduct(westminsterShoppingManager);
                        break;
                    case 5:
                        westminsterShoppingManager.loadTextFile();
                        break;
                    case 6:
                        westminsterShoppingManager.GUI();
                        break;
                    case 7:
                        System.out.println("Thank you");
                        run=false;
                        break;
                }

            }catch (InputMismatchException e){    //exception handling
                System.out.println("Enter valid Menu Input");
                input.next();

            }
        }
    }
    public static void addProduct(Scanner input,WestminsterShoppingManager westminsterShoppingManager){  //crating a method to add products

        while(true){
            try{
                System.out.print("Enter the Product Id : ");
                String productID=input.next(); // getting product Id
                if(!westminsterShoppingManager.Unique(productID)){
                    System.out.println("ProductID already existed\n");
                    return;
                }
                if(westminsterShoppingManager.getProductList().contains(productID)){  //checking if the id is already in the product list
                    System.out.println("Product already existed\n");
                    continue;
                }

                System.out.print("Enter the Product name : ");
                String productName=input.next(); // getting product name

                System.out.print("Enter the Product Stock : ");
                int productStock=input.nextInt(); // getting product available quantity

                System.out.print("Enter the Product Price £: ");
                double productPrice=input.nextDouble(); // getting product price

                System.out.println("Select Type\n 1.Electronics\n 2.Clothing");
                int productType= input.nextInt(); // getting product type

                if(productType==1) {
                    System.out.print("Enter the product Brand :");
                    String productBrand = input.next();  // getting product brand
                    if(!isValid(productBrand)){  //calling a method to check if the brand name contains any characters
                        System.out.println("Brand must contains a character\n");
                        return;
                    }

                    System.out.print("Enter the Product Warranty Period in weeks:");
                    int productWarranty = input.nextInt(); // getting product warranty period

                    //creating a instance of electronics
                    Electronics electronics = new Electronics(productID, productName, productStock, productPrice, productBrand, productWarranty);

                    westminsterShoppingManager.addNewProduct(electronics);// passing the electronic product

                } else if (productType==2) { //checking the product type
                    System.out.print("Enter the product Size : ");
                    String productSize = input.next(); // getting thr product size
                    if(!isValid(productSize)){  //calling a method to check if the brand name contains any characters
                        System.out.println("Size must contains a character\n");
                        return;
                    }

                    System.out.print("Enter the Product Colour : ");
                    String productColour = input.next(); //getting a product colour
                    if(!isValid(productColour)){  //calling a method to check if the brand name contains any characters
                        System.out.println("Colour must contains a character\n");
                        return;
                    }

                    //creating  instance of clothing
                    Clothing cloths = new Clothing(productID,productName,productStock,productPrice,productSize,productColour);
                    westminsterShoppingManager.addNewProduct(cloths); // passing the clothing product

                }else {
                    System.out.println("Please enter valid input \n Enter 1: for add Electronics\n Enter 2: for add Cloths");
                }
                break;
            }catch (InputMismatchException e){  //handling invalid inputs
                System.out.println("Invalid Input Value enter again\n");
                input.next();
            }

        }


    }
    public static void deleteProduct(Scanner input,WestminsterShoppingManager westminsterShoppingManager){  //creating a method to delete products
        if(westminsterShoppingManager.getProductList().isEmpty()){
            System.out.println("Shop is empty"); //checking if the product list is empty

        }else{
            System.out.print("Enter the Product Id : ");
            String productID=input.next(); // getting the product id which needs to delete
            westminsterShoppingManager.deleteProduct(String.valueOf(productID)); //passing the value


        }
    }
    public static void printProducts(WestminsterShoppingManager westminsterShoppingManager){ //method for print products
        westminsterShoppingManager.printProduct(); //calling the print product method in westminster manager

    }
    public static void fileProduct(WestminsterShoppingManager westminsterShoppingManager){
        westminsterShoppingManager.file(); //calling the file method in westminster manager
    }
    public static boolean  isValid(String character) { //method for checking user inputs contains string values

        for (char c : character.toCharArray()) {
            if (Character.isLetter(c)) {

                return true;

            }
        }
        return false;
    }
}














