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

public class HappyTeams 
{
	// global variables
	private static int teamSize; // changeable
	private static int preference; // changeable
	private static double decline; // changeable
	private static int LoopTimes = 10;
	private static int howManyShuffles = 1000;
	private static String content;
	private static String str = "";
	private static String[] names;
	private static String[] checkedNames;
	private static String[] highestNames;
	private static String[] prio;
	private static String[] tempNames;
	private static String[] tempPrio;
	private static String[] con;
	private static String[] numberArray;
	private static String[] tempnumberArray;
	private static String[] highestnumberArray;
	private static String[][] pastNames;
	private static int[] highestHappinessPerPerson;
	private static int[] happinessPerPersonBig;
	private static int[] happinessPerTeam;
	private static int[] highestHappinessPerTeam;
	private static int placeholder = 0;
	private static int tempPlaceholder = 0;
	private static int highestHappiness = 0;
	private static int Happiness = 0;
	private static int testHappiness = 0;
	private static int verbosity = 0;
	public static int test = 0;
	public static int iteration = 0;
	public static String[] Empty = new String[0];

	//setters
	public static void setCheckedNames(String[] input) { checkedNames = input.clone(); }
	public static void setHighestNames(String[] input) { highestNames = input.clone(); }
	public static void setTempNames(String[] input) { tempNames = input.clone(); }
	public static void setTempNumberArray(String[] input) { tempnumberArray = input.clone(); }
	public static void setPrio(String[] input) { prio = input.clone(); }
	public static void setTempPrio(String input[]) {tempPrio = input.clone();}
	public static void setStr(String input) { str = input; }
	public static void setTeamSize(int input) { teamSize = input; }
	public static void setPreference(int input) { preference = input+1; }
	public static void setDecline(double input) { decline = input/100.0; }
	public static void setPlaceholder(int input) { placeholder = input; }
	public static void setLoopTimes(int input) { LoopTimes = input; }
	//					number of swap to attempt
	public static void setHowManyShuffles(int input) { howManyShuffles = input; }
	//					number of times to perform N swaps
	public static void setTempPlaceholder(int input) { tempPlaceholder = input; }
	public static void setHighestHappiness(int input) { Happiness = input; }
	public static void setHappiness(int input) { Happiness = input; }
	public static void setTestHappiness(int input) { testHappiness = input; }
	public static void setContent(String input) { content = input; }
	public static void setVerbosity(int input) { verbosity = input; }

	
	//getters
	
	public static int getTeamSize() { return teamSize; }
	public static int getTestHappiness() { return testHappiness; }
	public static int getVerbosity() { return verbosity; }
	public static int getPlaceholder() { return placeholder; }
	public static int getLoopTimes() { return LoopTimes; }
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
	public static String[] getHighestNames() { return highestNames; }

	
	public static void regular() 
	{
		//This is a default looking set of instructions
		setTeamSize(3);
		setPreference(6); //This is 6 because there are 6 preferences + 1 name = 7
		setDecline(2);
		setPlaceholder(0);
		setLoopTimes(4);
		setHowManyShuffles(500);
		setTempPlaceholder(0);
		setHappiness(0);
		setTestHappiness(0);
		setContent("");
		test = 0;
		
	}
	
	public static void reset() 
	{
		//reset the happinesses so we can try and find another branch for a higher outcome
		setTestHappiness(0); 
		setHappiness(0); 
		setContent("");
		iteration = 0;
		checkedNames = get(Empty);
		prio = getPrio(checkedNames);
		numberArray = new String[checkedNames.length];
		
		//This fills numberArray with 1,2,3,4... which are Strings to compare to priority in calculate
		for(int i = 0; i < numberArray.length; i++)
		{
			String stringTemp = String.valueOf(i+1);
			numberArray[i] = stringTemp;
		}
		
	}

	//Verbosity key:
	//v = 0: Peak Total Hapinessand all the teams at the end
	//v = 1: Adds Shuffling List and Shuffling Over indicaters,
	//		 When a new peak is found showing the new highestHappiness
	//		 When the shuffle resets "Starting over" resetting happiness and such
	// 		 Shows command line args from user input
	//v = 2: Can now see the iterations
	// 		 See when the getPrio and checkNames are reset to txt file 
	//v = 3: Happiness each Iteration,
	//		 See when an Array equals a past shuffle so it go again the same iteration
	//		 See when each function is called
	//v = 4: Everytime there is a calculate for equality between a priority and team member
	//		 Outputs the new peak happiness team config
	
	//javac  src/main/java/HappyTeams.java
	//java -cp src/main/java HappyTeams -t 3 -v 2 -n 1 -l 1000 -r 4 -p 6 < choice/testFinal.txt
	public static void main (String[] args)
	{
		if(args.length > 0)
		{
			for( int i = 0; i < args.length; i++)
			{
				if(args[i].equals("-t"))
				{	
					//TeamSize
					setTeamSize(Integer.parseInt(args[i+1]));
					if(getVerbosity() == 1 || getVerbosity() == 2 || getVerbosity() == 3 || getVerbosity() == 4) 
						System.out.println("TeamSize: " + getTeamSize());
				}
				if(args[i].equals("-v"))
				{
					//Verbosity
					setVerbosity(Integer.parseInt(args[i+1]));
					if(getVerbosity() == 1 || getVerbosity() == 2 || getVerbosity() == 3 || getVerbosity() == 4) 
						System.out.println("Verbosity: " + getVerbosity());
				}
				if(args[i].equals("-n"))
				{
					//LoopTimes
					setLoopTimes(Integer.parseInt(args[i+1]));
					if(getVerbosity() == 1 || getVerbosity() == 2 || getVerbosity() == 3 || getVerbosity() == 4) 
						System.out.println("LoopTimes: " + getLoopTimes());
				}
				if(args[i].equals("-l"))
				{
					//HowManyShuffles
					setHowManyShuffles(Integer.parseInt(args[i+1]));
					if(getVerbosity() == 1 || getVerbosity() == 2 || getVerbosity() == 3 || getVerbosity() == 4) 
						System.out.println("HowManyShuffles: " + getHowManyShuffles());
				}
				if(args[i].equals("-r"))
				{
					//Decline percentage
					setDecline(Integer.parseInt(args[i+1]));
					if(getVerbosity() == 1 || getVerbosity() == 2 || getVerbosity() == 3 || getVerbosity() == 4) 
						System.out.println("Decline: " + getDecline());
				}
				if(args[i].equals("-p"))
				{
					//Preferece
					setPreference(Integer.parseInt(args[i+1]));
					if(getVerbosity() == 1 || getVerbosity() == 2 || getVerbosity() == 3 || getVerbosity() == 4) 	
						System.out.println("Preferences: " + (getPreference()-1)+"\n");
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
		
		//Call get and getPrio and reset numberArray to be in Order
		checkedNames = get(Empty);
		prio = getPrio(checkedNames);
		numberArray = new String[checkedNames.length];
		
		for(int i = 0; i < numberArray.length; i++)
		{
			String stringTemp = String.valueOf(i+1);
			numberArray[i] = stringTemp;
		}
		
		//Set the Size of all the highest variables
		highestnumberArray = new String[checkedNames.length];
		highestNames = new String[prio.length];
		highestHappinessPerPerson = new int[checkedNames.length];
		highestHappinessPerTeam = new int[checkedNames.length/teamSize];
		pastNames = new String[howManyShuffles][checkedNames.length];
		
		if( getVerbosity() == 1 || getVerbosity() == 2 || getVerbosity() == 3 || getVerbosity() == 4) 
		{	
			System.out.println("\n------------------");
			System.out.println("- Shuffling List -");
			System.out.println("------------------\n");
		}
		
		for(int n = 0; n < LoopTimes; n++)
		{
			//Inbetween each big loop reset the Happinesses and reset the arrays to their original text files
			reset();
			
			if(getVerbosity() == 1 || getVerbosity() == 2 || getVerbosity() == 3 || getVerbosity() == 4) 
				System.out.println("\nStarting over - Local Happiness Reset \n");
			
			for(int i = 0; i < howManyShuffles; i++)
			{
				
				//Shuffle these suckers
				shuffle();
				
				//Find the Happiness of the groups
				//Temps are set inside of shuffle
				calculate(tempNames, tempPrio);
				
				//Find if testHappiness or Happiness is better and change stuff, make it worse "decline" % of times
				compare();
				
				if(getVerbosity() == 2 || getVerbosity() == 3 || getVerbosity() == 4) 
					System.out.printf("[" + i + "]");
				if(getVerbosity() == 3 || getVerbosity() == 4) 
					System.out.println(" Happiness: " + Happiness);
				if(getVerbosity() == 2)
					System.out.println("");		
			}
		}	
		
		if( getVerbosity() == 2 || getVerbosity() == 3 || getVerbosity() == 4) 
		{	
			System.out.println("\n-----------------");
			System.out.println("- Shuffling Over -");
			System.out.println("-----------------");
		}
			
		System.out.println("");
		System.out.println("Peak Total Happiness: " + highestHappiness+ "\n");
		
		//Outputs a nice looking list of the best teams
		seeTeamsPlease(highestNames);

	}

	//Gets the names and sorts into teamSize number add placeholders if needed
	public static String[] get(String[] testNames) 
	{		
			if(getVerbosity() == 3 || getVerbosity() == 4) 
				System.out.println("      called fn: get(String[] testNames)");
			if(getVerbosity() == 2 || getVerbosity() == 3 || getVerbosity() == 4) 
				System.out.println("\nName Array set to Original Txt file");
			
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
		if(getVerbosity() == 3 || getVerbosity() == 4) 
				System.out.println("      called fn: getPrio(String[] names)");
		if(getVerbosity() == 2 || getVerbosity() == 3 || getVerbosity() == 4) 
			System.out.println("Preferece Array set to Original Txt file");
		
		//make an array to old names and prio seperatly
		prio = new String[names.length * preference];
		int start = 0;
		
		for(int i = 0; i < names.length; i++)
		{
			//Splice at ","
			String[] spliced = names[i].split(",");

			//get from spliced at 0 to prio at start spliced.length amount of things
			System.arraycopy(spliced, 0, prio, start, spliced.length);
			start += preference;		
		}
		
		//Fill the dont care preferences for all names
		for(int i = 0; i < prio.length; i++)
			if(prio[i] == null)
				prio[i] = "---";
			
		//If there are placeholders make them only have 1 dont care. The rest should not register in calculate because it will be extreamly inflated
		for(int i = 0; i < prio.length; i++)
		{	if(prio[i] == "Placeholder")
				for(int p = 2; p < preference; p++)
					prio[p+i] = "-!-";
				
		}

		return prio;
	}
	
	//Shuffle the decks
	public static void shuffle()
	{
		if(getVerbosity() == 3 || getVerbosity() == 4) 
				System.out.println("      called fn: void shuffle()");
			
		
		//checkNames length is 0 find where it is iniitualized
		tempNames = new String[checkedNames.length];
		tempPrio = new String[prio.length];
		tempnumberArray = new String[checkedNames.length];
			
		if(test == 0)	
		{	
			//make temps from originals
			for(int i = 0; i < prio.length; i++)
				tempPrio[i] = prio[i];
			for(int i = 0; i < tempNames.length; i++)
				tempNames[i] = checkedNames[i];
			for(int i = 0; i < tempNames.length; i++)
				tempnumberArray[i] = numberArray[i];
		}
		
		//used if an array equal a past iteration of array so we know if we should shuffle again in the same iteration
		boolean moveOn = false;
		//this is for if there arent any other possible shuffles just break the loop
		int toManyReDo = 0;
		
		while(moveOn == false)
		{
			Random rand = new Random(); 
			
			// Generate random integers in range 0 to length of either array 
			int switchA = rand.nextInt(checkedNames.length-1); 
			int switchB = rand.nextInt(checkedNames.length-1);
			
			//If swithcA and switch B are equal go until they are different
			while(switchA == switchB)
				switchB = rand.nextInt(checkedNames.length-1); 
			
			//This is for swapping the prefercne lists and to keep prio and name list parallel
			int switchP = switchA * preference;
			int switchQ = switchB * preference;
			
			String nameSwap = "";
			String prioSwap = "";
			
			//Switch 
			nameSwap = tempNames[switchA];
			tempNames[switchA] = tempNames[switchB];
			tempNames[switchB] = nameSwap;
			
			//Switch
			nameSwap = tempnumberArray[switchA];
			tempnumberArray[switchA] = tempnumberArray[switchB];
			tempnumberArray[switchB] = nameSwap;
			
			//swap the preference index in deckPrio
			//check to see if this works
			for(int i = 0; i < preference; i++)
			{
				prioSwap = tempPrio[switchP+i];
				tempPrio[switchP+i] = tempPrio[switchQ+i];
				tempPrio[switchQ+i] = prioSwap;
			}

			if(test == 0)
			{
				//Keep adding to the pastNames Iteration each time we make a shuffle
				for(int i = 0; i < tempNames.length; i++)
				{	
					pastNames[iteration][i] = tempNames[i];
					
				}
				
				//If the shuffle matches any previous shuffle
				//it will shuffle again for this iteration
				for(int i = 0; i < iteration-1; i++)
					if(Arrays.equals(tempNames, pastNames[i]))
					{
						toManyReDo++;
						if(getVerbosity() == 3 || getVerbosity() == 4)
							System.out.println("      Arr1 == Arr2 shuffling again this turn.\n");
						moveOn = false;
						break;
					}
					else
					{
						moveOn = true;
					}
					
					//Dont do this if there are only just begun the search
					if(iteration < 2)
						moveOn = true;
			}
			if(test == 1)
				moveOn = true;
			
			//If toManyRedo has switched arrays more then 100 times already just go ahead
			if(toManyReDo > 100)
				moveOn = true;
		}
		
		//After each valid shuffle add to iteration
		iteration++;
	}
	
	public static void calculate(String[] listNames, String[] listPrio)
	{	
		if(getVerbosity() == 3 || getVerbosity() == 4) 
				System.out.println("      called fn: void calculate(String[]...)");
			
		//Used for organizing certain teams and priorities inside the calculation for loops
		String[] smallListOfPrio = new String[preference];
		String[] smallListOfNumbers = new String[teamSize];
		int[] happinessPerPersonSmall = new int[listNames.length/teamSize];
		happinessPerPersonBig = new int[listNames.length];
		happinessPerTeam = new int[listNames.length/teamSize];
		
		//Reset the comparison arrays so we dont add upon an existing number 
		Arrays.fill(happinessPerPersonBig, 0);
		Arrays.fill(happinessPerTeam, 0);
		Arrays.fill(happinessPerPersonSmall, 0);
	
		int countUp = 0;
		for(int n = 0; n < listNames.length/teamSize; n++)
		{
			//Makes smallListOfNumbers like 1,2,3
			
			System.arraycopy(tempnumberArray, teamSize*n, smallListOfNumbers, 0, teamSize);
			for(int j = 0; j < teamSize; j++)
			{
				
				//Makes smallListOfPrio Nic,2,3						
				System.arraycopy(listPrio, preference*countUp, smallListOfPrio, 0, preference);
				for(int i = 0; i < teamSize; i++)
				{	
					for(int k = 0; k < preference; k++)
					{
						if(getVerbosity() == 4) 
						{		
							System.out.printf(smallListOfNumbers[i] + " == " + (smallListOfPrio[k]));
							if(smallListOfNumbers[i].equals(smallListOfPrio[k]) || "---".equals(smallListOfPrio[k]))
								System.out.println(" -- TRUE... ADDED HAPPINESS");
							else 
								System.out.println("");
						}	
						
						//if somones preference matches whos on their team add happiness bassed on where the preference
						if(smallListOfNumbers[i].equals(smallListOfPrio[k]))
							if(k == 1)
								happinessPerPersonSmall[j] += 4;
							else if(k == 2)
								happinessPerPersonSmall[j] += 3;
							else if(k == 3)
								happinessPerPersonSmall[j] += 2;
							else if(k > 3)
								happinessPerPersonSmall[j] += 1;
						
						if("---".equals(smallListOfPrio[k]) )
							happinessPerPersonSmall[j] += 1;
					}
				}
				countUp++;
			}
			//Used for resetting things and making the happinessSmall put into happinessPerPersonBig
			System.arraycopy(happinessPerPersonSmall, 0, happinessPerPersonBig, teamSize*n, teamSize);
			Arrays.fill(happinessPerPersonSmall, 0);
		}
		
		//Finds happiness per team 
		int i = 0;
		for(int p = 0; p < happinessPerTeam.length; p++)
			for (int j = 0; j < teamSize; j++)
			{	
				happinessPerTeam[p] += happinessPerPersonBig[i]; 
				i++;
			}

		//Finds total up happiness
		int tot = 0;
		for(int z = 0; z < happinessPerTeam.length; z++)
		{
			tot += happinessPerTeam[z];
		}
		
		testHappiness = tot;
		

	}	
	
	public static Random r = new Random();
	
	public static void compare()
	{
		if(getVerbosity() == 3 || getVerbosity() == 4) 
				System.out.println("      called fn: void compare()");
		
		float chance = r.nextFloat();
		
		//the tests are better 
		if(testHappiness > Happiness)
		{
			//make the originals equal to the test
			prio = tempPrio.clone();
			checkedNames = tempNames.clone();
			numberArray = tempnumberArray.clone();
			Happiness = testHappiness;
		}
		else if(chance < decline)
		{
			//if the test is worse BUT the antiHillClimbing is met then change originals anyway
			prio = tempPrio.clone();
			checkedNames = tempNames.clone();
			numberArray = tempnumberArray.clone();
			Happiness = testHappiness;
		}
		
		if(Happiness > highestHappiness)
		{	
			//if a higher total happiness is found make store the highestones
			highestHappinessPerTeam = happinessPerTeam.clone();
			highestHappinessPerPerson = happinessPerPersonBig.clone();
			highestHappiness = Happiness;
			highestNames = prio.clone();
			highestnumberArray = numberArray.clone();
			
			
			if( getVerbosity() == 1 || getVerbosity() == 2 || getVerbosity() == 3 || getVerbosity() == 4)
				System.out.println("\nNew Peak Found at Happiness: [" + highestHappiness + "]");
			if(getVerbosity() == 3 || getVerbosity() == 4)
				seeTeamsPlease(highestNames);
		}
		
		
	}
	
	
	
	public static void seeTeamsPlease(String[] input)
	{
		if(getVerbosity() == 3 || getVerbosity() == 4) 
				System.out.println("      called fn: void seeTeamsPlease(String[] input)\n");
		
		int k = 0;
		String outTeams = "";
		String[] outputTeams = new String[input.length/preference/teamSize];
		String[] outNames = new String[input.length/preference];
		
		//Takes out all the preferences and extra numbers out put only names in outNames
		Arrays.fill(outputTeams, "");
		for(int i = 0; i < input.length; i++)
			if(i%preference == 0)
			{
				outNames[k] = input[i];
				k++;
			}
			
		//Organize the Names into their teams 
		int i = 0;	
		for(int p = 0; p < outputTeams.length; p++)
			for(int j = 0; j < teamSize; j++)
			{
				outputTeams[p] += outNames[i] + " (" + highestHappinessPerPerson[i] + ")"; 
				if(j+1 == teamSize)
					outputTeams[p] += " ";
				else 
					outputTeams[p] += ", ";

				i++;
			}
		
		//output the teams with total happiness team happiness and per person happiness
		for(int q = 0; q < outputTeams.length; q++)
		{	
			System.out.printf("Team " + (q+1) + " (" + highestHappinessPerTeam[q] + "): "); 
			System.out.println(outputTeams[q] + "\n");
		}
	}
}

