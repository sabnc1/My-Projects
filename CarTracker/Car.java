public class Car {

private String VIN;
private int Price;	
private String Make;
private String Model;
private double Mileage;
private String Color;


	public Car (String vin,int price,String make, String model, double mileage,String color)
	{
		VIN = vin;
		Price = price;
		Make = make;
		Model = model;
		Mileage = mileage;
		Color = color;
		}
	public void updatePrice(int priceIn){
		Price = priceIn;
	}
	public void updateColor(String colorIn){
		Color = colorIn;
	}
	public void updateMileage(double mileageIn){
		Mileage = mileageIn;
	}
	public String getVIN(){
		return VIN;
	}
	public String getMake(){
		return Make;
	}
	public String getModel(){
		return Model;
	}
	public int getPrice(){
		return Price;
	}
	public double getMileage(){
		return Mileage;
	}
	public String getColor(){
		return Color;
	}
	public int compareByMileage(Car x){
	    int i =  Double.valueOf(Mileage).compareTo(Double.valueOf(x.Mileage));
	    return i;
	   }
	public int compareByPrice(Car x){
	    int i = Integer.valueOf(Price).compareTo(Integer.valueOf(x.Price));
	    return i;
	   }
}

