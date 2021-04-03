package javaFinal;
//Artiom Sheremetiev and Niv Porat
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EShopFrame extends JFrame implements ActionListener {

	private EShop sHop;//need reference to EShop
	private Container container = getContentPane();
	private JTextArea infoArea = new JTextArea(10,20);//text area in panel where is data
	

	private JButton signUp = new JButton("Sign Up");
	private JButton removeCustomer = new JButton("Remove customer");
	private JButton addCart = new JButton("Add cart");
	private JButton removeCart= new JButton("Remove cart");
	private JButton showActiveCustomers = new JButton("Show active customers");
	private JButton showBusyCarts = new JButton("Show busy carts");
	private JButton moneyReport = new JButton("Money report");
	private JButton productReport = new JButton("Product report");
	private JButton setProduct = new JButton("Set product price");
	
	
	public EShopFrame(EShop sHop,Product[] inventory) 
	{
		this.sHop=sHop;
		setLayoutManager();
		setLocationAndSize();
		ScrollPane();
		addComponetsToContainer();
		addActionEvent();
	}
	
	
	private void setLayoutManager()
	{
		container.setLayout(null);
	}
	
	
	private void ScrollPane() //create scroll panel to print  data and add to container
	{
		JScrollPane panel = new JScrollPane(infoArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		panel.setBounds(500,100,1000,800);
		infoArea.setBackground(Color.black);
		infoArea.setForeground(Color.cyan);
		infoArea.setText(sHop.toString());
		container.add(panel);
	}
	
	
	
	private void setLocationAndSize() 
	{
		
		
		signUp.setBounds(10, 50, 150, 30);
		removeCustomer.setBounds(10, 80, 150, 30);
		addCart.setBounds(10, 110, 150, 30);
		removeCart.setBounds(10, 140, 150, 30);
		showActiveCustomers.setBounds(10, 170, 150, 30);
		showBusyCarts.setBounds(10, 200, 150, 30);
		moneyReport.setBounds(1380, 5, 200, 30);
		productReport.setBounds(1100, 5, 200, 30);
		setProduct.setBounds(220, 10, 150, 40);
	
	}
	
	
	
	private void addComponetsToContainer() 
	{
		try 
		{
			container.add(signUp);
			container.add(removeCustomer);
			container.add(addCart);
			container.add(removeCart);
			container.add(showActiveCustomers);
			container.add(showBusyCarts);
			container.add(moneyReport);
			container.add(productReport);
			container.add(setProduct);
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(this, "Some of components is invalid to add to container in eshop frame"+"\n" + e.getMessage());
		}
		
		
	}
	
	
	
	private void addActionEvent() 
	{
		signUp.addActionListener(this);
		removeCustomer.addActionListener(this);
		addCart.addActionListener(this);
		removeCart.addActionListener(this);
		showActiveCustomers.addActionListener(this);
		showBusyCarts.addActionListener(this);
		moneyReport.addActionListener(this);
		productReport.addActionListener(this);
		setProduct.addActionListener(this);
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		try 
		{
			if(e.getSource()==setProduct)//clicked button to set price for product
			{
				//panel for changing price and discount of product
				JPanel setPriceJPanel = new JPanel();
				JButton setButton = new JButton("Set");
				JLabel priceJLabel = new JLabel("Price: ");
				JLabel discountJLabel = new JLabel("Discount");
				JLabel productJLabel = new JLabel("Product number");
				JTextField priceJTextField = new JTextField();
				JTextField discoutJTextField = new JTextField();
				JTextField productJTextField = new JTextField();
				setPriceJPanel.setBounds(200, 80, 250, 250);
				setPriceJPanel.setBackground(Color.LIGHT_GRAY);
				setPriceJPanel.setLayout(null);
				
				productJLabel.setBounds(5, 10, 60, 30);
				productJTextField.setBounds(75, 10, 150, 30);
				discountJLabel.setBounds(5, 50, 60, 30);
				priceJTextField.setBounds(75, 90, 150, 30);
				priceJLabel.setBounds(5, 90, 60, 30);
				discoutJTextField.setBounds(75, 50, 150, 30);
				setButton.setBounds(150, 200, 90, 30);
				
				
				setPriceJPanel.add(priceJLabel);
				setPriceJPanel.add(discoutJTextField);
				setPriceJPanel.add(discountJLabel);
				setPriceJPanel.add(productJLabel);
				setPriceJPanel.add(productJTextField);
				setPriceJPanel.add(priceJTextField);
				setPriceJPanel.add(setButton);
				setPriceJPanel.setVisible(true);
				container.add(setPriceJPanel);
				container.repaint();
				container.validate();
				
				setButton.addActionListener(new ActionListener() 
				{
					
					@Override
					public void actionPerformed(ActionEvent s) {
						if(s.getSource()==setButton)//clicked button Set in set price panel
						{
							Integer discount=Integer.parseInt(discoutJTextField.getText());
							Integer price=Integer.parseInt(priceJTextField.getText());
							Integer product=Integer.parseInt(productJTextField.getText());
							Product[] inventory=sHop.getShopInventory();
							if(price==0 || discount==null || product==null)
							{
								JOptionPane.showMessageDialog(setPriceJPanel,"Price cannot be zero and discount may be begins from zero and up,must input product number");
							}
							else
							{
								infoArea.setText(sHop.toStringIventory());
								for(int i=0;i<inventory.length;i++)
								{
									if(product==inventory[i].getCatalogNumber()&&inventory[i].setUnitPrice(price,discount))
									{
											JOptionPane.showMessageDialog(setPriceJPanel,"Price and discount setted");
									}
								}
								infoArea.setText(sHop.toStringIventory());
							}
						}
					}
				});
			}
			if(e.getSource()==removeCart)//remove cart clicked
			{
				//create panel with labels and buttons to remove cart
				JPanel removeCartJPanel = new JPanel();
				JButton deletButton = new JButton("Delete");
				JLabel numberJLabell = new JLabel("Cart number: ");
				JTextField numberTextField = new JTextField();
				removeCartJPanel.setBounds(10, 600, 250, 150);
				removeCartJPanel.setBackground(Color.LIGHT_GRAY);
				removeCartJPanel.setLayout(null);
				
				numberJLabell.setBounds(5, 10, 60, 30);
				numberTextField.setBounds(75, 10, 150, 30);
				deletButton.setBounds(135, 80, 90, 30);
				
				removeCartJPanel.add(numberJLabell);
				removeCartJPanel.add(numberTextField);
				removeCartJPanel.add(deletButton);
				removeCartJPanel.setVisible(true);
				container.add(removeCartJPanel);
				container.repaint();
				container.validate();
				deletButton.addActionListener(new ActionListener() 
				{
					
					@Override
					public void actionPerformed(ActionEvent g) 
					{
						if(g.getSource()==deletButton)//clicked remove button in remove cart panel
						{
							Integer input = Integer.parseInt(numberTextField.getText());
							Cart toDeleteCart = new Cart(input);
							if(sHop.removeCart(toDeleteCart))
							{
								JOptionPane.showMessageDialog(removeCartJPanel, "Cart was deleted");
								removeCartJPanel.setVisible(false);
							}
							else 
							{
								JOptionPane.showMessageDialog(removeCartJPanel, "Cart wasn't found");
							}
						}
					}
				});
			}
			if(e.getSource()==moneyReport)//daily money report clicked
			{
				String printMoney = "Amount of goted money today "+sHop.dailySalesReport()+"$";
				infoArea.setText(printMoney);
			}
			if(e.getSource()== productReport)//daily product report clicked
			{
				String printProduct = "Amount of products sold today "+sHop.dailyProductReport();
				infoArea.setText(printProduct);
			}
			if(e.getSource() == removeCustomer)//remove customer from EShop
			{
				//create panel with labels and buttons to remove customer
				JPanel removeCustomerJPanel = new JPanel();
				JButton deleteButton = new JButton("Delete");
				JLabel nameJLabell = new JLabel("Name: ");
				JTextField nameTextField = new JTextField();
				removeCustomerJPanel.setBounds(10, 800, 250, 150);
				removeCustomerJPanel.setBackground(Color.LIGHT_GRAY);
				removeCustomerJPanel.setLayout(null);
				
				nameJLabell.setBounds(5, 10, 60, 30);
				nameTextField.setBounds(75, 10, 150, 30);
				deleteButton.setBounds(135, 80, 90, 30);
				
				removeCustomerJPanel.add(nameJLabell);
				removeCustomerJPanel.add(nameTextField);
				removeCustomerJPanel.add(deleteButton);
				removeCustomerJPanel.setVisible(true);
				container.add(removeCustomerJPanel);
				container.repaint();
				container.validate();
				
				//anonime class listener for button in remove customer panel
				deleteButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent d) 
					{
						if(d.getSource()==deleteButton)//clicked delete customer button
						{
							
							String name = nameTextField.getText();
							Customer nameCustomer = new Customer(name);
							if(sHop.removeCostumer(nameCustomer))
							{
								JOptionPane.showMessageDialog(removeCustomerJPanel,"The customer removed");
								removeCustomerJPanel.setVisible(false);;
								infoArea.setText(sHop.toString());
								
							}
							else
							{
								JOptionPane.showMessageDialog(removeCustomerJPanel,"Inputed customer dosen't exist");
							}
							
						}
						
					}
				});
			}
			
			if(e.getSource() == addCart)//clicker button Add ot add cart
			{
				Customer noCustomer = new Customer("No name");
				Cart newCart = new Cart(sHop,noCustomer);
				sHop.addNewCart(newCart);
				JOptionPane.showMessageDialog(this, "New cart added");
				Cart []carts = sHop.getShopCarts();
				String data="";
				for(int i = 0;i < sHop.getCurrentCartSize();i++)
				{
					data+=carts[i].toString();
				}
				infoArea.setText(data);
			}
			if(e.getSource()== showBusyCarts)//clicked show busy carts to see amount of busy carts
			{
				
				infoArea.setText("Amount of busy carts is " + sHop.showBusyCarts());
			}
			if(e.getSource() == showActiveCustomers)
			{
				infoArea.setText("Amount of active customers is " + sHop.showActiveCostumers());
				
			}
			if(e.getSource() == signUp)//if clicked Sign Up button create panel to field new customer 
			{
				//create panel with labels and buttons
				JPanel newCustomerJPanel = new JPanel();
				JButton applyButton = new JButton("Apply");
				JLabel nameJLabell = new JLabel("Name: ");
				JLabel passwordJLabel = new JLabel("Password: ");
				JLabel addressJLabel = new JLabel("Address: ");
				JLabel phoneJLabel = new JLabel("Phone: ");
				JTextField nameTextField = new JTextField();
				JTextField passwordJTextField = new JTextField();
				JTextField addressJTextField = new JTextField();
				JTextField phoneJTextField = new JTextField();
				newCustomerJPanel.setBounds(10, 350, 250, 220);
				newCustomerJPanel.setBackground(Color.LIGHT_GRAY);
				newCustomerJPanel.setLayout(null);
				
				nameJLabell.setBounds(5, 10, 60, 30);
				passwordJLabel.setBounds(5, 50, 60, 30);
				addressJLabel.setBounds(5, 90, 60, 30);
				phoneJLabel.setBounds(5, 130, 60, 30);
				nameTextField.setBounds(75, 10, 150, 30);
				passwordJTextField.setBounds(75, 50, 150, 30);
				addressJTextField.setBounds(75, 90, 150, 30);
				phoneJTextField.setBounds(75, 130, 150, 30);
				applyButton.setBounds(135, 180, 90, 30);
				
				newCustomerJPanel.add(nameJLabell);
				newCustomerJPanel.add(passwordJLabel);
				newCustomerJPanel.add(addressJLabel);
				newCustomerJPanel.add(phoneJLabel);
				newCustomerJPanel.add(nameTextField);
				newCustomerJPanel.add(passwordJTextField);
				newCustomerJPanel.add(addressJTextField);
				newCustomerJPanel.add(phoneJTextField);
				newCustomerJPanel.add(applyButton);
				newCustomerJPanel.setVisible(true);
				container.add(newCustomerJPanel);
				container.repaint();
				container.validate();
				
				applyButton.addActionListener(new ActionListener()
				{
					
					@Override
					public void actionPerformed(ActionEvent a) 
					{
						if(a.getSource() == applyButton)//if clicked Apply button add new customer and print all customers in EShop
						{
							
							String name = nameTextField.getText();
							String password = passwordJTextField.getText();
							String address = addressJTextField.getText();
							String phone = phoneJTextField.getText();
						
								Customer newCustomer = new Customer(name,password,address,phone);
								if(sHop.signUp(newCustomer))
								{
									JOptionPane.showMessageDialog(newCustomerJPanel, "Customer Signed Up");
									newCustomerJPanel.setVisible(false);
									container.remove(newCustomerJPanel);
								}
								
								else
								{
									JOptionPane.showMessageDialog(newCustomerJPanel, "Cannot sihg up a customer");
								}
							
							
						}
					}
				});
			}
		} 
		catch (Exception e2) {
			JOptionPane.showMessageDialog(this, "Check listeners in eshop frame"+"\n" + e2.getMessage());
		}
		
	}
}
