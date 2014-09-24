import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Assignment1Comment {
    //Here you create an enum to hold the possible options for the sex of the user.
    //It's easier and more efficient than doing them in separate variables.
    private enum Sex {
        Male,
        Female
    }

    public static void main(String[] args) {

        //Here you create a new scanner object that will take user input from the console.
        Scanner scanner = new Scanner(System.in);

        //userInput is your string and it is initialized to be blank. Not null, but blank.
        String userInput = "";

        //A do while loop. Inside the do block are things that you want to occur until the condition of the while is broken.
        do {
            //Call the clearScreen method to clear the screen before displaying the menu.
            clearScreen();

            //Display a basic text based menu. It's just a few sout lines giving the user options
            //That they will then enter as input and will be analyzed by the switch statement that follows.
            System.out.println("Please make a selection.");
            System.out.println("1 - Calculate life");
            System.out.println("2 - Amortization Chart");
            System.out.println("X - Exit");

            //set the userInput string to the next line that the user types.
            userInput = scanner.nextLine();

            //The switch statement analyzes what the user entered as their string and performs the correct action or case
            //based on that input.
            //Java 7 is supposed to be able to support entering a String as the parameter in a switchs statement, but my
            //IDE has major beef with it and I don't know why, because I'm using Java 7.
            switch (userInput) {
                //If the user enters 1, the calcLife function is called.
                case "1":
                    calcLife();
                    break;
                //If the user enters 2, the amortization function is called.
                case "2":
                    amortization();
                    break;
                case "x":
                //If the user enters X, they exit out of the application because the condition of the while has been changed.
                case "X":
                    System.out.println("Goodbye!!!");
                    break;
                //If the user enters something that is not one of the above cases, this is the default case and lets the user know
                //That it did not recognize what they entered and checks for the next input from them.
                default:
                    System.out.println("Unknown Command. Hit enter to continue");
                    scanner.nextLine();
                    break;
            }
        //This is the while statement that keeps the menu going. It's conditions are that it keeps going as long as the userInput
        //String is not null and the userInput string is not X, the loop will continue. Once userInput is either null or X, it will break
        //out of the loop.
        } while(null != userInput && !"X".equals(userInput.toUpperCase()));
    }

    //This is the clearScreen method that clears the screen before the menu is displayed. It's fairly simple, as it prints a blank
    //line to the screen 100 times.
    //There is probably a better way to do it that involves a library. Java has libraries for pretty much everything.
    private static void clearScreen() {
        for(int i = 0; i < 100; i++) {
            System.out.println();
        }
    }


    //This is the calcLife method. It has several calculations made for determining how much life the user has lived and how much they have left. Morbid.
    private static void calcLife(){
        //Make a new scanner for userInput
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        //We gonna use that Sex enum that was defined up in the first few lines of the class.
        Sex sex;

        //Calendar is a Java class. Using two Calendar objects, we can easily calculate the information we need regarding
        //Life lived and life left using them.
        Calendar birthday = Calendar.getInstance();
        Calendar deathday = Calendar.getInstance();
        //This lifeExpectancy int is going to vary with the sex of the user. Women live longer than men.
        int lifeExpectancy;

        //Clear the screen before displaying the morbid life expectancy calculations.
        clearScreen();
        System.out.println("Let's figure out how long you have to live.");

        do {
            //This is a submenu that is displayed once the user chooses the calcLife function. It asks the user
            //if they are male or female and also gives them the option to exit at any time.
            System.out.println("Are you male or female?");
            System.out.println("M - Male");
            System.out.println("F - Female");
            System.out.println("X - Return");
            //This gets the user input.
            userInput = scanner.nextLine();

            //If the user chooses x or X, they quit out of this menu.
            if(null != userInput && "X".equals(userInput.toUpperCase())) {
                return;
            }
            //If the user chooses something other than M or F, the application prints that it doesn't know what their choice means.
            if(null != userInput && !"M".equals(userInput.toUpperCase()) && !"F".equals(userInput.toUpperCase())) {
                System.out.printf("I am not sure what %s means\n", userInput);
            } else {
                //This ternary statement checks if the string input by the user is male or female by seeing if "M" equals user input
                //and if it does sets sex = Male. If it does not equal "M", then it sets sex to Female.
                sex = "M".equals(userInput.toUpperCase()) ? Sex.Male : Sex.Female;
                //This one checks if the sex of the user is male and if it is, sets teir lifeExpectancy to 77, and if they are female,
                //It sets their life Expectancy to 82.
                lifeExpectancy = sex.equals(Sex.Male) ? 77 : 82;
                break;
            }
        //Goes forever until the return or break statement occurs in the do.
        } while(true);


        do {
            //The application asks the user when they were born and specifies how it would like the birthday to be entered into the
            //console and also gives the option for the user to exit.
            System.out.println("When were you born? (Like: 'July 4, 1776') or 'X' to return");
            userInput = scanner.nextLine();
            //If the user chooses x, quit.
            if("X".equals(userInput.toUpperCase())) {
                return;
            }
            try {
                //The birthday and deathday Calendar objects set their times to a date parsed from the userInput
                birthday.setTime(new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(userInput));
                deathday.setTime(new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(userInput));
                //Set the deathday to the amount of years in lifeExpectancy.
                deathday.add(Calendar.YEAR, lifeExpectancy);
                break;
            } catch (Exception ex) {
                //If something goes wrong with the birthday, this message will be displayed.
                System.out.println("That doesn't seem to be a valid birthday!!!");
            }

        } while (true);

        //This try/catch is going to both write the output to the life_expectancy.txt file and to the console.
        try(PrintWriter fileOutput = new PrintWriter(new FileWriter("life_expectancy.txt",true))) {
            //This block prints the user's sex and birthday to the console and writes them to the file.
            System.out.printf("\nYou are %s.\n", sex.name());
            System.out.printf("Your birthday is %1$td-%1tb: %1$tY.\n", birthday);
            fileOutput.printf("You are %s.\n", sex.name());
            fileOutput.printf("Your birthday is %1$td-%1tb: %1$tY.\n", birthday);

            //The following block calculates how many days the user has been alive as well as how many days the user has left to live.
            long diff = Math.abs(birthday.getTime().getTime() - (new Date()).getTime());
            long daysAlive = diff / (24 * 60 * 60 * 1000);
            diff = Math.abs((new Date()).getTime() - deathday.getTime().getTime());
            long daysToLive = diff / (24 * 60 * 60 * 1000);
            //The following block prints the calculations made in the previous block to the console and also to the file.
            System.out.printf("You are %d days old.\n", daysAlive);
            System.out.printf("You have %s days left to live.\n", daysToLive);
            System.out.printf("You have %.1f%% of your life left.\n\n", (daysToLive / (365.25 * lifeExpectancy)) * 100);
            fileOutput.printf("You are %d days old.\n", daysAlive);
            fileOutput.printf("You have %s days left to live.\n", daysToLive);
            fileOutput.printf("You have %.1f%% of your life left.\n\n", (daysToLive / (365.25 * lifeExpectancy)) * 100);

        } catch (IOException e) {
            //This will be printed to the conosle if something goes wrong writing to the file.
            System.out.println("Couldn't write life expectancy to file for some reason!!!");
        }
        //The user may hit enter to continue.
        System.out.println("Hit enter to continue.");

        scanner.nextLine();
    }

    private static void amortization(){

        //This block sets up everything needed to calculate and display everything for the loan amortization function.
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        double loanAmount, monthlyPayments, totalInterest=0, totalPrinciple=0;
        double interestRate;
        int loanYears, paymentNum=1;
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        //This string formats the output that is written to the file and conosle.
        String formattedLine = "%1$-11s %2$-11s %3$-11s %4$-17s %5$-16s %6$-16s\n";
        //Clear the screen.
        clearScreen();

        try(PrintWriter fileOutput = new PrintWriter("loan_amortization.txt")) {
            //Ask the user for the amount of money they would like to borrow or if they would like to exit.
            System.out.print("Please enter the amount to borrow or 'X' to return: ");
            userInput = scanner.nextLine();
            //If the user enters x or X, the user is taken back to the main menu.
            if("X".equals(userInput.toUpperCase())){
                return;
            }

            //the user input is parsed into a double object.
            loanAmount = Double.parseDouble(userInput);

            //The user is asked to enter the annual percentage rate or to enter x to exit.
            System.out.print("Please enter the APR (as 0.xy) or 'X' to return: ");
            userInput = scanner.nextLine();
            if("X".equals(userInput.toUpperCase())){
                return;
            }
            //The interestRate is parsed into a Double object.
            interestRate = Double.parseDouble(userInput);
            //The user is asked how many years comprise the term of the loan or to enter X to exit.
            System.out.print("Please enter the number of years or 'X' to return: ");
            userInput = scanner.nextLine();
            if("X".equals(userInput.toUpperCase())){
                return;
            }

            //The loan years are parsed into an Integer object.
            loanYears = Integer.parseInt(userInput);

            //Calculate the monthly payments.
            monthlyPayments = loanAmount * Math.pow(1 + interestRate / 12, loanYears * 12) * (interestRate / 12) / (Math.pow(1 + interestRate / 12, loanYears * 12) - 1);

            //Print the following to the console and write it to a file as well:
            //The Loan Amount
            //The APR
            //The Loan Term
            //and the Monthly Payment and formattedLine.
            System.out.print("\n");
            System.out.println("Loan Details:");
            System.out.printf("  Loan Amount: %s\n", currencyFormat.format(loanAmount));
            System.out.printf("  Annual Percentage Rate: %.3f%%\n", interestRate*100);
            System.out.printf("  Loan Term: %d Years\n", loanYears);
            System.out.printf("  Monthly payment: %s\n", currencyFormat.format(monthlyPayments));
            System.out.print("\n");
            System.out.printf(formattedLine, "Payment #", "Principle", "Interest", "Total Principle", "Total Interest", "Balance");
            fileOutput.println("Loan Details:");
            fileOutput.printf("  Loan Amount: %s\n", currencyFormat.format(loanAmount));
            fileOutput.printf("  Annual Percentage Rate: %.3f%%\n", interestRate*100);
            fileOutput.printf("  Loan Term: %d Years\n", loanYears);
            fileOutput.printf("  Monthly payment: %s\n", currencyFormat.format(monthlyPayments));
            fileOutput.print("\n");
            fileOutput.printf(formattedLine,"Payment #","Principle","Interest", "Total Principle", "Total Interest", "Balance");

            do {
                //Do the following until the loanAmount is 0.
                //This line calculates the total interest and increments it with each month.
                totalInterest += monthlyPayments - (monthlyPayments - loanAmount * interestRate / 12);
                //This line calculates the total principle and increments it with each month as it's payed.
                totalPrinciple += monthlyPayments - loanAmount * interestRate / 12;
                //The following two blocks of code calculate each piece of information and prints it to the console and
                //designated file in the format of the formatted Line.
                System.out.printf(formattedLine, paymentNum,
                        currencyFormat.format(monthlyPayments - loanAmount * interestRate / 12),
                        currencyFormat.format(monthlyPayments - (monthlyPayments - loanAmount * interestRate / 12)),
                        currencyFormat.format(totalPrinciple),
                        currencyFormat.format(totalInterest),
                        currencyFormat.format(Math.abs(loanAmount - (monthlyPayments - loanAmount * interestRate / 12))));
                fileOutput.printf(formattedLine, paymentNum + ":",
                        currencyFormat.format(monthlyPayments - loanAmount * interestRate / 12),
                        currencyFormat.format(monthlyPayments - (monthlyPayments - loanAmount * interestRate / 12)),
                        currencyFormat.format(totalPrinciple),
                        currencyFormat.format(totalInterest),
                        currencyFormat.format(Math.abs(loanAmount - (monthlyPayments - loanAmount * interestRate / 12))));
                loanAmount -= monthlyPayments - loanAmount * interestRate / 12;
                paymentNum++;
            } while(Math.round(loanAmount)>0);

            //Print and write the total amount of money paid.
            System.out.printf("\nTotal Payments: %1$-9s\n\n", currencyFormat.format(totalInterest + totalPrinciple));
            fileOutput.printf("\nTotal Payments: %1$-9s", currencyFormat.format(totalInterest + totalPrinciple));
        //Input, fileIO, and number formatting exceptions
        } catch(InputMismatchException intFormatError){
            System.out.println("That doesn't seem to be a valid number and the program will close!");
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't write loan amortization to file for some reason!!!");
        } catch (NumberFormatException ex) {
            System.out.println(("That isn't a valid number!!!"));
        }
        //The user inputs enter to continue.
        System.out.println("Hit enter to continue.");
        //
        scanner.nextLine();
    }
}
