/******************************************
 * Author: ndf17a Nicolas Fornicola
 * Class: Spring 2020 Dr,Reeves CS 374 SE
 * Task: Makes happy teams based on preferences
 * Due Date: Who knows at this point
 *
 * 
 */

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SortTeamsTest
{
	SortTeams happy;
	
	@Before //Before anything
    public void initialize()
	{
		happy = new SortTeams();
		happy.reset();	
		happy.test = 1;
    }
	
	//The testCheckNames cannot change test files, if they change test files it will fail
	//If TeamSize is change it could also fail, this passes because atleast one placeholder is needed
	@Test
	public void testCheckedNames1()
	{
		try
		{	
			System.out.println("RUNNING testCheckedNames1");
			String content = Files.readString(Paths.get("choice/test2.txt"));
			String splitNames[] = content.split("\n");
			String checkedNames[] = happy.get(splitNames);
			
			assertEquals("Bubba,2,3,6,5,4", checkedNames[0]);
			assertEquals("Adam,2,3,6,4", checkedNames[checkedNames.length/2]);
			assertEquals("Placeholder", checkedNames[checkedNames.length-1]);
		}
		catch (Exception e)
		{
			System.out.println("TESTCHECKEDNAMES1 NOT WORKING");
		}
	}
	
	//The testCheckNames cannot change test files, if they change test files it will fail
	//If TeamSize is change it could also fail, this passes because atleast one placeholder is needed
	@Test
	public void testCheckedNames2()
	{
		try
		{
			System.out.println("RUNNING testCheckedNames2");
			String content = Files.readString(Paths.get("choice/test3.txt"));
			String splitNames[] = content.split("\n");
			String checkedNames[] = happy.get(splitNames);
			
			assertEquals("Bubba,2,3", checkedNames[0]);
			assertEquals("Adam,2,3,6,5,4", checkedNames[checkedNames.length/2]);
			assertEquals("Placeholder", checkedNames[checkedNames.length-1]);
		}
		catch (Exception e)
		{
			System.out.println("TESTCHECKEDNAMES2 NOT WORKING");
		}
	}
	
	
	//Shuffles the tempPrio array and tempNames array then checks to see if they are different from their originals
	@Test
	public void testShuffle1()
	{
		try
		{
			
			System.out.println("RUNNING testShuffle1");
			
			happy.setPreference(2);
			String[] a = new String[] {"Nic","1","2","Eden","1","2","Ashley","1","2","Xander","1","2","Gerry","1","2","Mya","6","4"};
			String[] b = new String[] {"Nic,1,2", "Eden,1,2", "Ashley,1,2","Xander,2,3","Gerry,2,3","Mya,6,4"};
			happy.setPrio(a);
			happy.setCheckedNames(b);
			
			happy.shuffle();
			
			String tempPrio[] = happy.getTempPrio();
			String tempNames[] = happy.getTempNames();
			//This wants to be NOT equal, so the Arrays.equals need to return false.
			assertNotEquals(Arrays.equals(a, tempPrio), true);
			assertNotEquals(Arrays.equals(b, tempNames), true);
			
		}
		catch (Exception e)
		{
			System.out.println("TESTSHUFFLE1 NOT WORKING");
		}
	}
	
	
	
	@Test
	public void testShuffle2()
	{
		try
		{
			
			System.out.println("RUNNING testShuffle2");
			
			happy.setPreference(3);
			String[] a = new String[] {"Nic","1","2","2","Eden","1","2","2","Ashley","1","2","2","Xander","1","2","2","Gerry","1","2","2","Mya","6","2","4"};
			String[] b = new String[] {"Nic,1,2,2", "Eden,1,2,2", "Ashley,1,2,2","Xander,2,3,2","Gerry,2,2,3","Mya,6,2,4"};
			happy.setPrio(a);
			happy.setCheckedNames(b);
			
			happy.shuffle();
			
			String tempPrio[] = happy.getTempPrio();
			String tempNames[] = happy.getTempNames();
			//This wants to be NOT equal, so the Arrays.equals need to return false.
			assertNotEquals(Arrays.equals(a, tempPrio), true);
			assertNotEquals(Arrays.equals(b, tempNames), true);
			
		}
		catch (Exception e)
		{
			System.out.println("TESTSHUFFLE2 NOT WORKING");
		}
	}
	
	@Test
	public void testShuffle3()
	{
		try
		{
			System.out.println("RUNNING testShuffle3");
			happy.setPreference(3);
			String[] a = new String[] {"Nic","1","2","2","Eden","1","2","2","Ashley","1","2","2","Xander","1","2","2","Gerry","1","2","2","Mya","6","2","4"};
			String[] b = new String[] {"Nic,1,2,2", "Eden,1,2,2", "Ashley,1,2,2","Xander,2,3,2","Gerry,2,2,3","Mya,6,2,4"};
			happy.setPrio(a);
			happy.setCheckedNames(b);
			
			happy.shuffle();
			
			String tempPrio[] = happy.getTempPrio();
			String tempNames[] = happy.getTempNames();
			//This wants to be NOT equal, so the Arrays.equals need to return false.
			assertNotEquals(Arrays.equals(a, tempPrio), true);
			assertNotEquals(Arrays.equals(b, tempNames), true);
			
		}
		catch (Exception e)
		{
			System.out.println("TESTSHUFFLE3 NOT WORKING");
		}
	}
	
	//NOT GETTING THE RIGTH AMOUNT OF PLACEHOLDERS!!!!
	//Dont change the test file, if it is changed the test may fail 
	@Test
	public void getPlaceholder1()
	{
		try
		{
			System.out.println("RUNNING getPlaceholder1");
			
			happy.setTeamSize(3);
			String content = Files.readString(Paths.get("choice/test3.txt"));
			String splitNames[] = content.split("\n");
			String checkedNames[] = happy.get(splitNames);		
			
			int calcInt = happy.getPlaceholder();
			int expectedInt = 1;
			assertEquals(expectedInt, calcInt);
		}
		catch (Exception e)
		{
			System.out.println("getPLACEHOLDER1 NOT WORKING");
		}
	}
	
	//Dont change the test file, if it is changed the test may fail 
	@Test
	public void getPlaceholder2()
	{
		try
		{
			System.out.println("RUNNING getPlaceholder2");
			happy.setTeamSize(5);
			String content = Files.readString(Paths.get("choice/test2.txt"));
			String splitNames[] = content.split("\n");
			String checkedNames[] = happy.get(splitNames);
			
			

			int calcInt = happy.getPlaceholder();
			int expectedInt = 3;
			assertEquals(expectedInt, calcInt);
		}
		catch (Exception e)
		{
			System.out.println("getPLACEHOLDER2 NOT WORKING");
		}
	}
	
	//Dont change the test file, if it is changed the test may fail 
	@Test
	public void getPlaceholder3()
	{
		try
		{
			System.out.println("RUNNING getPlaceholder3");
			happy.setTeamSize(4);
			String content = Files.readString(Paths.get("choice/test3.txt"));
			String splitNames[] = content.split("\n");
			String checkedNames[] = happy.get(splitNames);

			int calcInt = happy.getPlaceholder();
			int expectedInt = 0;
			assertEquals(expectedInt, calcInt);
		}
		catch (Exception e)
		{
			System.out.println("getPLACEHOLDER3 NOT WORKING");
		}
	}
	
	//Dont change the test file, if it is changed the test may fail 
	@Test
	public void getPlaceholder4()
	{
		try
		{
			System.out.println("RUNNING getPlaceholder4");
			happy.setTeamSize(5);
			String content = Files.readString(Paths.get("choice/test3.txt"));
			String splitNames[] = content.split("\n");
			String checkedNames[] = happy.get(splitNames);

			int calcInt = happy.getPlaceholder();
			int expectedInt = 2;
			assertEquals(expectedInt, calcInt);
		}
		catch (Exception e)
		{
			System.out.println("getPLACEHOLDER4 NOT WORKING");
		}
	}
	
	@Test
	public void getTeamSizeTest1()
	{
		try
		{
			//Checks default teamSize from the initialization
			System.out.println("RUNNING getTeamSizeTest1");
			int calcInt = happy.getTeamSize();
			int expectedInt = 3;
			assertEquals(expectedInt, calcInt);
		}
		catch (Exception e)
		{
			System.out.println("TEAMSIZE1 NOT WORKING");
		}
	}
	
	@Test
	public void getTeamSizeTest2()
	{
		try
		{
			//Change the teamSize and check again
			System.out.println("RUNNING getTeamSizeTest2");
			happy.setTeamSize(5);
			int calcInt = happy.getTeamSize();
			int expectedInt = 5;
			assertEquals(expectedInt, calcInt);
		}
		catch (Exception e)
		{
			System.out.println("TEAMSIZE2 NOT WORKING");
		}
	}
	
	@Test
	public void ztestSeeTeamsPlease()
	{
		try
		{
			System.out.println("RUNNING zseeTeamsPlease\n");
			String[] sendNames = new String[] {"Nic","1","2","Eden","1","2","Ashley","1","2",
											   "Xander","2","3","Gerry","2","3","Mya","6","4",
											   "Austin","6","4","Josh","6","4","Will","6","4",
											   "Blee","1","2","Bla","1","2","Blue","1","2"};
			happy.setPreference(2);
			happy.setPrio(sendNames);
			happy.seeTeamsPlease();
		}
		catch (Exception e)
		{
			System.out.println("zSEETEAMSPLEASE NOT WORKING");
		}
	}
	/*
	@Test
	public void testCalculate()
	{
		try
		{
			//Change the teamSize and check again
			System.out.println("RUNNING testCalculate");
			String checkedNames[] = happy.get("choice/test3.txt");
			String prio[] = happy.getPrio(checkedNames);
			
			happy.calculate(checkedNames, prio);
			//assertEquals(expectedInt, calcInt);
		}
		catch (Exception e)
		{
			System.out.println("TESTCALCULATE NOT WORKING");
		}
	}
	*/
}

