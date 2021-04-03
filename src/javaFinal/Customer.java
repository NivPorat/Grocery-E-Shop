package javaFinal;
//Artiom Sheremetiev and Niv Porat
import java.util.Scanner;

public class Customer {
    private String userName;
    private String password;
    private String billingAddress;
    private String phoneNumber;
    private Cart myCart;
		

    private boolean isLoggedIn = false;
    private boolean hasCustomerPaid = false;

    

    public Customer(String userName, String password, String billingAddress, String phoneNumber) 
    {
        this.userName = userName;
        this.password = password;
        this.billingAddress = billingAddress;
        this.phoneNumber = phoneNumber;
    }

    
    public Customer(Scanner s) 
    {
        this.userName = s.next();
        this.password = s.next();
        this.billingAddress = s.next();
        this.phoneNumber = s.next();
    }

    public Customer(String userName)
    {
    	this.userName=userName;
    }
    
    
    public String getUserName() 
    {
        return userName;
    }
    
    
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    
    
    
    private boolean verifyBillingAddress(String billingAddress)
    {
        if(this.billingAddress.equals(billingAddress))
        {
            System.out.println("new billing address cant be the same as old!");
            return false;
        }
        return true;
    }
    
    
    
    private boolean verifyPhoneNumber(String phoneNumber) 
    {
        if(this.phoneNumber.equals(phoneNumber)) 
        {
            System.out.println("new phone number cant be the same as the old!");
            return false;
        }
        return true;

    }
    
    
    
    public boolean getHasCustomerPaid() 
    {
        return hasCustomerPaid;
    }
    
    
    
    public String getPassword() 
    {
        return password;
    }
    
    
    
    
    public void setPassword(String password) 
    {
        this.password = password;
    }
    
    
    
    public String getBillingAddress()
    {
        return billingAddress;
    }
    
    
    
    public void setBillingAddress(String billingAddress) 
    {
        if(!verifyBillingAddress(billingAddress))
            return;
        else
            this.billingAddress = billingAddress;
    }
    
    
    
    public String getPhoneNumber() 
    {
        return phoneNumber;
    }
    
    
    
    public void setPhoneNumber(String phoneNumber) 
    {
        if(!verifyPhoneNumber(phoneNumber))
            return;
        else
            this.phoneNumber = phoneNumber;
    }
    
    
    
    public Cart getMyCart() 
    {
        return myCart;
    }
    
    
    public void getNewCart(EShop s,Customer customerName) //create new cart to customer from UI
    {
    	myCart = new Cart(s, customerName);
        
    }
    
    public void setMyCart(Cart myCart)
    {
        this.myCart = myCart;
       
    }
    
    
    public void freeCart()
    {
        myCart.emptyCart();
        
    }
    
    
    
    public boolean isLoggedIn() 
    {
        return isLoggedIn;
    }
    
    
    
    public void changeLoginStatus(boolean isLogged) 
    {
        if(isLogged)
            isLoggedIn = true;

        else isLoggedIn = false;
    }

    
    
    
    public boolean login(String userName,String password) 
    {
        if(this.getUserName() != userName || this.getPassword() != password)
            return false;
        this.changeLoginStatus(true);
        return true;
    }

    
    
    public boolean addProductToCart(Product product) 
    {
        if(myCart.getProductsInCart() == null) 
        	return false;
        myCart.setProductsInCart(getMyCart().getProductsInCart());
        return true;
    }
    
    
    
    public String printBill() 
    {
    	String bill;
    	if(myCart.equals(null))
    	{
    		bill = "Noting to pay";
    	}
    	
    	else 
    	{
    		bill =  myCart.toString()+"\n" + "Sum to pay: " + myCart.cartTotalSum()+"$"+"\n"+myCart.printSumSavedByDiscount();
		}
        return bill;

    }
    
    
    
    public String payment()
    {
    	this.hasCustomerPaid = true;
    	String msg ="Payed  "+ myCart.cartTotalSum()+"$"+"    Sending bill to: "+getBillingAddress();
        printBill();
        freeCart();
        return msg;
    }


    

    
    @Override
    public String toString() 
    {
        return  "\n"+ "| Costumer |     "+
        		"   Name:     " + userName+ "     Password:     " + password+ "    Address:     " + billingAddress+"   Phone number:     " 
        		+ phoneNumber+ "     Is Logged:    " + isLoggedIn+"    Paid:    " + hasCustomerPaid+
                "\n" + "  Cart:    " + myCart;
    }

    public String toStringNoCart() 
    {
        return  "| Costumer |   "+
        		"    Name:    " + userName+ "      Password:    " + password+"    Address:    " + billingAddress
        		+"     Phone number:    " + phoneNumber+ "    Is Logged:    " + isLoggedIn+"   Paid:    " + hasCustomerPaid+
                "\n" + "   Cart not exist";
                
    }
}
