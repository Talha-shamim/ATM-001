
// Database basically for handling data
// having an Arraylist for all the user's info
//having methods for :
// -> confirm -> confirming pin
// -> getUserInfo -> for getting complete info of the user
// -> withdrawal -> for handling withdrawal feature
// -> changePassword -> for changing password normally and when user forgets the password
// -> transferAmount -> handle transfer of money
// -> forgetPassword -> handle password change after confirmation redirects to changePassword
// -> methods withdrawal and transfer amount are provided with otp function
// -> both these methods are provided with receipt options
// -> banking system is also added which charges some amount while its different atm card except SBI

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

class Database extends Customer {

    ArrayList<Customer> Arrli = new ArrayList<>();

    int pin_no ;
    int valid=0;
    int t_acc;
    int s_mo;
    int acc;
    int charge=0;
    int index ;
    int indexx;
    String nameofReceiver;
    Otphandler otphandler = new Otphandler();
    Global global = new Global();

    public void setacc(int a){
        acc=a;
    }

    public boolean confirm(int pin){
        for(int i=0;i<Arrli.size();i++){
            if(Arrli.get(i).getPin()==pin){
                pin_no = pin;
                return true;
            }
        }
        return false;
    }

    public void getUserInfo(int acc_no){
        otphandler.createOtp();
        otphandler.sendOtp();
        if(otphandler.checkOtp()) {
            int acc = acc_no;
            for (int i = 0; i < Arrli.size(); i++) {
                if (Arrli.get(i).getPin() == pin_no && Arrli.get(i).getAccountNo() == acc_no) {
                    System.out.println();
                    System.out.println("*********   Here's your account details   ********");
                    System.out.println("--------------------------------------------------");
                    System.out.println("     Your name           : " + Arrli.get(i).getName());
                    System.out.println("     Your Bank           : " + Arrli.get(i).getBank());
                    System.out.println("     Your AccountNo      : " + Arrli.get(i).getAccountNo());
                    System.out.println("     Your Balance        : " + Arrli.get(i).getBalance());
                    System.out.println("     Your Pin No         : " + pin_no);
                    System.out.println("     Your Phone no       : " + Arrli.get(i).getPhoneNo());
                    System.out.println("     Your Favorite word  : " + Arrli.get(i).getFavWord());
                    System.out.println("---------------------------------------------------");
                }
            }
        }
        else{
            System.out.println("---------------------------------------------------");
            System.out.println("          Sorry we could not confirm your Otp");
            System.out.println("---------------------------------------------------");
        }
    }

    public void withdrawal(){
        otphandler.createOtp();
        otphandler.sendOtp();
        if(otphandler.checkOtp()) {
            System.out.println("---------------------------------------------------");
            System.out.println("          Enter the amount to be Withdrawn");
            System.out.println("---------------------------------------------------");
            int amount = sc.nextInt();
            if (amount % 500 != 0) {
                System.out.println("---------------------------------------------------");
                System.out.println("          Please enter valid Amount");
                System.out.println("---------------------------------------------------");
                withdrawal();
            } else {
                for (int i = 0; i < Arrli.size(); i++) {
                    if (Arrli.get(i).getPin() == pin_no && Arrli.get(i).getAccountNo() == acc) {
                        index=i;
                        break;
                    }
                }
                for (int i = 0; i < Arrli.size(); i++) {
                    if (amount > Global.globalLimit) {
                        System.out.println("---------------------------------------------------");
                        System.out.println("          Transaction Limit : " + Global.globalLimit);
                        System.out.println("          Please try Again");
                        System.out.println("---------------------------------------------------");
                        return;
                    }
                    if (Arrli.get(index).getPin() == pin_no && Arrli.get(index).getBalance() >= amount) {
                        if(!Arrli.get(index).getBank().equals("SBI")){
                            System.out.println("---------------------------------------------------");
                            System.out.println("          Rs 5 will be deducted every 500 withdrawn");
                            System.out.println("          To Continue press 1 else 0");
                            System.out.println("---------------------------------------------------");
                            int op = sc.nextByte();
                            if(op==1){
                                charge = amount/100;
                                if(amount+charge>Arrli.get(index).getBalance()){
                                    System.out.println("---------------------------------------------------");
                                    System.out.println("    Sir, you dont have balance when tax included");
                                    System.out.println("---------------------------------------------------");
                                    return;
                                }
                            }
                            else{
                               return;
                            }
                        }
                        else{
                            System.out.println("---------------------------------------------------");
                            System.out.println("          No charge for SBI Atm :) ");
                            System.out.println("---------------------------------------------------");
                        }
                        amount += charge;
                        charge=0;
                        Arrli.get(index).setNewBalance(amount);
                        TransactionDetails td = new TransactionDetails();
                        td.getInfo(Arrli.get(i).getName(),"Self",amount,"Withdrawal");
                        global.Arr.add(td);

                        break;
                    } else {
                        System.out.println("---------------------------------------------------");
                        System.out.println("          You don't have required balance");
                        System.out.println("---------------------------------------------------");
                        return;
                    }
                }
                System.out.println("---------------------------------------------------");
                System.out.println("          Wait...");
                System.out.println("          Collect your Amount");
                System.out.println("          Want to know Balance press 1");
                System.out.println("---------------------------------------------------");
                int op = sc.nextByte();
                if (op == 1) {
                    for (int i = 0; i < Arrli.size(); i++) {
                        if (Arrli.get(i).getPin() == pin_no && Arrli.get(i).getAccountNo() == acc) {
                            System.out.println("Your Current Balance :" + Arrli.get(i).getBalance());
                            break;
                        }
                    }
                }
                System.out.println("---------------------------------------------------");
                System.out.println("          Press 1 to print receipt else press 0");
                System.out.println("---------------------------------------------------");
                int rec = sc.nextByte();
                if(rec==1){
                    DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                    Date dateobj = new Date();
                    System.out.println();
                    System.out.println("--------------------------------------------------");
                    System.out.println("       RECEIPT :");
                    System.out.println("       "+df.format(dateobj));
                    System.out.println("       Card Holder  : " + Arrli.get(index).getName() );
                    System.out.println("       Balance      : " + Arrli.get(index).getBalance());
                    System.out.println("       Withdrawn(Inclusive of Charge) :" + amount );
                    System.out.println("---------------------------------------------------");
                    System.out.println();
                }
                else{
                    return;
                }
            }
        }
        else{
            System.out.println("---------------------------------------------------");
            System.out.println("          We could not confirm your Otp");
            System.out.println("---------------------------------------------------");
        }
    }

    public void changePassword(){
        otphandler.createOtp();
        otphandler.sendOtp();
        if(otphandler.checkOtp()) {
            System.out.println("---------------------------------------------------");
            System.out.println("          Enter new Pin");
            System.out.println("---------------------------------------------------");
            int new_pin = sc.nextInt();
            int op = validatePin(new_pin);
            if (op == 1) {
                for (int i = 0; i < Arrli.size(); i++) {
                    if (Arrli.get(i).getPin() == pin_no && Arrli.get(i).getAccountNo()==acc) {
                        Arrli.get(i).setNewPin(new_pin);
                        System.out.println("   ***   Password changed Successfully   ***");
                        break;
                    }
                }
            } else if (op == 0) {
                changePassword();
            }
        }
        else{
            System.out.println("---------------------------------------------------");
            System.out.println("          Sorry we could'nt confirm your Otp");
            System.out.println("---------------------------------------------------");
        }
    }

    public void transferAmount(){
        otphandler.createOtp();
        otphandler.sendOtp();
        if(otphandler.checkOtp()) {
            System.out.println("---------------------------------------------------");
            System.out.println("Enter the Account No to whom amount is to transfer");
            System.out.println("---------------------------------------------------");
            t_acc = sc.nextInt();
            for (int i = 0; i < Arrli.size(); i++) {
                if (Arrli.get(i).getAccountNo() == t_acc) {
                    valid = 1;
                    break;
                }
            }

            for (int i = 0; i < Arrli.size(); i++) {
                if (Arrli.get(i).getAccountNo() == acc) {
                   indexx = i;
                   break;
                }
            }

            if (valid == 0) {
                System.out.println("---------------------------------------------------");
                System.out.println("          Enter valid Account No");
                System.out.println("---------------------------------------------------");
                transferAmount();
            } else {
                System.out.println("---------------------------------------------------");
                System.out.println("          Amount to be transferred");
                System.out.println("---------------------------------------------------");
                charge=0;
                s_mo = sc.nextInt();
                if(!Arrli.get(indexx).getBank().equals("SBI")){
                    System.out.println("---------------------------------------------------");
                    System.out.println("          Rs 5 will be deducted every 500 withdrawn");
                    System.out.println("          To Continue press 1 else 0");
                    System.out.println("---------------------------------------------------");
                    int op = sc.nextByte();

                    if(op==1){
                        charge = s_mo/100;
                        if(s_mo+charge>Arrli.get(indexx).getBalance()){
                            System.out.println("---------------------------------------------------");
                            System.out.println("   Sir, you don't have balance when tax included");
                            System.out.println("---------------------------------------------------");
                            return;
                        }
                    }
                    else{
                        return;
                    }
                }
                for (int i = 0; i < Arrli.size(); i++) {
                    if (Arrli.get(i).getAccountNo() == acc && Arrli.get(i).getBalance() < s_mo) {
                        System.out.println("---------------------------------------------------");
                        System.out.println("          Don't have required balance");
                        System.out.println("---------------------------------------------------");
                        return;
                    }
                }

                int check = validateSendAmount(s_mo);
                if (check == 1) {
                    transferAmount();
                } else {
                    for (int i = 0; i < Arrli.size(); i++) {
                        if (Arrli.get(i).getAccountNo() == t_acc) {
                            nameofReceiver = Arrli.get(i).getName();
                            Arrli.get(i).transferBalance(s_mo);
                            break;
                        }
                    }
                    System.out.println("---------------------------------------------------");
                    System.out.println("          Transition successful");
                    System.out.println("---------------------------------------------------");
                    for (int i = 0; i < Arrli.size(); i++) {
                        if (Arrli.get(i).getAccountNo() == acc) {
                            Arrli.get(i).setNewBalance(s_mo+charge);
                            TransactionDetails td = new TransactionDetails();
                            td.getInfo(Arrli.get(i).getName(),nameofReceiver,s_mo,"Transition");
                            global.Arr.add(td);
                            break;
                        }
                    }

                    charge=0;
                    System.out.println("---------------------------------------------------");
                    System.out.println("          Press 1 to print receipt else press 0");
                    System.out.println("---------------------------------------------------");
                    int rec = sc.nextByte();
                    if(rec==1){
                        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                        Date dateobj = new Date();
                        System.out.println();
                        System.out.println("--------------------------------------------------");
                        System.out.println("       RECEIPT :");
                        System.out.println("       "+df.format(dateobj));
                        System.out.println("       Card Holder :" + Arrli.get(indexx).getName() );
                        System.out.println("       Receiver    :" + nameofReceiver);
                        System.out.println("       Balance     :" + Arrli.get(indexx).getBalance() );
                        System.out.println("       Transfer    :" + s_mo );
                        System.out.println("---------------------------------------------------");
                        System.out.println();
                    }
                    else{
                        return;
                    }
                }
            }
        }
        else{
            System.out.println("---------------------------------------------------");
            System.out.println("          Sorry we could not confirm the Otp");
            System.out.println("---------------------------------------------------");
        }
    }

    public void forgotPassword() {
        otphandler.createOtp();
        otphandler.sendOtp();
        if (otphandler.checkOtp()) {
            System.out.println("---------------------------------------------------");
            System.out.println("          Forgot PIN press YES else No");
            System.out.println("---------------------------------------------------");
            String forget = sc.next();
            if (forget.equals("YES")) {
                System.out.println("---------------------------------------------------");
                System.out.println("          Enter your favorite Word");
                String fav = sc.next();
                System.out.println("          Enter your Account No");
                int ac = sc.nextInt();
                System.out.println("          Your account is being accessed Please wait...");
                System.out.println("---------------------------------------------------");
                for (int i = 0; i < Arrli.size(); i++) {
                    if (Arrli.get(i).getAccountNo() == ac && Arrli.get(i).getFavWord().equals(fav)) {
                        System.out.println("          Change your pin and try again!");
                        Arrli.get(i).setPin();
                        break;
                    } else {
                        System.out.println("          Sorry we could not find any Account");
                        return;
                    }
                }

            } else {
                return;
            }
        }
        else{
            System.out.println("---------------------------------------------------");
            System.out.println("          Sorry we could not confirm your Otp");
            System.out.println("---------------------------------------------------");
        }
    }

}