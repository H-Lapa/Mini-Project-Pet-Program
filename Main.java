//Author: Hugo Lapa
//31/10/2021

//impots for the program
import java.util.Scanner;
import java.util.Random;


class Game {
    public static void main (String[] args)
    {
        //declaration of variables for creating pets
        String petname;
        String petspecies;
        general.print("(maximum of 5)");
        int petQuant = general.getint("Quantity of pets: ");
        pet[] petarr = new pet[petQuant]; // pet array delcaration

        //loop which creates pets, and puts them into an array
        for (int i = 0; i < petQuant; i++)
        {
            petname = general.getstring("Pet name: ");
            petspecies = general.getstring("Pet species: ");
            pet record = createpet(petname, petspecies);
            petarr[i] = record;
        }


        //declaration of variables needed for while loop
        boolean end = false;
        int count = 0;
        String action;
        int num;
        int petnum;
        int[][] temp = new int[5][2];
        pet[] sortedArr = new pet[petQuant];

        //while loop until the end of game becomes true
        while (end == false)
        {
            //sets temp to current petarray stored, clones it otherwise temp will be assigned to memory location and update along with petarr, used later for comparison
            temp = statClone(petarr);

            //implement insertion sort for new array
            sortedArr = insertionSort(petarr);

            //prints pet name and stats to screen
            for (int z = 0; z < petQuant; z++)
            {
                //this should loop through the insertion sorted array
                general.print(" ");
                general.print(z+1 + ". " + getname(sortedArr[z]) + " the " + getspecies(sortedArr[z]));
                stateofpet(sortedArr[z]);
            }

            System.out.println("             "); // gap to make visually appealing

            //user interaction, asking which pet to apply action and wether to feed or cuddle
            num = general.getint("Pick a pet to apply action: ");
            petnum = num-1;
            petnum = findloc(petarr, sortedArr[petnum]);
            action = general.getstring("Feed or Cuddle pet: ");

            // if function returns true, function also acts on the pet which was selected by feeding or cuddling
            if (feedCuddle(action, petarr[petnum]))
            {
                for (int i = 0; i < petQuant;i++)
                {
                    //dimiishes stats of all other pets except the one where action is applied
                    if (petarr[i] != petarr[petnum])
                    {
                        regressStats(petarr[i]);
                    }
                }
            }
            else
            {
                general.print("Sorry your response wasnt understood! Please try again...");
            }


            //checks if stats using previous array and current array
            if (loseCheck(temp, petarr)) {
                //if true then it prints an end message
                for (int z = 0; z < petQuant; z++)
                {
                    general.print(" ");
                    general.print(getname(petarr[z]) + " the " + getspecies(petarr[z]));
                    stateofpet(petarr[z]);
                }
                general.print("Hunger or Happiness was at 5 for 2 rounds!");
                //sets end to true to end loop
                end = true;
            }
            else
            {
                //count is incremented because of another successful round
                count += 1;
            }

            //when 10 rounds have passed
            if (count == 10) {
                for (int z = 0; z < petQuant; z++)
                {
                    //print winning message
                    general.print(" ");
                    general.print(getname(petarr[z]) + " the " + getspecies(petarr[z]));
                    stateofpet(petarr[z]);
                }
                general.print("You won! you lasted until round 10.");
                end = true;
            }

        }

        System.exit(0);
    }//END of main

    //clones the hunger and happiness stats from a pet class into a 2d array and returns it
    public static int[][] statClone (pet[] curr)
    {
        int[][] petStat = new int[5][2];
        for (int i=0; i< curr.length; i++)
        {
            //copying the hunger and happiness values
            petStat[i][0] = curr[i].hunger;
            petStat[i][1] = curr[i].happiness;
        }
        return petStat;
    }//END of statClone

    //checks if the user has lost in the game
    public static boolean loseCheck (int[][] prev, pet[] curr)
    {
        //this loops through the size of the array
        for (int z=0; z< curr.length; z++)
        {
            //if the hunger or happiness is equal to 5 from previous and current round then the user has lost
            if ((prev[z][0] == 5 && curr[z].hunger == 5) || (prev[z][1] == 5 && curr[z].happiness == 5) )
            {
                //thus returns true
                return true;
            }
        }

        //if hasnt lost will return false
        return false;
    }//END of loseCheck

    //Does insertion sort on the pet array
    public static pet[] insertionSort (pet[] array)
    {
        int n = array.length;
        //loops through array
        for (int j = 1; j < n; j++) {
            int key = getCombinedStats(array[j]);
            pet secKey = array[j];
            int i = j-1;
            while ( (i > -1) && ( getCombinedStats(array[i]) > key ) ) {
                array[i+1] = array[i];
                i--;
            }
            array[i+1] = secKey;
        }
        return array;
    }//END of insertionSort
    
    //returns the addition of both happiness and hunger
    public static int getCombinedStats (pet x)
    {
        
        int num = gethappiness(x) + gethunger(x);
        return num;
    }//END of getCombinedStats

    //finds the position of a specfic pet in an array
    public static int findloc (pet[] petarr, pet petchoice)
    {
        //loops through the pet array
        for (int i = 0; i < petarr.length; i++)
        {
            //if the pet in the array is the same as the pet your searching for
            if (petarr[i] == petchoice)
            {
                //then return the i value 
                return i;
            }
        }

        return 0;
    }//END of findloc

    //gives a pet random hunger and happiness levels
    public static pet randStats (pet record)
    {
        record.hunger = general.getRand(5);
        record.happiness = general.getRand(5);
        return record;
    }//END of randStats
    
    //returns the state fo the pet depedning on the value of hunger / happiness
    public static void stateofpet (pet record)
    {
        //hunger value then used to determine message
        if ( gethunger(record) == 1) {
            general.print("Pet is bloated. Hunger score at " + gethunger(record) +"/5");
        } else if (gethunger(record) == 2) {
            general.print("Pet is full. Hunger score at " + gethunger(record) +"/5");
        } else if (gethunger(record) == 3) {
            general.print("Pet is peckish. Hunger score at " + gethunger(record) +"/5");
        } else if (gethunger(record) == 4) {
            general.print("Pet is famished. Hunger score at " + gethunger(record) +"/5");
        } else {
            general.print("Pet is ravenous. Hunger score at " + gethunger(record) +"/5");
        }

        if ( gethappiness(record) == 1) {
            general.print("Pet is blissfull. Happiness score at " + gethappiness(record) +"/5");
        } else if (gethappiness(record) == 2) {
            general.print("Pet is cheerfull. Happiness score at " + gethappiness(record) +"/5");
        } else if (gethappiness(record) == 3) {
            general.print("Pet is neutral. Happiness score at " + gethappiness(record) +"/5");
        } else if (gethappiness(record) == 4) {
            general.print("Pet is unhappy. Happiness score at " + gethappiness(record) +"/5");
        } else {
            general.print("Pet is depressed. Happiness score at " + gethappiness(record) +"/5");
        }
    }//END of stateofpet
    
    //applies the cuddle or feed, on a chosen pet 
    public static boolean feedCuddle (String action, pet record)
    {
        //if its cuddle, will decrease the value of happiness
        if (action.equals("cuddle")) {
            record.happiness = (gethappiness(record) - general.getRand(3));
            //if happiness is less than 1 then it becomes 1
            if (gethappiness(record) < 1) {
                record.happiness = 1;
            }
            //however hunger value increases with a limit of 5
            record.hunger = (gethunger(record) + general.getRand(3));
            if (gethunger(record) > 5) {
                record.hunger = 5;
            }
            return true;
        }
        
        //if its feed, will decrease the value of hunger
        if (action.equals("feed")) {
            record.hunger = (gethunger(record) - general.getRand(3));
            //hunger is less than 1 it gets set back to 1 as thats the limit
            if (gethunger(record) < 1) {
                record.hunger = 1;
            }
            //however the happiness value increases with a limit of 5
            record.happiness = (gethappiness(record) + general.getRand(3));
            if (gethappiness(record) > 5) {
                record.happiness = 5;
            }
            return true;
        }
        //returns false when none of the statements was successful
        return false;
    }//END of feedCuddle
    
    //worsening of the hunger and happiness of pets randomly
    public static void regressStats (pet record)
    {
        int newhunger = gethunger(record) + general.getRand(2);
        int newhappiness = gethappiness(record) + general.getRand(2);
        if (newhunger > 5)
        {
            newhunger = 5;
        }
        if (newhappiness > 5)
        {
            newhappiness = 5;
        }
        record.hunger = newhunger;
        record.happiness = newhappiness;
    }//END of regressStats
    

    //accessor methods, only methods directly accessing the values in the pet ADT
    
    //creates an ADT using the parameters
    public static pet createpet (String name, String species)
    {
        pet record = new pet();
        setpetname(record, name);
        setpetspecies(record, species);
        randStats(record);
        return record;
    }//END of createpet
    
    //getters and setters
    
    public static void setpetspecies (pet p, String txt)
    {
        p.species = txt;
    }//End of setpetspecies
    public static void setpetname (pet p, String txt)
    {
        p.name = txt;
    }//End of setpetname
    public static String getname (pet p)
    {
        return p.name;
    }//End of getname
    public static String getspecies (pet p)
    {
        return p.species;
    }//End fo getspecies
    public static int gethunger (pet p)
    {
        return p.hunger;
    }//End of gethunger
    public static int gethappiness (pet p)
    {
        return p.happiness;
    }//End of gethappiness
    public static void setpethappiness (pet p, int newvalue)
    {
        p.happiness = newvalue;
        return;
    }//End of setpethappiness
    public static void setpethunger (pet p, int newvalue)
    {
        p.hunger = newvalue;
        return;
    }//End of setpethunger

}
/* ***************************************************
 Create a new type (a ADT) called pet that records a pets data
 A Pet, has a Strign name and species and integer hunger and happiness values
 Used to store pet details, so the data is used in conjuction with accessor methods, which directly access the attributes
 Accessor methods:
 setpetspecies, setpetname, setpethunger, setpethappiness, getname, getspecies, gethunger, gethappiness
 */
class pet
{
    String name;
    String species;
    int hunger;
    int happiness;
}
