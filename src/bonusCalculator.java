import java.util.*;
import java.io.*;

class Employee {
    private String name;
    private ArrayList<Integer> sale = new ArrayList<Integer>();
    private int salesBonus, overtimeBonus, totalBonus;

    /* Constructor */
    Employee(String n) {
        name = n;
    }

    public void readFile() {

    }

}

class Product {
    private String nameProduct;
    private double price;

    Product(String n, double pr) {
        nameProduct = n;
        price = pr;
    }

}

public class bonusCalculator {
    public static void main(String[] args) throws Exception {

        boolean openPSuccess = false, openESuccess = false, openOSuccess = false;
        String pfile;
        String efile;
        String ofile;
        Scanner input = new Scanner(System.in);
        Scanner scanFile;

        while (!openPSuccess) {
            try {
                System.out.println("Enter product file");
                pfile = input.nextLine();
                scanFile = new Scanner(new File(pfile));
                openPSuccess = true;
            } catch (FileNotFoundException e) {
                System.out.println(e);
            }
        }
        while (!openESuccess) {
            try {
                System.out.println("Enter employee file");
                efile = input.nextLine();
                scanFile = new Scanner(new File(efile));
                openESuccess = true;
            } catch (FileNotFoundException e) {
                System.out.println(e);
            }
        }
        while (!openOSuccess) {
            try {
                System.out.println("Enter overtime file");
                ofile = input.nextLine();
                scanFile = new Scanner(new File(ofile));
                openOSuccess = true;
            } catch (FileNotFoundException e) {
                System.out.println(e + "\n");
            }
        }

        input.close();

    }
}
