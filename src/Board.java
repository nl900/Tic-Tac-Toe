/**
 * 
 * The board object and associated functions to the board.
 *
 */


public class Board {
	private final int size = 3;
	private final Player[][] board;
	private Player currPlayer;
    private Player winner;
    private boolean isGameOver;
    
    Board() {
    	board  = new Player[size][size];
    	isGameOver = false;
    	currPlayer = Player.getRandomPlayer();
    }
    
    public boolean getisGameOver() {
    	return this.isGameOver;
    }
    
    public String getCurrPlayer() {
    	return currPlayer.toString();
    }
    
    public Player getWinner() {
    	return winner;
    }
    
    /**
     * Prints current state of the board to stdout
     */
    public void display() {
    	System.out.println("+---+---+---+");
    	for (int row = 0; row < size; row++) {
    		System.out.print("|");
    		for (int column = 0; column < size; column++) {
    			if (board[row][column]== null) {
    				System.out.print("  ");
    			}else {
    				System.out.print(" ");
    				System.out.print(board[row][column].toString());
    			}
    			System.out.print(" |");
    		}
			System.out.println("");
			System.out.println("+---+---+---+");
    		
    	}
    }
    
    /**
     * Get user input and convert it to x,y coordinates
     * @param gridPos
     */
    
    public boolean getMove(int gridPos) {   	
    	int x=-1, y=-1;
    	if (isBetween(gridPos, 1, 3)) {
    		x = 0;
    		y = gridPos - 1;
    	} else if (isBetween(gridPos, 4, 6)) {
    		x = 1;
    		y = gridPos - 1 - 3;
    	} else if (isBetween(gridPos,7, 9)) {
    		x = 2;
    		y = gridPos - 1 - 6;
    	}
    	
    	if (isValid(x, y)) {
    		mark(x,y);
    		return true;
    	}else {
    		return false;
    	}
    }
    
   
    
    /**
     * Validate user input
     * @param row
     * @param col
     * @return true if valid
     */
    
    private boolean isValid(int row, int col) {
        if (isOutOfBounds(row) || isOutOfBounds(col) ) {
        	System.out.println("This is not a valid grid position");
            return false;
        } else if(isCellValueAlreadySet(row, col) ) {
        	System.out.println("This cell is already taken");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Mark position for the current player
     *
     * @param row 0..2
     * @param col 0..2
     *
     */
    public void mark(int row, int col) {
        board[row][col] = currPlayer;

        if(isWinningMoveByPlayer(currPlayer, row, col)) {
        	isGameOver = true;
            winner = currPlayer;

        } else {
            // switch to other player
        	currPlayer = Player.flipPlayer(currPlayer);
        }
        
    }
    
    /**
     * Check whether user input is within bounds
     * @param x
     * @param lower
     * @param upper 
     * @return true if within boundary
     */
    public boolean isBetween(int x, int lower, int upper) {
    	  return lower <= x && x <= upper;
    	}

    private boolean isOutOfBounds(int idx) {
        return idx < 0 || idx > 2;
    }
    
    private boolean isCellValueAlreadySet(int row, int col) {
        return board[row][col] != null;
    }
    
    /**
     * Adapted from http://www.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe.html
     * @param player
     * @param currentRow
     * @param currentCol
     * @return true if <code>player</code> who just played the move at the <code>currentRow</code>, <code>currentCol</code>
     *              has a tic tac toe.
     */
    private boolean isWinningMoveByPlayer(Player player, int currentRow, int currentCol) {

        return (board[currentRow][0] == player         // 3-in-the-row
                && board[currentRow][1] == player
                && board[currentRow][2] == player
                || board[0][currentCol] == player      // 3-in-the-column
                && board[1][currentCol] == player
                && board[2][currentCol]== player
                || currentRow == currentCol            // 3-in-the-diagonal
                && board[0][0] == player
                && board[1][1] == player
                && board[2][2] == player
                || currentRow + currentCol == 2    // 3-in-the-opposite-diagonal
                && board[0][2] == player
                && board[1][1] == player
                && board[2][0] == player);
    }

}
