package zelda.enemy;

import zelda.karacter.Direction;
import zelda.karacter.Karacter;
import zelda.link.Link;

/**
 *
 * @author vincentklarholz
 */
public class PasiveBehavior extends Behavior
{

    private Karacter soldier;
    private Link link;

    private int valueX;
    private int valueY;

	public PasiveBehavior(Karacter soldier)
	{
        //System.out.println("here");
		this.soldier = soldier;
        link = soldier.getGame().getLink();
	}

	public void behave()
    {
        
    }
}
