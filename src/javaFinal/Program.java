package javaFinal;
//Artiom Sheremetiev and Niv Porat
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;



public class Program {

    public static void main(String[] args) throws FileNotFoundException{

    	try 
    	{
    	       final int maxCostumerSize = 100;
               final int maxInventory=1000;
               File fp = new File("D:\\product.txt");//load products from file with scanner
               Scanner scanner = new Scanner(fp);
               int productSize = scanner.nextInt();
               EShop eShop = new EShop();//shop to operate with
               Product products[] = new Product[productSize];


               for (int i = 0; i < productSize&&productSize<maxInventory; i++) 
               {
                 products[i] = new Product(scanner);
                 
               }
               
               Product[] inventory;//products to add from shop
               eShop.setShopInventory(products);
               eShop.setShopName("Supermarkus");
               inventory= products.clone();//no reference to inventory,must be separated
               eShop.setShopInventory(inventory);
               
               
               fp = new File("D:\\costumers.txt");//load customers from file with scanner
               scanner = new Scanner(fp);
               int costumerSize = scanner.nextInt();
               Customer customers[] = new Customer[costumerSize ];
               Cart carts[] = new Cart[costumerSize];
               
               
               for (int i = 0; i < costumerSize&&costumerSize<maxCostumerSize; i++) //init customers and carts in eShop
               {
                   customers[i] = new Customer(scanner);
                   carts[i] = new Cart(eShop, products.clone(),customers[i]);
                   customers[i].setMyCart(carts[i]);
                   eShop.addNewCart(customers[i].getMyCart());
                  
               }
               eShop.setShopCostumers(customers);
               
               
               
            JFrame mainFrame = new JFrame("Program");//main panel frame
       		mainFrame.setSize(480,300);//size of panel
       		mainFrame.setResizable(false);//non resizable
       		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close on X
       		mainFrame.setLayout(null);//place components where you want
       		JButton customerJButton =new JButton("Login Customer");//customer button
       		customerJButton.setBounds(20, 100, 200, 100);
       		JButton shopButtonbutton =new JButton("Supermarkus");//customer button
       		shopButtonbutton.setBounds(240, 100, 200, 100);
       		mainFrame.add(shopButtonbutton);
       		mainFrame.add(customerJButton);
       		mainFrame.setVisible(true);//show panel
       		
       		customerJButton.addActionListener(new ActionListener() {
   				@Override
   				public void actionPerformed(ActionEvent e) {
   					LoginFrame login = new LoginFrame(eShop);//if logged continue from login frame to customer frame
   					login.setTitle("Login");
   					login.setBounds(10, 10, 370,600);
   					login.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   					login.setVisible(true);
   				}
   			});

       		shopButtonbutton.addActionListener(new ActionListener() //shop management
       		{
   				
   				@Override
   				public void actionPerformed(ActionEvent e) {
   					EShopFrame eshop = new EShopFrame(eShop,inventory);
   					eshop.setTitle("Supermarkus");
   					eshop.setSize(1600,1000);
   					eshop.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   					eshop.setVisible(true);
   					
   					
   				}
   			});	
		} 
    	catch (Exception e) 
    	{
    		System.out.println("Cannot load file or null pointer"+"\n"+e.getMessage());
		}
     
 }
}