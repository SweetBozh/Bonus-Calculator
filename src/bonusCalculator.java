import java.util.*;
class Product implements Comparable<Product>{
    private String nameProduct;
    private double price;

    Product(String n,double pr){
        nameProduct = n;
        price = pr;
    }

    public int Comparable(Product other){
        if(this.price > other.price )
            return -1;
        else if()
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
