package zelda;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import testCases.BowCommand;
import testCases.Command;
import zelda.engine.GObject;
import zelda.engine.Game;
import zelda.items.Arrow;

/**
 * The Controller is reponsible for the gameloop.
 * And it handles user keyinput for the game.
 *
 * @author maartenhus
 */
public class TestController implements Runnable, KeyListener
{
	private Thread thread;
	private Game game;
	private View view;
	private PolyCreator polyCreator;
	private Robot robot ;
	private int currentMove = 0;
	private boolean isArrow =false  ;

	private ArrayList<Command> moves ;

	public TestController(Game game, View view, JFrame frame, ArrayList<Command> moves)
	{
		this.game = game;
		this.view = view;

		frame.addMouseListener(new PolyCreator(game));
		frame.addKeyListener(this);
		this.moves = moves ;
		thread = new Thread(this, "GameLoop");
		thread.start();
	}

	/**
	 * This function represents the gameloop, it does stuff like make objects
	 * react on input and draw the game.
	 */
	public void run()
	{	
		try
		{
		
		Thread.sleep(1000) ;
		while (game.isRunning())
		{
			
				if(!game.isPaused())
				{
					if (currentMove == moves.size() && !isArrow) break ;

					
					game.getScene().handleInput(); // let scene handle user input for menu's etc.

					if (!isArrow)
						Thread.sleep(50) ;  
					if (currentMove < moves.size() && !isArrow ) {
						moves.get(currentMove).execute() ;
						game.getLink().handleInput(); // let link handle key input.
						moves.get(currentMove).finish() ;
					}
					isArrow = false ;
					for(GObject obj : game.getScene().getGObjects())
					{
						if (obj instanceof Arrow) 
							isArrow=true ;
						obj.doInLoop(); // this lets the GObject hook in on the gameloop
					}
					if (currentMove < moves.size() && !isArrow )	
						currentMove++ ;

				}

                try
                {
                    view.draw();
                }catch(Exception e){}
				Thread.sleep(game.getGameSpeed());
			
		}
		}
		catch (InterruptedException e){}
	}

	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			game.setRunning(false); //quit game
		}

		if (e.getKeyCode() == KeyEvent.VK_P)
		{
			game.setPaused(!game.isPaused()); //pauze game
		}

		switch(e.getKeyCode())
		{
			case KeyEvent.VK_A:
				game.setaPressed(true);
				break;
			case KeyEvent.VK_D:
				game.setdPressed(true);
				break;
			case KeyEvent.VK_W:
				game.setwPressed(true);
				break;
			case KeyEvent.VK_S:
				game.setsPressed(true);
				break;
			case KeyEvent.VK_J:
				game.setjPressed(true);
				break;
			case KeyEvent.VK_K:
				game.setkPressed(true);
				break;
			case KeyEvent.VK_L:
				game.setlPressed(true);
				break;
			case KeyEvent.VK_ENTER:
				game.setEnterPressed(true);
				break;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_A:
				game.setaPressed(false);
				break;
			case KeyEvent.VK_D:
				game.setdPressed(false);
				break;
			case KeyEvent.VK_W:
				game.setwPressed(false);
				break;
			case KeyEvent.VK_S:
				game.setsPressed(false);
				break;
			case KeyEvent.VK_J:
				game.setjPressed(false);
				break;
			case KeyEvent.VK_K:
				game.setkPressed(false);
				break;
			case KeyEvent.VK_L:
				game.setlPressed(false);
				break;
			case KeyEvent.VK_ENTER:
				game.setEnterPressed(false);
				break;
		}
	}

	public void keyTyped(KeyEvent e){}
}
