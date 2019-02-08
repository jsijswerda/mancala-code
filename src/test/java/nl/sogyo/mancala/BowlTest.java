package nl.sogyo.mancala;

import org.junit.Assert;
import org.junit.Test;

public class BowlTest {

    @Test
    public void getBowlNumberX() {
    	NormalBowl bowl1 = new NormalBowl();
    	Bowl bowl3 = bowl1.getBowlNumberX(3);
    	Assert.assertEquals(bowl1.getNeighbour().getNeighbour().bowlNumber, bowl3.bowlNumber);
    
    }
    
    
	
}
