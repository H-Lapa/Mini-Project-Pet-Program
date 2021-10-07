//Author: Hugo Lapa
//07/10/2021
import java.util.Scanner;

//main class for the program
class petProgram {
  //main function executed
  public static void main(String[] args) {
    petNameMessage();
  }

  //produces message with pet name
  public static void petNameMessage ()
  {
    Scanner scanner = new Scanner(System.in);
    
    System.out.print("What's your pets name? ");
    String name = scanner.nextLine(); // input for pets name

    //message with name
    System.out.print("Happy 0th Birthday " + name);
  }
}
