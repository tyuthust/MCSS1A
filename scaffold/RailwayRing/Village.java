package RailwayRing;

import TourGroup.Group;

public class Village {
    int villageNum;

    //record the current occupied tour group,
    //if the village is empty now, the value will be null
    private Group group;

    public Village(int i) {
        villageNum = i;
    }

    //tour group occupies the village
    //since a village can only be occupied by a group, use synchronized key words
    public synchronized void occupy(Group group){
        while(null!=this.group){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.group = group;
    }

    //tour group leave the village
    //notify all other waiting tour group
    public synchronized Group leave(){
        Group leavingGroup = this.group;
        this.group = null;
        notifyAll();
        return leavingGroup;
    }

    @Override
    public String toString() {
        return "village " + Integer.toString(villageNum);
    }
}
