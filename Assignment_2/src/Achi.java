/**
 * 
 * @author Robert O'Neill
 * 		   250052733	
 * 		   CS 2210 - Assignment 2 - Achi class that will insert new moves into the dictionary. 
 * 					 It will also evaluate the board and determine if there is a winner or a draw.
 */
public class Achi {
	// variable declarations
	private final static int HASH_SIZE = 9973;
	private char[][] gameBoard;
	
	/**
	 * Constructor for Achi, Takes size and depth parameters
	 * @param board_size - The size of the game board to be created.
	 * @param max_levels - The max levels the algorithm with traverse.
	 */
	public Achi (int board_size, int max_levels){
		gameBoard = new char[board_size][board_size];
		// creates an empty game board of provided size.
		for (int i = 0; i < board_size; i++){
			for (int j = 0; j < board_size; j++){
				gameBoard[i][j] = ' ';
			}
		}
	}
	
	/**
	 * Creates a new dictionary.
	 * @return - Returns an empty dictionary of size HASH_SIZE;
	 */
	public Dictionary createDictionary(){
		Dictionary dict = new Dictionary(HASH_SIZE);
		return dict;
	}
	
	/**
	 * Tries to find the current configuration in the dictionary and returns its score.
	 * @param configurations - The board configuration to look for in the dictionary.
	 * @return - Returns the value returned from the find function.
	 */
	public int repeatedConfig(Dictionary configurations){
		int score = 0;
		String config = getBoardConfig();
		score = configurations.find(config);
		return score;
	}
	
	/**
	 * Inserts the supplied board configuration into the dictionary.
	 * @param configurations - The board configuration to be inserted.
	 * @param score - The score associated with the supplied configuration.
	 * @throws DictionaryException - Exception thrown due to error with the dictionary.
	 */
	public void insertConfig(Dictionary configurations, int score) {	
		String config = getBoardConfig();
		ConfigData newItem = new ConfigData(config, score);
		try {
			configurations.insert(newItem);
		} catch (DictionaryException e) {
			System.out.println("Error: insertConfig() Dictionary Exception"); 			
		}
	}
	
	/**
	 * Puts the current move onto the game board.
	 * @param row - The row the move is to be inserted into.
	 * @param col - The column the move is to be inserted into.
	 * @param symbol - The symbol to be inserted. X or O.
	 */
	public void storePlay(int row, int col, char symbol){
		gameBoard[row][col] = symbol;
	}
	
	/**
	 * Checks if the tile at the provided coordinates is empty.
	 * @param row - The row to be checked.
	 * @param column - The column to be checked.
	 * @return - Returns true/false.
	 */
	public boolean tileIsEmpty(int row, int column){
		if (gameBoard[row][column] == ' ') {
			return true;
		}
		else return false;
	}
	
	/**
	 * Checks if the tile at the provided coordinates is the computers.
	 * @param row - The row to be checked.
	 * @param column - The column to be checked.
	 * @return - Returns true/false.
	 */
	public boolean tileIsComputer(int row, int column){
		// checks to see if the row/column is out of bounds.
		if ((row < 0) || (row >= gameBoard.length) || (column < 0) || (column >= gameBoard.length)){
			return false;
		}
		else {
			if (gameBoard[row][column] == 'O') return true;
			else return false;
		}
	}
	
	/**
	 * Checks if the tile at the provided coordinates is the humans.
	 * @param row - The row to be checked.
	 * @param column - The column to be checked.
	 * @return - Returns true/false.
	 */
	public boolean tileIsHuman(int row, int column){
		// checks to see if the row/column is out of bounds.
		if ((row < 0) || (row >= gameBoard.length) || (column < 0) || (column >= gameBoard.length)){
			return false;
		}
		else {
			if (gameBoard[row][column] == 'X') return true;
			else return false;
		}	
	}
	
	/**
	 * Checks to see if there is a winner.
	 * @param symbol - Check if there is a winner for the player that corresponds to the symbol.
	 * @return - Returns true/false.
	 */
	public boolean wins(char symbol){
		int[] diag = new int[2];
		int size = gameBoard.length;
		int[] col = new int[size];
		// set Initial values to 0;
		// diag[0] is top left to bottom right, diag[1] is top right to bottom left
		diag[0] = 0;
		diag[1] = 0;
		for (int i=0; i<size; i++){
			col[i] = 0;
		}
		int row = 0;
		// go through the current game board.
		for (int i=0; i < size; i++){
			for (int j=0; j<size; j++){
				if (gameBoard[i][j] == symbol){
					row++;
					col[j]++;
					// if at right side and column is at the size, then winner
					if ((i == (size-1)) && (col[j] == size)){
						return true;						
					}
					// if i and j are equal, then add one to the diagonal 1.
					else if (i == j) {
						diag[0]++;
						// if i and j are equal, and if it is on the diagonal 2, then add to diagonal 2.
						if (i == ((size - 1) - j)){
							diag[1]++;
						}
						// if at the right side, and diag[0] count is equal to table size, then winner
						if ((i == (size - 1)) && (diag[0] == size)){
							return true;
						}
					// if on the diag[1], then add 1 to diag[1]
					} else if (i == ((size - 1) - j)){
						diag[1]++;
						// if at the bottom and diag[1] is equal to the size, then winner
						if ((j == 0) && (diag[1] == size)) {
							return true;
						}
					}
				}
			}
			// if the count of row is equal to board size, then winner. 
			if (row == size) {
				return true;
			}
			else row = 0;
		}		
		return false;
	}	
	
	/**
	 * Checks to see if the game is a draw, checks for the player that corresponds to the provided symbol.
	 * @param symbol - Checks if the provided symbols player has any moves left.
	 * @return
	 */
	public boolean isDraw(char symbol){
		int[] empty = new int[2];
		int row = 0, col = 0;
		// if there is more than one empty tile, then return false as it cannot
		// be a draw.
		if (numberEmptyTiles() > 1) {
			return false;
		}
		else {
			// checks the eight surrounding squares for a match to the symbol, of there is
			// then returns false as there is a valid move still available.
			empty = getEmptySpace();
			row = empty[0];
			col = empty[1];
			
			// if the symbol is X, then test if they are human. If any match then return false as it is
			// not a draw
			switch (symbol) {
				case 'X': 
					if (tileIsHuman(row-1, col-1)) return false;
					if (tileIsHuman(row-1, col)) return false;
					if (tileIsHuman(row-1, col+1)) return false;
					if (tileIsHuman(row, col-1)) return false;
					if (tileIsHuman(row, col+1)) return false;
					if (tileIsHuman(row+1, col-1)) return false;
					if (tileIsHuman(row+1, col)) return false;
					if (tileIsHuman(row+1, col+1)) return false;
					break;
				// if the symbol is X, then test if they are human. If any match then return false as it is
				// not a draw
				case 'O':
					if (tileIsComputer(row-1, col-1)) return false;
					if (tileIsComputer(row-1, col)) return false;
					if (tileIsComputer(row-1, col+1)) return false;
					if (tileIsComputer(row, col-1)) return false;
					if (tileIsComputer(row, col+1)) return false;
					if (tileIsComputer(row+1, col-1)) return false;
					if (tileIsComputer(row+1, col)) return false;
					if (tileIsComputer(row+1, col+1)) return false;
					break;
				default:
					break;
			}
		}
		// if there are no valid moves left, then returns true and it is a draw.
		return true;
	}
	
	/**
	 * Evaluates the board, to see if there is a winner, a draw or if the game can go on.
	 * @param symbol - The symbol of the player to check.
	 * @return - Returns 1 if game goes on, 2 if draw, 0 if player wins, 3 if computer wins.
	 */
	public int evalBoard(char symbol){
		if (wins(symbol)) {
			// if computer wins return 3, else return 1 for player wins
			// return 2 for a draw and 1 for game continues.
			if (symbol == 'O') return 3;
			else return 0;
		}
		else if (isDraw(symbol)) return 2;
		else return 1;		
	}
	
	/**
	 * Gets the number of empty tiles on the game board.
	 * @return - Returns the number of empty tiles on the board.
	 */
	public int numberEmptyTiles(){
		int empty = 0;
		String config = getBoardConfig();
		for (int i = 0; i < config.length(); i++){
			if (config.charAt(i) == ' '){
				empty++;
			}
		}
		return empty;
	}
	
	/**
	 * Gets the configuration of the current board.
	 * @return - Returns a string representation of the current board.
	 */
	private String getBoardConfig(){
		String config = "";		
		for (int i = 0; i < gameBoard.length; i++){
			for (int j = 0; j < gameBoard.length; j++){
				config += gameBoard[i][j];				
			}
		}	
		return config;
	}
	
	/**
	 * Gets the location of the empty space on the board. 
	 * @return - Returns the row and column of the empty space.
	 */
	private int[] getEmptySpace() {
		int[] empty = new int[2];
		
		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard.length; j++) {
				if (tileIsEmpty(i, j)) {
					empty[0] = i;
					empty[1] = j;
					return empty;
				}
			}
		}		
		return empty;
	}
}
