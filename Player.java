/* Pokemon Project - Player Class
 * Priyanshi Ramani
 * December 4, 2021
 * ICS4U1 
 * Creating a Player object stores player's name, backpack, sword and money, 
 * with the added ability to use sword object, get pokemon and use AI for mini battle
 */ 

import java.io.File;
import java.util.Scanner;

public class Player {
  //variables
  private String name; //stores name
  private Backpack bk; //stores backpack
  private Sword swd; //stores sword
  private Money mon; //stores money
  
  //constructs Player object using name input
  public Player (String name){
    this.name=name;
    //initializes new objects 
    bk=new Backpack(); 
    swd = new Sword();
    mon = new Money();
  }
  
  //accessor methods 
  public String getName(){ //gets player name
    return name;
  }
  
  public Backpack getBk(){ //gets player backpack
    return bk;
  }
  
  public Sword getSword(){ //gets player sword
    return swd;
  }
  
  public Money getMoney(){ //gets player money
    return mon;
  }
  
  //gets coins earned for player versing AI, by reading a file and corresponding coin amounts
  public int getCoins(String m, String r)throws java.io.FileNotFoundException{ 
    String filename = "Moves.txt"; //file name
    Scanner file = new Scanner(new File(filename)); 
    int c = 0; //stores coin value
    //reads file
    while (file.hasNext()){
      if (file.next().equals(m) && file.next().equals(r)){ //reads file until both choices match
        c = file.nextInt(); //sets coin value to corresponding integer 
        break;
      }
      else {
        file.nextLine(); 
      }
    }
    
    return c; //returns coin value
  }
  
  //shortcut to get pokemon
  public Pokemon getPoke(int p){
    return bk.getPokemon(p);
  }
  
  //mutator methods
  public void setName(String name){ //sets name
    this.name=name;
  }
  
  public void setBk(Backpack i){ //sets backpack oject to another backpack object
    bk=i;
  }
  
  public void setSword(Sword s1){ //sets sword oject to another sword object
    swd = s1;
  }
  
  public void setMoney(Money m1){ //sets money oject to another money object
    mon = m1;
  }
  
  public void addPower(int num){ //adds power to player's sword object
    swd.add(num);
  }
  
  public void useSword(Pokemon p, boolean w){
    if (swd.getStat() == true){ //rechecks to make sure of user choice
      swd.use();
      p.setHL(0);//sets opponent's pokemon's health to 0
    }
  }
  
  //toString method
  public String toString(){
    return "Player "+name+":\n\n"+bk+"\n\nSword: "+swd+"\n\n"+mon+"\n";
}
}
  