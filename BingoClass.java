// HomeWork Assignment #2
// Nazareth Diaz 
// 5/31/18
// This is a bingo game.
// This game is played between you(the user)
// and the computer. Two cards are generated in the 
// beginning of the game and the user can quit any time. 

import java.util.Scanner;

public class BingoClass {

static final int MAX_BINGO_NUM = 75;
static final int MIN_BINGO_NUM = 1;
static final int BINGO_COL_RANGE = 15;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		int [][] bingoNums1;
		int [][] bingoNums2;
		boolean [][] bingoMatches1;
		boolean [][] bingoMatches2;
		String bingoCard = "Your Bingo Card: ";
		
		// allocate memory 
		bingoNums1 = new int[5][5];
		bingoNums2 = new int [5] [5];
		bingoMatches1 = new boolean [5][5];
		bingoMatches2 = new boolean [5][5];
		
		// call static method fillBingoNums 
		bingoNums1 = fillBingoNums(bingoNums1);
		
		bingoNums2 = fillBingoNums(bingoNums2);
		
		// STEP 3 call displaybingonums 
		displayBingoNums(bingoCard, bingoNums1);
		
		System.out.println();
		System.out.println();
		
		// STEP 4 call display 
		displayBingoNums("Computer's Bingo card: ", bingoNums2);
		
		// STEP 5 call bingomatches
		initBingoMatches(bingoMatches1);
		
		// Step 6 call bingomatches for second array 
		initBingoMatches(bingoMatches2);
		
		// Step 7 calling playBingo 
		playBingo(bingoNums1, bingoNums2, bingoMatches1, bingoMatches2);
		
		

	}

	public static int [][] fillBingoNums(int bingoOne[][])
	{
		int min = 1, max = 15;
		int temp;
		
		
		
		
	
		for ( int i = 0; i < bingoOne.length; i++) 
		
		{
			
			int randArray [];
			randArray = new int[5];
			
			for (int k = 0; k < bingoOne[i].length; k++)
			{
				temp = (int)(Math.random() * (max-min + 1)) + min;
			
				for(int l = 0; l <= k;)
					
				{
				
					if ( temp != randArray[l] )
					{	
						l++;
					}
					else
						temp = (int)(Math.random() * (max-min + 1)) + min;
				}
				
				randArray[k] = temp;
			}
				
				for (int j=0; j < bingoOne[i].length; j++) 
				{
					bingoOne[i][j] = randArray[j];
					if (bingoOne[i][j] == bingoOne[2][2])
						bingoOne[2][2] = -1;
				}
					
			
				
			min += 15;
			max += 15;
					
			}
			
			
			
					
						
							
					
		return bingoOne;		
	}


public static void displayBingoNums(String card, int bingo1 [][] )
{
	System.out.println(card);
	System.out.println("B" + "\t" + "I" + "\t" + "N" + "\t" + "G" + "\t" + "O");
	
	for (int i = 0; i < bingo1[0].length; i++)
		{
			
		
			for(int j =0; j < bingo1.length; j++)
				{
				if (bingo1[i][j] == -1)
					System.out.print("FREE" + "\t");
				else
				System.out.print(bingo1[j][i] + " \t");
				
				
				}
			System.out.println();
		}
}

public static boolean [][] initBingoMatches(boolean matches[][])
{

	for(int i=0; i < matches.length ; i++)
		for(int j = 0; j< matches[i].length; j++)
		{
			if (matches[i][j] == matches[2][2])
				matches [2][2] = true;
			else
				matches[i][j] = false;
			
		}
	return matches;
}

public static void playBingo( int bingo1[][], int bingo2[][], boolean matches1[][], boolean matches2[][])
{
	
	Scanner s = new Scanner(System.in);
	char answer;
	
	
	
		
		boolean [] bingoArray = new boolean [76];
		
		
	do {
		
	
	
		System.out.println("The Bingo game will continue until "
			+ "someone wins or you enter 'N'.");
	// this is JUST to get a new random number 
		// for bingo card that has not already been used 
	int num = getBingoNumber(bingoArray);
	displayBingoNum(num);
			
	 
	if (findMatch(bingo1, matches1, num) )
		// here we check if player1 finds a match in their array, returns boolean 
		// and updates corresponding bingomatches boolean array 
		{
			System.out.println("You matched the number!");
			
			//call checkWin
			
			
			 int [] winArray1 = checkWin(bingo1,matches1);
			 if (winArray1 != null)
			 {
		          System.out.println("You Won!");
					for (int i = 0; i < winArray1.length; i++)
					{
						System.out.println(winArray1[i]);
					}
			 }
		}
			
		
	// check for comp 
	if (findMatch(bingo2, matches2, num)) 
	{
		System.out.println("The computer matched the number.");
		
		int [] winArray2 = checkWin(bingo2,matches2);
		 if (winArray2 != null)
		 {
	          System.out.println("The computer won!");
				for (int i = 0; i < winArray2.length; i++)
				{
					System.out.println(winArray2[i]);
				}
		 }
	}
	
	
	
		System.out.println("Keep playing? (N for no)");
		 answer = s.next().charAt(0);
		 
	
	
	}while (answer != 'N' && answer != 'n');
  
	s.close();
	
	
		
	
	
	
	
	
	
	
	
	

	
	
}





//MAX_BINGO_NUM , MIN_BINGO_NUM are static final vars assigned 1 & 75
//bingoNumbers will have all false before the loop in the game method

public static int getBingoNumber(boolean[] bingoNumbers)
	{
		int currentNum=0;
		// here we are getting BINGO number generated from
		// computer to then keep on going with matches 
		do {
			currentNum = (int)(Math.random()*MAX_BINGO_NUM) + MIN_BINGO_NUM;
		}while( bingoNumbers[currentNum] == true );
		bingoNumbers[currentNum] = true;
		
		// here we are generating rand # 1-75, assigning
		// the bingoArray with matched number to true 
		//this array is a 1 day array of 76 elements 
		return currentNum;
	}
	
// BINGO_COL_RANGE is a static final var. assigned 15
public static void displayBingoNum(int currentNum)
{
			if( currentNum > 0 && currentNum <= MAX_BINGO_NUM ){
				// calculate the "column" based on the currentNum
				int column = (currentNum-1)/BINGO_COL_RANGE;
				// get the column letter at that column
				char columnLetter = "BINGO".charAt(column);

				System.out.print(""+columnLetter +" " + currentNum + "  ");
			}else if( currentNum == 0) // should never get here
						System.out.print("No Number");
					else { // must be -1
						System.out.print("FREE ");
			}// else
}// end displayBingoNum

public static boolean findMatch(int bingoNums [][], boolean matches [][], int number)
{
	if (number >= 1 && number <= 15)
		// check first row 
	{
		for (int i = 0; i < bingoNums[0].length; i++ )
		{
			if (bingoNums[0][i] == number)
			{
				matches[0][i] = true;
				System.out.println("Found a match at 0" + "[" + i + "]");
				return true;
			}
				
		}
	}
	else if (number >= 16 && number <= 30)
	{
		for (int i = 0; i < bingoNums[1].length; i++ )
		{
			if (bingoNums[1][i] == number)
			{
				matches[1][i] = true;
				System.out.println("Found a match at 1" + "[" + i +"]");
				return true;
			}
		}
	}
	else if (number >= 31 && number <= 45)
	{
		for (int i = 0; i < bingoNums[2].length; i++ )
		{
			if (bingoNums[2][i] == number)
			{
				matches[2][i] = true;
				System.out.println("Found a match at 2" + "[" + i + "]");
				return true;
			}
		}
	}
	else if ( number >= 46 && number <= 60)
	{
		for (int i = 0; i < bingoNums[3].length; i++ )
		{
			if (bingoNums[3][i] == number)
			{
				matches[3][i] = true;
				System.out.println("Found a match at [3]" + "[" + i + "]");
				return true;
			}
		}
	}
	else if (number >= 61 && number <= 75)
	{
		for (int i = 0; i < bingoNums[4].length; i++ )
		{
			if (bingoNums[4][i] == number)
			{
				matches[4][i] = true;
				System.out.println("Found a match at [4]" + "[" + i + "]");
				return true;
			}
		}
	}
	return false;
	
		
}

public static int[] checkWin(int[][] bingoNums, boolean[][] bingoMatches)
{
	int[] winningNums = null;
	boolean allTrue;

	// check rows (bingo "columns")
	for(int i=0; i < bingoMatches.length; ++i )
	{
		allTrue = true;
		for(int j = 0; j < bingoMatches[i].length; ++j )
		{
			if( !bingoMatches[i][j] )
			{
				allTrue = false;
				break;
			}
		}// end for j
		if( allTrue )
		{ // matched whole row?
			winningNums= new int[bingoMatches.length];
			for( int j=0; j < winningNums.length; ++j )
				{
					winningNums[j] = bingoNums[i][j];
				}// end for
			return winningNums;
		}// end if
	}// end for i

// YOU WRITE THE CODE TO CHECK THE columns ("bingo" rows)
	for(int i = 0; i < bingoMatches.length ; i++)
	{
		allTrue = true;
		for (int j = 0 ; j < bingoMatches[i].length ; j++)
		{
			
			if(!bingoMatches[j][i])
			{
				allTrue = false;
				break;
			}
		}
			if (allTrue)
			{
				winningNums = new int[bingoMatches.length];
				for (int j = 0; j < winningNums.length; j++)
				{
					winningNums[j] = bingoNums[j][i];
				}
				return winningNums;
			}
		}
	
	

// YOU WRITE THE CODE TO CHECK THE right-diagonal
	
	
		for (int j = 0, i = 0 ; j < bingoMatches.length ; j++, i++)
		{
			allTrue = true;
			
			if(!bingoMatches[j][i])
			{
				allTrue = false;
				break;
			}
		
			if (allTrue)
			{
				winningNums = new int[bingoMatches.length];
				for (int k = 0; k < winningNums.length; k++)
				{
					winningNums[k] = bingoNums[j][i];
				}
				return winningNums;
			}
		}
	

// YOU WRITE THE CODE TO CHECK THE left-diagonal
		for (int j = 0, i = 4 ; j < bingoMatches.length ; j++, i--)
		{
			allTrue = true;
			
			if(!bingoMatches[j][i])
			{
				allTrue = false;
				break;
			}
		
			if (allTrue)
			{
				winningNums = new int[bingoMatches.length];
				for (int k = 0; k < winningNums.length; k++)
				{
					winningNums[k] = bingoNums[j][i];
				}
				return winningNums;
			}
		}
		
		return winningNums;



}



}

