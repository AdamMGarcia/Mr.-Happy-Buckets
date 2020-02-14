/******************************************
 * Author: ndf17a Nicolas Fornicola
 * Class: Spring 2020 Dr,Reeves CS 374 SE
 * Task: Accepting a file string test
 * Due Date: 1/24/2020
 *
 * Test all the functions inside of Calculator.java will throw exceptions if test fail
 */
package com.bla;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.*;


public class AcceptNamesTest
{
	AcceptNames accept;
	
	@Before //Before anything, create a new Calculator so we can call its functions
    public void initialize()
	{
		accept = new AcceptNames();
		accept.assignTeamSize(3);
		accept.assignPath("choice/grab.txt");
		accept.get();
    }


	/////////////////////////////////////////////////////////////////////////////
	// tests
	////////////
	@Test
	public void viewTeamSizeTest()
	{
		try
		{
			System.out.println("RUNNING viewTeamSizeTest");
			int calcInt = accept.viewTeamSize();
			int expectedInt = 3;
			assertEquals(expectedInt, calcInt);
		}
		catch (Exception e)
		{
			System.out.println("TEAMSIZE NOT WORKING");
		}
	}


	@Test
	public void viewPathTest()
	{
		try
		{
			System.out.println("RUNNING viewPathTest");
			String calcString = accept.viewPath();
			String expectedString = "choice/grab.txt";
			assertEquals(expectedString, calcString);
		}
		catch (Exception e)
		{
			System.out.println("PATH NOT WORKING");
		}
	}


	@Test
	public void viewPlaceholderTest()
	{
		try
		{
			System.out.println("RUNNING viewPlaceholderTest");
			int calcInt = accept.viewPlaceholder();
			int expectedInt = 1;
			assertEquals(expectedInt, calcInt);
		}
		catch (Exception e)
		{
			System.out.println("PLACEHOLDER NOT WORKING");
		}
	}


	@Test
	public void newTestConditions()
	{
		try
		{
			System.out.println("RUNNING newTestConditions");
			accept.assignTeamSize(4);
			accept.assignPath("choice/SSTeams.txt");
			accept.get();

			int calcInt = accept.viewPlaceholder();
			int expectedInt = 0;
			assertEquals(expectedInt, calcInt);
		}
		catch (Exception e)
		{
			System.out.println("NEWTESTCONDITIONS NOT WORKING");
		}
	}



	/////////////////////////////////////////////////////////////////////////////

	
 //    @Test
	// public void sameContents()
	// {
	// 	try
	// 	{
	// 		String was = accept.get();
	// 		assertEquals(was, was);
	// 	}
	// 	catch (Exception e)
	// 	{
	// 		System.out.println("Not found or somthing");
	// 	}
	// }
}

