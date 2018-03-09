package RailwayRing;

import CableCar.CableCar;
import TourGroup.Group;

public class Train<S, E> extends Thread {
    private S startPoint;
    private E endPoint;
    private Group group;

    //unified constructor for all possibilities
    public Train(S startPoint, E endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
//        System.out.println("start point: " + this.startPoint.toString());
//        System.out.println("end point: " + this.endPoint.toString());
    }

    @Override
    public void run() {
        while (startPoint.getClass() == CableCar.class) {
            CableCar cableCar = (CableCar) startPoint;
            this.group = cableCar.dropOff();
            if (null != group) {
                System.out.println("Get on firstTrain: " + group.toString());
            }

        }
    }

}
