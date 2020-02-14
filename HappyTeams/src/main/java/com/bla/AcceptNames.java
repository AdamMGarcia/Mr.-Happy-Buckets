/******************************************
 * Author: ndf17a Nicolas Fornicola
 * Class: Spring 2020 Dr,Reeves CS 374 SE
 * Task: Accept a list of names from a page
 * Due Date: 2/11/2020
 *
 */
package com.bla;

import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;

public class AcceptNames 
{
	// global varriables
	private static int teamSize = 3; // changable
	private static String content;
	private static String[] names;
	private static String path; // changable
	private static int placeholder = 0; // changable
	private static int tempPlaceholder = 0;


	public static void assignTeamSize(int inputSize)
	{
		teamSize = inputSize;
	}

	public static void assignPath(String inputPath)
	{
		path = inputPath;
	}

	public static void assignPlaceholder(int inputPlaceholder)
	{
		placeholder = inputPlaceholder;
	}


	public static String get() 
	{
		//Looks in the file path starting at the HappyTeams, it go to choice then opens grab.txt
        //String path = "choice/grab.txt";
		
        try
		{
			//ACTS AS USER INPUT FOR TEAMSIZE
			//teamSize = 3;
			placeholder = 0;
			tempPlaceholder = 0;

            content = Files.readString(Paths.get(path));
			//this outputs the string with "mvn test" under the T E S T S
			System.out.println("=====================================");
			System.out.println("This is the unaltered text from the file\n");
            System.out.println(content);
			
			//Splits the content into an array "names" where it finds a \n which is endline
			names = content.split("\n");

			//Finds the length of the array, this number starts at 1
			int length = names.length;
			//Find if there is a remainder
			int remainder = length % teamSize;
			//int placeholder = 0;
			if( teamSize-remainder!=teamSize )
				placeholder = teamSize-remainder;
					
			if(length == 1)
			{
				System.out.println("Nice try, minimum team size of 2...");
				return "Why are you doing this";
			}
			System.out.println("\nLength of array: " + length);
			System.out.println("Team size: " + teamSize);
			
			System.out.println("Remainder from length % teamSize : " + remainder);
			System.out.println("Placeholders needed : " + placeholder);
			
			//adding a certain number of spots to length to make a new array with
			//Either 0 or a number needed
			length += placeholder;

			System.out.println("\nLength after adding placeholders: " + length);
			
			//Make the new array with the new length 
			String[] checkedNames = new String[length];
			
			System.out.println("New checkedNames array length: " + checkedNames.length);
			System.out.println("Names in checkedNames array:");
			
			//Length is -1 is this for loop because we need to account for starting at 0
			//Go through and make the array equal up to the length of the old array
			for(int i = 0; i <= names.length-1; i++)
			{
				checkedNames[i] = names[i];
			}
			
			//if we need to assign Placeholder spots to the new array and label them
			if(remainder > 0)
			{
				tempPlaceholder = placeholder; // so that we don't change placeholder
				int i = length-tempPlaceholder;
				while(tempPlaceholder != 0)
				{
					checkedNames[i] = "Placeholder";
					i++;
					tempPlaceholder--;
				}
			}
			
			//Print the new array contents
			for(int i = 0; i <= length-1; i++)
			{
				System.out.println(i + ": "+ checkedNames[i]);
			}
			System.out.println("");
		// 	//This just returns an arbitrary value that doesnt matter right now
			return "";

        } 
		catch (IOException e) {
			String content;
            e.printStackTrace();
			//if nothing is found return an exception wrong
			return content = "Oops, Somthing is wrong";
        }
	}

///////////////////////////////////////////////////////////////
// New Code
///////////////

	public static int viewTeamSize()
	{
		return teamSize;
	}

	public static String viewPath()
	{
		return path;
	}

	public static int viewPlaceholder()
	{
		return placeholder;
	}



}
	