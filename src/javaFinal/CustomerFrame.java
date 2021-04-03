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

//Customer frame class
public class CustomerFrame extends JFrame implements ActionListener {
	private Customer customer;
	private EShop SHOP;
	private String name ;
	private String address ;
	private String telephone ;
	private String input;
	
	private Container container = getContentPane();// need to get data from components in container
	private JTextArea infoArea = new JTextArea(10,20);//text area in panel where is data
	
	private JLabel nameJLabel = new JLabel(name);//show name in up of frame
	private JLabel addressJLabel = new JLabel(address);//show address in up of frame
	private JLabel telephoneJLabel = new JLabel(telephone);//show telephone number in up of frame
	private JLabel loginStatuslJLabel;//show login status in up of frame

	private JButton changePasswordButton = new JButton("Change Password");
	private JButton changeAddressButton = new JButton("Change Address");
	private JButton changeTelephoneButton = new JButton("Change Telephone");
	private JButton freeCartButton = new JButton("Free Cart");
	private JButton billButton = new JButton("Show Bill");
	private JButton payButton = new JButton("Pay");
	private JButton logoutButton = new JButton("Logout");
	private JButton getCartButton = new JButton("Get Cart");
	private JButton removeProductButton = new JButton("Remove Product");
	private JButton addProducButton = new JButton("Add Product");


	
	public CustomerFrame(Customer customer,EShop SHOP)// shop has reference to eShop from class Program
	{
		this.SHOP=SHOP;//reference to eShop 
		this.customer = customer;//need customer data to operate
		this.customer.changeLoginStatus(true);
		nameJLabel = new JLabel(customer.getUserName());
		addressJLabel =new JLabel(customer.getBillingAddress());
		telephoneJLabel =new JLabel(customer.getPhoneNumber());
		loginStatuslJLabel = new JLabel("Logged");
		
		setLayoutManager();
		setLocationAndSize();
		ScrollPane();
		addComponetsToContainer();
		addActionEvent();
		
	}
	
	//create panel in frame where will be data about customer
	private void ScrollPane() 
	{
		try {
			JScrollPane panel = new JScrollPane(infoArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
			panel.setBounds(500,100,1000,800);
			infoArea.setBackground(Color.black);
			infoArea.setForeground(Color.cyan);
			infoArea.setText(customer.toString());
			container.add(panel);
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(this, "Scroll pane is dead " + e.getMessage() );
		}
		
		
		
	}
	
	
	// size and location of components in container
	private void setLocationAndSize() 
	{
		nameJLabel.setBounds(10,5,200, 30);
		addressJLabel.setBounds(210, 5, 200, 30);
		telephoneJLabel.setBounds(420, 5, 200, 30);
		loginStatuslJLabel.setBounds(630, 5, 200, 30);
		logoutButton.setBounds(1380, 5, 200, 30);
		changePasswordButton.setBounds(10, 50, 150, 30);
		changeAddressButton.setBounds(10, 80, 150, 30);
		changeTelephoneButton.setBounds(10, 110, 150, 30);
		getCartButton.setBounds(10, 140, 150, 30);
		freeCartButton.setBounds(10, 170, 150, 30);
		addProducButton.setBounds(10, 200, 150, 30);
		removeProductButton.setBounds(10, 230, 150, 30);
		billButton.setBounds(10, 260, 150, 30);
		payButton.setBounds(10, 290, 150, 30);
	}

	// add components to container of login panel
	private void addComponetsToContainer()
	{
		try 
		{
			container.add(nameJLabel);
			container.add(addressJLabel);
			container.add(telephoneJLabel);
			container.add(loginStatuslJLabel);
			container.add(changePasswordButton);
			container.add(changeAddressButton);
			container.add(changeTelephoneButton);
			container.add(freeCartButton);
			container.add(billButton);
			container.add(payButton);
			container.add(logoutButton);
			container.add(getCartButton);
			container.add(addProducButton);
			container.add(removeProductButton);
		}
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(this, "Some of components is invalid to add to container in customer frame"+"\n" + e.getMessage());
		}
		
	}

	// no movements or hide of components,no display block or grid
	private void setLayoutManager() 
	{
		container.setLayout(null);
	}

	// add events when clicked on component of this class 
	private void addActionEvent() 
	{
		changePasswordButton.addActionListener(this);
		changeAddressButton.addActionListener(this);
		changeTelephoneButton.addActionListener(this);
		freeCartButton.addActionListener(this);
		billButton.addActionListener(this);
		payButton.addActionListener(this);
		logoutButton.addActionListener(this);
		getCartButton.addActionListener(this);
		addProducButton.addActionListener(this);
		removeProductButton.addActionListener(this);

	}
	
	
	

	// Listener for buttons in container and his components
	@Override
	public void actionPerformed(ActionEvent e) // e is object in system that track click on components like buttons
	{
		try {
			if(e.getSource() == removeProductButton) //delete product
			{
				if(customer.getMyCart()!=null)//create small panel for deleting
				{
					infoArea.setText("Choose product to delete by catalog number"+"\n"+customer.getMyCart().toString());
					JPanel removeProductJPanel = new JPanel();
					JButton removeButton = new JButton("Delete");
					JLabel removelabel = new JLabel("Catalog number");
					JTextField inputProduct = new JTextField();
					removeProductJPanel.setBounds(220, 760, 250, 160);
					removeProductJPanel.setBackground(Color.LIGHT_GRAY);
					removeProductJPanel.setLayout(null);
					removeButton.setBounds(125, 100, 100, 30);
					inputProduct.setBounds(105, 50, 100, 30);
					removelabel.setBounds(5, 50, 100, 50);
					removeProductJPanel.add(inputProduct);
					removeProductJPanel.add(removeButton);
					removeProductJPanel.add(removelabel);
					removeProductJPanel.setVisible(true);
					container.add(removeProductJPanel);
					container.repaint();
					container.validate();
					
					removeButton.addActionListener(new ActionListener() 
					{
						
						@Override
						public void actionPerformed(ActionEvent f)
						{
						 if(f.getSource()==removeButton)//button in deleting panel
						  {
							Integer input = Integer.parseInt(inputProduct.getText());
							Cart inCartToDelete = customer.getMyCart();
							
							if(inCartToDelete.getProductCounter() > 0)
							{
								if(inCartToDelete.removeItemFromCartByCatalog(input))//!!!!!!!!!!!!!
								{
									JOptionPane.showMessageDialog(removeProductJPanel, "Product removed");
									if(inCartToDelete.getProductCounter() > 0)
									{
										infoArea.setText(customer.getMyCart().toString());
									}
										
									else
									{
										infoArea.setText(customer.getMyCart().toString());
									}
									removeProductJPanel.setVisible(false);
									container.remove(removeProductJPanel);
								}
								else
								{
									JOptionPane.showMessageDialog(removeProductJPanel, "Product not found");
								}
								
							}
							
							else 
							{
								JOptionPane.showMessageDialog(container, "Cart has no products");
								infoArea.setText(customer.getMyCart().toString());
								removeProductJPanel.setVisible(false);
								container.remove(removeProductJPanel);
								
							}
							
						  }
						}
					});
					
				}
				else
				{
					infoArea.setText("Cant remove product,cart dosen't exist");
				}
				}
				
			if(e.getSource() == addProducButton) //clicked add product button 
			{
				if(customer.getMyCart()!=null)
				{
					//panel to choose product to add
					infoArea.setText("Choose product to add by catalog number"+"\n"+ SHOP.toStringIventory());
					JPanel addProductJPanel = new JPanel();
					JButton addButton = new JButton("Add");
					JLabel addlabel = new JLabel("Catalog number");
					JTextField inputProduct = new JTextField();
					addProductJPanel.setBounds(220, 580, 250, 160);
					addProductJPanel.setBackground(Color.LIGHT_GRAY);
					addProductJPanel.setLayout(null);
					addButton.setBounds(125, 100, 100, 30);
					inputProduct.setBounds(105, 50, 100, 30);
					addlabel.setBounds(5, 50, 100, 50);
					addProductJPanel.add(inputProduct);
					addProductJPanel.add(addButton);
					addProductJPanel.add(addlabel);
					addProductJPanel.setVisible(true);
					container.add(addProductJPanel);
					container.repaint();
					container.validate();
					
					addButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent x) {
							if(x.getSource()==addButton)//add button product in add product panel
							{
								
							String input = inputProduct.getText();
							Product[] itemsProducts = SHOP.getShopInventory();
							Integer tempCatalog;
							for(int i=0;i < itemsProducts.length;i++)
							{
								tempCatalog = itemsProducts[i].getCatalogNumber(); 
								
								if(tempCatalog.toString().equals(input))
								{
									customer.getMyCart().addProduct(new Product( itemsProducts[i].getProductName(),itemsProducts[i].getCatalogNumber(), itemsProducts[i].getUnitPrice()));///!!!!!!!!!!!!!!!!!
									JOptionPane.showMessageDialog(addProductJPanel, "Product added");
									infoArea.setText(customer.getMyCart().toString());
									addProductJPanel.setVisible(false);
									container.remove(addProductJPanel);
									return;
								}
								
							}
							JOptionPane.showMessageDialog(addProductJPanel, "Product not found");
							addProductJPanel.setVisible(false);
							container.remove(addProductJPanel);
							}
							else
							{
								JOptionPane.showMessageDialog(addProductJPanel, "Cant add product,cart dosen't exist");
							}
						}
					});
					
				}
			}
					
				

			if(e.getSource()==getCartButton)//button to get new cart if need
			{
				if(customer.getMyCart()!=null)
				{
					JOptionPane.showMessageDialog(this, "You allready have a cart");
				}
				else
				{
					Cart newCart = new Cart(SHOP,customer);
					customer.setMyCart(newCart);
					JOptionPane.showMessageDialog(this, "New cart gotted");
					infoArea.setText(customer.getMyCart().toString());
				}

			}
			
			
			if(e.getSource()==payButton)//pay price of product in cart button
			{
				if(customer.getMyCart() == null)
				{
					infoArea.setText("Can't pay,cart dosen't exist");
				}
				else
				{
					if(customer.getMyCart().getProductCounter()==0)
					{
						infoArea.setText("Nothing to pay for");
					}
					else
					{
						SHOP.addDailyProducts(customer);
						SHOP.addDailySales(customer);
						infoArea.setText(customer.payment());
					}
					
				}
			}
			
			
			if(e.getSource() == billButton)//bill button
			{
				if(customer.getMyCart() == null )
				{
					infoArea.setText("Cant print bill,cart dosen't exist");
					
				}
				else
				{
					if (customer.getMyCart().getProductCounter()==0) 
					{
						infoArea.setText(customer.getMyCart().toString());
						JOptionPane.showMessageDialog(this, "No products in cart");
					}
					else
					{
						infoArea.setText(customer.printBill());
					}
				}
			}
			
			
			if(e.getSource()==freeCartButton )//free all product from cart button
			{
				if(customer.getMyCart() == null)// || customer.getMyCart().getProductCounter()==0
				{
					JOptionPane.showMessageDialog(this, "Can't free cart,cart dosen't exist yet");
				}
				else
				{
					customer.freeCart();
					customer.setMyCart(null);
					JOptionPane.showMessageDialog(this, "Cart is free");
					infoArea.setText(customer.toStringNoCart());
				}
			}
			
			
			if(e.getSource() == logoutButton)//logout button 
			{
				
				
				customer.changeLoginStatus(false);
				infoArea.removeAll();
				infoArea.setText(customer.toStringNoCart());
				container.removeAll();
				container.setVisible(false);
				this.dispose();
			}
			
			if (e.getSource() == changeTelephoneButton)// if clicked change password button
			{
				
				JPanel changeTelephoneJPanel = new JPanel();
				JButton button3 = new JButton("Change");
				JLabel label = new JLabel("New Phone");
				JTextField inpuField3 = new JTextField();
				changeTelephoneJPanel.setBounds(220, 400, 250, 160);
				changeTelephoneJPanel.setBackground(Color.LIGHT_GRAY);
				changeTelephoneJPanel.setLayout(null);
				button3.setBounds(125, 100, 100, 30);
				inpuField3.setBounds(90, 50, 150, 30);
				label.setBounds(5, 50, 80, 50);
				changeTelephoneJPanel.add(inpuField3);
				changeTelephoneJPanel.add(button3);
				changeTelephoneJPanel.add(label);
				changeTelephoneJPanel.setVisible(true);
				container.add(changeTelephoneJPanel);
				container.repaint();
				container.validate();
				
				button3.addActionListener(new ActionListener()//and if clicked button in change panel of phone number
				{
					
					@Override
					public void actionPerformed(ActionEvent s) {
						
						if(s.getSource() == button3) 
						{
							input = inpuField3.getText();
							if (customer.getPhoneNumber().equals(input))// check if phone the same or not
							{
								JOptionPane.showMessageDialog(changeTelephoneJPanel, "Must be new phone number");
							}
							
							else 
							{
								JOptionPane.showMessageDialog(changeTelephoneJPanel, "Phone changed");
								customer.setPhoneNumber(input);//change number in data of customer
								changeTelephoneJPanel.setVisible(false);//close panel after changing
								container.remove(changeTelephoneJPanel);
								if(customer.getMyCart().equals(null)|| customer.getMyCart().getProductCounter()==0)
								{
									infoArea.setText(customer.toStringNoCart());
								}
								else
								{
									infoArea.setText(customer.toString());
								}
								
							}
						}
					}
				});
			}
			
			
			
			
			if(e.getSource() == changePasswordButton)//clicked button to change password
			{
				JTextField inpuField1 = new JTextField();
				JPanel changePassWordJPanel = new JPanel();
				JButton button1 = new JButton("Change");
				JLabel label = new JLabel("New Password");
				changePassWordJPanel.setBounds(220, 50, 250, 160);
				changePassWordJPanel.setBackground(Color.LIGHT_GRAY);
				changePassWordJPanel.setLayout(null);
				button1.setBounds(125, 100, 100, 30);
				inpuField1.setBounds(90, 50, 150, 30);
				label.setBounds(5, 50, 100, 50);
				changePassWordJPanel.add(inpuField1);
				changePassWordJPanel.add(button1);
				changePassWordJPanel.add(label);
				changePassWordJPanel.setVisible(true);
				container.add(changePassWordJPanel);
				container.repaint();
				container.validate();
				
				button1.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent p) {
						if(p.getSource() == button1)
						{
							input = inpuField1.getText();
							if (customer.getPassword().equals(input))// check if name and password exist in Customers
							{
								JOptionPane.showMessageDialog(changePassWordJPanel, "Must be new password");
							}
							
							else 
							{
								JOptionPane.showMessageDialog(changePassWordJPanel, "Password changed");
								customer.setPassword(input);
								changePassWordJPanel.setVisible(false);
								container.remove(changePassWordJPanel);
								if(customer.getMyCart().equals(null)|| customer.getMyCart().getProductCounter()==0)
								{
									infoArea.setText(customer.toStringNoCart());
								}
								else
								{
									infoArea.setText(customer.toString());
								}
							}
						}
						
						
					}
				});
			}
			
			
			
			
			if(e.getSource() == changeAddressButton)//clicked button to change address
			{
				JTextField inpuField2 = new JTextField();
				JPanel changeAddressJPanel = new JPanel();
				JButton button2 = new JButton("Change");
				JLabel label = new JLabel("New Address");
				changeAddressJPanel.setBounds(220, 230, 250, 160);
				changeAddressJPanel.setBackground(Color.LIGHT_GRAY);
				changeAddressJPanel.setLayout(null);
				button2.setBounds(125, 100, 100, 30);
				inpuField2.setBounds(100, 50, 150, 30);
				label.setBounds(5, 50, 80, 50);
				changeAddressJPanel.add(inpuField2);
				changeAddressJPanel.add(button2);
				changeAddressJPanel.add(label);
				changeAddressJPanel.setVisible(true);
				container.add(changeAddressJPanel);
				container.repaint();
				container.validate();
				
				button2.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent a) {
						
						if(a.getSource() == button2)//clicked conform button to change password
						{
							input = inpuField2.getText();
							if (customer.getBillingAddress().equals(input))// check if name and password exist in Customers
							{
								JOptionPane.showMessageDialog(changeAddressJPanel, "Must be new address");
							}
							
							else 
							{
								JOptionPane.showMessageDialog(changeAddressJPanel, "Address changed");
								customer.setBillingAddress(input);
								changeAddressJPanel.setVisible(false);
								container.remove(changeAddressJPanel);
								if(customer.getMyCart().equals(null)|| customer.getMyCart().getProductCounter()==0)
								{
									infoArea.setText(customer.toStringNoCart());
								}
								else
								{
									infoArea.setText(customer.toString());
								}
							}
						}
						
					}
				});
				
			}
			
		}
		catch (Exception e2) 
		{
			JOptionPane.showMessageDialog(this, "Check the listeners" + "\n" + e2.getMessage());
		}
	
		
		}
	

	}



