import java.util.Scanner;
public class carTracker{
    private static  DoublePQ data = new DoublePQ();
    private static Scanner scan = new Scanner(System.in);
    public static void main(String[]args){
        String input;
        String VIN;
        String Make;
        String Model;
        double Price;
        int Mileage;
        String color;
        
        System.out.println("Hello user");
        
        while(true)
        {
            
            System.out.println("What would you like to do?");
            System.out.println("1:Add a car\n2:Update a car\n3:Remove a car\n4:Get lowest price car");
            System.out.println("5:Get best mileage car\n6:Get lowest price car by make and model");
            System.out.println("7:Get best mileage car by make and model\n8:Quit");
            input = scan.next();
            if(input.equals("1")) {
                addCar();
            }
            if(input.equals("2")){
                updateCar();
            }
            if(input.equals("3")){
                removeCar();
            }
            if(input.equals("4")){
                getLowestPrice();
            }
            if(input.equals("5")){
                getBestMileage();
            }
            if(input.equals("6")){
                getLowestPriceSpecific();
            }
            if(input.equals("7")){
                getBestMileageSpecific();
            }
            if(input.equals("8")){
                break;
            }
        }
    }
    private static void addCar(){
        String VIN;
        String Make;
        String Model;
        int Price;
        double Mileage;
        String Color;
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the following information");
        
        System.out.print("VIN:");
        VIN = scan.next();
        
        System.out.print("Make:");
        Make = scan.next();
        
        System.out.print("Model:");
        Model = scan.next();
        
        System.out.print("Price:");
        Price = Integer.parseInt(scan.next());
        
        System.out.print("Mileage:");
        Mileage= 0-Double.parseDouble(scan.next());
        
        System.out.print("Color:");
        Color = scan.next();
        Car newCar = new Car(VIN,Price,Make,Model,Mileage,Color);
        data.add(newCar);
    }
    private static void removeCar(){
        System.out.println("Enter the VIN of the car to remove");
        scan.nextLine();
        String vin = scan.nextLine();
        data.remove(vin);
    }
    private static void updateCar(){
        System.out.println("Enter the VIN of the car to update");
        scan.nextLine();
        String vin = scan.nextLine();
        System.out.println("Choose a field to update -\n1:Price\n2:Mileage\n3:Color");
        int field = Integer.parseInt(scan.nextLine());
        System.out.println("Enter the value you wish to change it to");
        String val = scan.nextLine();
        data.update(vin,field,val);
    }
    private static void getLowestPrice(){
        System.out.println("The car with the lowest price:");
        Car tmp = data.bestPrice();
        if(tmp!=null)
        printCar(tmp);
    }
    private static void getBestMileage(){
        System.out.println("The car with the best mileage:");
        Car tmp = data.bestMileage();
        if(tmp!=null)
        printCar(tmp);
    }
    private static void getLowestPriceSpecific(){
        System.out.println("Enter the make of the car");
        scan.nextLine();
        String make = scan.nextLine();
        
        System.out.println("Enter the model of the car");
        String model = scan.nextLine();
        
        System.out.println("The " + make + " " + model +" with the lowest price is:");
        Car tmp = data.bestPriceSpecific(make,model);
        if(tmp!=null){
         printCar(tmp);  
        }
    }
    private static void getBestMileageSpecific(){
        System.out.println("Enter the make of the car");
        scan.nextLine();
        String make = scan.nextLine();
        
        System.out.println("Enter the model of the car");
        String model = scan.nextLine();
        
        Car tmp = data.bestMileageSpecific(make,model);
        if(tmp!=null){
         printCar(tmp);  
        }
    }
    private static void printCar(Car c){
        if(c==null)
        System.out.print("null");
        System.out.println("VIN: " +c.getVIN());
        System.out.println("Make: " +c.getMake());
        System.out.println("Model: " +c.getModel());
        System.out.println("Price: " +c.getPrice());
        System.out.println("Mileage: " +c.getMileage());
        System.out.println("Color: " +c.getColor());
    }
}