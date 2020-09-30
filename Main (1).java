package src;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

	static RandomDouble randomDouble = new RandomDouble();
	static RandomInt randomInt = new RandomInt();

	static {
		randomDouble.initialize();
		randomInt.initialize( );
	}

	

	public static  String Buy_Sell() throws InterruptedException {
	
			      Random rd = new Random();// creating Random object
			      if(rd.nextBoolean() == true)
			      {
			      return "BUY";
			      
			      }
			      else {
			    	  return "SELL";
			    	 
			      }


		}
	
	public static double Quantity() throws InterruptedException {
	
				double qty = randomInt.nextInt();
				return qty;

		}
	
	
	public static double Price() throws InterruptedException {
		
		double price = randomDouble.nextDouble();

				return price;

		}
	
	
	public static String All_None() throws InterruptedException {
		
			      Random rd = new Random(); // creating Random object
			      
			      if(rd.nextBoolean() == true) {
				      return "ALL";
				      } else {
				    	  return "NONE";
				      }

		}
	
	public static String Order_Type() throws InterruptedException {
		
       
           Random rd = new Random(); // creating Random object
			      
			      if(rd.nextBoolean() == true) {
				      return "LIMIT";}
				      else {
				    	  return "MARKET"; 
				      }
		  
		
		}
	
	public static String Order_Status() throws InterruptedException {
		
		
				return "PENDING";
	
		}
	
	
	public static String Time() throws InterruptedException {
		

				DateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
	             Calendar cal=Calendar.getInstance();
	             String str_date1="2020-Jun-07 11:00:00";
	             String str_date2="2020-Jun-07 12:00:00";

	             try {
					cal.setTime(formatter.parse(str_date1));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	             Long value1 = cal.getTimeInMillis();

	             try {
					cal.setTime(formatter.parse(str_date2));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	             Long value2 = cal.getTimeInMillis();

	             long value3 = (long)(value1 + Math.random()*(value2 - value1));
	             cal.setTimeInMillis(value3);
	             return formatter.format(cal.getTime());
			} 
		

	
	
	public static String Min_Fill() throws InterruptedException {
		
		return "ZERO";

		
		}
	
	
	
	public static void main(String[] args) throws InterruptedException {
		//Buy_Sell();
		//Quantity();
		//Price();
		//All_None();
		//Order_Type();
		//Order_Status();
		//Time();
		//Id();
		for(int i = 0; i< 100;i++) {
		RandomGenerator obj = new RandomGenerator();
		
		obj.setBuyOrSell(Buy_Sell());
		obj.setQuantity(Quantity());
		obj.setPrice(Price());
		obj.setAllorNone(All_None());		
		obj.setOrderType(Order_Type());
		obj.setOrderStatus(Order_Status());
		obj.setOrderTime(Time());
		obj.setMinFill(Min_Fill());
		
	 System.out.println(obj.toString());
	}}

}