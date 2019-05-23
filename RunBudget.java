package budget;
import java.io.*;
import java.util.Calendar;
import java.util.Scanner;
import java.util.TimeZone;

/**
 *
 * @author adamw
 */
// Demo class that utilizes the Budget class to calculate remaining budget totals based on the
// day of the month we are currently in. Class saves remaining budget throughout the month, and then
// resets on the first day of the month.

public class RunBudget extends Budget {
    static int dayOfMonth;

    public static void main(String[] args) throws Exception {
        RunBudget demo = new RunBudget();      //Instantiate RunBudget object
        Budget budget = new Budget();            //Instantiate Budget object
        int day = RunBudget.getDayOfMonth();          //Invoke the getDayOfMonth method
        /*If statement to determine which path to take depending on the day of the month. The program
        will ask you to initialize values if it is the first of the month, otherwise, it will load
        previous data and continue to calculate based of the month's running total.*/
        if(day > 1) {
            String file2 = RunBudget.readFile();
            //If statement to check if the save file is empty
            if (file2.length() > 0) {
                System.out.println("Remaining budget: $" + file2);
                double remainingBudget = Double.parseDouble(file2);
                double rem = Budget.calculateRemainder(remainingBudget);
                Budget.saveFile(rem);
            }
            else if (file2.length() == 0){
                Scanner in = new Scanner(System.in);
                System.out.println("Enter the amount you can spend this month: ");
                double total = in.nextDouble();
                double working_total = Budget.workingTotal(total);    //calling workingTotal method from Budget class
                double remaining = Budget.calculateRemainder(working_total);    //calling checkCon method from Budget class
                Budget.saveFile(remaining);
            }
        }
        else{
            Scanner in = new Scanner(System.in);
            System.out.println("Enter the amount you can spend this month: ");
            double total = in.nextDouble();
            double working_total = Budget.workingTotal(total);
            double remaining = Budget.calculateRemainder(working_total);
            Budget.saveFile(remaining);
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

        InputStream is = new FileInputStream("C:\\Users\\adamw\\Documents\\NetBeansProjects\\Budget\\src\\budget\\budgetdata.txt");
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
