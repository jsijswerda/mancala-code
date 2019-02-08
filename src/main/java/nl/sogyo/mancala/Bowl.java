package nl.sogyo.mancala;

abstract class Bowl
{
	
	private int numberOfStones;
	protected Bowl neighbour;
	protected Player owner;
	protected int bowlNumber;
	
	public Bowl() {}
	
	public Bowl getNeighbour() {
		return neighbour;
	}
	
	public int getNumberOfStones() {
		return numberOfStones;
	}
	
	protected void setNumberOfStones(int numberOfStones) {
		this.numberOfStones = numberOfStones;
	}
	
	public Bowl getBowlNumberX(int x) {
		if (x == 1)
			return this;
		else {
			return neighbour.getBowlNumberX(x-1);
		}
	}
	
	public int stepsToFindKalaha() {
		if (this instanceof Kalaha)
			return 0;
		else
			return 1 + getNeighbour().stepsToFindKalaha();
	}
	
	public int stepsToFindKalahaOpponent() {
		if (this instanceof Kalaha)
			return 7;
		else
			return 1 + getNeighbour().stepsToFindKalahaOpponent();
	}	

	
	public boolean checkIfEmptyBowl() {
		return this.getNumberOfStones() == 0;
	}
	
	public boolean checkEndGame() {
		for (int i = 1;i<15;i++) {
			if (getBowlNumberX(i) instanceof NormalBowl && getBowlNumberX(i).owner.getHasTurn()) {
				if (!getBowlNumberX(i).checkIfEmptyBowl())
					return false;
			}
			
		}
		return true;
	}
	
	public void playerStonesAtEndGame() {
		int total = 0;
		for (int i = 1;i<15;i++) {
			if (this.getBowlNumberX(i).owner.getHasTurn()) {
				total += getBowlNumberX(i).getNumberOfStones();
			}
	
		}
		if (this instanceof Kalaha)
			this.setNumberOfStones(total);
		else if (this instanceof NormalBowl)
			this.getBowlNumberX(stepsToFindKalaha()+1).setNumberOfStones(total);
	}
	
	public void opponentStonesAtEndGame() {
		int total = 0;
		for (int i = 1;i<15;i++) {
			if (!this.getBowlNumberX(i).owner.getHasTurn()) {
				total += getBowlNumberX(i).getNumberOfStones();
			}
		
		}

			this.getBowlNumberX(stepsToFindKalahaOpponent()+1).setNumberOfStones(total);
		
		
	}
	public abstract void distributeStones(int stonesToBePassed);

}
