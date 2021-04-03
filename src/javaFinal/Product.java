package javaFinal;
//Artiom Sheremetiev and Niv Porat
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Product {
    private int catalogNumber;
    private double unitPrice;
    private float discountPrecentage;
    private String productName;

    
    
    public Product(String ProductName,int catalogNumber,double unitPrice)//manual input constructor
    {
        this.productName= ProductName;
        this.catalogNumber = catalogNumber;
        this.unitPrice = unitPrice;
    }
    
    
    
    
    public Product (Scanner s)throws FileNotFoundException //contructor for reading from file
    {
        this.productName = s.next();
        this.catalogNumber = s.nextInt();
        this.unitPrice =Double.parseDouble(s.next());
    }

    
    
    public Product() // default constructor
    {

    }


    public String getProductName() {
        return productName;
    }
    
    
    
    
    public void setProductName(String productName)
    {
        this.productName = productName;
    }
    
    
    
    public int getCatalogNumber()
    {
        return catalogNumber;
    }
    
    
    
    
    
    public void setCatalogNumber(int catalogNumber)
    {
        if(catalogNumber<999) this.catalogNumber = 1000;
        else
            this.catalogNumber = catalogNumber;
    }
    
    
    
    
    public double getUnitPrice() 
    {
        return unitPrice;
    }
    
    
    
    
    
    public boolean setUnitPrice(double unitPrice)
    {
        setDiscountPrecentage(0);
        if(unitPrice <= 0) {
            this.unitPrice=0;
            return false;
        }

        else {
            this.unitPrice = unitPrice;
            return true;
        }
    }

    
    
    public boolean setUnitPrice(double unitPrice, float discountPrecentage) 
    {
        if(unitPrice <0 || discountPrecentage<0|| discountPrecentage >99) 
        	return false;
        
            this.unitPrice = (100-discountPrecentage)*unitPrice / 100;
            this.discountPrecentage = discountPrecentage;
            return true;

    }
    
    
    
    public float getDiscountPrecentage() 
    {
        return discountPrecentage;
    }
    
    
    
    
    
    public boolean setDiscountPrecentage(float discountPrecentage) 
    {
        if(discountPrecentage < 0 || discountPrecentage >99) return false;
        else {
            this.discountPrecentage = discountPrecentage;
            setUnitPrice(this.unitPrice, this.discountPrecentage);
            return true;
        }
    }

    
    
    
//    public String toString() {
//        return 	"\n"+"\n"+"| Product |" +"\n"
//        		+"\n"+"Catalog Number: " + catalogNumber 
//        		+"\n"+ "Unit Price: " + unitPrice 
//        		+"\n"+ "Discount Precentage: " + discountPrecentage 
//        		+"\n"+ "Product Name: " + productName;
//    }


    public String toString() {
        return 	"\n" +"  | Product |  " +"   Catalog Number:   " + catalogNumber + "    Unit Price:    " + unitPrice 
        		+ "    Discount Precentage:   " + discountPrecentage + "    Product Name:    " + productName;
    }


}
