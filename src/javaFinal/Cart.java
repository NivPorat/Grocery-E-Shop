package javaFinal;
//Artiom Sheremetiev and Niv Porat
import java.util.Arrays;

public class Cart {
    private int cartNumber;
    private final int maxProducts = 100;
    private int availableProducts = 100;
    private int productCounter;
    private	Product[] productsInCart = new Product[productCounter];
    private Customer customerName = new Customer("","","","");

  

    public Cart(EShop shop, Product[] productsInCart, Customer costumerName) 
    {
        this.productsInCart = productsInCart;
        productCounter = productsInCart.length;
        this.availableProducts-=productCounter;
        this.customerName = costumerName;
        cartNumber = shop.getCurrentCartSize()+1000;
    }
    
    
    
    public Cart(int cartNumber, Customer costumerName) 
    {
        this.cartNumber = cartNumber;
        this.customerName = costumerName;
    }

    
    public Cart(int cartNumber,Product[] productsInCart,Customer customerName)
    {
    	this.customerName=customerName;
    	this.cartNumber = cartNumber;
    	this.productsInCart=productsInCart;
    	productCounter = productsInCart.length;
    	
    }
    public Cart(EShop shop,Customer customerName)
    {
    	this.customerName=customerName;
    	cartNumber = shop.getCurrentCartSize()+1000;
    	
    }
    
    public Cart (int cartNumber)
    {
    	this.cartNumber=cartNumber;
    }
    
    
    public Cart() 
    {
    	
    }
    
    public int getCartNumber() 
    {
        return cartNumber;
    }
    
    
    
    public void setCartNumber(int cartNumber) 
    {
        if(cartNumber <= 999) 
        	this.cartNumber = 1000 + productCounter;
        else
            this.cartNumber = cartNumber;
    }
    
    
    
    public Product[] getProductsInCart() 
    {
        return productsInCart;
    }
    
    
    
    public void setProductsInCart(Product[] productsInCart) 
    {
        this.productsInCart = productsInCart;
    }
    
    
    
    public int getProductCounter() 
    {
        return productCounter;
    }
    
    
    
    public boolean setProductCounter(int productCounter) 
    {
        if(productCounter >maxProducts || productCounter > availableProducts)
            return false;
        else 
        {
            availableProducts-=productCounter;
            this.productCounter = productCounter;
            return true;
        }
    }
    
    
    
    public Customer getCostumerName() 
    {
        return customerName;
    }
    
    
    
    public void setCostumerName(Customer costumerName) 
    {
        this.customerName = costumerName;
    }

    
    
    
    public boolean addProduct(Product p) 
    {
    	
        if(productCounter == maxProducts || productsInCart == null)
        {
            return false;
        }
        		 
        productsInCart=Arrays.copyOf(productsInCart, ++productCounter);
        productsInCart[productCounter-1]=new Product(p.getProductName(),p.getCatalogNumber(),p.getUnitPrice());
        availableProducts--;
        return true;
    }
    
    
    
    
    
    public boolean removeItemFromCartByCatalog(int catalogNumber) 
    {
    	if(productsInCart==null)
    	{
    		return false;
    	}
                
    	for (int i = 0; i < productCounter; i++) 
             {
                 if (productsInCart[i].getCatalogNumber() == catalogNumber) 
                    {
                        productsInCart[i] = productsInCart[productCounter - 1];
                        productsInCart[productCounter - 1] = null;
                        productsInCart=Arrays.copyOf(productsInCart, --productCounter);
                        availableProducts++;
                        return true;
                    }

              }
        return false;
    }
    
    
    
    
    public void emptyCart() 
    {
        for(int i = 0;i<productCounter;i++)
        {
            productsInCart[i] = null;
        }
        productCounter = 0;
        availableProducts = maxProducts;
    }

    
    public String printCartItems() 
    {
        String str = "";
        if(productsInCart == null)
        	return "No Products";
        for(int i = 0 ; i < productCounter;i++)
        {
        	if(productsInCart[i] != null)
        		{
        			str+=productsInCart[i].toString()+"\n";
        		}
                
		}
        return str;
    }
    
    
    
    
    public int cartTotalSum()
    {
        int sum = 0;
        for(int i = 0 ; i<productCounter;i++) 
        {
        	if(productsInCart[i] != null) 
        	{
        		sum+=productsInCart[i].getUnitPrice();
        	}
        }
        return sum;
    }
    
    
    
    
    public double showSumSavedByDiscount() 
    {
        double sum = 0;
        for(int i=0; i < productCounter; i++) 
        {
            if(productsInCart[i]!=null&&productsInCart[i].getDiscountPrecentage() > 0){
                double x = (100-productsInCart[i].getDiscountPrecentage())/100;
                double originalPrice =productsInCart[i].getUnitPrice()/x;
                sum+= originalPrice - productsInCart[i].getUnitPrice();
            }
        }
        return Math.round(sum);
    }
    
    
    
    public String printSumSavedByDiscount()
    {
        return "In this purchase you've saved "+ showSumSavedByDiscount()+"$";
    }

    
    @Override
    public String toString() 
    {
        return	"\n"+"  | Cart | " + "  Cart Number: " + cartNumber + "   Max Products: " + maxProducts 
        		+"  Available Products: " + availableProducts
                +"  Product Counter:  " + productCounter
                +"  Costumer Name:   " + customerName.getUserName()+"\n"
                +"\n"+"  Products in cart:    "+"\n" + printCartItems();
    }
}
