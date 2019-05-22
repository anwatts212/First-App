/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.util.Calendar;
import java.util.Scanner;
import java.util.TimeZone;
import budget.Budget;

/**
 *
 * @author adamw
 */
// Demo class that utilizes the Budget class to calculate remaining budget totals based on the
// day of the month we are currently in. Class saves remaining budget throughout the month, and then
// resets on the first day of the month.

public class DemoBudget {
    static int dayOfMonth;

    public static void main(String[] args) throws Exception {
        DemoBudget demo = new DemoBudget();      //Instantiate DemoBudget object
        Budget budget = new Budget();            //Instantiate Budget object
        int day = demo.getDayOfMonth();          //Invoke the getDayOfMonth method
        /*If statement to determine which path to take depending on the day of the month. The program
        will ask you to initialize values if it is the first of the month, otherwise, it will load
        previous data and continue to calculate based of the month's running total.*/
        if(day > 1) {
            String file2 = demo.readFile();
            //If statement to check if the save file is empty
            if (file2.length() > 0) {
                System.out.println("Remaining budget: $" + file2);
                double remainingBudget = Double.parseDouble(file2);
                double rem = budget.calculateRemainder(remainingBudget);
                budget.saveFile(rem);
            }
            else if (file2.length() == 0){
                Scanner in = new Scanner(System.in);
                System.out.println("Enter the amount you can spend this month: ");
                double total = in.nextDouble();
                double working_total = budget.workingTotal(total);    //calling workingTotal method from Budget class
                double remaining = budget.calculateRemainder(working_total);    //calling checkCon method from Budget class
                budget.saveFile(remaining);
            }
        }
        else{
            Scanner in = new Scanner(System.in);
            System.out.println("Enter the amount you can spend this month: ");
            double total = in.nextDouble();
            double working_total = budget.workingTotal(total);
            double remaining = budget.calculateRemainder(working_total);
            budget.saveFile(remaining);
        }

    }
    // Method that creates calendar object to get the current day of the month
    public static int getDayOfMonth() {
        Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
        dayOfMonth = localCalendar.get(Calendar.DAY_OF_MONTH);
        return dayOfMonth;
    }
    // Method that reads the save file
    public static String readFile() throws IOException {

        InputStream is = new FileInputStream("C:\\Users\\adamw\\Documents\\NetBeansProjects\\Budget\\src\\budgetdata.txt");
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));
        String line = buf.readLine();
        StringBuilder sb = new StringBuilder();

        while (line != null) {
            sb.append(line).append("\n");
            line = buf.readLine();
        }
        String fileAsString = sb.toString();
        return fileAsString;
    }
}

