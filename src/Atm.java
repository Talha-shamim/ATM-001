import java.util.ArrayList;
import java.util.Scanner;

// Interface for all the necessary methods I need in my atm
interface Components{

    public void displayMessage1();
    public void validateCustomer();
    public void displayMessage2();

}

//driver - Main
//calls interface methods

public class Atm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Implementer implementer = new Implementer();

        while(true){
            implementer.displayMessage1();
            implementer.validateCustomer();
            implementer.displayMessage2();

            System.out.println("--------------------------------------------------");
            System.out.println("          Want to continue press 1 else press 0");
            System.out.println("--------------------------------------------------");
            int op = sc.nextByte();
            if(op==0)break;
            if(op!=1 && op!=0) {
                System.out.println("--------------------------------------------------");
                System.out.println("            Invalid Selection");
                System.out.println("--------------------------------------------------");
                implementer.displayMessage2();
                return;
            }
        }
    }
}
