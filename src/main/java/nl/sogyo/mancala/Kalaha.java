package nl.sogyo.mancala;

public class Kalaha extends Bowl {

	private final int STARTING_STONES = 0;
	
	
	public Kalaha(Player player, int countBowls, Bowl startingBowl) {
		setNumberOfStones(STARTING_STONES);
		owner = player;
		bowlNumber = countBowls;
		neighbour = new NormalBowl(player.getOpponent(),countBowls+1,startingBowl);
		
	}
	
	public Kalaha(Player player, Bowl startBowl) {
		setNumberOfStones(STARTING_STONES);
		owner = player;
		neighbour = startBowl;
		
		
	}
	
	public void distributeStones(int stonesToBePassed) {
		if (this.owner.getHasTurn() && stonesToBePassed > 1) {
			setNumberOfStones(getNumberOfStones()+1);
			stonesToBePassed--;
			getNeighbour().distributeStones(stonesToBePassed);
				 
			}
		else if (this.owner.getHasTurn() && stonesToBePassed == 1) {
			setNumberOfStones(getNumberOfStones()+1);
			stonesToBePassed--;
			
		}
		else if (stonesToBePassed > 0) {
			getNeighbour().distributeStones(stonesToBePassed);
		}
	}
	


	

	

}
