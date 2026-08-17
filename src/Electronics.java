class Electronics extends Product {
    private String brand;
    private int warrantyPeriod;


    public Electronics(String productID, String product_name, int getNumberOfItems, double price, String brand, int warrantyPeriod) { //constructor for electronics class
        super(productID, product_name, getNumberOfItems, price); //calling the constructor of the super class
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    } //set method for brand

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    } //set method for warranty period

    public String getBrand() {
        return brand;
    } //get method for brand

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }  //get method for warranty period

}
