
// Class Customer extending ValidateCustomerInfo
//having  getters and setters for -> AccountNo
// -> name
// -> pin
// -> balance
// -> phoneno
// -> favword
// -> setNewBalance -> after withdrawal
// -> setNewPin -> for changing new pin
// -> transferAmount -> after transfer
// -> added banking options
// -> added Atm card options - >for checking before transaction for cutting tax
// -> also added all the methods for changing users data

import java.util.ArrayList;
import java.util.Scanner;

class Customer extends ValidateCustomerInfo{

    static int x = 0;
    private String name;
    private String favWord;
    private int pin;
    private int balance = 0;
    private String phoneNo;
    private int AccountNo;
    private Boolean SBI = true;
    private String bank = "SBI";

    public String getBank() {
        return bank;
    }

    public void setBank() {
        System.out.println("--------------------------------------------------");
        System.out.println("          Enter your bank name");
        System.out.println("--------------------------------------------------");
        this.bank = sc.next();
    }

    public String getSBI() {
        if(SBI)return "SBI";
        else return "Other";
    }

    public void setSBI() {
        System.out.println("--------------------------------------------------");
        System.out.println("          If Atm is SBI press 1 else press 0");
        System.out.println("--------------------------------------------------");
        int op = sc.nextByte();
        if(op==1)SBI = true;
        else if(op==0)SBI = false;
        else {
            System.out.println("--------------------------------------------------");
            System.out.println("          Invalid option");
            System.out.println("          Select again");
            System.out.println("--------------------------------------------------");
            setSBI();
        }
    }

    public void getInfo(){
        setName();
        setFavWord();
        setPhoneNo();
        setPin();
        setAccountNo();
        setSBI();
        if(!getSBI().equals("SBI")){
            setBank();
        }
    }

    public int getAccountNo() {
        return AccountNo;
    }

    public void setAccountNo() {
        AccountNo = ++x;
        System.out.println("--------------------------------------------------");
        System.out.println("          Your account No is ");
        System.out.println("          *** "+AccountNo+" ***");
        System.out.println("--------------------------------------------------");
    }

    Scanner sc = new Scanner(System.in);

    public String getName() {
        return name;
    }

    public void setName() {
        System.out.println("--------------------------------------------------");
        System.out.println("          Enter your name");
        System.out.println("--------------------------------------------------");
        this.name = sc.next();
    }

    public String getFavWord() {
        return favWord;
    }

    public void setFavWord() {
        System.out.println("--------------------------------------------------");
        System.out.println("          Set your fav word");
        System.out.println("--------------------------------------------------");
        this.favWord = sc.next();
    }

    public int getPin() {
        return pin;
    }

    public void setPin() {
        System.out.println("--------------------------------------------------");
        System.out.println("          Set your 4 digits pin");
        System.out.println("    ***   Don't share with anyone  :)  ***");
        System.out.println("--------------------------------------------------");
        this.pin = sc.nextInt();
        if(validatePin(pin)==0){
            setPin();
        }
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance() {
        System.out.println("--------------------------------------------------");
        System.out.println("          Enter the amount to deposit");
        System.out.println("--------------------------------------------------");
        int money = sc.nextInt();
        if(money%500!=0){
            System.out.println("--------------------------------------------------");
            System.out.println("          Enter valid Amount");
            System.out.println("--------------------------------------------------");
            setBalance();
        }
        else {
            this.balance += money;
            System.out.println("         Amount Successfully deposited");
        }
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo() {
        System.out.println("--------------------------------------------------");
        System.out.println("          Enter your phone no.");
        System.out.println("          Please enter with Region code");
        System.out.println("--------------------------------------------------");
        this.phoneNo = sc.next();
        if(validatePhoneNo(phoneNo)==0){
            setPhoneNo();
        }
    }

    public void setNewBalance(int b){
        balance -= b;
    }

    public void setNewPin(int p){
        pin = p;
    }

    public void transferBalance(int t){
        balance += t;
    }

}