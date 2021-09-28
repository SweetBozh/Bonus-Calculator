import java.util.*;
import java.io.*;

class Employee{
    private String name;
    //private 
}

class Product implements Comparable<Product>{
    private String nameProduct;
    private int price;
    private int bonus, totalSalesUnit,totalSalesBaht;
    
    Product(String n,int  pr){
        nameProduct = n;
        price = pr;
        setBonus(pr);
    }

    public int compareTo(Product other){
        if(this.price > other.price )
            return -1;
        else if(this.price == other.price)
            return 0;
        else
            return 1;
     }

     public int getPrice(){
         return price;
     }

     public void setBonus(double pr){
        if(pr>=0 && pr<10000)
            bonus = price * (1/100);
        else if(pr>=10000 && pr<30000)
            bonus = price * (15/1000);
        else if(pr>=30000 && pr<50000)
            bonus = price * (2/100);
        else 
            bonus = price * (25/1000);      
    }
    public int getBonus(){
        return bonus;
    }
/*
    public void SumSalesUnit(ArrayList <Product> p,ArrayList<Employee> em){
        int temp;
            for(int i=0;i<5;i++){
             for(int j = 0 ;j<em.size();j++){
                p.get(i).totalSalesUnit += em.get(j).getSales(i);
        }
    }
*/
    public void printProduct(){
        System.out.printf("%s\t price = %,6d\t (bonus = %,5d)\ttotal sales = %,4d units\t%,7d\n",nameProduct,price,bonus,totalSalesUnit,totalSalesBaht);
    }   

}

public class bonusCalculator {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        String a = input.nextLine();
        System.out.println("Hello, World!");
        System.out.println(a);
        input.close();
        
    }
}
