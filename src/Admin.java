import java.util.Scanner;

// class admin extending database
// ->having all that admin controls
// ->applying limits
// ->removing limits
// ->checking transaction history (last 4)

class Admin extends Database{

    private int op;
    private int limit;

    Scanner sc = new Scanner(System.in);

    public void execute(){
        displayMessage();
        takeInput();
    }

    public void displayMessage(){
        System.out.println("--------------------------------------------------");
        System.out.println("          Welcome! Sir");
        System.out.println("          Press the key for required Purpose");
        System.out.println("          1 -  knowing transaction History");
        System.out.println("          2 -  Activate limit");
        System.out.println("          3 -  Remove Limit");
        System.out.println("          4 -  No. of Transaction till date");
        System.out.println("--------------------------------------------------");
    }

    public void takeInput(){
        op = sc.nextByte();
        if(op==1){
            System.out.println("--------------------------------------------------");
            System.out.println("          Here are last 4 Transactions ");
            for(int j=global.Arr.size()-1;j>=0 && j>=global.Arr.size()-4;j--){
                System.out.println();
                System.out.println("--------------------------------------------------");
                System.out.println("          Name     :    " + global.Arr.get(j).getName1() );
                System.out.println("          Amount   :    "+ global.Arr.get(j).getAmount());
                System.out.println("          Method   :    " + global.Arr.get(j).getMethod());
                System.out.println("          To       :    "+global.Arr.get(j).getName2());
                System.out.println("--------------------------------------------------");
                System.out.println();
            }
        }
        else if(op==2){
            takeLimit();
        }
        else if(op==3){
            removelimit();
        }
        else if(op==4){
            System.out.println("--------------------------------------------------");
            System.out.println("          Total transaction till date :");
            System.out.println("--------------------------------------------------");
            System.out.println(global.Arr.size());
        }
        else{
            System.out.println("--------------------------------------------------");
            System.out.println("          Please Enter Valid Choice");
            System.out.println("--------------------------------------------------");
            takeInput();
        }

    }

    public void takeLimit(){
        System.out.println("--------------------------------------------------");
        System.out.println("          Please Enter the Limit");
        limit = sc.nextInt();
        if(limit<1000 || limit>100000){
            System.out.println("Sir please select Limit in range of 1000 && 100000 As per Norms");
            takeLimit();
        }
        else{
           Global.globalLimit =  limit;
            System.out.println("          Transaction Limit Activated");
            System.out.println("--------------------------------------------------");
        }
    }


    public void removelimit(){
        Global.globalLimit = 10000000;
        System.out.println("--------------------------------------------------");
        System.out.println("          Transaction Limit Removed");
        System.out.println("--------------------------------------------------");
    }


}
