import java.util.ArrayList;

public class ShoppingCart  {
    private ArrayList<Product>products;//Array list to save products

    public ShoppingCart(ArrayList<Product> cartProducts) { //creating a constructor
        this.products = cartProducts;
    }

    public void setProducts(ArrayList<Product> products) { //set method for arraylist
        this.products = products;
    }


    public ArrayList<Product> getProducts() { //get method for arraylist
        return products;
    }
    public void addProduct (Product product){
        products.add(product);

    }
    public void removeProduct(Product product){
        products.remove(product);
    }
    public double totalCost(){
        double totalcost=0;
        for(Product product:products){
            totalcost=product.getPrice();

        }
        return totalcost;
    }

}
