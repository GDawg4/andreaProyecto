package workshop.orders;

public class BusOrder extends PaintOrder{
    int price;
    public BusOrder(int number, String plate, int total, double time, int price){
        super(number, plate, total, time);
        this.price = price;
    }

    public String getType(){
        return "BUS";
    }

    public String getPrice(){
        int digit = 0;
        int price = 0;
        char ch;
        if(super.state != State.DONE){
            return "?";
        }else{
            //Revizo el numero de placa
            ch = super.plate.charAt(2);
            digit = Integer.parseInt(Character.toString(ch));
            return Integer.toString((total*number)*(1-digit/100));
        }
    }

    public void end(){
        super.state = State.DONE;
    }
}