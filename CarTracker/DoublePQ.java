import java.lang.System;
import java.util.*;
import java.util.Stack;


public class DoublePQ{
    public enum Field {VIN,Mileage,Color,Make,Model,Price}
        
    public static ArrayList<Car> list = new ArrayList<>();
    public static MinPQ pricePQ = new MinPQ();         //change this as needed for the program
    public static MinPQ mileagePQ = new MinPQ();       //change this as needed for the program
    public static int size;
    
    public DoublePQ(){
        int size = 0;
    }
    public void add(Car c){
        String VIN = c.getVIN();
        for(Car x:list){
            if (VIN.equals(x.getVIN())){
                System.out.println("This VIN is already added");
                return;
            }
        }
        list.add(c);
        
        CarSortType mileage = new CarSortType(size,VIN,c.getMileage());
        mileagePQ.insert(mileage);
        
        CarSortType price = new CarSortType(size,VIN,c.getPrice());
        pricePQ.insert(price);
        
        size++;
    }
    public Car bestMileage(){
        if(size<=0)
        {
            System.out.println("No Cars found");
            return null;
        }
        int index =((CarSortType) mileagePQ.min()).index;
        return list.get(index);
    }
    public Car bestPrice(){
        if(size<=0)
        {
            System.out.println("No Cars found");
            return null;
        }
        int index =((CarSortType) pricePQ.min()).index;
        return list.get(index);
    }
    public Car bestMileageSpecific(String make, String model){
        int count = 0;
        boolean matchFound = false;
        Stack<CarSortType> carStack= new Stack<>();
        Car match = null;
        while(count<size){
            CarSortType tmpValue= (CarSortType)mileagePQ.delMin();
            Car tmp = list.get(tmpValue.index);
            if(tmp.getMake().equals(make)&&tmp.getModel().equals(model)){
                match = tmp;
                matchFound=true;
                carStack.push(tmpValue);
                break;
            }
            carStack.push(tmpValue);
            count++;
        }
        if(!matchFound){
            System.out.println("Car not found");
            return null;
        }
        while(!carStack.isEmpty()){
            mileagePQ.insert(carStack.pop());
            
        }
        return match;
    }
    public Car bestPriceSpecific(String make, String model){
        int count = 0;
        boolean matchFound = false;
        Stack<CarSortType> carStack= new Stack<>();
        Car match = null;
        while(count<size){
            CarSortType tmpValue= (CarSortType)pricePQ.delMin();
            Car tmp = list.get(tmpValue.index);
            if(tmp.getMake().equals(make)&&tmp.getModel().equals(model)){
                match = tmp;
                matchFound=true;
                carStack.push(tmpValue);
                break;
            }
            carStack.push(tmpValue);
            count++;
        }
        if(!matchFound){
            System.out.println("Car not found");
            return null;
        }
        while(!carStack.isEmpty()){
            pricePQ.insert(carStack.pop());
            
        }
        return match;
    }
    public void update(String VIN, int fieldType, String value){
        Stack<CarSortType> mileStack = new Stack<>();
        Stack<CarSortType> priceStack = new Stack<>();
        
        boolean matchFound = false;
        int count = 0;
        for(Car c:list){
            if(VIN.equals(c.getVIN())){
                if(fieldType==1)
                c.updatePrice(Integer.parseInt(value));
                if(fieldType==2)
                c.updateMileage(0-Double.parseDouble(value));
                if(fieldType==3)
                c.updateColor(value);
                matchFound = true;
                break;
            }
            count++;
        }
        if(matchFound==false){
            System.out.println("VIN not found");   
            return;
        }
        while(true){
            CarSortType temp = (CarSortType)mileagePQ.delMin();
            if(temp.index==count){
                break;
            }
            mileStack.push(temp);
        }
        while(true){
            CarSortType temp = (CarSortType)pricePQ.delMin();
            if(temp.index==count){
                break;
            }
            priceStack.push(temp);
        }
        while(!mileStack.isEmpty()){
            mileagePQ.insert(mileStack.pop());
        }
        while(!priceStack.isEmpty()){
            pricePQ.insert(priceStack.pop());
        }
        Car tmpCar = list.get(count);
        mileagePQ.insert(new CarSortType(count,tmpCar.getVIN(),tmpCar.getMileage()));
        pricePQ.insert(new CarSortType(count,tmpCar.getVIN(),tmpCar.getPrice()));
    }
    public void remove(String VIN){
        Stack<CarSortType> mileStack = new Stack<>();
        Stack<CarSortType> priceStack = new Stack<>();
        
        boolean matchFound = false;
        int count = 0;
        for(Car c:list){
            if(VIN.equals(c.getVIN())){
                list.set(count,null);
                matchFound = true;
                break;
            }
            count++;
        }
        if(matchFound==false){
            System.out.println("VIN not found");   
            return;
        }
        while(true){
            CarSortType temp = (CarSortType)mileagePQ.delMin();
            if(temp.index==count){
                break;
            }
            mileStack.push(temp);
        }
        while(true){
            CarSortType temp = (CarSortType)pricePQ.delMin();
            if(temp.index==count){
                break;
            }
            priceStack.push(temp);
        }
        while(!mileStack.isEmpty()){
            mileagePQ.insert(mileStack.pop());
        }
        while(!priceStack.isEmpty()){
            pricePQ.insert(priceStack.pop());
        }
        size--;
    }
}