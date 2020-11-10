import java.util.Scanner;

//class OtpHandler for managing otp related stuff
//having methods for
// creating otp
// sending otp
// checking otp

class Otphandler extends ValidateCustomerInfo{

    Scanner sc = new Scanner(System.in);
    int min = 1000;
    int max = 9999;
    int otp;
    int getotp;

    public void createOtp(){
        otp = (int)Math.floor(Math.random()*(max-min+1)+min);
    }

    public void sendOtp(){
        System.out.println("---------------------------------------------------");
        System.out.println("          Otp is sended to your Phone No please Check");
        System.out.println("          Your Otp : "+ otp);
        System.out.println("          Enter the Otp");
        System.out.println("---------------------------------------------------");
        getotp = sc.nextInt();
    }

    boolean correct = false;
    public boolean checkOtp(){
        if(validateOtp(getotp)==1) {
            if (otp == getotp)correct=true ;
            else return false;
        }
        else{
            System.out.println("---------------------------------------------------");
            System.out.println("          Enter Valid Otp");
            System.out.println("---------------------------------------------------");
            return false;
        }
        return correct;
    }

}