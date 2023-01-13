/* Pokemon Project - Money Class
 * Priyanshi Ramani
 * December 4, 2021
 * ICS4U1 
 * Creating a Money object that gets, sets, uses and adds coins
 */

public class Money{
  private int coins; //variable to store coins collected
  
  //constructor that initially sets player money to 0
  public Money(){
    coins = 0;
  }
  
  //accessor method
  //gets number of coins
  public int getCoins(){
    return coins;
  }
  
  //mutator methods
  //sets number of coins
  public void setCoins(int num){
    coins = num;
  }
  
  //subtracts coins from coins collected
  public void use(int num){
    coins -= num;
  }
  
  //adds coins to coins collected
  public void add(int num){
    coins += num;
  }
  
  //toString method
  public String toString(){
    return "Money Available: $" + coins;
  }
}