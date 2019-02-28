import java.util.Comparator;
import java.util.Random;

import static java.lang.Math.sqrt;

public class Address implements Comparable<Address> {

    boolean direction; //TRUE, 1 = east, FALSE, 0 = south
    int houseNum; //house numbers are multiples of 10, starting at 100.  However, there are no houses at the multiples of 100s, as the first house on each street would be, for example, 110.
    int streetNum; //street numbers start at 1, and go
    private static final int DISTRIBUTION_HOUSENUM = 910;
    private static final int DISTRIBUTION_STREETNUM = 9;

    Random rand = new Random();

    protected Address(){
        direction = rand.nextBoolean();
        houseNum = getRandomHouseNum();
        streetNum = getRandomStreetNum();
    }

    protected Address(int houseNum, boolean direction, int streetNum){
     if(houseNum >= 0 && houseNum < 2000)
        this.houseNum = houseNum;

     this.direction = direction;

     if(streetNum >= 0 && streetNum < 20)
         this.streetNum = streetNum;
    }

    public int getRandomHouseNum(){
        int n = rand.nextInt(20);

        if(n == 0)
            n = 1;

        n = n * 100;

        n = n + (rand.nextInt(10) * 10);

        if (n % 100 == 0)
            n = n + 10;

        return n;
    }

    public int getRandomStreetNum(){
        int n = rand.nextInt(10);

        if (n == 0)
            n = 1;

        return n;

    }


    public boolean isDirection() {
        return direction;
    }

    public String directionToString(){
        if (direction == false)
            return "South";
        else
            return "East";
    }

    public int getHouseNum() {
        return houseNum;
    }

    public int getStreetNum() {
        return streetNum;
    }

    public double distance(){
        if(!direction)
            return Math.sqrt(Math.pow(DISTRIBUTION_HOUSENUM - (streetNum * 100), 2) + Math.pow((DISTRIBUTION_STREETNUM * 100) - houseNum, 2));

        return Math.sqrt(Math.pow(DISTRIBUTION_HOUSENUM - houseNum,2) + Math.pow((DISTRIBUTION_STREETNUM * 100) - (streetNum * 100),2));
    }

    @Override
    public String toString() {
        return Integer.toString(getHouseNum()) + " " + directionToString() + " " + Integer.toString(getStreetNum());
    }

  /*  @Override
    public int compare(Address o1, Address o2) {
        if (o1.distance() < o2.distance())
            return 1;

        if (o1.distance() > o2.distance())
            return -1;

        return 0;
    } */

    @Override
    public int compareTo(Address o) {
        if (distance() < o.distance())
            return -1;

        if (distance() > o.distance())
            return 1;

        return 0;
    }
}
