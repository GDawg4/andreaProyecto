package policies;

import workshop.orders.PaintOrder;

import java.util.LinkedList;

public class RoundRobin extends Policy {

    public LinkedList<PaintOrder> mainList = new LinkedList<PaintOrder>();

    public void add(PaintOrder order) {
        mainList.addLast(order);

    }

    public void paintPiece(){
        PaintOrder currentOrder = mainList.getLast();
        try {
            currentOrder.paint(1);
            mainList.addLast(currentOrder);
            Thread.sleep(1000);
        }catch (Exception e){
            System.out.println("Algo salió mal, fuck you");
        }
    }

    public PaintOrder remove() {
        return null;
    }

    public PaintOrder next() {
        // TODO Auto-generated method stub
        return null;
    }
}