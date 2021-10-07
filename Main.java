//Author: Hugo Lapa
//07/10/2021
import java.util.Scanner;
import java.util.Random;

//main class for the program
class Main {
  //main function executed
  public static void main(String[] args) 
  {
    sequence();
    System.exit(1);
  }

  //method calls methods in the right order
  public static void sequence()
  {
    String name = getPetName();
    print(name);
    int hunger = getHunger();
    stateOfPet(hunger);

  }

  //produces message with pet name
  public static String getPetName ()
  {
    Scanner scanner = new Scanner(System.in);
    
    System.out.print("What's your pets name? ");
    String name = scanner.nextLine(); // input for pets name

    return name;
  }

  //outputs to the screen
  public static void print (String txt) 
  {
    System.out.println(txt);
    return;
  }

  //outputs detail about the state of the pet
  //takes hunger as input
  public static void stateOfPet (int hunger)
  {
    //hunger value then used to determine message
    if ( hunger == 1) {
      print("Pet is bloated.");
    } else if (hunger == 2) {
      print("Pet is full.");
    } else if (hunger == 3) {
      print("Pet is peckish.");
    } else if (hunger == 4) {
      print("Pet is famished.");
    } else {
      print("Pet is ravenous.");
    }
  }

  //produces a random number between 1 and 5 to be a hunger value
  public static int getHunger ()
  {
    Random rand = new Random();
    int hunger;

    hunger = rand.nextInt(5) + 1;

    return hunger;
  }
}
