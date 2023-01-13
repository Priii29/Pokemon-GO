/* Pokemon Project - Sword Class
 * Priyanshi Ramani
 * December 4, 2021
 * ICS4U1 
 * Creating a Sword object that adds power, subtracts power when used and printed in ASCII art
 */ 
import java.io.PrintWriter;
public class Sword {
  //variables
  private int power; //stores power collected
  private boolean full; //determines if sword is full
  
  //constructs a sword with no power
  public Sword(){
    power=0;
    full = false;
  }
  
  //accessor methods
  public int getPower(){ //gets sword power
    return power;
  }
  
  public boolean getStat(){ //gets sword status (full or not)
    if (power == 10){ //10 means full
      full = true;
    }
    else {
      full = false;
    }
    return full;
  }
  
  //mutator methods
  public void setPower(int num){ //sets sword power
    power = num;
  }
  
  public void setStat(boolean e){ //sets sword status
    full = e;
  }
    
  public void add(int num){ //collects power 
    if (power + num > 10){ 
      power = 10; //sets to 10 if adding causes it to go higher
    }
    
    else {
      power +=num; //adds power
    }
  }
  
  public void use(){ //sets sword power to 0 
    power = 0;
  }
  
  //special character printer method (failed in DrJava but works online)
  public void printS(){
    PrintWriter printWriter = new PrintWriter(System.out,true); //replaces System.out with PrintWriter
    String x = "";
    char a = '\u258A'; //unicode for special character
    for (int i = 0; i<power; i++){
      x+=a; //outputs according to power
    }
        printWriter.println(x);
  }
  
  //toString method with ASCII art (not the one I wanted)
  public String toString(){
    
    String x = "";
    for (int i = 0; i<power; i++){
      x+="]"; //prints for amount of power earned
      
    }
    
    if (power < 10){
      for (int i = 0; i<10-power; i++){
        x+="-"; //prints blank for unearned power
        
      }
    }
    
    return x;
  }
}
    
    
    
    
    
    
    
    
    
    