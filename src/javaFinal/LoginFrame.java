package javaFinal;
//Artiom Sheremetiev and Niv Porat
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Login frame class
public class LoginFrame extends JFrame implements ActionListener
{
	private EShop shop;
	private Container container =getContentPane();
	private JLabel userLabel=new JLabel("USERNAME");
	private JLabel passworLabel=new JLabel("PASSWORD");
	private JTextField userTextField=new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private JButton loginButton = new JButton("Login");
	private JButton resetButton = new JButton("Reset");
	private JCheckBox showPassword = new JCheckBox("Show Password");//no stars on password
	private Customer customerToReturn;
	
	//half automatic constructor of LoginFrame class
	public LoginFrame(EShop shop)//shop has reference to eShop from class Program
	{
		this.shop = shop;
		setLayoutManager();
		setLocationAndSize();
		addComponetsToContainer();
		addActionEvent();
	}
	
	//size and location of components in login frame panel
	public void setLocationAndSize()
	{
		userLabel.setBounds(50, 150, 100, 30);
		passworLabel.setBounds(50, 220, 100, 30);
		userTextField.setBounds(150, 150, 150, 30);
		passwordField.setBounds(150, 220, 150, 30);
		showPassword.setBounds(150, 250, 150, 30);
		loginButton.setBounds(50,300, 100, 30);
		resetButton.setBounds(200, 300, 100, 30);
	}
	
	//add components to container of login panel
	public void addComponetsToContainer()
	{
		try 
		{
			container.add(userLabel);
			container.add(passworLabel);
			container.add(passwordField);
			container.add(showPassword);
			container.add(loginButton);
			container.add(resetButton);
			container.add(userTextField);
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(this, "Some of components is invalid to add to container in login frame"+"\n" + e.getMessage());
		}
		
		
	}
	
	
	//no movements or hide of components,no display block or grid
	private void setLayoutManager()
	{
		container.setLayout(null);
	}
	
	//add events when clicked on component of this class like a parent class to constructor to be before new creating
	private void addActionEvent() 
	{
		loginButton.addActionListener(this);
		resetButton.addActionListener(this);
		showPassword.addActionListener(this);
		
	}
	
	//Listener for buttons in Login panel
	@Override
	public void actionPerformed(ActionEvent e) //e is object in system that track click on components like buttons
	{
		try {
			if(e.getSource()==loginButton)//if clicked login button
			{
				String userText;
				String passwordText;
				userText = userTextField.getText();//put input of line textField to userText
				passwordText = passwordField.getText();//put input of passwordFied to passwordText

				if(CheckPasswordAndLogin(userText,passwordText))//check if name and password exist in Customers
				{
					JOptionPane.showMessageDialog(this, "Logged");
					CustomerFrame customerFrame = new CustomerFrame(customerReturn(),shop);
					customerFrame.setTitle("Customer");
					customerFrame.setVisible(true);
					customerFrame.setBounds(10, 10, 1600,1200);
					customerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					this.dispose();
					
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Invalid login or password");
				}
			}	
			
			
			if(e.getSource()==resetButton)//if clicked reset,reset all lines
			{
				userTextField.setText("");
				passwordField.setText("");
			}
			
			
			if(e.getSource() == showPassword)
			{
				if(showPassword.isSelected())//if check box selected
				{
					passwordField.setEchoChar((char)0);//show chars instead of stars
				}
				else
				{
					passwordField.setEchoChar('*');//show stars instead of password chars
				}
		} 
		}
		catch (Exception e2) 
		{
			JOptionPane.showMessageDialog(this, "Check listeners in login frame"+"\n" + e2.getMessage());
		}
		
		
		
	}

	private Customer customerReturn() 
	{
		return customerToReturn;
		
	}
	
	
	//login and password check in
	public boolean CheckPasswordAndLogin(String userText, String passwordText) 
	{
		try 
		{
			String login;
			String pass;
			Customer[] customer = shop.getShopCostumers();
			Customer customer2;
			for(int i = 0; i < shop.getcurrentCostumerSize();i++)
			{
				customer2 = customer[i];
				login = customer2.getUserName();
				pass = customer2.getPassword();
				if(userText.equals(login) && passwordText.equals(pass))
					{
						
					    customer2.changeLoginStatus(true);
					    customerToReturn=customer2;
						return true;
					}
			}
			return false;
			
		
		}
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(this, "Check password and login in login frame,null"+"\n" + e.getMessage());
			return false;
		}
	}

	
}
