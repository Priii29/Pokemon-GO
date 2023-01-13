/* Pokemon Project - Main Game
 * Priyanshi Ramani
 * December 4, 2021
 * ICS4U1 
 * Simulates a Pokemon game with many extra features and additions
 */

import java.util.Scanner;
import java.io.File;
import java.util.Random;

public class AGame {
  //main method
  public static void main(String[] args) throws java.io.FileNotFoundException{
    Scanner in = new Scanner(System.in);
    //displays title
    String filename = "Title.txt";
    Scanner file = new Scanner(new File(filename)); 
    
    while (file.hasNextLine()){
      System.out.print(file.nextLine()+"\n");
    }
    //Introduction
    System.out.println("Welcome To Pokemon!");
    System.out.println();
    //setting names and creating players
    System.out.println("Player 1: Please Enter Your Name.");
    String p1n = in.nextLine();
    
    Player p1 = new Player(p1n); 
    
    System.out.println("Player 2: Please Enter Your Name.");
    String p2n = in.nextLine();
    
    Player p2 = new Player(p2n); 
    
    //major variables
    int p1WC = 0; //stores how many games player 1 won
    int p2WC = 0; //stores how many games player 2 won
    int battleCount = 1; //counts how many battles have passed
    
    //continues loop for 3 battles 
    while (battleCount < 4){
      
      //variables
      int roundCount = 1; //counts current round number
      boolean d = false; // accounts for player 1 "dodge" choice
      boolean dd = false;// accounts for player 2 "dodge" choice
      boolean s = false;// accounts for player 1 "sword" choice
      boolean ss = false;// accounts for player 2 "sword" choice
      
      //prints backpack and prompts user
      System.out.println(p1.getBk());
      System.out.println(p1n+": Select a Pokemon.");
      int choice1 = in.nextInt();
      choice1 = checkkk(choice1, 1,(p1.getBk()).getpCount(), p1);
      
      
      System.out.println(p2.getBk());
      System.out.println(p2n+": Select a Pokemon.");
      int choice2 = in.nextInt();
      choice2 = checkkk2(choice2, 1,(p2.getBk()).getpCount(), p2);
      
      //copying selected pokemons 
      Pokemon k1 = p1.getPoke(choice1);
      Pokemon k2 = p2.getPoke(choice2);
      
      //BATTLE BEGINS
      System.out.println("Let's Begin!");
      System.out.println();
      System.out.println("{BATTLE "+battleCount+"}");
      System.out.println();
      
      //variables  
      boolean hl = true; //to determine if pokemon 1 has health left
      boolean hll = true; //to determine if pokemon 2 has health left
      
      //continues loop until either pokemon has no health left
      while(hl == true && hll ==true){
        
        //variables
        int k1HL = k1.getHL(); //pokemon 1 health left value 
        int k2HL = k2.getHL(); //pokemon 2 health left value
        int p1PO = (p1.getSword()).getPower(); //player 1's sword power
        int p2PO = (p2.getSword()).getPower();//player 2's sword power
        
        //ROUND BEGINS
        System.out.println("{Round "+roundCount+"}");
        
        //prints the poken name, type and beginning health of pokemons
        k1.print();
        k2.print();
        
        //Player 1 turn
        //if player 1's sword power is full (10), asks player if they want to use it
        if (p1PO == 10){
          System.out.println();
          System.out.println(p1n+": {Attacking}");
          System.out.println(p1n+": Would you like to use sword? {Yes} or {No}");
          String s1 = in.next().toLowerCase();
          s1 = check(s1, "yes","no"); //checking errors
          
          //factoring the response into attack
          if (s1.equals("yes")){
            s = true;
            p1.useSword(k2, s);
            hl=false;
          }
        }
        
        //gives option to dodge if pokemon 2's health is below 101 and player1 did not choose to use sword
        if (k2HL <= 100 && s == false){
          System.out.println();
          System.out.println(p1n+": {Attacking}");
          System.out.println(p2n+": Would you like to dodge? {Yes} or {No}");
          String d1 = in.next().toLowerCase();
          d1 = check(d1, "yes","no"); //checking errors
          
          //factoring the response into attack method
          if (d1.equals("yes")){
            d = true;
          }
          else {
            d = false;
          }
        }

        double hp = k1.getHP(k2, d);//calculating damage
        k1.attack(k2, hp); //attacking 
        p1.addPower(k1.getPower(hp)); //adding corresponding power to player's sword
        
        
        //Player 2 turn 
        //same as player 1 except player 2 is attacking
        if (p2PO == 10){
          System.out.println();
          System.out.println(p2n+": {Attacking}");
          System.out.println(p2n+": Would you like to use sword? {Yes} or {No}");
          String s2 = in.next().toLowerCase();
          s2 = check(s2, "yes","no");
          if (s2.equals("yes")){
            ss = true;
            p2.useSword(k1, ss);
            hl=false;
          }
        }
        
        if (k1HL <= 100 && ss == false){
          System.out.println();
          System.out.println(p2n+": {Attacking}");
          System.out.println(p1n+": Would you like to dodge? {Yes} or {No}");
          String d2 = in.next().toLowerCase();
          d2 = check(d2, "yes","no");
          if (d2.equals("yes")){
            dd = true;
          }
          else {
            dd = false;
          }
        }

        hp = k2.getHP(k1, dd); //calculating damage
        k2.attack(k1, hp); //attacking
        p2.addPower(k2.getPower(hp)); //adding power to player 2's sword
        
        //prints sword as ASCII art but DRjava does not support special caharacters 
        System.out.println();
        System.out.println(p1n+"'s Sword: "+p1.getSword()); //(p1.getSword()).printS(); //desperate attempts to get special chaarcters to work
        System.out.println(p2n+"'s Sword: "+p2.getSword()); //(p2.getSword()).printS();
        System.out.println();
        System.out.println();
        
        //checks if either pokemon's health is 0
        hl = check(k1.getHL());
        hll = check(k2.getHL());
        //runs if both pokemons run of of health at the same time
        if (hl==false && hll == false){
          battleCount++;
          System.out.println("It's a tie!");
        }
        //runs if player 1 loses
        else if (hl==false){
          battleCount++;
          System.out.println("{"+p2n+" WINS BATTLE!}");
          p2WC++;
          //Invitation to mini battle if player2 won and this was not the last battle
          if (battleCount < 4){
            System.out.println(p2n+": Would you like to play a mini battle? {Yes} or {No}");
            String b = in.next().toLowerCase();
            b = check(b, "yes", "no");
            if (b.equals("yes")){
              miniBattleShop(p2); //runs method
            }
          }
        }
        else if (hll==false){
          battleCount++;
          System.out.println("{"+p1n+" WINS BATTLE!}");
          p1WC++;
          //Invitation to mini battle if player1 won and this was not the last battle
          if (battleCount < 4){
            System.out.println(p1n+": Would you like to play a mini battle? {Yes} or {No}");
            String l = in.next().toLowerCase();
            l = check(l, "yes", "no");
            if (l.equals("yes")){
              miniBattleShop(p1);//runs method
            }
          }
        }
        roundCount++; //round ends
        System.out.println();
      }  
    }
    
    //determines winner
    //if player 1 wins
    if (p1WC > p2WC){
      System.out.println();
      //reads file here to avoid errors
      filename = "Winner.txt"; //displays medal
      file = new Scanner(new File(filename));
      while (file.hasNextLine()){
        System.out.print(file.nextLine()+"\n");
      }
      System.out.println();
      System.out.println();
      System.out.println("        {"+p1n.toUpperCase()+"}"); //formatted nicely
    }
    //if player 2 wins
    else if (p2WC > p1WC){
      System.out.println();
      //reads file here to avoid errors
      filename = "Winner.txt"; //displays medal
      file = new Scanner(new File(filename));
      while (file.hasNextLine()){
        System.out.print(file.nextLine()+"\n");
      }
      System.out.println();
      System.out.println();
      System.out.println("        {"+p2n.toUpperCase()+"}"); //formatted nicely
      
      //if it was a tie
    }
    else {
      System.out.println();
      System.out.println();
      System.out.println("GAME ENDS: IT WAS A TIE.");
      
    }
    
  }
  
  //other methods
  //checks if a number input is within range
  public static int check(int n, int min, int max){ 
    Scanner in = new Scanner(System.in);
    while (n<min || n>max){ //2 comparisons
      System.out.println("Invaild. Pick between "+min+" - "+max);
      n = in.nextInt();
    }
    return n;
  }
  
  //checks if player 1's pokemon choice is within range and pokemon is healthy
  public static int checkkk(int n, int min, int max, Player p){ 
    Scanner in = new Scanner(System.in);
    while (n<min || n>max || (p.getPoke(n)).getHL()==0){ //3 comparisons
      System.out.println("Invaild. Pick only healthy Pokemons between "+min+" - "+max);
      n = in.nextInt(); //prompts repeatedly for correct input
    }
    return n;
  }
  //checks if player 2's pokemon choice is within range and pokemon is heathly
  public static int checkkk2(int n, int min, int max, Player p){
    Scanner in = new Scanner(System.in);
    while (n<min || n>max || (p.getPoke(n)).getHL()==0){ //3 comparisons
      System.out.println("Invaild. Pick only healthy Pokemons between "+min+" - "+max);
      n = in.nextInt();//prompts repeatedly for correct input
    }
    return n;
  }
  
  //checks if health left for either pokemon is 0
  public static boolean check(int n){
    if (n==0){
      return false;
    }
    else {
      return true;
    }
  }
  
  //check for correct String input regardless of cases 
  public static String check(String n, String min, String max){
    Scanner in = new Scanner(System.in);
    while(!(n.toLowerCase()).equals(min) && !(n.toLowerCase()).equals(max)){ //2 comparisons
      System.out.println("Invaild. Pick between {"+min+"} or {"+max+"}");
      n = in.nextLine();//prompts repeatedly for correct input
    }
    return n;
  }
  
  //check for correct String input regardless of cases 
  public static String check(String n, String min, String med, String max){
    Scanner in = new Scanner(System.in);
    while(!(n.toLowerCase()).equals(min) && !(n.toLowerCase()).equals(med)&&!(n.toLowerCase()).equals(max)){ //3 comparisons
      System.out.println("Invaild. Pick between {"+min+"} or {"+med+"} or {"+max+"}");
      n = in.nextLine();
    }
    return n;
  }
  // runs a mini battle between temporary winning player and computer AI
  public static void miniBattleShop(Player p)throws java.io.FileNotFoundException{
    Scanner in = new Scanner(System.in);
    //variables
    int rcount = 1; //counts rounds
    int earned = 0; //collects coins earned
    
    //Introduction
    System.out.println();
    System.out.println("Welcome to Mini Battle!");
    System.out.println();
    System.out.println("INSTRUCTIONS: Beat the AI and collect coins!");
    
    //runs for 5 rounds 
    while (rcount < 6){
      //AI mechanisms
      String [] arr = {"punch", "kick", "dodge"};
      Random ran2 = new Random();
      int i = ran2.nextInt(3);
      String r = arr[i]; //randomly chosen input out of the array of choices available
      
      //prompts user for their choice
      System.out.println("{Round "+rcount+"}");
      System.out.println();
      System.out.println(p.getName()+": Please type your move.");
      System.out.println("{punch} {kick} {dodge}");
      String m = in.next().toLowerCase();
      m = check(m, "punch", "kick", "dodge");//checks errors
      System.out.println();
      
      //determines coins collected for player based on player and AI choices
      int c = p.getCoins(m,r);
      
      //shows choices and coins earned
      System.out.println("You Chose: "+m);
      System.out.println("AI Chose: "+r);
      System.out.println("COINS EARNED: $"+c);
      System.out.println();
      
      //adds coins collected to player's money 
      (p.getMoney()).add(c);
      earned +=c; // keeps track of total money
      rcount++; //round ends
    }
    
    //Opens shop after coins are collected
    System.out.println("TIME TO SHOP! TOTAL COINS AVAILABLE: $"+earned);
    System.out.println();
    System.out.println("What would you like to buy?");
    //prompts user to buy a new pokemon, buy health for existing pokemon or buy nothing
    System.out.println("{Pokemon} {Health} {Nothing}");
    String t = in.next().toLowerCase();
    t = check(t, "pokemon", "health", "nothing"); //checks errors
    System.out.println();
    
    //runs if player chose to buy new pokemon and has enough money
    if (t.equals("pokemon") && earned >=5){
      (p.getBk()).printOptions(); //prints 9 options to purchase from
      System.out.println();
      System.out.println("Choose by entering a number.");
      int y = in.nextInt();
      y = check(y, 1, 9); //checks errors
      
      (p.getBk()).addPokemon(y); //adds pokemon to backpack
      (p.getMoney()).use(5); //subtracts cost from money
      System.out.println("PURCHASED!");
      
    }
    
    //runs if player chose to buy health for existing pokemon and has enough money
    else if (t.equals("health")&& earned >=4){
      System.out.println(p.getBk()); //diplays existing pokemons
      System.out.println();
      System.out.println("Choose which Pokemon to heal by entering a number.");
      int z = in.nextInt();
      z = check(z, 1, (p.getBk()).getpCount()); //checks errors
      
      (p.getBk()).healPokemon(z); //fills up to max health 
      (p.getMoney()).use(4); //subtracts cost from money
      System.out.println("PURCHASED!");
    }
    
    //runs if player did not choose to buy anything
    else if (t.equals("nothing")){
      System.out.println("Hope to see you again!");
    }
    
    //runs if player did not have enough money
    else {
      System.out.println("Not Enough Money. Please come back later.");
    }
  }
}
