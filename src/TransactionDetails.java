
// class TranscationDetails having all methods and
// variables related to handling transaction for
// saving record in history
// having following methods:
// ->getName1 and getName2 for getting names
// ->getMethods for getting transaction methods
// ->getAmount for getting amount transfer
// ->getters and setters for all the variables


public class TransactionDetails {

    private String  name1;
    private int amount;
    private String method;
    private String name2;

    public void getInfo(String name1, String name2 ,int money, String method){
        setName1(name1);
        setName2(name2);
        setAmount(money);
        setMethod(method);
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

}
