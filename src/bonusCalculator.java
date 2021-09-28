import java.util.*;
import java.io.*;

class Employee {
    private String name;
    private ArrayList<Integer> sale = new ArrayList<Integer>();
    private int salesBonus, overtimeBonus, totalBonus;

    /* Constructor */
    Employee(String n, ArrayList<Integer> s) {
        super();
        name = n;
        sale = s;
    }
    /*Getter Method*/
    public String getName(){
        return name;
    }
    public ArrayList<Integer> getSale(){
        return sale;
    }
    public void print(){
        System.out.println(name + ": " + sale.get(0) + " " + sale.get(1) + " " + sale.get(2) + " " + sale.get(3) + " " + sale.get(4));
    }
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
    private static Scanner scanFile;
    private static ArrayList<Employee> empArray  = new ArrayList<Employee>();
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        openFile("Enter employee file: ");
        readFileEmployee();

        //Test Printing if Read file correctly? (Correct!)
        for(int i=0; i<empArray.size(); i++){
            empArray.get(i).print();
            System.out.println();
        }


        scanFile.close();
        input.close();
    }//end main
   
    public static void openFile(String ask){ //Method for asking valid Filename
        boolean openSuccess = false;
        while (!openSuccess) {
            try{
                /* Ask for File name */
                System.out.println(ask);
                String fileName = input.nextLine();
                /* Open File */
                scanFile = new Scanner(new File(fileName),"UTF-8");
                openSuccess = true;
                fileName = "";   
            } catch (FileNotFoundException e) {
                System.out.println(e + "\n");
            } catch (RuntimeException e) {
                System.out.println(e + "\n");
            }
        }
    }

    public static void readFileEmployee() { //Method for Read & Create Employee obj.
        while (scanFile.hasNext()) {
            ArrayList<Integer> s = new ArrayList<Integer>();
            String n; //for Name of Employee
            String line = scanFile.nextLine();
            try{
                String[] buf = line.split(",");
                n = buf[0]; // Set to Object Variable
                s.add(Integer.parseInt(buf[1].trim()));
                s.add(Integer.parseInt(buf[2].trim()));
                s.add(Integer.parseInt(buf[3].trim()));
                s.add(Integer.parseInt(buf[4].trim()));
                s.add(Integer.parseInt(buf[5].trim()));

                if(s.size() < 5) throw new ArithmeticException("Missing value : Skip");

                for(int i=0; i<5; i++){
                    if(s.get(i) < 0) throw new ArithmeticException("Unit less than zero");
                }
                Employee emp = new Employee(n,s);
                empArray.add(emp);
            }
            catch(RuntimeException e){
                System.out.println(e + "\n" + line + "\n");
                s.clear();
            }
        }//end while
    }//end ReadFile()

}//end class BonusCalculator
