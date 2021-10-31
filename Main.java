//Author: Hugo Lapa
//31/10/2021
import java.util.Scanner;
import java.util.Random;
class Game {
    public static void main (String[] args)
    {
        String petname;
        String petspecies;

        pet[] petarr = new pet[5];
        general.print("(maximum of 5)");
        int petQuant = general.getint("Quantity of pets: ");
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
        boolean valid = true;
        while (end == false)
        {
            for (int z = 0; z < petQuant; z++)
            {
                general.print(" ");
                general.print(z+1 + ". " + getname(petarr[z]) + " the " + getspecies(petarr[z]));
                stateofpet(petarr[z]);
            }
            general.print(" ");
            num = general.getint("Pick a pet to apply action: ");
            petnum = num - 1;
            action = general.getstring("Feed or Cuddle pet: ");
            valid = feedCuddle(action, petarr[petnum]);
            if (valid == true)
            {
                for (int i = 0; i < petQuant;i++)
                {
                    if (petarr[i] != petarr[petnum])
                    {
                        regressStats(petarr[i]);
                    }
                }
                count += 1;
            }
            else
            {
                general.print("Sorry your response wasnt understood! Please try again...");
            }

        }

        System.exit(0);
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
        setpethunger(record, general.getRand(5));
        setpethappiness(record, general.getRand(5));
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
            setpethappiness(record, (gethappiness(record) - general.getRand(3)));
            if (gethappiness(record) < 1) {
                setpethappiness(record, 1);
            }
            setpethunger(record, (gethunger(record) + general.getRand(3)));
            if (gethunger(record) > 5) {
                setpethunger(record, 5);
            }
            return true;
        }

        if (action.equals("feed")) {
            setpethunger(record, (gethunger(record) - general.getRand(3)));
            if (gethunger(record) < 1) {
                setpethunger(record, 1);
            }
            setpethappiness(record, (gethappiness(record) + general.getRand(3)));
            if (gethappiness(record) > 5) {
                setpethappiness(record, 5);
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
        setpethunger(record, newhunger);
        setpethappiness(record, newhappiness);
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
