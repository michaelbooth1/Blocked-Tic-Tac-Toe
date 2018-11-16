/**
 * Class that plays blocked Tic-Tac-Toe against the computer
 * 
 * @author Michael Booth
 */
public class BlockedTicTacToe {
	private int blockedBoardSize; // Size of the board
	private int blockedInline; // Number in a row needed to win
	private int blockedMaxLevels; // Max levels the computer checks
	private char[][] gameboard; // Stores the game board

	/*
	 * Constructor creates and initializes the game board
	 */
	public BlockedTicTacToe(int board_size, int inline, int max_levels) {
		blockedBoardSize = board_size;
		blockedInline = inline;
		blockedMaxLevels = max_levels;

		gameboard = new char[board_size][board_size];

		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				gameboard[i][j] = ' ';
			}
		}
	}

	/*
	 * Creates a TTTDictionary of size 5051
	 */
	public TTTDictionary createDictionary() {
		TTTDictionary HashDict = new TTTDictionary(5051);
		return HashDict;
	}

	/*
	 * Checks if the current board configuration is already in the dictionary
	 */
	public int repeatedConfig(TTTDictionary configurations) {
		String boardString = "";
		for (int i = 0; i < blockedBoardSize; i++) {
			for (int j = 0; j < blockedBoardSize; j++) {
				boardString += gameboard[j][i];
			}
		}

		TTTRecord boardConfig = configurations.get(boardString);

		if (boardConfig == null) {
			return -1;
		} else {
			return boardConfig.getScore();
		}
	}

	/*
	 * Insterts the board configuration into the dictionary
	 */
	public void insertConfig(TTTDictionary configurations, int score, int level) {
		String boardString = "";
		for (int i = 0; i < blockedBoardSize; i++) {
			for (int j = 0; j < blockedBoardSize; j++) {
				boardString += gameboard[j][i];
			}
		}

		TTTRecord record = new TTTRecord(boardString, score, level);

		try {
			configurations.put(record);
		} catch (DuplicatedKeyException e) {
		}

	}

	/*
	 * Changes the specified board piece to the specified symbol
	 */
	public void storePlay(int row, int col, char symbol) {
		gameboard[row][col] = symbol;
	}

	/*
	 * Checks if the square is empty
	 */
	public boolean squareIsEmpty(int row, int col) {
		return (gameboard[row][col] == (' '));
	}

	/*
	 * Checks if the specified player has won the game
	 */
	public boolean wins(char symbol) {

		int counter = 0;

		// Checks the board horizontally for a winner
		for (int i = 0; i < blockedBoardSize; i++) {
			for (int j = 0; j < blockedBoardSize; j++) {
				char current = gameboard[i][j];
				while (current == symbol && j + counter < blockedBoardSize) {
					counter += 1;
					if (counter >= blockedInline) {
						return true;
					}
					if (j + counter < blockedBoardSize) {
						current = gameboard[i][j + counter];
					}
				}
				counter = 0;

			}
		}

		// Checks the board vertically for a winner
		for (int i = 0; i < blockedBoardSize; i++) {
			for (int j = 0; j < blockedBoardSize; j++) {
				char current = gameboard[i][j];
				while (current == symbol && i + counter < blockedBoardSize) {
					counter += 1;
					if (counter >= blockedInline) {
						return true;
					}
					if (i + counter < blockedBoardSize) {
						current = gameboard[i + counter][j];
					}
				}
				counter = 0;

			}
		}

		// Checks the board diagonally for a winner
		for (int i = 0; i < blockedBoardSize; i++) {
			for (int j = 0; j < blockedBoardSize; j++) {
				char current = gameboard[i][j];
				while (current == symbol && i + counter < blockedBoardSize && j + counter < blockedBoardSize) {
					counter += 1;
					if (counter >= blockedInline) {
						return true;
					}
					if (i + counter < blockedBoardSize && j + counter < blockedBoardSize) {
						current = gameboard[i + counter][j + counter];
					}
				}
				counter = 0;

			}
		}

		return false;

	}

	/*
	 * Checks to see if there are no possible moves left and the game is a draw
	 */
	public boolean isDraw() {
		if (wins('x') || wins('o')) {
			return false;
		}

		for (int i = 0; i < blockedBoardSize; i++) {
			for (int j = 0; j < blockedBoardSize; j++) {
				if (gameboard[i][j] == ' ') {
					return false;
				}
			}
		}

		return true;
	}

	/*
	 * Evaluates the best move to make for the computer
	 */
	public int evalBoard() {
		if (wins('o')) {
			return 3;
		} else if (wins('x')) {
			return 0;
		} else if (isDraw()) {
			return 1;
		} else {
			return 2;
		}
	}

}
