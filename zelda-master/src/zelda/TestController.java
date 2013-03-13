package zelda;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
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

	public TestController(Game game, View view, JFrame frame)
	{
		this.game = game;
		this.view = view;

		frame.addMouseListener(new PolyCreator(game));
		frame.addKeyListener(this);

		thread = new Thread(this, "GameLoop");
		thread.start();
	}

	/**
	 * This function represents the gameloop, it does stuff like make objects
	 * react on input and draw the game.
	 */
	public void run()
	{
		while (game.isRunning())
		{
			
			for ( GObject arrow : game.getScene().getGObjects()){
				if (arrow instanceof Arrow)
					arrow.doInLoop();
			}
                    
                    try {
                    	view.draw();
						Thread.sleep(10) ;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				   
		}

		// if the game is not running close up.
		view.exitFullScreen();
		game.quit();
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
