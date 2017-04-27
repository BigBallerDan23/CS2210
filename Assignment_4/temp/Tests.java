import java.util.ArrayList;
import java.util.List;
public class Tests {
	public static void main(String args[]) {
		boolean failed = false;
		OrderedDictionary dict = new OrderedDictionary();
		// Create array of keys
		List<Key> keys = new ArrayList<Key>();
		// make the keys		
		keys.add(new Key("element", 2));
		keys.add(new Key("computer", 3));
		keys.add(new Key("computer", 1));
		keys.add(new Key("temp", 2));
		keys.add(new Key("computer", 2));		
		keys.add(new Key("temp", 1));		
		keys.add(new Key("temp", 3));
		keys.add(new Key("element", 1));		
		keys.add(new Key("element", 3));
		
		// Create array of records
		List <Record> records = new ArrayList<Record>();
		for (int i = 0; i < keys.size(); i++) {
			if (keys.get(i).getType() == 1) {				
				records.add(new Record(keys.get(i), (keys.get(i).getWord().toString() + " is awesome.")));
			}
			else if (keys.get(i).getType() == 2) {
				records.add(new Record(keys.get(i), (keys.get(i).getWord().toString() + ".mp3")));
			}
			else {				
				records.add(new Record(keys.get(i), (keys.get(i).getWord().toString() + ".jpg")));
			}
		}
		
		System.out.println("Running Insertion Test...");
		// Insert them all into the dictionary.
		for (int i = 0; i < records.size(); i++){			
			try {
				dict.insert(records.get(i));				
			} 
			catch (DictionaryException e){
				System.out.println(e);
				failed = true;
			}
		}
		// Results of Insert Test.
		if (!failed) System.out.println("\tPASSED all insertions.");
		else System.out.println("Insertion test FAILED");
		System.out.println("Insertion Test Finished.");
		System.out.println("........................");
		
		// Find elements in the tree.
		failed = false;
		System.out.println("Running Find Test.");
		
		for (int i = 0; i < records.size(); i++){
			Record rec = dict.find(records.get(i).getKey());			
			if (rec == null) {
				System.out.println("Test Failed...... Key not present.");
			}
			else {
				Key k = rec.getKey();
				String d = rec.getData();
				if ((k.compareTo(records.get(i).getKey()) == 0) && (d.equals(records.get(i).getData()))) {}
				else {
					failed = true;
				}
			}
		}
		
		// Results of Find test.
		if (!failed) System.out.println("\tPASSED all finds.");
		else System.out.println("Find test FAILED");
		System.out.println("Find Test Finished.");
		System.out.println("........................");
		
		// Testing the Smallest method.
		failed = false;
		System.out.println("Testing the smallest method.");		
		Record small = dict.smallest();
		if (small == null) {
			failed = true;
		}
		System.out.println("Smallest is: " + small.getData().toString());
		if (failed) System.out.println("\tSmallest FAILED.");
		else System.out.println("\tSmallest Passed.");
		System.out.println("Smallest Test Finished.");
		System.out.println("........................");
		
		
		// Testing the Largest method.
		failed = false;
		System.out.println("Testing the smallest method.");		
		Record large = dict.largest();
		if (large == null) {
			failed = true;
		}
		System.out.println("Largest is: " + large.getData().toString());
		if (failed) System.out.println("\tLargest FAILED.");
		else System.out.println("\tLargest Passed.");
		System.out.println("Largest Test Finished.");
		System.out.println("........................");
		
		
		// Testing Predecessor
		failed = false;
		System.out.println("Testing the Predecessor method.");
		Record pre = dict.predecessor(keys.get(3));		 // temp, 2 = temp.mp3
		if (pre == null) {
			System.out.println("Has no Predecessor.");
			failed = true;
		}			
		if (failed) System.out.println("\tPredecessor FAILED.");
		else if (pre.getData().equals("temp is awesome")) System.out.println("\tPredecessor Passed.");
		else System.out.println("\tPredecessor Passed.");
		System.out.println("Predecessor Test Finished.");
		System.out.println("........................");
		
		// Testing Successor.
		failed = false;
		System.out.println("Testing the Successor method.");
		pre = dict.successor(keys.get(1));		 // computer, 3 = temp.jpg  --> Suc == Element, 1
		if (pre == null) {
			System.out.println("Has no Successor.");
			failed = true;
		}		
		if (failed) System.out.println("\tSuccessor FAILED.");
		else if (pre.getData().equals("element is awesome.")) System.out.println("\tSuccessor Passed.");
		else System.out.println("\tSuccessor Failed.");
		System.out.println("Successor Test Finished.");
		System.out.println("........................");
		
		
	}
}
