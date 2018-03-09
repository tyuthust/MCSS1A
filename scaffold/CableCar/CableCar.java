package CableCar;

import TourGroup.Group;
import Utils.Params;

import static java.lang.Thread.sleep;

public class CableCar {
    //The enum lists all the potential location of cable car
    enum location {
        terminus, valley
    }

    enum direction {
        top, down
    }

    //The cable car current location,
    //The default location is valley
    private static location locatedFlag = location.valley;
    //The cable car current direction,
    //The default value is top
    private static direction currentDirection = direction.top;
    //The cable car current passengers,
    //The default value is null
    private Group group = null;

    /**
     * the current group arrived the end point of the travel
     * and get off the cable car
     *
     * @Param group the new arrived group who takes the cable car
     * @Author rishengf@student.unimelb.edu.au
     */
    public synchronized void depart() {
        //if there is any group finishes their travel, get of the cable car
        if (locatedFlag == location.valley && null != group && currentDirection == direction.down) {
            System.out.println("get off: " + group.toString());
            getOff();
            notifyAll();
        }
    }

    /**
     * a given group get on the cable car
     *
     * @Param Group the new arrived group who takes the cable car
     * @Author rishengf@student.unimelb.edu.au
     */
    public synchronized void arrive(Group group) {
        //check if there is any other group in the cable car
        while (null != this.group || locatedFlag == location.terminus) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        getOn(group);
        System.out.println("get on:" + this.group.toString());
        travelTerminus();
    }

    //Tour group gets on the cable car
    private void getOn(Group group) {
        this.group = group;
    }

    //Tour group gets off the cable car
    private void getOff() {
        this.group = null;
    }

    public void travelTerminus() {
        try {
            Thread.sleep(Params.OPERATE_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //the cable car takes OPERATE_TIME to drive from valley to terminus
        locatedFlag = location.terminus;
    }

    public synchronized Group dropOff(){
        Group leavedGroup = this.group;
        this.group = null;
        notifyAll();
        return leavedGroup;
    }

    public void travelValley() {
        try {
            sleep(Params.OPERATE_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //the cable car takes OPERATE_TIME to drive from valley to terminus
        locatedFlag = location.valley;
    }


}
