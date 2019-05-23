/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;
import java.util.Scanner;
import java.lang.*;
import java.io.FileWriter;
import java.io.IOException;


/**
 *
 * @author adamw
 */
// Budget class that establishes methods used to calculate remaining budget amount after expenses.
// This class does not actually run, but provides methods to be used by DemoBudget class.
public class Budget {
    /**
     */
     
    
    // Method to determine remaining budget after factoring in known expenses
    public static double workingTotal(double total){
        Scanner in = new Scanner(System.in);
        double  mort, veh, bill, wt;

        System.out.println("Enter housing amount: ");
        mort = in.nextDouble();

        System.out.println("Enter monthly vehicle note: ");
        veh = in.nextDouble();

        System.out.println("Enter total amount of bills with a fixed monthly amount: ");
        bill = in.nextDouble();

        wt = total - mort - veh - bill;
        System.out.println("After known expenses, you have $" + wt + " left to spend.");
        return wt;
    }
    //determine remaining budget
    public static double remBudget(double a, double b){
        double rem = a - b;
        return rem; 
    }
    // Method to calculate remaining budget totals after each expense
    public static double calculateRemainder(double y){
        Scanner in = new Scanner(System.in);
        boolean flag = true;
        double remain;
        while (flag){
            System.out.println("How much did you spend: ");
            double spent = in.nextDouble();
            if (spent > 0){
                remain = remBudget(y, spent);
                if(remain > 0)
                    System.out.println("You are still $" + remain + " under budget");
                else if (remain == 0)
                    System.out.println("You have reached your budget!");
                else
                    System.out.println("You are $" + Math.abs(remain) + " over budget");
                y = remain;
            }
            else
                flag = false;
        }
        return y;
    }
    // Method used to save the remaining budget amount to use later
    public static void saveFile(double remaining) throws IOException{
        Double r = remaining;
        String s = Double.toString(r);

        FileWriter fw = new FileWriter ("C:\\Users\\adamw\\Documents\\NetBeansProjects\\Budget\\src\\budget\\budgetdata.txt");
        fw.write(s);
        fw.close();
    }
}