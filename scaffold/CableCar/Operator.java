package CableCar;

import CableCar.CableCar;
import Utils.Params;

public class Operator extends Thread {
    private CableCar cableCar;

    public Operator(CableCar cableCar) {
        this.cableCar = cableCar;
    }

//    public void interrupt() {
//        try {
//            Thread.sleep(Params.MAX_OPERATE_INTERVAL);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}
