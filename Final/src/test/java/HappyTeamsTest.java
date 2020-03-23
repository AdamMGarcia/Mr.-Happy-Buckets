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
public class HappyTeamsTest
{
	HappyTeams happy;
	
	@Before //Before anything
    public void initialize()
	{
		happy = new HappyTeams();
		happy.regular();	
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
	public void getTeamSizeTest3()
	{
		try
		{
			//Change the teamSize and check again
			System.out.println("RUNNING getTeamSizeTest3");
			happy.setTeamSize(10);
			int calcInt = happy.getTeamSize();
			int expectedInt = 10;
			assertEquals(expectedInt, calcInt);
		}
		catch (Exception e)
		{
			System.out.println("TEAMSIZE3 NOT WORKING");
		}
	}
	
	@Test
	public void calculateTest1()
	{
		try
		{
			//Change the teamSize and check again
			System.out.println("RUNNING calculateTest1");
			happy.setTeamSize(2);			
			happy.setPreference(1);
			
			String[] a = new String[] {"Nic,2", "Eden", "Ashley","Xander","Gerry","Mya"};
			String[] b = new String[] {"Nic","2","Eden","---","Ashley","---","Xander","---","Gerry","---","Mya","---"};
			String[] c = new String[] {"1","2","3","4","5","6"};
						
			happy.setTempNumberArray(c);
			happy.calculate(a, b);
			
			int is = happy.getTestHappiness();
			int shouldBe = 14;
			assertEquals(shouldBe, is);
		}
		catch (Exception e)
		{
			System.out.println("CALCULATETEST1 NOT WORKING");
		}
	}
	
	@Test
	public void calculateTest2()
	{
		try
		{
			//Change the teamSize and check again
			System.out.println("RUNNING calculateTest2");
			happy.setTeamSize(2);	
			happy.setPreference(1);			
			
			String[] a = new String[] {"Nic,2", "Eden", "Ashley,4","Xander","Gerry,6","Mya"};
			String[] b = new String[] {"Nic","2","Eden","---","Ashley","4","Xander","---","Gerry","6","Mya","---"};
			String[] c = new String[] {"1","2","3","4","5","6"};
			
	
			happy.setTempNumberArray(c);
			happy.calculate(a, b);
			
			int is = happy.getTestHappiness();
			int shouldBe = 18;
			assertEquals(shouldBe, is);
		}
		catch (Exception e)
		{
			System.out.println("CALCULATETEST2 NOT WORKING");
		}
	}
	
	@Test
	public void calculateTest3()
	{
		try
		{
			//Change the teamSize and check again
			System.out.println("RUNNING calculateTest3");
			happy.setTeamSize(3);	
			happy.setPreference(2);			
			
			String[] a = new String[] {"Nic,2,3", "Eden,1,3", "Ashley,1,2","Xander,5,6","Gerry,4,6","Mya,4,5","Liz,8,9","Audrey,7,9","Morgan,7,8"};
			String[] b = new String[] {"Nic","2","3","Eden","1","3","Ashley","1","2","Xander","5","6","Gerry","4","6","Mya","4","5","Liz","8","9","Audrey","7","9","Morgan","7","8"};
			String[] c = new String[] {"1","2","3","4","5","6","7","8","9"};
			
	
			happy.setTempNumberArray(c);
			happy.calculate(a, b);
			
			int is = happy.getTestHappiness();
			int shouldBe = 63;
			assertEquals(shouldBe, is);
		}
		catch (Exception e)
		{
			System.out.println("CALCULATETEST3 NOT WORKING");
		}
	}
	
	@Test
	public void calculateTest4()
	{
		try
		{
				
			//Change the teamSize and check again
			System.out.println("RUNNING calculateTest4");
			happy.setTeamSize(3);	
			happy.setPreference(5);			
			
			String[] a = new String[] {"Bubba,18,13,14,12,4", "Nate,6,18,19,5,1", "Ashley,8,4,16,12,17",
									   "Eden,6,3,15,13,14","Mya,2,1,4,19,16","Nic,4,3,5,2,14",
									   "Xander,1,2,3,4,5","Nathan,19,18,15,13,12","Emma,1,6,2,7,16",
									   "Justin,16,14,1,2,3", "Audrey,10,12,17,1,2", "Elizebeth,1,5,3,6,10",
									   "A,12,17,4,7,1","B,1,2,3","C,5,6,7",
									   "D,8,1,2,3,4","E,19,1,2,10","F,4,6,16,14,9",
									   "G,9,8,7,6,5,4", "Placeholder","Placeholder"};
									   
			String[] b = new String[] {"Bubba","18","13","14","12","4","Nate","6","18","19","5","1","Ashley","8","4","16","12","17",
									   "Eden","6","3","15","13","14","Mya","2","1","4","19","16","Nic","4","3","5","2","14",
									   "Xander","1","2","3","4","5","Nathan","19","18","15","13","12","Emma","1","6","2","7","16",
									   "Justin","16","14","1","2","3", "Audrey","10","12","17","1","2", "Elizebeth","1","5","3","6","10",
									   "A","12","17","4","7","1","B","1","2","3","---","---","C","5","6","7","---","---",
									   "D","8","1","2","3","4","E","19","1","2","10","---","F","4","6","16","14","9",
									   "G","9","8","7","6","5","4", "Placeholder","---","-!-","-!-","-!-","-!-","Placeholder","---","-!-","-!-","-!-","-!-"};
									   
			String[] c = new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21"};
			
	
			happy.setTempNumberArray(c);
			happy.calculate(a, b);
			
			int is = happy.getTestHappiness();
			int shouldBe = 45;
			assertEquals(shouldBe, is);
		}
		catch (Exception e)
		{
			System.out.println("CALCULATETEST4 NOT WORKING");
		}
	}
	
	@Test
	public void testBadAssign()
	{
		try
		{
			String[] a = happy.getNames();
			a[a.length+1] = "a";
		}
		catch (Exception e)
		{
			System.out.println("RUNNING testBadAssign");
		}
		
	}
	
	@Test
	public void testBadCalculatePass()
	{
		try
		{
			happy.setTeamSize(3);	
			happy.setPreference(5);			
			
			String[] a = new String[] {"Bubba,18,13,14,12,4", "Nate,6,18,19,5,1",
									   "Ashley,8,4,16,12,17","Placeholder"};
									   
			String[] b = new String[] {"Bubba","Placeholder","---","-!-","-!-","-!-","-!-",
									   "Placeholder","---","-!-","-!-","-!-","-!-"};		
			happy.calculate(a, b);
			
		}
		catch (Exception e)
		{
			System.out.println("RUNNING testBadCalculatePass");
		}
		
	}
	
	@Test
	public void testBadPreference()
	{
		try
		{
			happy.setTeamSize(3);	
			happy.setPreference(10);			
			
			String[] a = new String[] {"Bubba,18,13,14,12,4", "Nate,6,18,19,5,1", "Ashley,8,4,16,12,17",
									   "Eden,6,3,15,13,14","Mya,2,1,4,19,16","Nic,4,3,5,2,14",
									   "Xander,1,2,3,4,5","Nathan,19,18,15,13,12","Emma,1,6,2,7,16",
									   "Justin,16,14,1,2,3", "Audrey,10,12,17,1,2", "Elizebeth,1,5,3,6,10",
									   "A,12,17,4,7,1","B,1,2,3","C,5,6,7",
									   "D,8,1,2,3,4","E,19,1,2,10","F,4,6,16,14,9",
									   "G,9,8,7,6,5,4", "Placeholder","Placeholder"};
									   
			String[] b = new String[] {"Bubba","18","13","14","12","4","Nate","6","18","19","5","1","Ashley","8","4","16","12","17",
									   "Eden","6","3","15","13","14","Mya","2","1","4","19","16","Nic","4","3","5","2","14",
									   "Xander","1","2","3","4","5","Nathan","19","18","15","13","12","Emma","1","6","2","7","16",
									   "Justin","16","14","1","2","3", "Audrey","10","12","17","1","2", "Elizebeth","1","5","3","6","10",
									   "A","12","17","4","7","1","B","1","2","3","---","---","C","5","6","7","---","---",
									   "D","8","1","2","3","4","E","19","1","2","10","---","F","4","6","16","14","9",
									   "G","9","8","7","6","5","4", "Placeholder","---","-!-","-!-","-!-","-!-","Placeholder","---","-!-","-!-","-!-","-!-"};
									   
			String[] c = new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21"};
			
	
			happy.setTempNumberArray(c);
			happy.calculate(a, b);

		}
		catch (Exception e)
		{
			System.out.println("RUNNING testBadPreference");
		}
	}
	
		@Test
	public void testBadTeamSize()
	{
		try
		{
			happy.setTeamSize(3);	
			happy.setPreference(10);			
			
			String[] a = new String[] {"Bubba,18,13,14,12,4", "Nate,6,18,19,5,1", "Ashley,8,4,16,12,17",
									   "Eden,6,3,15,13,14","Mya,2,1,4,19,16","Nic,4,3,5,2,14",
									   "Xander,1,2,3,4,5","Nathan,19,18,15,13,12","Emma,1,6,2,7,16",
									   "Justin,16,14,1,2,3", "Audrey,10,12,17,1,2", "Elizebeth,1,5,3,6,10",
									   "A,12,17,4,7,1","B,1,2,3","C,5,6,7",
									   "D,8,1,2,3,4","E,19,1,2,10","F,4,6,16,14,9",
									   "G,9,8,7,6,5,4", "Placeholder","Placeholder"};
									   
			String[] b = new String[] {"Bubba","18","13","14","12","4","Nate","6","18","19","5","1","Ashley","8","4","16","12","17",
									   "Eden","6","3","15","13","14","Mya","2","1","4","19","16","Nic","4","3","5","2","14",
									   "Xander","1","2","3","4","5","Nathan","19","18","15","13","12","Emma","1","6","2","7","16",
									   "Justin","16","14","1","2","3", "Audrey","10","12","17","1","2", "Elizebeth","1","5","3","6","10",
									   "A","12","17","4","7","1","B","1","2","3","---","---","C","5","6","7","---","---",
									   "D","8","1","2","3","4","E","19","1","2","10","---","F","4","6","16","14","9",
									   "G","9","8","7","6","5","4", "Placeholder","---","-!-","-!-","-!-","-!-","Placeholder","---","-!-","-!-","-!-","-!-"};
									   
			String[] c = new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21"};
			
	
			happy.setTempNumberArray(c);
			happy.calculate(a, b);

		}
		catch (Exception e)
		{
			System.out.println("RUNNING testBadPreference");
		}
	}

}

