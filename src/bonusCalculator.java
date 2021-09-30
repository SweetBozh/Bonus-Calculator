
/*Members
1. Warunyupa Lerdsaeng   6313180
2. Nalin Suesangiamsakul 6313216*/
import java.util.*;
import java.io.*;

class Employee implements Comparable<Employee> {
    private String name;
    private ArrayList<Integer> sale = new ArrayList<Integer>();
    private double salesBonus, overtimeBonus, totalBonus = 0;
    private double totalSalesEmp = 0, extraBonus;
    private boolean winner = false;

    /* Constructor */
    Employee(String n, ArrayList<Integer> s) {
        super();
        name = n;
        sale = s;
    }

    public int compareTo(Employee other) {
        if (this.totalSalesEmp > other.totalSalesEmp)
            return -1;
        else if (this.totalSalesEmp == other.totalSalesEmp)
            return 0;
        else
            return 1;
    }

    /* Getter & Setter Method */
    public String getName() {
        return name;
    }

    public ArrayList<Integer> getSale() {
        return sale;
    }

    public double getTotalSalesEmp() {
        return totalSalesEmp;
    }

    public void set_overtimeBonus(double OTBonus) {
        overtimeBonus = OTBonus;
        totalBonus += overtimeBonus;
    }

    public void set_salesBonus(double sb) {
        salesBonus = sb;
        totalBonus += salesBonus;
    }

    public void set_extraBonus(double eb) {
        extraBonus = eb;
        totalBonus += extraBonus;
    }

    public void set_totalSales(double ts) {
        totalSalesEmp = ts;
    }

    public void setWinner(boolean a) {
        winner = a;
    }

    /* Activity */
    public void calSalesBonus(ArrayList<Product> p) {
        double sb = 0;
        for (int i = 0; i < 5; i++) {
            sb += (p.get(i).getBonus() * sale.get(i));
        }
        this.set_salesBonus(sb);
    }

    public void calTotalSales(ArrayList<Product> p) {
        double ts = 0;
        for (int i = 0; i < 5; i++) {
            ts += (p.get(i).getPrice() * sale.get(i));
        }
        this.set_totalSales(ts);
    }

    public void calExtraBonus(int amountWinner) {
        double extraBonus = (totalSalesEmp * 0.005) / amountWinner;
        this.set_extraBonus(extraBonus);
    }

    public void print() {
        if (winner == true) {
            System.out.printf("%-8s >> Air conditioners (%2d)  CCTV systems (%2d)  Dishwashers (%2d)  Microwave ovens (%2d)  Refridgerators (%2d)\n",name, sale.get(0), sale.get(1), sale.get(2), sale.get(3), sale.get(4));
            System.out.printf("            Total sales: %,.0f\n", totalSalesEmp);
            System.out.printf("            Total bonus: %,.0f\n", totalBonus);
            System.out.printf("                  -> sales bonus    = %,.0f\n", salesBonus);
            System.out.printf("                  -> overtime bonus = %,.0f\n", overtimeBonus);
            System.out.printf("                  -> extra bonus    = %,.0f\n", extraBonus);
        } 
        else {
            System.out.printf("%-8s >> Air conditioners (%2d)  CCTV systems (%2d)  Dishwashers (%2d)  Microwave ovens (%2d)  Refridgerators (%2d)\n",name, sale.get(0), sale.get(1), sale.get(2), sale.get(3), sale.get(4));
            System.out.printf("            Total sales  = %,.0f\n", totalSalesEmp);
            System.out.printf("            Total bonus: %,.0f\n", totalBonus);
            System.out.printf("                  -> sales bonus    = %,.0f\n", salesBonus);
            System.out.printf("                  -> overtime bonus = %,.0f\n", overtimeBonus);
        }

    }
}// end class Employee

class Product implements Comparable<Product> {
    private String nameProduct;
    private double price;
    private double bonus, totalSalesUnit, totalSalesBaht;

    /* Constructor */
    Product(String n, double pr) {
        nameProduct = n;
        price = pr;
        setBonus(pr);
    }

    public int compareTo(Product other) {
        if (this.totalSalesBaht > other.totalSalesBaht)
            return -1;
        else if (this.totalSalesBaht == other.totalSalesBaht)
            return 0;
        else
            return 1;
    }

    /* Getter & Setter Method */
    public double getPrice() {
        return price;
    }

    public double getBonus() {
        return bonus;
    }

    public String getName() {
        return nameProduct;
    }

    public void setBonus(double pr) {
        if (pr >= 0 && pr < 10000)
            bonus = price * 0.01;
        else if (pr >= 10000 && pr < 30000)
            bonus = price * 0.015;
        else if (pr >= 30000 && pr < 50000)
            bonus = price * 0.02;
        else
            bonus = price * 0.025;
    }
    
    public void setSalesBaht() {
        totalSalesBaht = totalSalesUnit * price;
    }

    /* Activity */
    public void SumSalesUnit(double sum) {
        totalSalesUnit = sum;
    }

    public void printProduct() {
        System.out.printf("%-20s price = %,7.0f\t (bonus = %,5.0f)\ttotal sales = %,5.0f units\t%,10.0f baht\n",
                nameProduct, price, bonus, totalSalesUnit, totalSalesBaht);
    }
}// end class Product

class Overtime {
    private int month;
    private ArrayList<String> nameOTList = new ArrayList<String>();

    /* Constructor */
    Overtime(int m, ArrayList<String> nList) {
        month = m;
        nameOTList = nList;
    }

    /* Getter Method */
    public int getMonth() {
        return month;
    }

    public ArrayList<String> getNameOT() {
        return nameOTList;
    }

    /* Activity */
    public void removeOT(int k) {
        nameOTList.remove(k);
    }
}// end class Overtime

// ======================================= Main Class
// ================================================
public class bonusCalculator {
    private static Scanner scanFile;
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Employee> empArray = new ArrayList<Employee>();
    private static ArrayList<Product> proArray = new ArrayList<Product>();
    private static ArrayList<Overtime> OTArray = new ArrayList<Overtime>();
    private static int invalid; // for checking invalid value
    private static int unit; // for checking if unit of Product is valid
    private static int printCorrect; // for printing Correction

    public static void main(String[] args) throws Exception {
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("                                   -=*=*== Welcome to Bonus Calculator ==*=*=-");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------------");

        /* Open and Read file */
        openFile("Enter product file: ");
        readFileProduct();
        System.out.println();

        openFile("Enter overtime file: ");
        readFileOvertime();
        System.out.println();

        openFile("Enter employee file: ");
        System.out.println();
        readFileEmployee();

        sumSalesUnit();
        removeDuplicateOT();
        calOvertime();
        calculateBonus();
        Collections.sort(empArray);
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("                                          =*== Bonus Calculating Result ==*=");
        System.out.println();
        for (int i = 0; i < empArray.size(); i++) {
            empArray.get(i).print();
            System.out.println();
        }
        /* Print Product ArrayList */
        Collections.sort(proArray);//Sort Product for Printing Summary
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.printf("\n                                           =*== Product summary ==*=\n\n");
        for (int i = 0; i < proArray.size(); i++) {
            proArray.get(i).printProduct();
        }
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println("End Program.");
        System.out.println();
        scanFile.close();
        input.close();
    }// end main

    public static void openFile(String ask) { // Method for asking valid Filename
        boolean openSuccess = false;
        while (!openSuccess) {
            try {
                /* Ask for File name */
                System.out.println(ask);
                String fileName = input.nextLine();
                /* Open File */
                scanFile = new Scanner(new File(fileName), "UTF-8");
                openSuccess = true;
                fileName = "";
            } catch (FileNotFoundException e) {
                System.out.println(e + "\n");
            } catch (RuntimeException e) {
                System.out.println(e + "\n");
            }
        }
    }// end openFile

    public static void readFileEmployee() { // Method for Read & Create Employee obj.
        while (scanFile.hasNext()) {
            ArrayList<Integer> s = new ArrayList<Integer>();
            String n; // for Name of Employee
            String line = scanFile.nextLine();
            try {
                String[] buf = line.split(",");
                n = buf[0]; // Set to Object Variable
                s.add(checkInvalid(buf[1].trim(), line)); // checkInvalid check if buf[1] is Integer, if not -> convert
                                                          // to zero, return int value.
                s.add(checkInvalid(buf[2].trim(), line));
                s.add(checkInvalid(buf[3].trim(), line));
                s.add(checkInvalid(buf[4].trim(), line));
                s.add(checkInvalid(buf[5].trim(), line));

                if (printCorrect == 1) {
                    System.out.println("Input Error : " + line);
                    System.out.printf("Correction  : %s, %2d, %2d, %2d, %2d, %2d\n", n, s.get(0), s.get(1), s.get(2),
                            s.get(3), s.get(4));
                    System.out.println();
                    printCorrect = 0;
                }
                Employee emp = new Employee(n, s);
                empArray.add(emp);

            } catch (IndexOutOfBoundsException e) {
                System.out.println("Input error : " + line);
                System.out.println("Missing value, skip\n");
                s.clear();
            }
        } // end while
    }// end readFileEmployee()

    public static int checkInvalid(String buf, String line) { // Get value from buf[1],buf[2],... Convert to zero if
                                                              // invalid. Then, return value.
        try {
            unit = Integer.parseInt(buf);
            invalid = 0;
            if (unit < 0)
                throw new ArithmeticException("Unit less than zero");
            if (buf.isEmpty())
                throw new IndexOutOfBoundsException("Missing Value");
        } catch (RuntimeException e) {
            invalid = 1; // Use in condition return below
            printCorrect = 1; // Use for count if already print Correction
        }
        if (invalid == 0)
            return unit;
        else
            return 0;
    }// end checkInvalid

    public static void readFileProduct() {
        String nameProduct;
        int price;
        Product p;
        while (scanFile.hasNext()) {
            String line = scanFile.nextLine();
            String[] buf = line.split(",");
            nameProduct = buf[0].trim();
            price = Integer.parseInt(buf[1].trim());
            p = new Product(nameProduct, price);
            proArray.add(p);
        }
    }// end readFileProduct

    public static void sumSalesUnit() {
        /* calculate totalSalesUnit */
        ArrayList<Integer> tempSales = new ArrayList<Integer>();
        double sumUnit;
        for (int i = 0; i < proArray.size(); i++) {
            sumUnit = 0;
            for (int j = 0; j < empArray.size(); j++) {
                tempSales = empArray.get(j).getSale();
                sumUnit += tempSales.get(i);
            }
            proArray.get(i).SumSalesUnit(sumUnit);
            proArray.get(i).setSalesBaht();
        }
    }// end sumSalesUnit

    public static void readFileOvertime() {
        /* Read file and add all Overtime Employee to OTArray */
        int month;
        ArrayList<String> nameList;
        Overtime OT;

        while (scanFile.hasNext()) {
            String line = scanFile.nextLine();
            String[] buf = line.split(",");
            nameList = new ArrayList<String>();
            month = Integer.parseInt(buf[0].trim());
            for (int i = 1; i < buf.length; i++) {
                nameList.add(buf[i].trim());
            }
            OT = new Overtime(month, nameList);
            OTArray.add(OT);
        }
    }// ead readFileOvertime

    public static void removeDuplicateOT() {
        /* Remove Employee that already get OT this month in OTArray */
        int copy;
        ArrayList<String> tempListOT = new ArrayList<String>();
        for (int i = 0; i < OTArray.size(); i++) {
            tempListOT = OTArray.get(i).getNameOT();
            for (int j = 0; j < tempListOT.size(); j++) {
                for (int k = j + 1; k < tempListOT.size(); k++) {
                    copy = tempListOT.get(j).compareToIgnoreCase(tempListOT.get(k));
                    if (copy == 0)
                        OTArray.get(i).removeOT(k);
                }
            }
        }
    }// end removeDuplicateOT

    public static void calOvertime() {
        /* Calculate and set overtimeBonus for 'an non-duplicate employee' */
        ArrayList<String> tempListOT = new ArrayList<String>(); // keep Overtime Employees in a month temporarily
        int checkOT;
        double sumOT;
        for (int i = 0; i < empArray.size(); i++) {// each employee
            sumOT = 0;
            for (int j = 0; j < OTArray.size(); j++) { // each month
                tempListOT = OTArray.get(j).getNameOT();
                for (int k = 0; k < tempListOT.size(); k++) { // compare an employee with each name in a month
                    checkOT = empArray.get(i).getName().compareToIgnoreCase(tempListOT.get(k));
                    if (checkOT == 0) { // name exists, get overtimeBonus
                        sumOT += 2000.0;
                    }
                } // end for
            } // end for
            empArray.get(i).set_overtimeBonus(sumOT);
        } // end for
    }// end calOvertime

    public static void calculateBonus() {
        /* Calculate SalesBonus */
        ArrayList<Double> tsaleEmpList = new ArrayList<Double>();
        ArrayList<Integer> indexOfMax = new ArrayList<Integer>();

        for (int i = 0; i < empArray.size(); i++) {
            empArray.get(i).calTotalSales(proArray);
            empArray.get(i).calSalesBonus(proArray); // Set Employee's SalesBonus from price of Products
            tsaleEmpList.add(empArray.get(i).getTotalSalesEmp());
        }
        /* Calculate ExtraBonus */
        Double maxSale = Collections.max(tsaleEmpList).doubleValue();
        for (int i = 0; i < tsaleEmpList.size(); i++) {
            if (tsaleEmpList.get(i).doubleValue() == maxSale) {
                indexOfMax.add(i); //Get Amount of Winner
            }
        }
        for (int i = 0; i < tsaleEmpList.size(); i++) {
            if (tsaleEmpList.get(i).doubleValue() == maxSale) {
                empArray.get(i).calExtraBonus(indexOfMax.size()); //Give Extra Bonus devided by indexOfMax.size()
                empArray.get(i).setWinner(true); //If winner = true -> Print with extraBonus
            }
        }
    }
}// end class BonusCalculator