//Author: Hugo Lapa
//31/10/2021
import java.util.Scanner;
import java.util.Random;
class Game {
    public static void main (String[] args)
    {
        String petname;
        String petspecies;
        general.print("(maximum of 5)");
        int petQuant = general.getint("Quantity of pets: ");
        pet[] petarr = new pet[petQuant];
        for (int i = 0; i < petQuant; i++)
        {
            petname = general.getstring("Pet name: ");
            petspecies = general.getstring("Pet species: ");
            pet record = createpet(petname, petspecies);
            petarr[i] = record;
        }

        boolean end = false;
        int count = 0;
        String action;
        int num;
        int petnum;
        int[][] temp = new int[5][2];
        pet[] sortedArr = new pet[petQuant];

        while (end == false)
        {
            //sets temp to current petarray stored, clones it otherwise temp will be assigned to memory location and update along with petarr, used later for comparison
            temp = statClone(petarr);

            //implement insertion sort for new array
            sortedArr = insertionSort(petarr);


            //prints pet name and stats to screen
            for (int z = 0; z < petQuant; z++)
            {
                //this hsould loop through the insertion sorted array
                general.print(" ");
                general.print(z+1 + ". " + getname(sortedArr[z]) + " the " + getspecies(sortedArr[z]));
                stateofpet(sortedArr[z]);
            }

            System.out.println("             "); // gap to make visually appealing
            num = general.getint("Pick a pet to apply action: ");
            petnum = num-1;
            //petnum = findloc(petarr, sortedArr[petnum]);
            action = general.getstring("Feed or Cuddle pet: ");

            // if function returns true
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
    }

    public static int[][] statClone (pet[] curr)
    {
        int[][] petStat = new int[5][2];
        for (int i=0; i< curr.length; i++)
        {
            petStat[i][0] = curr[i].hunger;
            petStat[i][1] = curr[i].happiness;
        }
        return petStat;
    }

    public static boolean loseCheck (int[][] prev, pet[] curr)
    {
        for (int z=0; z< curr.length; z++)
        {
            if ((prev[z][0] == 5 && curr[z].hunger == 5) || (prev[z][1] == 5 && curr[z].happiness == 5) )
            {
                return true;
            }
        }

        return false;
    }

    public static pet[] insertionSort (pet[] array)
    {
        //does insertion sort on pet array and returns
        int n = array.length;
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
    }



    public static int getCombinedStats (pet x)
    {
        int num = gethappiness(x) + gethunger(x);
        return num;
    }












    //accessor methods
    public static void setpetspecies (pet p, String txt)
    {
        p.species = txt;
    }
    public static void setpetname (pet p, String txt)
    {
        p.name = txt;
    }
    public static String getname (pet p)
    {
        return p.name;
    }
    public static String getspecies (pet p)
    {
        return p.species;
    }
    public static int gethunger (pet p)
    {
        return p.hunger;
    }
    public static int gethappiness (pet p)
    {
        return p.happiness;
    }
    public static pet randStats (pet record)
    {
        record.hunger = general.getRand(5);
        record.happiness = general.getRand(5);
        return record;
    }
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
    }
    public static boolean feedCuddle (String action, pet record)
    {
        if (action.equals("cuddle")) {
            record.happiness = (gethappiness(record) - general.getRand(3));
            if (gethappiness(record) < 1) {
                record.happiness = 1;
            }
            record.hunger = (gethunger(record) + general.getRand(3));
            if (gethunger(record) > 5) {
                record.hunger = 5;
            }
            return true;
        }

        if (action.equals("feed")) {
            record.hunger = (gethunger(record) - general.getRand(3));
            if (gethunger(record) < 1) {
                record.hunger = 1;
            }
            record.happiness = (gethappiness(record) + general.getRand(3));
            if (gethappiness(record) > 5) {
                record.happiness = 5;
            }
            return true;
        }
        return false;
    }
    public static void setpethappiness (pet p, int newvalue)
    {
        p.happiness = newvalue;
        return;
    }
    public static void setpethunger (pet p, int newvalue)
    {
        p.hunger = newvalue;
        return;
    }
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
    }
    public static pet createpet (String name, String species)
    {
        pet record = new pet();
        setpetname(record, name);
        setpetspecies(record, species);
        randStats(record);
        return record;
    }

}

class pet
{
    String name;
    String species;
    int hunger;
    int happiness;
}
