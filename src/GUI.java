import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class GUI extends JFrame{
    private JButton shoppingCartButton;
    private  JComboBox comboBoxButton;

    private JTable productTable;

    private JTextArea productDetailsArea;
    private JTextArea cartDetails;
    private JButton addtoCartButton;


    private ArrayList<Product> cartProducts;
    private HashMap<String,Integer>productCount;

    public GUI(ArrayList<Product>productsgui){

        cartProducts = new ArrayList<Product>();
        ShoppingCart shoppingCart = new ShoppingCart(cartProducts);//create a new instance of shopping cart with cart products list
        shoppingCart.setProducts(productsgui); //set products in shopping cart


        String[] productType = {"ALL", "Electronics", "Clothing"};//array of product types for combo box

        //construct components
        shoppingCartButton = new JButton ("Shopping Cart");//button view shopping cart
        comboBoxButton = new JComboBox (productType);//combo box for select product type
        addtoCartButton=new JButton("ADD to the cart");//button to add selected products


        shoppingCartButton.setBounds (750, 20, 200, 25);//set bounds for shopping cart button
        comboBoxButton.setBounds (450, 30, 100, 25);//set bounds for shopping combo box button
        addtoCartButton.setBounds(450,690, 150, 40);//set bounds for shopping add to cart button


        JPanel panel1=new JPanel();//creating a jpanel
        panel1.setBounds(0,0,1000,400);//creating bounds for the panel
        panel1.setBorder(new LineBorder(Color.BLACK,2));//setting border for panel1
        panel1.setLayout(null);//setting layout to null and allows to manual palcements of components

        JPanel panel2=new JPanel();//creating a new jpanel
        panel2.setBounds(0,0,1000,400);//creating bounds for the panel
        panel2.setBorder(new LineBorder(Color.BLACK,2));//setting border for panel1
        panel2.setLayout(null);//setting layout to null and allows to manual palcements of components

        createProductTable(productsgui);//passing the arraylist for this method

        JFrame frame= new JFrame();//create a new Jframe
        frame.setTitle("Westminster Shopping Center"); //setting a title for jframe


        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(1000,800);//setting size for thr frame
        frame.setVisible(true);//make frame visible
        frame.add(panel1);//adding panel1 to frame
        frame.add(panel2);//adding panel2 to frame
        panel1.add(shoppingCartButton);//adding shopping cart button to frame
        panel1.add(comboBoxButton);//adding combo box button to frame



        JScrollPane scrollPane=new JScrollPane(productTable);//create a scrollPane associated with product table
        productTable.setGridColor(Color.BLACK);//setting grid colour of the product table to black
        productTable.setRowHeight(40);//setting the row height

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();// Create a DefaultTableCellRenderer
        center.setHorizontalAlignment(JLabel.CENTER);//setting up the horizontal alignment to center
        productTable.setDefaultRenderer(Object.class,center);
        panel1.add(scrollPane);//adding the scrollPane to the panel1
        scrollPane.setBounds(75,100,700,200);//setting bounds for scroll pane

        comboBoxButton.addActionListener(e -> updateProductTable(productsgui));//actionListener to update product table based on combo box

        //ListSelectionListener to update text area based on the selected row of the table
        productTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                updateTextArea(productsgui);
            }
        });
        //ActionListener to open new frame to shopping cart when user clicks on shopping cart button
        shoppingCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCart(); // Call a method to open the shopping cart in a new frame
            }
        });
        //ActionListener to add selected products to shopping cart
        addtoCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToCart(productsgui);
                updateTextArea(productsgui);
            }

        });

        productDetailsArea = new JTextArea();//creating a text area
        productDetailsArea.setEditable(false);//set area not editable
        Font font = new Font("Arial", Font.PLAIN, 16); // change the font
        productDetailsArea.setFont(font);//set the created fon for the text are
        JScrollPane detail = new JScrollPane(productDetailsArea);//add a ScrollPane for the text are
        detail.setBounds(50,420,500,250);//setting bounds for the text area

        panel2.add(detail);//add text area to the panel 2
        panel2.add(addtoCartButton); //add cart button to the panel2

        DefaultTableCellRenderer renderer=new DefaultTableCellRenderer(){
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                //call the super class method
                Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if(row<productsgui.size()){ //check the row index within the range
                    Product product=productsgui.get(row); //get the row of the product
                    if(product.getNumberOfItems()<=3){ //check the if the quantity is less than 3
                        cellComponent.setBackground(Color.red); //set background colour to red
                        cellComponent.setForeground(Color.white);//set text color to white

                    }else{
                        cellComponent.setBackground(Color.white);//set background colour to white
                        cellComponent.setForeground(Color.BLACK);//set text color to black
                    }
                }
                if (isSelected) { //check if the cell is selected
                    cellComponent.setBackground(table.getSelectionBackground());
                    cellComponent.setForeground(table.getSelectionForeground());
                }

                return cellComponent; //rerun rendering component
            }
        };
        productTable.setDefaultRenderer(Object.class,renderer);// Set the custom renderer for the entire table

    }

    private void updateTextArea(ArrayList<Product>productsgui){
        int selectedRow= productTable.getSelectedRow();//get the index of the selected row
        if(selectedRow>=0){
            //getting the product information form the table
            String productID = (String) productTable.getValueAt(selectedRow, 0);
            String productName = (String) productTable.getValueAt(selectedRow, 1);
            String category = (String) productTable.getValueAt(selectedRow, 2);
            String price = (String) productTable.getValueAt(selectedRow, 3);
            String info = (String) productTable.getValueAt(selectedRow, 4);
            for (Product product : productsgui){
                // Check if the product ID matches the selected row's product ID and the product type is Electronics.
                if (product.getProductID().equals(productID) && product instanceof Electronics){
                    //update the text area with the product details
                    productDetailsArea.setText("Product Details"+"\nProduct ID: " + productID +
                            "\nCategory: " + category +
                            "\nProduct Name: " + productName +
                            "\nPrice: $" + price+
                            "\nBrand: " + ((Electronics) product).getBrand()+
                            "\nWarranty Period: "+((Electronics) product).getWarrantyPeriod()+
                            "\nAvailable Products: "+ product.getNumberOfItems());

                    //Check if the product ID matches the selected row's product ID and the product type is Electronics.
                } else if (product.getProductID().equals(productID) && product instanceof Clothing) {
                    //update the text area with the product details
                    productDetailsArea.setText("Product Details"+"\nProduct ID: " + productID +
                            "\nCategory: " + category +
                            "\nProduct Name: " + productName +
                            "\n Size: " + ((Clothing) product).getSize()+
                            "\nPrice: $" + price+
                            "\nColour: " + ((Clothing) product).getColour()+
                            "\nItems Available: "+ product.getNumberOfItems());
                }
            }
        }else {
            productDetailsArea.setText(" ");
        }
    }


    private void createProductTable(ArrayList<Product>productsgui) {
        Collections.sort(productsgui, (p1, p2) -> p1.getProductID().compareTo(p2.getProductID()));

        // Create a default table model
        DefaultTableModel tableModel = new DefaultTableModel();
        productTable = new JTable(tableModel);//creates Jtable
        productTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tableModel.addColumn("Product ID");
        tableModel.addColumn("Product Name");
        tableModel.addColumn("Category");
        tableModel.addColumn("Price");
        tableModel.addColumn("Info");

        // Populate the table model with product data
        for (Product product : productsgui) {
                if(product instanceof Electronics){
                    tableModel.addRow(new Object[]{product.getProductID(), product.getProductName(), "Electronics",
                            String.valueOf(product.getPrice()), ((Electronics) product).getBrand() + "," +
                            ((Electronics) product).getWarrantyPeriod() + " weeks warranty"});//add data to the row

                } else if (product instanceof Clothing) {
                    tableModel.addRow(new Object[]{product.getProductID(), product.getProductName(), "Clothing",
                            String.valueOf(product.getPrice()), ((Clothing) product).getColour() + "," +
                            ((Clothing) product).getSize()});//add data to the table row
                }
        }

    }

    private void updateProductTable(ArrayList<Product>productsgui) {
        DefaultTableModel tableModel = (DefaultTableModel) productTable.getModel();//get table model associated with product table
        tableModel.setRowCount(0);

        String selectedCategory = (String) comboBoxButton.getSelectedItem();//selected the selected category from the combo box

        for (Product product : productsgui) {//iterate through  the productsgui list
            //Checks if the selected category is ALL or matches the type of the product.
            if(selectedCategory.equals("ALL")||(selectedCategory.equals("Electronics")&&product instanceof Electronics)||
                    (selectedCategory.equals("Clothing")&& product instanceof Clothing)){
                String [] showproduct;

                //checks if the product id type of electronics
                if(product instanceof Electronics){
                    showproduct = new String[]{product.getProductID(), product.getProductName(), "Electronics",
                            String.valueOf(product.getPrice()), ((Electronics) product).getBrand() + "," +
                            ((Electronics) product).getWarrantyPeriod() + " weeks"};//creates a array with product details
                } else{
                    showproduct = new String[]{product.getProductID(), product.getProductName(), "Clothing",
                            String.valueOf(product.getPrice()), ((Clothing) product).getColour() + "," +
                            ((Clothing) product).getSize()};//creates a array with product details

                }
                tableModel.addRow(showproduct);//add the array for the table
            }
        }

    }
    private void addToCart(ArrayList<Product>productsgui){

        //Checks if the productCount map is null and initializes it with a new HashMap
        if(productCount==null){
            productCount= new HashMap<>();
        }
        int selectedRow= productTable.getSelectedRow();//get the index of the selected row
        if(selectedRow >= 0){//check if the a row is selected
            String productId= productTable.getValueAt(selectedRow,0).toString();//Retrieves the product ID from the selected row
            for(Product product:productsgui){
                //check if the current product is equals to selected product id
                if(product.getProductID().equals(productId)){
                    if(product.getNumberOfItems()==0){
                        JOptionPane.showMessageDialog(GUI.this,"Selected Product is out of stock","Product Unavailable",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int count=productCount.getOrDefault(productId,0);//get the current count of the selected product
                    productCount.put(productId,count+1);//updates the products
                    cartProducts.add(product);//add selected product to cart products
                    JOptionPane.showMessageDialog(GUI.this,"Product added to the cart","Product added",JOptionPane.INFORMATION_MESSAGE);
                    product.setNumberOfItems(product.getNumberOfItems()-1);//update the number of available items for the selected product

                    break;
                }
            }

        }else {
            JOptionPane.showMessageDialog(GUI.this,"Please select product","No product Selected",JOptionPane.WARNING_MESSAGE);
        }
    }
    private void openCart(){
        JFrame cartFrame = new JFrame("Shopping Cart"); //create a Jframe for the shoppingcart
        JPanel cartPanel= new JPanel();//create a cart panel
        JPanel cartPanel2 = new JPanel();//create a cart panel

        cartFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        cartFrame.setResizable(false);//disable the ability to resize
        cartFrame.setSize(600,600);//set the size ofr the frame
        cartFrame.setVisible(true);//set the cart frame visible

        cartPanel.setBounds(0,0,600,300);//set bounds cart panel
        cartPanel.setLayout(null);//disable the layout for the cart panel

        cartPanel2.setBounds(0,0,600,300);//set bounds cart panel
        cartPanel2.setLayout(null);//disable the layout for the cart panel


        DefaultTableModel cartTable = new DefaultTableModel();//create a default table model
        JTable cartJTable = new JTable(cartTable);//creates a Jtable
        JScrollPane cartScrollPane = new JScrollPane(cartJTable);//create a scroll pane
        cartJTable.setRowHeight(70);//add the height for the row
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);//center align the content of the cells
        cartJTable.setDefaultRenderer(Object.class, centerRenderer);//Sets the default cell renderer for the shopping cart table

        cartTable.addColumn("Product ");
        cartTable.addColumn("Quantity");
        cartTable.addColumn("Price");

        ArrayList<String>uniqueProducts=new ArrayList<>();
        int Total1=0;
        int Total2=0;
        double FinalTotal=0;
        double discountPrice=0;
        int count=0;
        double FinalPrice=0;

        for (Product product : cartProducts) {

            if(product instanceof Electronics){
                String productID=product.getProductID();//get the product id
                if(!uniqueProducts.contains(productID)){ //checks if the product id is already in the table
                    count= productCount.getOrDefault(productID,0);//get the product count
                    cartTable.addRow(new Object[]{product.getProductID()+" "+product.getProductName()+"\n"+" "+((Electronics) product).getBrand()+" "+((Electronics) product).getWarrantyPeriod(), count, product.getPrice()*count+" £"});//add to the row
                    Total1 += (int) (product.getPrice()*count);
                }
                uniqueProducts.add(productID);

            } else if (product instanceof Clothing) {
                String productID=product.getProductID();//get the product id
                if(!uniqueProducts.contains(productID)){//checks if the product id is already in the table
                    count= productCount.getOrDefault(productID,0);//get the product count
                    cartTable.addRow(new Object[]{product.getProductID()+" "+product.getProductName()+"\n"+((Clothing) product).getSize()+" "+((Clothing) product).getColour(), count, product.getPrice()*count+" £"});
                    Total2 += (int) (product.getPrice()*count);

                }
                uniqueProducts.add(productID);
            }
            FinalTotal=Total1+Total2;
            if(count>=3){
                discountPrice=(FinalTotal*20)/100;

            }
            FinalPrice=FinalTotal-discountPrice;
        }

        cartDetails = new JTextArea();//adding the text area
        cartDetails.setEditable(false);//set editable false
        Font font = new Font("Arial", Font.PLAIN, 20); //set font
        cartDetails.setFont(font);
        JScrollPane cartDetail = new JScrollPane(cartDetails);//creates a Jscrollpane

        cartDetail.setBounds(50,350,500,150);//set bounds for the text area

        cartDetails.setText("                                                            Total        :"+FinalTotal+"£"+"\n"+
                "   Three Item in same category discount(20%)   :"+discountPrice+"£"+"\n"+
                "                                                       Final Total    :"+FinalPrice+"£");
        cartPanel2.add(cartDetail);//add details to the cart

        cartScrollPane.setBounds(40,15,450,200);
        cartPanel.add(cartScrollPane);

        cartFrame.add(cartPanel);//add cartpanel to the frame
        cartFrame.add(cartPanel2);//add cartpanel2 to the frame
    }


}
