package javaFinal;
//Artiom Sheremetiev and Niv Porat
import java.util.Arrays;



public class EShop {
    private final int MaxInventorySize = 1000;
    private final int maxCostumerSize = 100;
    private final int maxCartSize = 100;
    private int totalCartAvailable = 100;
    private int currentInventorySize;
    private int currentCustomerSize;
    private int currentCartSize;
    private int dailySales;
    private int dailyProduct;
    private String shopName;
    private Product[] shopInventory;
    private Customer[] shopCostumers = new Customer[currentCustomerSize];
    private Cart[] shopCarts = new Cart[currentCartSize];

    
    
    
    public int getcurrentCostumerSize() 
    {
    	return currentCustomerSize;
	}
    
    
    
    
    public String getShopName()
    {
        return shopName;
    }
    
    
    public void setShopName(String shopName) 
    {
        this.shopName = shopName;
    }
    
    
    
    public Product[] getShopInventory() 
    {
        return shopInventory;
    }
    
    
    
    
    public void setShopInventory(Product[] shopInventory) 
    {
        this.shopInventory = shopInventory;
        currentInventorySize = shopInventory.length;
    }
    
    
    
    public Customer[] getShopCostumers() 
    {
        return shopCostumers;
    }
    
    
    
    public void setShopCostumers(Customer[] shopCostumers)
    {
        this.shopCostumers = shopCostumers;
        currentCustomerSize=shopCostumers.length;
    }
    
 
    
    public Cart[] getShopCarts() 
    {
        return shopCarts;
    }
    
    
    
    public void setShopCarts(Cart[] shopCarts) 
    {
        this.shopCarts = shopCarts;
    }
    
    
    
    public boolean signUp(Customer costumer)
    {

        if (currentCustomerSize == maxCostumerSize)
            return false;
		
        	shopCostumers = Arrays.copyOf(shopCostumers, ++currentCustomerSize);
            shopCostumers[currentCustomerSize-1] = new Customer(costumer.getUserName(),costumer.getPassword(),costumer.getBillingAddress(),costumer.getPhoneNumber());
            return true;
    }

    
    
    
    public int getCurrentInventorySize() 
	{
		return currentInventorySize;
	}
    
    
    
    public boolean removeCostumer(Customer name)
    {
        for(int i = 0; i < currentCustomerSize;i++)
        {
            if(shopCostumers[i].getUserName().equals(name.getUserName())) 
            {
            	if(shopCostumers[i].getMyCart()!=null)
            	{
            		Customer customerName = new Customer("No name");
            				shopCarts[i].setCostumerName(customerName);
            				shopCostumers[i].freeCart();
                    		shopCostumers[i].getMyCart().setCostumerName(customerName);

            		
            	}
            		
            		shopCostumers[i] = shopCostumers[currentCustomerSize-1];
            		shopCostumers[currentCustomerSize-1]=null; 
            		currentCustomerSize--;
            		shopCostumers = Arrays.copyOf(shopCostumers, currentCustomerSize);
                    return true;
				
            	
            }
        }
        return false;
    }

    
    
    public int  getCurrentCartSize() 
    {
		return currentCartSize;
	}
    
    
    public boolean addNewCart(Cart newCart) 
    {
        if(currentCartSize == maxCartSize)
            return false;
        shopCarts=Arrays.copyOf(shopCarts, ++currentCartSize);
        shopCarts[currentCartSize-1] = new Cart(getCurrentCartSize()+999 ,newCart.getProductsInCart(), newCart.getCostumerName());
        totalCartAvailable--;
        return true;
    }
    
    
    
    
    public boolean removeCart(Cart cartToRemove)
    {
        if(cartToRemove==null || shopCarts==null)
        {
        	return false;
        }
        for(int i = 0 ; i < currentCartSize;i++) 
        {
            if(shopCarts[i].getCartNumber() == cartToRemove.getCartNumber()) 
            {
            	shopCarts[i]=shopCarts[currentCartSize-1];
            	shopCarts[currentCartSize-1]=null;
                shopCarts=Arrays.copyOf(shopCarts, --currentCartSize);
                return true;
            }
        }
        return false;
    }
      
    
    public int showActiveCostumers() 
    {
        int counter = 0;
        for(int i = 0 ; i < currentCustomerSize;i++)
            if(shopCostumers[i].isLoggedIn())
                counter++;
        return counter;
    }
    
    
    
    public int showBusyCarts() {
		int busyCartCount = 0;
		for(int i=0;i<getCurrentCartSize();i++)
		{
			if(shopCarts[i].getCostumerName().getUserName()!="No name")
			{
				busyCartCount++;
			}
		}
		return busyCartCount;
	}
    
    
    
    public int showActiveCarts() 
    {
        //maybe to return the length of shopCarts[]?
        int counter = 0;
        for(int i = 0 ; i< shopCarts.length;i++)
            if(shopCarts[i]!= null)
            	counter++;
        return counter;
    }
    
    public void addDailySales(Customer cust) 
    {
		dailySales+=cust.getMyCart().cartTotalSum();
	}
    
    public void addDailyProducts(Customer cust) 
    {
		dailySales+=cust.getMyCart().getProductCounter();
	}
    
    public int dailySalesReport() 
    {
        for(int i = 0 ;i< currentCustomerSize; i++) 
        {
            if(shopCostumers[i].getMyCart()!=null&&shopCostumers[i].getHasCustomerPaid())
                dailySales+=shopCostumers[i].getMyCart().cartTotalSum();
        }
        return dailySales;
    }
    
    
    
    public int dailyProductReport() 
    {
        for(int i = 0; i < shopCarts.length ; i++) {
            if(shopCarts[i]!=null && shopCarts[i].getCostumerName().getHasCustomerPaid())
                dailyProduct+= shopCarts[i].getProductCounter();
        }
        return dailyProduct;
    }
    
    
    public String toStringIventory()
    {
    	String item = Arrays.toString(shopInventory);
    	return item;
    }
    
    
    @Override
    public String toString() 
    {
        return  " |Shop Name|: " + shopName 
        		+"  Max inventory size: " + this.MaxInventorySize
        		+"  Costumers signed up: " + this.currentCustomerSize 
        		+"  Carts available: " +totalCartAvailable 
        		+"\n"+ "  |Shop Inventory|: "+ Arrays.toString(shopInventory) 
        		+"\n"+ "  |Shop Costumers|: "+ Arrays.toString(shopCostumers)
        		+"\n"+ "  |Shop Carts|: " + Arrays.toString(shopCarts)+"\n";
    }


}






