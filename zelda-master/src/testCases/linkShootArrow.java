package testCases;

import static org.junit.Assert.*;

import java.lang.Thread.State;

import javax.swing.JFrame;


import org.junit.Before;
import org.junit.Test;

import zelda.Controller;
import zelda.TestController;
import zelda.View;
import zelda.enemy.BlueSoldier;
import zelda.enemy.GhostSoldier;
import zelda.enemy.PasiveBehavior;
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

public class linkShootArrow {

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
		malo = new BlueSoldier(game, 154, 250, Direction.DOWN,5);
		PasiveBehavior maloBehave = new PasiveBehavior(malo);
		malo.setBehavior(maloBehave);
		malo.setState(new StandState(malo));
		Link link = game.getLink();
		link.setX(154);
		link.setY(150);
		testScene.addGObject(malo);
		game.setScene(testScene);
		view = new View(game, mainView);
		ctl = new TestController(game, view, mainView);
		
	}
	@Test
	public void test() throws InterruptedException {
		
		
		
		Link link = game.getLink();
		Thread.sleep(1000);
		game.setkPressed(true);
		link.handleInput();
		game.getScene().handleInput();
		
		Thread.sleep(90) ;
		
		game.getScene().handleInput();

		
		Thread.sleep(1000);
		//System.out.println(link.getX()+" "+link.getY());
		assertEquals(3,malo.getHealth());
		assertEquals(5,link.getHealth());
		//game.quit();
		
	}

}
