import java.io.*;
import java.util.StringTokenizer;
/**
 * 
 * @author Robert O'Neill
 * 		   250052733
 * Assignment 4 - 
 *
 */
public class UI {	
	public static void main(String args[]) {		
		OrderedDictionary dictionary = new OrderedDictionary();
		String filename = "small.txt";
		
		// checks to make sure arguments were entered correctly.
		if (args.length != 1) {
			System.out.println("Usage: Java UI <filename>");
			System.exit(0);
		}
		filename = args[0];

		// tries to open the file and populate the dictionary.
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			String word = in.readLine();
			String definition, line;
			int type;
			
			try {
				while (word != null) {
					try {
						line = in.readLine();
						definition = line;
						// checks to see what kind of file it is
						if ((line.endsWith(".gif")) || (line.endsWith(".jpg"))) type = 3;
						else if ((line.endsWith(".wav")) || (line.endsWith(".mid"))) type = 2;
						else type = 1;
						// insert into dictionary
						dictionary.insert(new Record(new Key(word.toLowerCase(), type), definition));
						word = in.readLine();						
					} catch (DictionaryException e) {
						System.out.println(e);
					} catch (Exception e) { }
				}
			} catch (Exception e) {
				
			} finally { in.close(); }	
			in.close();
		} catch (IOException e) {
		    System.out.println("Cannot open file: " + filename);		    
		}
		
		// Implement the string reader.
		StringReader reader = new StringReader();
		StringTokenizer st;
		String command = "";			
		String typ = "", word = "", data = "", cmd = "";
		int count = 0;
		boolean restart = false;
		
		while (!command.equals("end")) {		
			// variable resets
			typ = ""; word = ""; data = ""; cmd = "";
			count = 0;
			
			// get command from user.
			command = reader.read("Enter Next Command: ");
			st = new StringTokenizer(command);		
			
			// see how many items user entered and act accordingly.	
			count = st.countTokens();
			if (count == 0) restart = true;
			else {
				for (int i = 0; i < count; i++){
					if (i == 0) cmd = st.nextToken();
					else if (i == 1) word = st.nextToken();
					else if (i == 2) typ = st.nextToken();
					else if (i >= 3) data += st.nextToken().toString() + " ";
				}
			}
			data = data.trim();
			
			// decide what command was entered and take appropriate action.
			if (!restart) {
				switch (cmd.toLowerCase()){
				case "search":
					search(dictionary, word);					
					break;
				case "remove":
					remove(dictionary, word, typ);
					break;
				case "insert":
					insert(dictionary, word, typ, data);
					break;
				case "next":
					next(dictionary, word, typ);
					break;
				case "prev":
					prev(dictionary, word, typ);
					break;
				case "first":
					first(dictionary);
					break;
				case "last":
					last(dictionary);
					break;
				case "end":
					break;
				default :
					System.out.println("Unrecognized command.");
					break;
				}
			}			
		}
		
	} 
	
	/**.
	 * This will run the search command.
	 * @param dictionary - the dictionary to search in.
	 * @param word - The word that is to be searched for.
	 */
	private static void search (OrderedDictionary dictionary, String word){
		Record rec;
		int found = 0;
		// searches for all three item types and displays them if found.
		for (int i = 1; i < 4; i++) {
			rec = dictionary.find(new Key(word, i));
			if (rec != null) {
				if (i == 1) System.out.println(rec.getData().toString());
				else if (i == 2) {
					try {
						SoundPlayer player = new SoundPlayer();
						player.play(rec.getData().toString());
					}catch (MultimediaException e){ System.out.println(e); }
				}
				else if (i == 3) {
					try {
						PictureViewer viewer = new PictureViewer();
						viewer.show(rec.getData().toString());
					}catch (MultimediaException e){ System.out.println(e); }
				}
				found++;
			}
		}
		if (found == 0) System.out.println("Item not in dictionary.");		
	}
	
	/**
	 * This runs the remove command. Removing an item from the dictionary.
	 * @param dictionary - The dictionary to search in. 
	 * @param word - The word to be removed
	 * @param typ - The type of item to be removed.
	 */
	private static void remove(OrderedDictionary dictionary, String word, String typ){
		int type;
		try {
			type = Integer.parseInt(typ);
			// makes sure type is between 1 and 3
			if ((type < 1) || (type > 3)) {
				System.out.println("Type has to be an integet between 1-3");
				return;
			}			
			dictionary.remove(new Key(word, type));
		} catch (NumberFormatException e){ System.out.println("Type has to be an integet between 1-3");
		} catch (DictionaryException e) { System.out.println(e); }
	}
	
	private static void insert(OrderedDictionary dictionary, String word, String typ, String data) {
		int type;		
		try {
			type = Integer.parseInt(typ);					
			if (data.equals("")) {
				System.out.println("No data present.");
				return;
			}
			// makes sure type is between 1 and 3
			if ((type < 1) || (type > 3)) {
				System.out.println("Type has to be an integet between 1-3");
				return;
			}
			// if type 1
			else if (type == 1) {
				if ((data.endsWith(".wav")) || (data.endsWith(".mid")) || (data.endsWith(".gif")) || (data.endsWith(".jpg"))) {
					System.out.println("Data type 1 is not for images or sound files.");
					return;
				}
				else {
					dictionary.insert(new Record(new Key(word, type), data));
				}
			}
			// if type 2
			else if (type == 2) {								
				if ((data.endsWith(".wav")) || (data.endsWith(".mid"))) {
					dictionary.insert(new Record(new Key(word, type), data));
				}
				else {					
					System.out.println("Data type 2 is for sound files.");
					return;
				}
			}
			// if type 3
			else if (type == 3) {
				if ((data.endsWith(".gif")) || (data.endsWith(".jpg"))){
					dictionary.insert(new Record(new Key(word, type), data));
				}
				else {
					System.out.println("Data type 3 is for image files.");
					return;
				}
			}			
		} catch (NumberFormatException e){ System.out.println("Type has to be an integet between 1-3");
		} catch (DictionaryException e)	{ System.out.println(e); }
	}
	
	/**
	 * This will run the next command, finding the next item in the dictionary.
	 * @param dictionary - The dictionary to search in.
	 * @param word - The word to be used to find the next word.
	 * @param typ - The type that goes with the word.
	 */
	private static void next(OrderedDictionary dictionary, String word, String typ) {
		int type;
		Record rec;
		try {
			type = Integer.parseInt(typ);
			// makes sure type is between 1 and 3
			if ((type < 1) || (type > 3)) {
				System.out.println("Type has to be an integet between 1-3");
				return;
			}
			rec = dictionary.successor(new Key(word, type));
			if (rec == null) {
				System.out.println("There is no item after this one.");
				return;
			}
			else System.out.println("(" + rec.getKey().getWord().toString() + ", " + rec.getKey().getType() + ")");
			
		} catch (NumberFormatException e){ System.out.println("Type has to be an integet between 1-3"); }
	}
	
	/**
	 * This will run the previous command, finding the previous item in the dictionary.
	 * @param dictionary - The dictionary to search in.
	 * @param word - The word to be used to find the previous word.
	 * @param typ - The type that goes with the word.
	 */
	private static void prev(OrderedDictionary dictionary, String word, String typ) {
		int type;
		Record rec;
		try {
			type = Integer.parseInt(typ);
			// makes sure type is between 1 and 3
			if ((type < 1) || (type > 3)) {
				System.out.println("Type has to be an integet between 1-3");
				return;
			}
			rec = dictionary.predecessor(new Key(word, type));
			if (rec == null) {
				System.out.println("There is no item before this one.");
				return;
			}
			else System.out.println("(" + rec.getKey().getWord().toString() + ", " + rec.getKey().getType() + ")");
			
		} catch (NumberFormatException e){ System.out.println("Type has to be an integet between 1-3"); }
	}
	
	/**
	 * This will run the first command, finding the smallest item in the dictionary.
	 * @param dictionary - The dictionary to search in.
	 */
	private static void first(OrderedDictionary dictionary){
		Record rec = dictionary.smallest();
		System.out.println("(" + rec.getKey().getWord().toString() + ", " + rec.getKey().getType() + ")");
	}
	
	/**
	 * This will run the last command, finding the largest item in the dictionary.
	 * @param dictionary - The dictionary to search in.
	 */
	private static void last(OrderedDictionary dictionary) {
		Record rec = dictionary.largest();
		System.out.println("(" + rec.getKey().getWord().toString() + ", " + rec.getKey().getType() + ")");
	}
}
