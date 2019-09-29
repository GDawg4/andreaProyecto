package policies;

import workshop.orders.PaintOrder;

import java.util.concurrent.ConcurrentLinkedDeque;

public class FirstComeFirstServed extends Policy {
    public ConcurrentLinkedDeque<PaintOrder> mainQueue = new ConcurrentLinkedDeque<PaintOrder>();
    public void add(PaintOrder order) {
        mainQueue.add(order);

    }

    public PaintOrder remove() {
        PaintOrder currentOrder = mainQueue.peekFirst();
        try {
            Thread.sleep(currentOrder.getTotal()*(int)currentOrder.getTime());
        }catch (Exception e){

        }
        mainQueue.remove(currentOrder);
        return null;
    }

    public PaintOrder next() {
        // TODO Auto-generated method stub
        return null;
    }
}
