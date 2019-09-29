package workshop.orders;

public class SedanOrder extends PaintOrder{
    int price;
    public SedanOrder(int number, String plate, int total, double time, int price){
        super(number, plate, total, time);
        this.price = price;
    }

    public String getType(){
        return "SEDAN";
    }
    public String getPrice(){
        if (this.state == State.DONE){
            return Integer.toString(price*total);
        }
        return "?";
    }
    public void end(){
        super.state = State.DONE;
    }
}