import java.io.*;

public class myTestDict {
	public static void main(String args[]) {
		OrderedDictionary dictionary = new OrderedDictionary();
		Record rec;
		Key key;
		Node<Record> temp;
		
		try {
			// Insert a large number of words in the dictionary
		    BufferedReader in = new BufferedReader(new FileReader("small.txt"));
		    String word = in.readLine();
		    String definition;
		    String line;
		    int type;
		    boolean test10 = true;
		    
		    try {
		    	while(word != null) {
		    		try {
						line = in.readLine();
						definition = line;
						if ((line.endsWith(".gif")) || (line.endsWith(".jpg"))) type = 3;
						else if ((line.endsWith(".wav")) || (line.endsWith(".mid"))) type = 2;
						else type = 1;
						dictionary.insert(new Record(new Key(word,type),definition));
						word = in.readLine();
				    } catch (Exception e) {
				    	test10 = false;
				    }		    		
		    	}
		    	
		    	if (test10) {
				    // Now, try to find the word "practic"
				    rec = dictionary.find(new Key("flower",3));
				    if ((rec.getData()).indexOf("flower.jpg") == -1)
						System.out.println("Test 10 failed");
				    else System.out.println("Test 10 passed");
				}
				else System.out.println("Test 10 failed");		    	
		    	
		    } catch (Exception e) {
				System.out.println("Test 10 failed");
			}
/*		    
		 // Test the predecessor method TEST 14
		    try {
				rec = dictionary.predecessor(new Key("nap",3));				
				if ((rec.getKey().getWord()).compareTo("nap") == 0) 
					System.out.println("Test 14 passed");
				else {
					System.out.println("Test 14 failed");
					System.out.println("\t"+rec.getKey().getWord().toString());
				}
		    }
		    catch (Exception e) {
				System.out.println("Test 14 failed");
		    }
		    
		 // Test removing a word and the using pred TEST 15
		    try {
				dictionary.remove(new Key("homework",1));
				rec = dictionary.predecessor(new Key("nap",3));				
				if ((rec.getKey().getWord()).compareTo("nap") == 0)
					System.out.println("Test 15 passed");
				else {
					System.out.println("Test 15 failed");
					System.out.println("\t" +rec.getKey().getWord().toString());
				}
		    }
		    catch (Exception e) {
				System.out.println("Test 15 Exception: " + e);
		    }
		    	   
/********************************************************************************************/
/*		 // Test the successor method  TEST 20
		    try {
				rec = dictionary.successor(new Key("nap",3));
				if ((rec.getKey().getWord()).compareTo("OhCanada") == 0) {
					System.out.println("Test 20 passed");					
				}
				else System.out.println("Test 20 failed");
		    }
		    catch (Exception e) {
				System.out.println("Test 20 failed");
		    }

		    // Test removing a word and the using successor TEST 21
		    try {
				dictionary.remove(new Key("nap",1));
				//rec = dictionary.find(new Key("nap", 3));				
				rec = dictionary.successor(new Key("lightning",2));
				if ((rec.getKey().getWord()).compareTo("lightning") == 0){				
					System.out.println("Test 21 passed");					
				}
				else {
					System.out.println("Test 21 failed");
					System.out.println("\t Rec: " + rec.getKey().getWord().toString());
				}
		    }
		    catch (Exception e) {
				System.out.println("Test 21 failed" + e);
		    }		    
/***********************************************************************************************/   
		    // test is right child.

		    
		} catch (IOException e) {
		    System.out.println("Cannot open file: small.txt");
		}
	}
}
