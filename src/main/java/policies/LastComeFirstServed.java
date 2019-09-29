package policies;


import workshop.orders.PaintOrder;

import java.util.Stack;

public class LastComeFirstServed extends Policy {
    public Stack<PaintOrder> mainStack = new Stack<PaintOrder>();

    public void add(PaintOrder order) {
        mainStack.push(order);

    }

    public PaintOrder remove() {
        PaintOrder currentOrder = mainStack.pop();
        try{
            Thread.sleep(currentOrder.getTotal()*(int)currentOrder.getTime());
        }catch (Exception e){

        }
        return null;
    }

    public PaintOrder next() {
        // TODO Auto-generated method stub
        return null;
    }
}
