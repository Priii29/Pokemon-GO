/* Pokemon Project - Pokemon Class
 * Priyanshi Ramani
 * December 4, 2021
 * ICS4U1 
 * Creating a Pokemon object that calculates hit points and sword power per round 
 * as well as attacks and uses sword object
 */ 

import java.util.Random;
import java.util.Scanner;
import java.lang.Math;
import java.io.File;

public class Pokemon {
  //variables
  private String name; //stores pokemon name
  private String type; //stores pokemon type (electric, grass, etc.)
  private int hl; //stores pokemon's health left
  private int ht;//stores pokemon's health total
  private double acc; //stores accuracy of attack
  
  //constructor initializing pokemon's name, type and health
  public Pokemon(String name, String type, int hl, int ht){
    this.name=name;
    this.type=type;
    this.hl=hl;
    this.ht=ht;
    acc=1; //accuracy by default is 1
  }
  
  //accessor methods
  public String getName(){ //gets pokemon name
    return name;
  }
  
  public String getType(){ //gets pokemon type
    return type;
  }
  
  public int getHL(){ //gets pokemon health left
    return hl;
  }
  
  public int getHT(){ //gets pokemon health total
    return ht;
  }
  
  public double getAcc(){ //gets accuracy
    return acc;
  }
  
  //calculated hit points
  public double getHP(Pokemon p, boolean w)throws java.io.FileNotFoundException{
    double [] arr = {0.2, 0.4, 0.6, 0.8, 1}; //possible accuracies
    Random ran2 = new Random();
    
    if (w == true && p.getHL() <= 100){ //boolean determines if opponent selected dodge (only selectable when opponent has less than 100 health
      acc = arr[0]; //if dodged, accuracy becomes low
    }
    
    else {
      int i = ran2.nextInt(4) + 1;
      acc = arr[i]; //else randomly selects accuracy factor
    }
    
    Random ran = new Random();
    int num = ran.nextInt(200) + 1; //randomly generated number for hit points
    String dtype = p.getType(); //stores opponent pokemon type
    double eft = 1; //effectivity of attack by default
    
    String filename = "Table.txt";
    Scanner file = new Scanner(new File(filename)); //file with effectivity rates of each combination of pokemon type
    
    while (file.hasNext()){
      if (file.next().equals(type) && file.next().equals(dtype)){ //reads file to find a match of inputed pokemon types
        eft = file.nextDouble(); //finds coresponding effectivity value
        break;
      }
      else {
        file.nextLine();
      }
    
  }
    
    double points = (double)((num*eft)*acc); //damage calculation
    return points; // returns hit points
    
  }
  
  //gets sword power to be added after each round
  public int getPower(double points){
    Math.round(points); //rounds hit points 
    int power = 0; //stores power
    
   if (points > 50 && points <=100){ //if player attacked with hit points in this range, power gained is 1 bar
      power = 1;
    }
    
    else if (points > 100 && points <=200){ //range between 101 - 200
      power = 2;
    }
    
    else if (points > 200 && points <=300){ //range between 201 - 300
      power = 3;
    }
    
    else if (points > 300){ //range between 301 or more
      power = 4;
    }
    
    else {
      power = 0; //if less than 50, no power is gained
    }
    return power;
  }
  
  //mutator methods
  public void setName(String name){ //sets pokemon name
    this.name=name;
  }
  
  public void setType(String type){ //sets pokemon type
    this.type=type;
  }
  
  public void setHL(int hl){ //sets pokemon health left
    this.hl=hl;
  }
  
  public void setHT(int ht){ //sets pokemon health total
    this.ht=ht;
  }
  
  public void setAcc(double acc){ //sets pokemon attack accuracy
    this.acc=acc;
  }
  
  //formatted print method
  public void print(){
    System.out.print(name+" ("+type+"): "+hl+"/"+ht+"hp\n");
  }
  
  //attacks opponent pokemon with hit points calculated
  public void attack(Pokemon p, double points){
    int dhp = p.getHL(); //stores opponent pokemon's health left
    Math.round(points); //rounds hit points
    
    if (dhp-(int)points > 0){ //subtracts opponent pokemon's health left by hit points
      p.setHL(dhp-(int)points); 
      if (hl - 10 <= 0){ //only decreases if more than zero, otherwise sets to 0
        hl = 0;
      }
      else {
        hl -= 10; //pokemon attacking decreases in health by 10 everytime it attacks
      }
    }
    
    else{
      p.setHL(0); //if health left becomes a negative integer, sets to 0
       if (hl - 10 <= 0){
        hl = 0;
      }
      else {
        hl -= 10;//pokemon attacking decreases in health by 10 everytime it attacks
      }
    }
  }
      
  public void useSword(Pokemon p){
    p.setHL(0); //sets opponent's pokemon to 0 if sword is used
    }
  
  //toString method
  public String toString(){
    return "Name: "+name+"\nType: "+type+"\nHealth Points: "+hl+"/"+ht+"hp\n\n";
    }
}
  
  