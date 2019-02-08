package nl.sogyo.mancala;

public class NormalBowl extends Bowl{
	
	private final int STARTING_STONES = 4;
	
	//first bowl
	public NormalBowl() {
		Player player = new Player(true);
		setNumberOfStones(STARTING_STONES);
		owner = player;
		bowlNumber = 1;
		neighbour = new NormalBowl(player, bowlNumber+1, this);
	}
	
	//all other bowls
	public NormalBowl(Player player, int bowlCount, Bowl startBowl) {
		setNumberOfStones(STARTING_STONES);
		owner = player;
		bowlNumber = bowlCount;
		
		if (bowlCount < 6) 
			neighbour = new NormalBowl(player,bowlCount+1,startBowl); 	
		else if (bowlCount == 6) 
			neighbour = new Kalaha(player,bowlCount+1, startBowl);
		else if (bowlCount > 6 && bowlCount < 13)
			neighbour = new NormalBowl(player,bowlCount+1,startBowl);
		else if (bowlCount == 13) 
			neighbour = new Kalaha(player, startBowl);
	
	}

	public void doMove() throws Exception {
		if (!this.owner.getHasTurn()) 
			throw new Exception("You can't do a move from this bowl");
		if (!checkEndGame()) {
			int stonesToDistribute = this.getNumberOfStones();
			emptyBowl();
			getNeighbour().distributeStones(stonesToDistribute);
		}
		else {
			playerStonesAtEndGame();
			opponentStonesAtEndGame();
		}
	}

	
	
	
	public void emptyBowl() {
		this.setNumberOfStones(0);
	}
	

	public void distributeStones(int stonesToDistribute) {
		
		if (stonesToDistribute > 1) {
			setNumberOfStones(getNumberOfStones()+1);
			stonesToDistribute--;
			getNeighbour().distributeStones(stonesToDistribute);
		}
				
		else if (stonesToDistribute == 1) {
			
			this.setNumberOfStones(getNumberOfStones()+1);
			stonesToDistribute--;
			if (this.getNumberOfStones() == 1 && this.owner.getHasTurn()) {
				steal();
			}
			owner.changeTurn();
		}
		
	}
	
	public Bowl getOppositeBowl() {
		int stepsToKalaha = stepsToFindKalaha();
		return this.getBowlNumberX(2*stepsToKalaha+1);
	}
	
	public void steal() {
		Bowl oppositeBowl = getOppositeBowl();
		int stolenStones = oppositeBowl.getNumberOfStones();
		int totalNumberOfStonesToKalaha = stolenStones + this.getNumberOfStones();
		oppositeBowl.setNumberOfStones(0);
		this.setNumberOfStones(0);
		this.getBowlNumberX(stepsToFindKalaha()+1).setNumberOfStones(getNumberOfStones()+totalNumberOfStonesToKalaha);
	}
}
