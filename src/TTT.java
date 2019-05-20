import java.util.Scanner;

public class TTT {
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		Board board = new Board();
		
		while (!board.getisGameOver()) {
			board.display();
			
			//keep asking until input is valid
			boolean validInput = false;
			int gridPosition;
			while (!validInput) {
				System.out.print("Player " + board.getCurrPlayer() + " please enter grid positions 1-9: ");
				gridPosition = sc.nextInt();
				if (board.getMove(gridPosition)) {
					validInput = true;
				}
			}
		}
		board.display();
		System.out.print("Game over. ");
		if (board.getWinner() == null) {
			System.out.print("Tie.");
		} else {
			System.out.print(board.getWinner().toString() + " won!");
		}	
	}
}
