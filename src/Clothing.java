class Clothing extends Product {
    private String size;
    private String colour;

    public Clothing(String productID, String product_name, int getNumberOfItems, double price, String size, String colour) { //constructor for clothing class
        super(productID, product_name, getNumberOfItems, price);//calling the super class constructor
        this.size = size;
        this.colour = colour;
    }

    public void setSize(String size) { //set method for size
        this.size = size;
    }

    public void setColour(String colour) { //set method for colour
        this.colour = colour;
    }

    public String getSize() {
        return size;
    } //get method for size

    public String getColour() {
        return colour;
    } //get method for colour
}


