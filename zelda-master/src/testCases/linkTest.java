package testCases;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.lang.Thread.State;
import java.util.ArrayList;

import javax.swing.JFrame;

import junit.framework.TestCase;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import zelda.Controller;
import zelda.TestController;
import zelda.View;
import zelda.enemy.BlueSoldier;
import zelda.enemy.GhostSoldier;
import zelda.enemy.PasiveBehavior;
import zelda.enemy.PasiveSoldier;
import zelda.enemy.PatrolBehavior;
import zelda.enemy.Soldier;
import zelda.enemy.StandState;
import zelda.engine.GObject;
import zelda.engine.Game;
import zelda.engine.Scene;
import zelda.items.Arrow;
import zelda.karacter.Direction;
import zelda.link.Link;
import zelda.link.SwordState;
import zelda.link.WalkState;
import zelda.scene.ZeldaScene;

public class linkTest extends TestCase {

	private Game game;
	private View view;
	private TestController ctl;
	private JFrame mainView;
	private Soldier malo;
	
	@Before
	public void setUp(){
		game = new Game();
		mainView = new JFrame();
		mainView.setIgnoreRepaint(true);
		mainView.setUndecorated(true);
		
		mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainView.setVisible(false);

		
		
		Scene testScene = game.initScene("TestScene");
		malo = new PasiveSoldier(game, 146, 182, Direction.UP,5);
		malo.setState(new StandState(malo));
		Link link = game.getLink();
		link.setX(154);
		link.setY(150);
		testScene.addGObject(malo);
		game.setScene(testScene);
		view = new View(game, mainView);
		ArrayList<Command>  moves = new ArrayList<Command>() ;
		
		moves.add(new LeftCommand(game)) ;
		moves.add(new LeftCommand(game)) ;


		moves.add(new LeftCommand(game)) ;
		
		
		moves.add(new DownCommand(game)) ;
		moves.add(new DownCommand(game)) ;
		
		moves.add(new SwordCommand(game)) ;
		moves.add(new SwordCommand(game)) ;
		moves.add(new SwordCommand(game)) ;
		moves.add(new SwordCommand(game)) ;
		moves.add(new SwordCommand(game)) ;
		moves.add(new SwordCommand(game)) ;
		moves.add(new SwordCommand(game)) ;


		ctl = new TestController(game, view, mainView, moves);
		
	}
	@Test
	public void test() throws InterruptedException {

		
		Thread.sleep(6000) ;
		Link link = game.getLink() ;
		System.out.println(link.getX()+" "+link.getY());
		//assertEquals(3,malo.getHealth());
		assertEquals(5,link.getHealth());
		assertEquals(146, link.getX());
		assertEquals(158,link.getY());

	}
	
	@After
	public void tearDown(){
		
	}

}
