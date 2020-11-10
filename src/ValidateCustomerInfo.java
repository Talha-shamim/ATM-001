
// class validateCustomerInfo
// for validating all kind of Pin, validate phone, validate send amount
// and validate otp

class ValidateCustomerInfo {

    int invalid=0;

    public int validatePin(int pin){

        if(pin<10000 && pin>999){
            return 1;
        }
        else{
            System.out.println("--------------------------------------------------");
            System.out.println("          Please choose 4 digit no");
            System.out.println("--------------------------------------------------");
            return 0;
        }
    }

    public int validatePhoneNo(String pno){
        if(pno.length()==12){
            for(int i=0;i<pno.length();i++){
                if(pno.charAt(i)<'0' || pno.charAt(i)>'9'){
                    System.out.println("--------------------------------------------------");
                    System.out.println("  Enter valid Phone no your Region code is '+91' ");
                    System.out.println("--------------------------------------------------");
                    return 0;
                }
            }
        }
        if(pno.length()==12 && pno.substring(0,2).equals("91")) {
            System.out.println("--------------------------------------------------");
            System.out.println("       Check your message for regular Updates");
            System.out.println("--------------------------------------------------");
            return 1;
        }
        else{
            System.out.println("--------------------------------------------------");
            System.out.println("  Enter valid Phone no your Region code is '+91' ");
            System.out.println("--------------------------------------------------");
            return 0;
        }
    }

    public int validateSendAmount(int m){
        if(m%500!=0){
            System.out.println("--------------------------------------------------");
            System.out.println("          Enter valid amount");
            System.out.println("--------------------------------------------------");
            invalid=1;
        }
        if(m>100000){
            System.out.println("--------------------------------------------------");
            System.out.println("          Amount should be less than 1 lakh");
            System.out.println("--------------------------------------------------");
            invalid=1;
        }
        if(m<0){
            System.out.println("--------------------------------------------------");
            System.out.println("          Enter Valid amount");
            System.out.println("--------------------------------------------------");
            invalid=1;
        }

        return invalid;
    }

    public int validateOtp(int o){
        if(o<1000 || o>9999){
            return 0;
        }
        else {
            return 1;
        }
    }

}