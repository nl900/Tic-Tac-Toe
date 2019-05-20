/**
 * 
 * Player can either be X or O.
 *
 */

enum Player { 
	X, O;
	
	/**
	 * Randomly select X or O player
	 * @return Player
	 */
	public static Player getRandomPlayer() {
		return values()[(int) (Math.random() * values().length)];
		
	}
	
	/**
	 * 
	 * Switch to other player
	 * @param current
	 * @return Player
	 */
	public static Player flipPlayer(Player current) {
		if (current == values()[0]) {
			return values()[1];
		}else {
			return values()[0];
		}
	}
}
