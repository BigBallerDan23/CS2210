import java.util.ArrayList;
import java.util.List;
public class TestOutcome {	
	public static void main(String[] args){
	    char[] symbol = new char[2];
	    symbol[0] = 'O';
	    symbol[1] = 'X';
	    
	    // Here are the test cases and the expected results
	    String cases1[] = {"XOOOXOOXX", "Win X"};
	    String cases2[] = {"XXOOOXOX ", "Win O"};
	    String cases3[] = {" XOXXOOOX", "Draw O"};
	    String cases4[] = {"OXOXOOXO ", "Draw X"};
	    String cases5[] = {"XOOOOXXXXX XOXXX", "Draw O"};
	    String cases6[] = {"OXOXOXOXOXOOOOXXO OXOOOOX","Draw X"};
	    String cases7[] = {"XOOOXOXXXOXOXOXOXOXXXOXO ", "Win X"};
	    String cases8[] = {"XOOOXOXXXOXOXOX OOXXXXXXX", "Win X"};
	    
	    // these are the tests to be run
	    List<String[]> tests = new ArrayList<String[]>();
	    for (int i = 0; i < 8; i++){
	    	tests.add(i, null);
	    }
	    tests.set(0 ,cases1);
	    tests.set(1 ,cases2);
	    tests.set(2 ,cases3);
	    tests.set(3 ,cases4);
	    tests.set(4 ,cases5);
	    tests.set(5 ,cases6);
	    tests.set(6 ,cases7);
	    tests.set(7 ,cases8);
	    
	    System.out.println("Ready to begin testing.");
	    
	    for (int t = 0; t < tests.size(); t++){
	    	String testCase[] = tests.get(t); 
	    	int size = (int) Math.sqrt(testCase[0].length());
	    	Achi game = new Achi(size, size);
	    
	    	// Put the config into the board.
	    	int spot = 0;
	    	for (int i = 0; i < size; i++){
	    		for (int j = 0; j < size; j++){
	    			game.storePlay(i, j, testCase[0].charAt(spot));
	    			spot++;
	    		}
	    	}	    	
	    	System.out.println("Testing for test case: " + (t + 1) + ": " + testCase[1]);
	    	
	    	// Draw the board that will be used
    
	    	spot = 0;
	    	for (int i = 0; i < size; i++){
		    	for (int j = 0; j < size; j++){
		    		System.out.print(testCase[0].charAt(spot) + "  ");
		    		spot++;
		    	}
		    	System.out.println();
		    }
    
	    	
	    	//Test for winners then for draw.
	    	// Test for Winner. Player, then Computer.
	    	if ((game.wins('X') && (testCase[1] == "Win X"))){	    		
	    		System.out.println("Test " + (t + 1) + ": Passed");
	    	}
	    	else if ((game.wins('O') && (testCase[1] == "Win O"))){	    		
	    		System.out.println("Test " + (t + 1) + ": Passed");
	    	}
	    	else if ((game.isDraw('X') && (testCase[1] == "Draw X"))) {	    		
	    		System.out.println("Test: " + (t + 1) + " Passed");
	    	}
	    	else if ((game.isDraw('O') && (testCase[1] == "Draw O"))) {	    		
	    		System.out.println("Test: " + (t + 1) + " Passed");
	    	}
	    	else {	    		
	    		System.out.println("Test: " + (t + 1) + " Failed");
	    	}
	    }	    
	} 
}
