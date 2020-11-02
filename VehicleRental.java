package GroupProject;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by Patrick Donnelly (group7) on 27/10/2020
 * An abstract super class for a vehicle rental program
 */
abstract public class VehicleRental {

   //import Scanner class
   static Scanner keyboard = new Scanner(System.in);
   //import Decimal Format class
   DecimalFormat df = new DecimalFormat("0.00");

   //variables required for constructor methods to create an object of the VehicleRental class
   private String make, regNum,rentalType;
   private int startMileage, rentalID, numDays;
   private double dailyCost;
   private static int countVehiclesRented=0, uniqueNum = 10001;
   final static int DISCOUNT_ITEMS = 10;
   static int [] percentageDiscount = new int[DISCOUNT_ITEMS];
   int discount;


   //variables required for other methods/calculations within the VehicleRental class
   private int endMileage, totalMileage;
   private final double EXTRA_FEE_PER_MILE=0.2;
   private final int DAILY_MILES_ALLOWED=150;
   private double totalRevenue,averageDailyMiles, totalRentalCost;




   //default constructor
   public VehicleRental(){
      rentalID=uniqueNum;
      uniqueNum++;
      countVehiclesRented++;
      discount= randomSelection();
      // ali editted
     // endMileage = startMileage;

   }//end default constructor method

   //alternative constructor method with 5 constructor variables passed as parameters
   public VehicleRental(String itsType,String itsMake, String itsRegNum, int MilesBefore, int lengthDays, double dailyFee){
      make=itsMake;
      regNum=itsRegNum;
      startMileage=MilesBefore;
      rentalID=uniqueNum;
      numDays=lengthDays;
      dailyCost=dailyFee;
      uniqueNum++;
      countVehiclesRented++;
      discount = randomSelection();
      // ali editted
      //endMileage = startMileage;
      rentalType=itsType;

   }//end alternative constructor

   //alternative constructor method
   public VehicleRental(String itsRegNum, int MilesBefore, int lengthDays){
      make="unknown";
      regNum=itsRegNum;
      startMileage=MilesBefore;
      rentalID=uniqueNum;
      numDays=lengthDays;
      dailyCost=-999;
      uniqueNum++;
      countVehiclesRented++;
      discount= randomSelection();
   }//end alternative constructor

   //alternative constructor method
   public VehicleRental(int MilesBefore, int lengthDays){
      make="unknown";
      regNum="***";
      startMileage=MilesBefore;
      rentalID=uniqueNum;
      numDays=lengthDays;
      dailyCost=-999;
      uniqueNum++;
      countVehiclesRented++;
      discount= randomSelection();
   }//end alternative constructor

   //alternative constructor method
   public VehicleRental(String itsRegNum, int MilesBefore, double dailyFee){
      make="unknown";
      regNum=itsRegNum;
      startMileage=MilesBefore;
      rentalID=uniqueNum;
      numDays=-999;
      dailyCost=-dailyFee;
      uniqueNum++;
      countVehiclesRented++;
      discount= randomSelection();
   }//end alternative constructor

   //method to getRentalID
   protected int getRentalID() {
      return rentalID;
   }//end getRentalID

   //method to getTotalMileage
   // added by Ali. I needed this for the bus rental
   protected int getTotalMileage() {
      return endMileage-startMileage;
   }//end getRentalID

   //method for setMake()
   protected void setMake(String vehicleMake) {
      vehicleMake = make;
   }//end setMake

   //method to getMake
   protected String getMake() {
      return make;
   }//end getMake

   //method for setRegNum()
   protected void setRegNum(String regNo) {
      regNum=regNo;
   }//end setRegNum

   //method for getRegNum()
   protected String getRegNum() {
      return regNum;
   }//end getRegNum

   //method to setStartMileage()
   protected void setStartMileage(int beforeMiles) {
      startMileage = beforeMiles;
   }//end setStartMileage()

   //method to getStartMileage()
   protected int getStartMileage() {
      return startMileage;
   }// end method to getStartMileage()

   //method to setNumDays()
   protected void setNumDays(int lengthDays) {
      numDays = lengthDays;
   }// end setNumdays()

   // method to getNumDays()
   protected int getNumDays() {
      return numDays;
   }//end getNumDays

   //method to setDailyCost()
   protected void setDailyCost(double dailyFee) {
      dailyCost = dailyFee;
   }// setDailyCost

   //method to getDailyCost()
   protected double getDailyCost() {
      return dailyCost;
   }//end getDailyCost()

   //method to setEndMileage()
   protected void setEndMileage(int mileageAfter) {
      endMileage=mileageAfter;
   }//end  setEndMileage()

   //method to getEndMileage()
   protected int getEndMileage() {
      return endMileage;
   }//end getEndMileage()

   protected String getRentalType() {
      return rentalType;
   }

   //method to calculate averageDailyMiles()
   protected double calculateAverageDailyMiles(){
      return (double) (endMileage-startMileage)/numDays;
   }//end calculateAverageDailyMiles()

   //method to calculate additional fee based on mileage
   protected double calculateAdditionalFee(){
      return (calculateAverageDailyMiles() - DAILY_MILES_ALLOWED)*numDays*EXTRA_FEE_PER_MILE;
   }//end calculateAdditionalFee

   //method to calulcate totol Rental price
   protected double calculateTotalRentalCost(){
      totalRentalCost= (dailyCost*numDays)+calculateAdditionalFee();
      totalRentalCost=totalRentalCost-((totalRentalCost/100)*discount);
      return totalRentalCost;
   }//end calculatetotalRentalCost()

/*   //method to populate array with int values representing discount
   protected static void updateDiscountList(){
      for (int index=0; index<DISCOUNT_ITEMS; index++){
         System.out.println("Enter the value Discount (%): "+ (index+1)+" of "+ DISCOUNT_ITEMS);
         percentageDiscount[index]= keyboard.nextInt();
      }//end for
   }// end updateDiscountList()*/

   //method to populate array with int values representing discount
   protected static void autoUpdateDiscountList(){
      Random r = new Random();
      int MAX = 40;
      // generate random list
      for (int index=0; index<DISCOUNT_ITEMS; index++){
         percentageDiscount[index]= r.nextInt(MAX);
      }//end for

      // display array of discounts
      System.out.print("This is the array of percentage discounts: ");
      String result = Arrays.stream(percentageDiscount).mapToObj(String::valueOf).collect(Collectors.joining(", "));
      //System.out.println(Arrays.toString(percentageDiscount));
      System.out.println(result);

      String wait = keyboard.nextLine();

   }// end autoUpdateDiscountList()


   //method to perform random selection from discount array
   protected int randomSelection(){
      int pick;
      pick=percentageDiscount[(int)(Math.random()*DISCOUNT_ITEMS)];
      return pick;
   }// end randomSelection

   //abstract method getVehicleType()
   abstract protected String getVehicleType();
   //end

   //toString Method
   public String toString() {
      String message;
      message= "Rental ID: " + rentalID + "\n"+
            "Vehicle Make: " + make + "\n" +
            "Registration Number: " + regNum + "\n" +
            "Rental Duration (Days): " + numDays + "\n"+
            "Daily Mileage Allowance: " + DAILY_MILES_ALLOWED + " miles\n" +
            "Daily Cost: £" + df.format(dailyCost) + "\n"+
            "Total Cost (before any additional fees): £"+dailyCost*numDays+"\n"+
            "Start Mileage: " + startMileage + " miles\n";
      if(endMileage==0) {
         message=message+"End Mileage: to be recorded on vehilce return\n";
      }//end if
      else {
         message=message+"End Mileage: "+endMileage+" miles\n";
      }//end else
      if (calculateAverageDailyMiles()<0) {
         message = message+ "Average Daily Miles: to be calculated on vehicle return\n";
      }//end if
      else{
         message = message+"Average Daily Miles: "+df.format(calculateAverageDailyMiles())+" miles\n";
      }//end else
      if(endMileage>startMileage){
         if((calculateAverageDailyMiles()*numDays)-(DAILY_MILES_ALLOWED*numDays)>0){
            message=message+"Additional Miles (over that contracted): " +
                  df.format((calculateAverageDailyMiles()*numDays)-(DAILY_MILES_ALLOWED*numDays))+"\n";
         }//end if
         else{
            message=message+"Additional Miles (over that contracted): 0\n";
         }//end else

      }//end if
      else {
         message = message + "Additional Miles (over that contracted): to be calculated on vehicle return\n ";
      }//end else
      if((calculateAverageDailyMiles()*numDays)-(DAILY_MILES_ALLOWED*numDays)*EXTRA_FEE_PER_MILE<=0){
         message=message+"Extra Fee: £" + df.format(EXTRA_FEE_PER_MILE) +" per mile:to be calculated on vehicle return\n";
      }//end if
      else{
         message=message+"Extra Fee: £" + df.format(EXTRA_FEE_PER_MILE) +" per mile: £"
               + df.format(((calculateAverageDailyMiles()*numDays)-(DAILY_MILES_ALLOWED*numDays))*EXTRA_FEE_PER_MILE)+"\n";
      }//end else
      message=message+"Discount Percentage applied (selected at random): " + discount +"%\n";
      if(calculateTotalRentalCost()<=0){
         message=message+"Total Cost (after "+discount+" % discount applied): to be calculated on vehicle return\n";
      }//end if
      else{
         message=message+"Total Cost (after "+discount+" % discount applied): £" + df.format(calculateTotalRentalCost());
      }//end else
      return message;
   }//end toString method

}//class
