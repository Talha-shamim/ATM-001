
// class Feature having all the options for customer
// with int type return type for options

import java.util.Scanner;

abstract class Features{

    Scanner sc = new Scanner(System.in);

    public int options(){
        System.out.println();
        System.out.println("********   Press the No. for various options   *********");
        System.out.println("--------------------------------------------------------");
        System.out.println("                  1 - Withdrawal");
        System.out.println("                  2 - Checking Balance");
        System.out.println("                  3 - Changing Password");
        System.out.println("                  4 - Tranferring Amount");
        System.out.println("                  5 - Account Info");
        System.out.println("                  6 - Add Amount");
        System.out.println("                  7 - Changing Phone No");
        System.out.println("                  8-  Change Favorite Word");
        System.out.println("                  9 - Mini Statement");
        System.out.println("-----------------------------------------------------------");
        System.out.println();
        int op = sc.nextInt();
        return op;
    }

}