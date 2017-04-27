
public class FindCo {
	public static void main(String[] args) {
	    //Dictionary dict = new Dictionary(9887);	    	    
	    int i,j;	    
	    
	    // Test 7: insert 10000 different values into the Dictionary
	    for (int k = 10000; k < 50000; k++){
	    	int collisions = 0;
		    String s = "";
	    	Dictionary dict = new Dictionary(9887, k);
	    	try {	    		
	    		for (i = 0; i < 10000; ++i) {
	    			s = (new Integer(i)).toString();
	    			for (j = 0; j < 5; ++j) s += s;
	    			collisions += dict.insert(new ConfigData(s,i));
	    		}
	    	} catch (DictionaryException e) { }
	    	if (collisions < 1000) {	    		
	    		System.out.println("Collisions: " + collisions + "\t Empty: " + dict.getEmpty() + 
	    				" \t Coefficient: " + k);
	    	}
	    }
	    System.out.println("Done");
	}
}
