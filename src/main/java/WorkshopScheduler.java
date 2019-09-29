import policies.FirstComeFirstServed;
import policies.LastComeFirstServed;
import policies.Policy;
import policies.RoundRobin;
import workshop.orders.BusOrder;
import workshop.orders.CoupeOrder;
import workshop.orders.PaintOrder;
import workshop.orders.SedanOrder;

import java.util.Random;

public class WorkshopScheduler{
    public static void main(String[] args) throws Exception{
        Policy currentPolicy = new RoundRobin();
        boolean isOk = false;
        String politica,rangostr = "";
        String[] range;
        double tsedan = 0;
        double tbus = 0;
        double tcoupe = 0;
        int precio = 0;
        int rend = 0;
        int rinit = 0;
        int orderNumber = 0;

        String[] test = new String[6];
        test[0] = "-fcfs";
        test[1] = "1.5-3";
        test[2] = "2";
        test[3] = "1";
        test[4] = "4";
        test[5] = "1500";
        try {
            politica = test[0];
            if(!(politica.equals("-fcfs") || politica.equals("-lcfs") || politica.equals("-rr"))){
                System.out.println("Politica invalida.");
                System.exit(1);
            }else{
                System.out.println("Política válida");
                switch (politica){
                    case "-fcfs":
                        currentPolicy = new FirstComeFirstServed();
                        break;
                    case ("-lcfs"):
                        currentPolicy = new LastComeFirstServed();
                        break;
                    case "-rr":
                        currentPolicy = new RoundRobin();
                        break;
                }
            }

            rangostr = test[1];
            if(rangostr.contains("-")){
                range = rangostr.split("-");
                if(range.length > 2){
                    System.out.println("Rango NO valido.");
                    System.exit(1);
                }

                double rinitTemporal = Double.parseDouble(range[0])*1000;
                rinit = (int)rinitTemporal;
                System.out.println(rinit);
                double rendTemporal = Double.parseDouble(range[1])*1000;
                rend = (int)rendTemporal;
                System.out.println(rend);

            }else{
                System.out.println("Rando NO valido.");
                System.exit(1);
            }

            tsedan = Double.parseDouble(test[2]);
            tbus = Double.parseDouble(test[3]);
            tcoupe = Double.parseDouble(test[4]);
            precio = Integer.parseInt(test[5]);

            if(tsedan <= 0 || tbus <= 0 || tcoupe <= 0){
                System.out.println("Los tiempos deben ser positivos.");
                System.exit(1);
            }else if(precio < 0){
                System.out.println("Los precios deben ser mayores o igual que cero.");
                System.exit(1);
            }

            System.out.println("Todos los datos correctos.");
            isOk = true;
        } catch (Exception e) {
            System.out.println("Existe un error en las banderas.");
            System.out.println(e.getMessage());
            System.exit(1);
        }

        while (true){
            if (isOk){
                Random random = new Random();
                int timeToWait = random.nextInt(rend-rinit)+rinit;
                int newOrderType = random.nextInt(2);
                StringBuilder str = new StringBuilder();
                str.append(random.nextInt(999));
                for (int i = 0; i < 3; i++){
                    char c = (char) (random.nextInt(26) + 'A');
                    str.append(c);
                }
                PaintOrder newOrder;
                switch (newOrderType){
                    case 0:
                        newOrder = new SedanOrder(orderNumber, str.toString(), random.nextInt(3) + 1,tsedan,precio);
                        break;
                    case 1:
                        newOrder = new BusOrder(orderNumber, str.toString(), random.nextInt(3) + 1,tsedan,precio);
                        break;
                    case 2:
                        newOrder = new CoupeOrder(orderNumber, str.toString(), random.nextInt(3) + 1,tsedan,precio);
                        break;
                    default:
                        newOrder = new SedanOrder(orderNumber, "ERROR", random.nextInt(3)+1 ,tsedan, precio);
                }
                System.out.println("Se ha ingresado una nueva orden " + timeToWait);
                System.out.println(newOrder.toString());
                orderNumber ++;
                Thread.sleep(timeToWait);
                currentPolicy.add(newOrder);

                currentPolicy.remove();

            }

        }
    }

}
