/******************************************
 * Author: ndf1preferencea Nicolas Fornicola
 * Class: Spring 2020 Dr,Reeves CS 3preference4 SE
 * Task: Accept a list of names from a .txt and do stuff with it
 * Due Date: I dont know dude
 *
 */

import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random; 

public class SortTeams 
{
	// global variables
	private static int teamSize; // changeable
	private static int preference; // changeable
	private static double decline; // changeable
	private static String content;
	private static String str = "";
	private static String[] names;
	private static String[] checkedNames;
	private static String[] prio;
	private static String[] tempNames;
	private static String[] tempPrio;
	private static String[] con;
	private static int[] happinessPerPersonBig;
	private static int[] happinessPerTeam;
	private static int placeholder = 0;
	private static int tempPlaceholder = 0;
	private static int Happiness = 0;
	private static int average = 0;
	private static int testHappiness = 0;
	private static int testAverage = 0;
	private static int shuffleTimes = 10;
	private static int howManyShuffles = 1000;
	private static int verbosity = 0;
	public static int test = 0;
	public static String[] Empty = new String[0];

	//setters
	public static void setCheckedNames(String[] input) { checkedNames = input.clone(); }
	public static void setTempNames(String[] input) { tempNames = input.clone(); }
	public static void setPrio(String[] input) { prio = input.clone(); }
	public static void setTempPrio(String input[]) {tempPrio = input.clone();}
	public static void setStr(String input) { str = input; }
	public static void setTeamSize(int input) { teamSize = input; }
	public static void setPreference(int input) { preference = input+1; }
	public static void setDecline(double input) { decline = input/100.0; }
	public static void setPlaceholder(int input) { placeholder = input; }
	public static void setShuffleTimes(int input) { shuffleTimes = input; }
	//					number of swap to attempt
	public static void setHowManyShuffles(int input) { howManyShuffles = input; }
	//					number of times to perform N swaps
	public static void setTempPlaceholder(int input) { tempPlaceholder = input; }
	public static void setHappiness(int input) { Happiness = input; }
	public static void setTestHappiness(int input) { testHappiness = input; }
	public static void setAverage(int input) { average = input; }
	public static void setTestAverage(int input) { testAverage = input; }
	public static void setContent(String input) { content = input; }
	public static void setVerbosity(int input) { verbosity = input; }

	
	//getters
	public static int getTeamSize() { return teamSize; }
	public static int getVerbosity() { return verbosity; }
	public static int getPlaceholder() { return placeholder; }
	public static int getShuffleTimes() { return shuffleTimes; }
	public static int getHowManyShuffles() { return howManyShuffles; }
	public static int getPreference() { return preference; }
	public static double getDecline() { return decline; }
	public static String getStr() { return str; }
	public static String[] getNames() { return names; }
	public static String[] gesmallListOfNumbersNames() { return checkedNames; }
	public static String[] gesmallListOfPrio() { return prio; }
	public static String[] getTempNames() { return tempNames; }
	public static String[] getTempPrio() { return tempPrio; }
	public static String[] getCon() { return con; }
	
	public static void reset() 
	{
		setTeamSize(3);
		//This is 6 because there are 6 preferences + 1 name = 7
		setPreference(6);
		setDecline(2);
		setPlaceholder(0); 
		setShuffleTimes(10);
		setHowManyShuffles(1000);
		setTempPlaceholder(0); 
		setHappiness(0); 
		setTestHappiness(0); 
		setAverage(0);
		setTestAverage(0);
		setContent("");
		test = 0;
		
	}
	
	//javac  src/main/java/sortTeams.java
	//java -cp src/main/java SortTeams -t 3 -v 4 -n 1 -l 1 -r 4 -p 6 < choice/test2.txt
	public static void main (String[] args)
	{
		if(args.length > 0)
		{
			for( int i = 0; i < args.length; i++)
			{
				if(args[i].equals("-t"))
				{	
					setTeamSize(Integer.parseInt(args[i+1]));
					System.out.println(getTeamSize());
				}
				if(args[i].equals("-v"))
				{
					setVerbosity(Integer.parseInt(args[i+1]));
					System.out.println(getVerbosity());
				}
				if(args[i].equals("-n"))
				{
					setShuffleTimes(Integer.parseInt(args[i+1]));
					System.out.println(getShuffleTimes());
				}
				if(args[i].equals("-l"))
				{
					setHowManyShuffles(Integer.parseInt(args[i+1]));
					System.out.println(getHowManyShuffles());
				}
				if(args[i].equals("-r"))
				{
					setDecline(Integer.parseInt(args[i+1]));
					System.out.println(getDecline());
				}
				if(args[i].equals("-p"))
				{
					setPreference(Integer.parseInt(args[i+1]));
					System.out.println(getPreference()-1);
				}
			}
		}
		
		if(test == 0)
		{
			Scanner scanner = new Scanner(System.in);
			while(scanner.hasNext())
			{
				setStr(getStr() + scanner.next() + " ");
			}
		}
		
		//get the first two arrays sorted from the file
		checkedNames = get(Empty);
		prio = getPrio(checkedNames);
		
		for(int n = 0; n < shuffleTimes; n++)
			for(int i = 0; i < howManyShuffles; i++)
			{
				
				//Shuffle these suckers
				shuffle();
				
				//Find the Happiness of the groups
				calculate(tempNames, tempPrio);
				
				//Find if testHappiness or Happiness is better and change stuff, make it worse "decline" % of times
				compare();
				
			System.out.println("[" + i + "]" + " Hap: " + Happiness);
			}
			
	}

	//Gets the names and sorts into teamSize number add placeholders if needed
	public static String[] get(String[] testNames) 
	{		
			setPlaceholder(0);
			tempPlaceholder = 0;
			
			//This is for testing 
			//if test == 0 do the regular thing if the test == 1 then it makes names = to what ever thing we pass it from the test file
			if(test == 0)
				names = getStr().split(" ");
			else 
				names = testNames.clone();
				
			//Finds the length of the array, this number starts at 1
			int length = names.length;
			//Find if there is a remainder
			int remainder = length % teamSize;
			if( teamSize-remainder!=teamSize )
				setPlaceholder(teamSize-remainder);
			
			if(length == 1)
			{
				System.out.println("Nice try, minimum team size of 2...");
				return names;
			}
			
			//adding a certain number of spots to length to make a new array with
			//Either 0 or a number needed
			length += placeholder;
			
			//Make the new array with the new length 
			String[] checkedNames = new String[length];
			
			//Go through and make the array equal up to the length of the old array
			for(int i = 0; i < names.length; i++)
			{
				checkedNames[i] = names[i];
			}
			
			//if we need to set Placeholder spots to the new array and label them
			if(remainder > 0)
			{
				tempPlaceholder = getPlaceholder(); // so that we don't change placeholder
				int i = length-tempPlaceholder;
				while(tempPlaceholder != 0)
				{
					checkedNames[i] = "Placeholder";
					i++;
					tempPlaceholder--;
				}
			}
			
			return checkedNames;	
	}

	//This function splices more things and puts each priority and name in a seperate index it fills spots with no preference with NP and handles placeholders
	public static String[] getPrio(String[] names)
	{
		prio = new String[names.length * preference];
		int start = 0;
		
		for(int i = 0; i < names.length; i++)
		{
			String[] spliced = names[i].split(",");

			System.arraycopy(spliced, 0, prio, start, spliced.length);
			start += preference;		
		}
		
		for(int i = 0; i < prio.length; i++)
			if(prio[i] == null)
				prio[i] = "---";

		return prio;
	}
	
	//Shuffle the decks
	public static void shuffle()
	{
		
		//checkNames length is 0 find where it is iniitualized
		tempNames = new String[checkedNames.length];
		tempPrio = new String[prio.length];
		
		
		for(int i = 0; i < prio.length; i++)
			tempPrio[i] = prio[i];
		
		for(int i = 0; i < tempNames.length; i++)
			tempNames[i] = checkedNames[i];
		
		Random rand = new Random(); 
		
		// Generate random integers in range 0 to length of either array 
		int switchA = rand.nextInt(checkedNames.length-1); 
		int switchB = rand.nextInt(checkedNames.length-1);
		
		while(switchA == switchB)
			switchB = rand.nextInt(checkedNames.length-1); 
		
		int switchP = switchA * preference;
		int switchQ = switchB * preference;
		
		String nameSwap = "";
		String prioSwap = "";
		
		//switch the two
		//maybe check if the thing is a placeholder if it is find somthing else to swap
		nameSwap = tempNames[switchA];
		tempNames[switchA] = tempNames[switchB];
		tempNames[switchB] = nameSwap;
	
		//swap the preference index in deckPrio
		//check to see if this works
		for(int i = 0; i < preference; i++)
		{
			prioSwap = tempPrio[switchP+i];
			tempPrio[switchP+i] = tempPrio[switchQ+i];
			tempPrio[switchQ+i] = prioSwap;
		}
	}
	
	public static Random r = new Random();
	
	public static void compare()
	{
		
		float chance = r.nextFloat();
		
		//what should we do if the Happiness is the same? go with the change or do nothing?
		if(Happiness < testHappiness)
		{
			//Adam code
			prio = tempPrio.clone();
			checkedNames = tempNames.clone();
			Happiness = testHappiness;
		}
		else if(chance < decline)
		{
			//Adam code
			//prio = tempPrio.clone();
			//checkedNames = tempNames.clone();
			//Happiness = testHappiness;
		}
	}
	
	public static void calculate(String[] listNames, String[] listPrio)
	{
		String[] smallListOfPrio = new String[preference];
		String[] smallListOfNumbers = new String[listNames.length/teamSize];
		String[] numberArray = new String[listNames.length];
		int[] happinessPerPersonSmall = new int[listNames.length/teamSize];
		happinessPerPersonBig = new int[listNames.length];
		happinessPerTeam = new int[listNames.length/teamSize];
		Arrays.fill(happinessPerTeam, 0);
		Arrays.fill(happinessPerPersonSmall, 0);
		Arrays.fill(happinessPerPersonBig, 0);
		Arrays.fill(happinessPerTeam, 0);
		
		for(int i = 0; i < numberArray.length; i++)
		{
			String stringTemp = String.valueOf(i+1);
			numberArray[i] = stringTemp;
		}
		
		for(int n = 0; n < listNames.length/teamSize; n++)//3
		{
			System.arraycopy(numberArray, teamSize*n, smallListOfNumbers, 0, teamSize);
			for(int j = 0; j < teamSize; j++)
			{
				System.arraycopy(listPrio, preference*j, smallListOfPrio, 0, preference);
				for(int i = 0; i < teamSize; i++)
					for(int k = 0; k < preference; k++)
					{ 
						if(smallListOfNumbers[i].equals(smallListOfPrio[k]))
							if(k == 1)
								happinessPerPersonSmall[j] += 4;
							else if(k == 2)
								happinessPerPersonSmall[j] += 3;
							else if(k == 3)
								happinessPerPersonSmall[j] += 2;
							else 
								happinessPerPersonSmall[j] += 1;
						
						if("---".equals(smallListOfPrio[k]))
							if(k == 1)
								happinessPerPersonSmall[j] += 1;
					}
			}
			System.arraycopy(happinessPerPersonSmall, 0, happinessPerPersonBig, teamSize*n, teamSize);
		}
		
		//Finds happiness per team 
		int i = 0;
		for(int p = 0; p < happinessPerTeam.length; p++)
			for (int j = 0; j < teamSize; j++)
			{	
				happinessPerTeam[p] += happinessPerPersonBig[i]; 
				i++;
			}

		int tot = 0;
		for(int z = 0; z < happinessPerTeam.length; z++)
		{
			tot += happinessPerTeam[z];
		}
		testHappiness = tot;
	}	
	
	public static void seeTeamsPlease()
	{
		//This prints the names organized by Teams of teamSize
		con = new String[prio.length/teamSize/teamSize];
		String [] namesOnly = new String[prio.length/getPreference()];
		
		int p = 0, x = 0, q = 0;
		
		for(int i = 0; i < prio.length; i++)
			if(i%teamSize == 0)
			{	
				namesOnly[q] = prio[i];
				q++;
			}
			
		for(int k = 0; k < con.length; k++)
		{	
			String put = "";
			for(int i = 0; i < teamSize; i++)
				put += namesOnly[i+x] + " ";			
			
			con[p] = put;
	
			p++;
			x+=teamSize;
		}

		for(int i = 0; i < con.length; i++)
		{
			System.out.println("======= Team" + (i+1) + "=======" );
			System.out.println(con[i]);
		}
		System.out.println("");
	}
}
	