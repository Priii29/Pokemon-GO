/* Pokemon Project - Backpack Class
 * Priyanshi Ramani
 * December 4, 2021
 * ICS4U1 
 * Creating a Backpack object that stores, adds, gets and heals Pokemon objects
 */
import java.util.ArrayList; 

public class Backpack {
  
  //initial Pokemons
  private Pokemon p1 = new Pokemon("Pikachu", "Electric", 300, 300);
  private Pokemon p2 = new Pokemon("Charizard", "Fire", 300, 300);
  private Pokemon p3 = new Pokemon("Blastoise", "Water", 400, 400);
  
  //Pokemons for sale
  private Pokemon r1 = new Pokemon("Venusaur", "Grass", 200, 200);
  private Pokemon r2 = new Pokemon("Flamethrower", "Dragon", 400, 400);
  private Pokemon r3 = new Pokemon("Butterfree", "Bug", 500, 500);
  private Pokemon r4 = new Pokemon("Pidgeot", "Normal", 300, 300);
  private Pokemon r5 = new Pokemon("Arbok", "Poison", 300, 300);
  private Pokemon r6 = new Pokemon("Sandslash", "Ground", 300, 300);
  private Pokemon r7 = new Pokemon("Surf", "Ice", 400, 400);
  private Pokemon r8 = new Pokemon("Fearow", "Normal", 500, 500);
  private Pokemon r9 = new Pokemon("Raticate", "Normal", 400, 400);
                                   
  //stores inifinite pokemons                                 
  private ArrayList<Pokemon> bpk = new ArrayList<Pokemon>();
  
  //stores 9 pokemons up for sale
  private Pokemon [] discover = {r1, r2, r3, r4, r5, r6, r7, r8, r9};
  
  //int variables
  private int pCount = 2; //holds pokemon count in terms of index value
  private int count = 3; //holds pokemon count in terms of number of pokemons
  
  //Backpack constructor initializing 3 pokemons
  public Backpack(){
    bpk.add(p1);
    bpk.add(p2);
    bpk.add(p3);
  }
  
  //accessor methods
  public int getpCount(){ //gets the pokemon count in terms of quantity
    return count;
  }
  
  public int getPCount(){ //gets the pokemon count in terms of arraylist index
    return pCount;
  }
  
  //gets pokemon from the range available
  public Pokemon getPokemon(int num){
    if (num >= 1 && num <= bpk.size()){
      return bpk.get(num-1);
    }
    else{
      return null; 
    }
  }
  
  //mutator methods
  private void setpCount(int num){ //kept private to prevent errors 
    pCount = num; //sets index count
  }
  
  private void setC(int num){ //kept private to prevent errors 
    count = num; //sets quantity count
  }
  
  //prints pokemon choices user can buy 
  public void printOptions(){
    System.out.print("1)");r1.print();
    System.out.print("2)");r2.print();
    System.out.print("3)");r3.print();
    System.out.print("4)");r4.print();
    System.out.print("5)");r5.print();
    System.out.print("6)");r6.print();
    System.out.print("7)");r7.print();
    System.out.print("8)");r8.print();
    System.out.print("9)");r9.print();
  }
  
  //adds purchased Pokemon to backpack
  public void addPokemon(int num){
    if (num >= 1 && num <=9){
      bpk.add(discover[num-1]);
      pCount++;
      count++;
    }
    else{
      System.out.println("Error: Not Found"); //invalid input
    }
  }
  
  //fills up pokemon health to maximum health
  public void healPokemon(int num){
    (bpk.get(num-1)).setHL((bpk.get(num-1)).getHT());
  }
  
  //toString method with ASCII art implemented
  public String toString(){
    String str = "";
    int k = 1; //choice number
    for (int i = 0; i <= pCount; i++) {
      str += "-------------------------\n|           "+(k++)+"           |\n-------------------------\n"+ bpk.get(i);
    }
    return str;
  }
}
      
  
  
  