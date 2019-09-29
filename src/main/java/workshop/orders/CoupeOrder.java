package workshop.orders;

import java.util.Random;

public class CoupeOrder extends PaintOrder{
    int precio;
    public CoupeOrder(int number, String plate, int total, double time, int precio){
        super(number, plate, total, time);
        this.precio = precio;
    }

    public String getType(){
        return "COUPE";
    }

    public String getPrice(){
        Random random = new Random();
        int randomDiscount = random.nextInt(5)+4;
        if(super.state != State.DONE){
            return "?";
        }else{
            return Integer.toString((total*number)*(1-randomDiscount/100));
        }
    }

    public void end(){
        super.state = State.DONE;
    }
}