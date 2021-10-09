//Author: Hugo Lapa
//09/10/2021
import java.util.Scanner;
import java.util.Random;

class petRecord {
  String name;
  String species;
  int hunger;
  int happiness;
}
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
    petRecord pet = new petRecord();
    pet.name = ask("What's your pets name? ");
    pet.species = ask("What species is your pet? ");
    print("Game Start for " + pet.name + " the " + pet.species);
    game(pet);

  }

  //produces message with pet name
  public static String ask (String x)
  {
    Scanner scanner = new Scanner(System.in);
    
    System.out.print(x);
    String response = scanner.nextLine(); 
    // input for pets name

    return response;
  }

  //outputs to the screen
  public static void print (String txt) 
  {
    System.out.println(txt);
    return;
  }

  //this is where the game starts and ends
  public static void game (petRecord record) 
  {
    //set random hunger and happy level
    record = randStats(record);
    int count = 1; // counting how many rounds
    boolean end = false;
    while (end == false)
    {
      //keeping track of the previous hunger and happy levels
      //later used to see if games ends
      int hunger = record.hunger;
      int happiness = record.happiness;

      //print statements
      stateOfPet(record); 
      String action = ask("Action: ");
      feedCuddle(action, record);

      //ends game if the score is 5 for 2 rounds
      if ((hunger == 5 && record.hunger == 5) || (happiness == 5 && record.happiness == 5)) {
        stateOfPet(record);
        print("Hunger or Happiness was at 5 for 2 rounds!");
        end = true;
      }

      count = count + 1;

      //when the game lasts until round 10 it ends
      if (count == 10) {
        stateOfPet(record);
        print("You won! you lasted until round 10.");
        end = true;
      }
    }
    if (count < 10) {
      // message for loss
      print("Pet died at round " + count + "/10");
    }
    return;
  }

  //outputs detail about the state of the pet
  //takes hunger as input
  public static void stateOfPet (petRecord record)
  {
    //hunger value then used to determine message
    if ( record.hunger == 1) {
      print("Pet is bloated. Hunger score at " + record.hunger +"/5");
    } else if (record.hunger == 2) {
      print("Pet is full. Hunger score at " + record.hunger +"/5");
    } else if (record.hunger == 3) {
      print("Pet is peckish. Hunger score at " + record.hunger +"/5");
    } else if (record.hunger == 4) {
      print("Pet is famished. Hunger score at " + record.hunger +"/5");
    } else {
      print("Pet is ravenous. Hunger score at " + record.hunger +"/5");
    }

    if ( record.happiness == 1) {
      print("Pet is blissfull. Happiness score at " + record.happiness +"/5");
    } else if (record.happiness == 2) {
      print("Pet is cheerfull. Happiness score at " + record.happiness +"/5");
    } else if (record.happiness == 3) {
      print("Pet is neutral. Happiness score at " + record.happiness +"/5");
    } else if (record.happiness == 4) {
      print("Pet is unhappy. Happiness score at " + record.happiness +"/5");
    } else {
      print("Pet is depressed. Happiness score at " + record.happiness +"/5");
    }
  }

  //produces a random number between 1 and 5 to be a hunger value
  public static int getRand (int num)
  {
    Random rand = new Random();
    int random;

    random = rand.nextInt(num) + 1;

    return random;
  }

  //produces random hunger and happy level for pet
  public static petRecord randStats (petRecord record)
  {
    record.hunger = getRand(5);
    record.happiness = getRand(5);
    return record;
  }

  //actions taken depending wether user response is cuddle or feed
  public static petRecord feedCuddle (String action, petRecord record)
  {
    if (action.equals("cuddle")) {
      record.happiness = record.happiness - getRand(3);
      if (record.happiness < 1) {
        record.happiness = 1;
      }
      record.hunger = record.hunger + getRand(3);
      if (record.hunger > 5) {
        record.hunger = 5;
      }
    }

    if (action.equals("feed")) {
      record.hunger = record.hunger - getRand(3);
      if (record.hunger < 1) {
        record.hunger = 1;
      }
      record.happiness = record.happiness + getRand(3);
      if (record.happiness > 5) {
        record.happiness = 5;
      }
    }
    return record;
  }

}
