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
    public void set_overtimeBonus(int OTBonus){
        overtimeBonus = OTBonus;
    }
    public void print(){
        //System.out.printf("%s, %2d, %2d, %2d, %2d, %2d", name, sale.get(0), sale.get(1), sale.get(2), sale.get(3), sale.get(4));
        
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

     public int getBonus(){
        return bonus;
     }

     public void setBonus(int pr){
        //Narrow Casting
         if(pr>=0 && pr<10000)
              bonus = (int)( (double)price * 0.01 );
         else if(pr>=10000 && pr<30000)
             bonus = (int)( (double)price * 0.015 );
         else if(pr>=30000 && pr<50000)
             bonus = (int)( (double)price * 0.02 );
         else 
             bonus = (int)( (double)price * 0.025 );
    }

    public void SumSalesUnit(int sum){
        totalSalesUnit = sum;
     }
     
    public void setSalesBaht(){
        totalSalesBaht = totalSalesUnit * price;
    }

    public void printProduct(){
        System.out.printf("%-20s price = %,7d\t (bonus = %,5d)\ttotal sales = %,5d units\t%,10d baht\n",nameProduct,price,bonus,totalSalesUnit,totalSalesBaht);
    }   
}

class Overtime{
    private int month;
    private ArrayList<String> nameOTList= new ArrayList<String>();
  
    Overtime(int m,ArrayList<String> list){
        month = m;
        nameOTList = list;
    }
    public ArrayList<String> getNameOT(){
      return nameOTList;
    }
    public void removeOT(int k){
      nameOTList.remove(k);
    }
}

public class bonusCalculator {
    private static Scanner scanFile;
    private static ArrayList<Employee> empArray  = new ArrayList<Employee>();
    private static Scanner input = new Scanner(System.in);
    private static int invalid; //for checking invalid value
    private static int unit; //for checking if unit is valid
    private static String correct;
    private static int printCorrect;
    private static ArrayList<Product> proArray = new ArrayList<Product>();
    private static ArrayList<Overtime> OTArray = new ArrayList<Overtime>();    
    public static void main(String[] args) throws Exception {
        openFile("Enter employee file: ");
        System.out.println("---------------------------------------------------------------");
        readFileEmployee();

        
        openFile("Enter product file: ");
        System.out.println("---------------------------------------------------------------");
        readFileProduct();
        sumSalesUnit();

        openFile("Enter Overtime file: ");
        System.out.println("---------------------------------------------------------------");
        readFileOvertime();
        removeDuplicateOT();
        calOvertime();


        //Test Printing if Read file correctly? (Correct!)
        for(int i=0; i<empArray.size(); i++){
            empArray.get(i).print();
            System.out.println();
        }
<<<<<<< HEAD
=======
        //Print Product ArrayList
        System.out.printf("\n\n=== Product summary ===\n");
        for(int i=0;i<proArray.size();i++){
            proArray.get(i).printProduct();
        }
        System.out.println();

>>>>>>> 1bf524cb9a4bea5875c2293ba3285f3003fcf066
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
                s.add(checkInvalid(buf[1].trim(),line)); //checkInvalid check if buf[1] is Integer, if not -> convert to zero, return int value.
                s.add(checkInvalid(buf[2].trim(),line));
                s.add(checkInvalid(buf[3].trim(),line));
                s.add(checkInvalid(buf[4].trim(),line));
                s.add(checkInvalid(buf[5].trim(),line));
                correct = n + ", " + s.get(0) + ", "+ s.get(1) +", "+  s.get(2)+ ", "+ s.get(3) + ", "+ s.get(4);

                if(printCorrect == 1){ 
                    System.out.println("Input Error : " + line);
                    System.out.println("Correction  : " + correct);
                    System.out.println();
                    printCorrect = 0;
                }
                Employee emp = new Employee(n,s);
                empArray.add(emp);
               
            }
            catch(IndexOutOfBoundsException e){
                System.out.println("Input error : "+ line);
                System.out.println("Missing value, skip\n");
                s.clear();
            }
        }//end while
    }//end ReadFile()

        
    public static int checkInvalid(String buf,String line){ //Get value from buf[1],buf[2],... Convert to zero if invalid. Then, return value.
        try{
            unit = Integer.parseInt(buf);
            invalid = 0;
            if(unit < 0) throw new ArithmeticException("Unit less than zero");
            if(buf.isEmpty()) throw new IndexOutOfBoundsException("Missing Value");
        }
        catch(RuntimeException e){
            invalid = 1; //Use in condition return below
            printCorrect = 1; //Use for count if already print Correction
        }
        if(invalid == 0) return unit;
        else return 0;
    }
    public static void readFileProduct(){
        String nameProduct;
        int price;
        Product p;
        while (scanFile.hasNext()) {
            String line = scanFile.nextLine();
            String []buf = line.split(",");
            nameProduct = buf[0].trim();
            price = Integer.parseInt(buf[1].trim()); 
            p  = new Product(nameProduct,price);
            proArray.add(p);
        }
    }

    public static void sumSalesUnit(){
       /*calculate totalSalesUnit*/
        ArrayList<Integer> tempSales = new ArrayList<Integer>();
        for(int i=0; i<proArray.size(); i++){
          int sumUnit = 0;
          for(int j=0; j<empArray.size(); j++){
              tempSales = empArray.get(j).getSale();
              sumUnit += tempSales.get(i);
          }
          proArray.get(i).SumSalesUnit(sumUnit);
          proArray.get(i).setSalesBaht();
        }
        
        Collections.sort(proArray);
    }

    public static void readFileOvertime(){
      /*Read file and add all Overtime Employee to OTArray*/
      int month;
      ArrayList<String> nameList;
      Overtime OT;

      while (scanFile.hasNext()) {
        String line = scanFile.nextLine();
        String []buf = line.split(",");
        nameList = new ArrayList<String>();
        month = Integer.parseInt(buf[0].trim());
        for(int i=1;i<buf.length;i++){
          nameList.add(buf[i].trim());
        }
        OT = new Overtime(month,nameList);
        OTArray.add(OT);
      }
    }

    public static void removeDuplicateOT(){
      /*Remove Employee that already get OT this month in OTArray*/
      int copy;
      ArrayList<String> tempListOT = new ArrayList<String>();
      for(int i=0;i<OTArray.size();i++){
        tempListOT = OTArray.get(i).getNameOT();
        for(int j=0;j<tempListOT.size();j++){
            for(int k=j+1;k<tempListOT.size();k++){
              copy = tempListOT.get(j).compareToIgnoreCase(tempListOT.get(k));
              if(copy==0)
                OTArray.get(i).removeOT(k);
            }
        }
      }
    }

    public static void calOvertime(){
      /*Calculate and set overtimeBonus for 'an non-duplicate employee'*/
      ArrayList<String> tempListOT = new ArrayList<String>(); //tempListOT keep Overtime Employees in a month
      int checkOT,sumOT;
      for(int i=0 ;i<empArray.size();i++){
        sumOT = 0;
        for(int j=0;j<OTArray.size();j++){ //OTArray keep All Overtime Employees in a year
          tempListOT = OTArray.get(j).getNameOT();
          for(int k=0;k<tempListOT.size();k++){
            checkOT = empArray.get(i).getName().compareToIgnoreCase(tempListOT.get(k)); //Check each Employee with the name in Overtime.txt. If exists, get overtimeBonus 
            if(checkOT==0){ 
              sumOT+=2000;
            }
          }
        }
       empArray.get(i).set_overtimeBonus(sumOT); //set 
      }//loop-employee
    }
}//end class BonusCalculator
