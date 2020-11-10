
// class implementer extending Features and
// Implementing the interface components
// having methods of ministatement

import java.util.Scanner;

class Implementer extends Features implements Components{

    Database dbms = new Database();
    Admin admin = new Admin();
    Otphandler otphandler = new Otphandler();
    Scanner sc = new Scanner(System.in);
    String pin;
    int acc;
    String nameofcardholder;
    String nameofbank;
    int valid=0;

    @Override
    public void displayMessage1() {
        System.out.println("---------------------------------------------------");
        System.out.println("          ***   Welcome to SBI Atm   *** ");
        System.out.println("      Please Enter your card for various Options");
    }

    @Override
    public void validateCustomer() {
        System.out.println("       ***      If New press 1 else press 0");
        System.out.println("       ***      If Admin press 2");
        System.out.println("---------------------------------------------------");
        int e = sc.nextByte();
        if(e==1){
            Customer customer = new Customer();
            customer.getInfo();
            dbms.Arrli.add(customer);
        }
        else if(e==0){
            System.out.println("---------------------------------------------------");
            System.out.println("          Enter your 4 digit PIN no.");
            pin = sc.next();
            System.out.println("---------------------------------------------------");
            System.out.println("          Enter Your Account no");
            System.out.println("---------------------------------------------------");
            acc = sc.nextInt();
            for(int i=0;i<dbms.Arrli.size();i++){
                if(dbms.Arrli.get(i).getAccountNo()==acc && dbms.Arrli.get(i).getPin()==Integer.parseInt(pin)){
                    valid = 1;
                }
            }
            if(valid==1) {
                if (pin.length() > 4) {
                    System.out.println("---------------------------------------------------");
                    System.out.println("          Invalid PIN");
                    System.out.println("          Press 1 to try again else press 0");
                    System.out.println("---------------------------------------------------");
                    int op = sc.nextByte();
                    if (op == 1) {
                        validateCustomer();
                    } else if (op != 0) {
                        System.out.println("---------------------------------------------------");
                        System.out.println("          Enter valid options");
                        System.out.println("---------------------------------------------------");
                    }
                } else {
                    int pin_no = Integer.parseInt(pin);
                    boolean status = dbms.confirm(pin_no);
                    if (status) {
                        int sel_no = options();
                        for (int i = 0; i < dbms.Arrli.size(); i++) {
                            if (dbms.Arrli.get(i).getPin() == pin_no && dbms.Arrli.get(i).getAccountNo()==acc){
                                dbms.setacc(acc);
                                nameofcardholder = dbms.Arrli.get(i).getName();
                                nameofbank = dbms.Arrli.get(i).getBank();
                                if (sel_no == 5) dbms.getUserInfo(acc);
                                else if (sel_no == 1) dbms.withdrawal();
                                else if (sel_no == 2) checkBalance();
                                else if (sel_no == 3) dbms.changePassword();
                                else if (sel_no == 4) dbms.transferAmount();
                                else if (sel_no == 6) dbms.Arrli.get(i).setBalance();
                                else if (sel_no == 7) {
                                    dbms.Arrli.get(i).setPhoneNo();
                                    System.out.println("   ***   Phone no changes Successfully   ***");
                                }
                                else if (sel_no == 8) {
                                    dbms.Arrli.get(i).setFavWord();
                                    System.out.println("   ***   Favorite Word changed Successfully   ***");
                                }
                                else if (sel_no == 9) ministatement(nameofcardholder);
                                break;
                            }
                        }
                    } else {
                        System.out.println("---------------------------------------------------");
                        System.out.println("          Invalid PIN");
                        System.out.println("---------------------------------------------------");
                        dbms.forgotPassword();
                    }
                }
            }

            else if(valid==0){
                System.out.println("---------------------------------------------------");
                System.out.println("          Check your Account No. and Pin");
                System.out.println("---------------------------------------------------");
                dbms.forgotPassword();
            }
        }
        else if(e==2){
            admin.execute();
        }
        else{
            System.out.println("---------------------------------------------------");
            System.out.println("          Enter valid Options");
            System.out.println("---------------------------------------------------");
            validateCustomer();
        }
    }

    public void checkBalance(){
        for(int i=0;i<dbms.Arrli.size();i++){
            if(dbms.Arrli.get(i).getPin()==Integer.parseInt(pin) && dbms.Arrli.get(i).getAccountNo()==acc){
                System.out.println("Your balance : " + dbms.Arrli.get(i).getBalance());
                break;
            }
        }
    }

    Global global = new Global();
    int k=0;
    public void ministatement(String name){
        System.out.println();
        System.out.println("Name of card holder : " + nameofcardholder);
        System.out.println("Name of Bank        : " + nameofbank);
        System.out.println("Account No          : " + acc);
        for(int i=global.Arr.size()-1;i>=0;i--){
            if(k==3)break;
            if(global.Arr.get(i).getName1().equals(name)){
                System.out.println();
                System.out.println("---------------------------------------------");
                System.out.println("          Method : "+global.Arr.get(i).getMethod());
                System.out.println("          Amount : "+global.Arr.get(i).getAmount());
                System.out.println("          To     : "+global.Arr.get(i).getName2());
                System.out.println("---------------------------------------------");
                System.out.println();
            }
        }
    }


    @Override
    public void displayMessage2() {
        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println("   ***   Thank you! for using ABC Atm   ***   ");
        System.out.println("-----------------------------------------------");
        System.out.println();
    }
}