package nl.sogyo.mancala;

public class Player {
	private boolean hasTurn;
	private Player opponent;



	
	public Player(boolean hasTurn){
		this.hasTurn = hasTurn;
		this.opponent = new Player(this);
	}
	
	public Player(Player otherPlayer) {
		this.hasTurn = false;
		this.opponent = otherPlayer;
		
	}
	
	public Player getOpponent() {
		return opponent;
	}
	
	public boolean getHasTurn() {
		return hasTurn;
	}
	
	public void changeTurn() {
		
		this.hasTurn = !this.hasTurn;
		this.opponent.hasTurn = !this.opponent.hasTurn;
	}
		



}
