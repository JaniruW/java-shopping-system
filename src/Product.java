public abstract class Product {
    private String productID;
    private String productName;
    private int getNumberOfItems;
    private double price;

    public Product(String productID,String productName,int getNumberOfItems,double price){ //constructor for the product class
        this.productID=productID;
        this.productName=productName;
        this.getNumberOfItems=getNumberOfItems;
        this.price=price;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    } //set method for product id

    public void setProductName(String productName) {
        this.productName = productName;
    } // set method for product name

    public void setNumberOfItems(int getNumberOfItems) {
        this.getNumberOfItems = getNumberOfItems;
    } //set method for product quantity

    public void setPrice(double price) {
        this.price = price;
    } //set method for price

    public String getProductID() {
        return productID;
    } //get method for product id

    public String getProductName() {
        return productName;
    } //get method for product name

    public int getNumberOfItems() {
        return getNumberOfItems;
    } //get method for product quantity

    public double getPrice() {
        return price;
    } //get method for price



}
