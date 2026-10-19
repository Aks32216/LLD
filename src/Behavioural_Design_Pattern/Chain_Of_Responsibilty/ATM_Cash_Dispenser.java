package Behavioural_Design_Pattern.Chain_Of_Responsibilty;

interface ATMDispenser{
    void setNext(ATMDispenser dispenser);
    void handle(int money);
}

abstract class BaseDispenser implements ATMDispenser{
    protected ATMDispenser atmDispenser;
    private int denomination;

    BaseDispenser(int denomination){
        this.denomination=denomination;
    }

    @Override
    public void setNext(ATMDispenser dispenser) {
        this.atmDispenser=dispenser;
    }

    public void forward(int money){
        if(atmDispenser!=null){
            atmDispenser.handle(money);
        }
    }

    @Override
    public void handle(int money) {
        if(money>=denomination){
            int curNotes=money/denomination;
            money%=denomination;
            System.out.println("Dispensed "+denomination+" * "+curNotes);
        }
        forward(money);
    }
}

class ThousandRupeeHandler extends BaseDispenser{
    public ThousandRupeeHandler(){
        super(1000);
    }
}

class FiveHundredRupeeHandler extends BaseDispenser{
    public FiveHundredRupeeHandler(){
        super(500);
    }
}

class TwoHundredRupeeHandler extends BaseDispenser{
    public TwoHundredRupeeHandler(){
        super(200);
    }
}

class OneHundredRupeeHandler extends BaseDispenser{
    public OneHundredRupeeHandler(){
        super(100);
    }
}

class FiftyRupeeHandler extends BaseDispenser{
    public FiftyRupeeHandler(){
        super(50);
    }
}

class TwentyRupeeHandler extends BaseDispenser{
    public TwentyRupeeHandler(){
        super(20);
    }
}

class TenRupeeHandler extends BaseDispenser{
    public TenRupeeHandler(){
        super(10);
    }
}

class FiveRupeeHandler extends BaseDispenser{
    public FiveRupeeHandler(){
        super(5);
    }
}

class TwoRupeeHandler extends BaseDispenser{
    public TwoRupeeHandler(){
        super(2);
    }
}

class OneRupeeHandler extends BaseDispenser{
    public OneRupeeHandler(){
        super(1);
    }
}



public class ATM_Cash_Dispenser {
    public static void main(String[] args) {
        BaseDispenser Thousand=new ThousandRupeeHandler();
        BaseDispenser FiveHundred=new FiveHundredRupeeHandler();
        BaseDispenser TwoHundred=new TwoHundredRupeeHandler();
        BaseDispenser OneHundred=new OneHundredRupeeHandler();
        BaseDispenser Fifty=new FiftyRupeeHandler();
        BaseDispenser Twenty=new TwentyRupeeHandler();
        BaseDispenser Ten=new TenRupeeHandler();
        BaseDispenser Five=new FiveRupeeHandler();
        BaseDispenser Two=new TwoRupeeHandler();
        BaseDispenser One=new OneRupeeHandler();

        Thousand.setNext(FiveHundred);
        FiveHundred.setNext(TwoHundred);
        TwoHundred.setNext(OneHundred);
        OneHundred.setNext(Fifty);
        Fifty.setNext(Twenty);
        Twenty.setNext(Ten);
        Ten.setNext(Five);
        Five.setNext(Two);
        Two.setNext(One);

        Thousand.handle(2056783);
    }
}
