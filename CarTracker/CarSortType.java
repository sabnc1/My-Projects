//easiest way to work around comparable interface
//separate each car into two instances of this object to make 2 priority queues
public class CarSortType implements Comparable<CarSortType>{
    double value;
    int index;
    String VIN;
    
    public CarSortType(int i, String vin, double v){
        index = i;
        value = v;
        VIN = vin;
    }
    public int compareTo(CarSortType c){
        if(c.value>value)
        return -1;
        else if (c.value==value)
        return 0;
        else
        return 1;
    }
}