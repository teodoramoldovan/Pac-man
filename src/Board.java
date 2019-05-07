

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener,KeyListener {

	private static final long serialVersionUID = 1L;
	public static int _PacX = 32;
	public static  int _PacY = 32;
	
	public static int viata_pierduta=0;
	public static int game_over=0;
	public static int winner=0;
	
	public static int g1x=427,g1y=32,g2x=96,g2y=222,g3x=384,g3y=352,g4x=32,g4y=640;
	public int[] directie={1,1,1,1,1};
	
	Random random=new Random();

	private static int dir=1;
	
	private static int scor=0;
	private static int viata=3;
	
	public static Tabla tabla=new Tabla();
	public static Player player=new Player(_PacX,_PacY);
	public static Enemy ghost1=new Enemy(g1x,g1y,1);
	public static Enemy ghost2=new Enemy(g2x,g2y,2);
	public static Enemy ghost3=new Enemy(g3x,g3y,3);
	public static Enemy ghost4=new Enemy(g4x,g4y,4);
	
	private boolean stanga,dreapta,sus,jos;
	
	
	Timer t=new Timer(10,this);
	
	public Board() {
		setBackground(Color.black);
		t.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		player.paintComponent(g);
		tabla.paintComponent(g);
		ghost1.paintComponent(g);
		ghost2.paintComponent(g);
		ghost3.paintComponent(g);
		ghost4.paintComponent(g);
		

		
		g.setColor(Color.yellow);
		//g.drawString(Integer.toString(getPacX())+" "+Integer.toString(getPacY()),0,10);
		g.setColor(Color.white);
		Font f = new Font("Helvetica", Font.BOLD, 25);
		g.setFont(f);
		g.drawString("SCOR: "+Integer.toString(scor), 100,750 );
		
		if(winner==1)
		{
			Font f1 = new Font("Helvetica", Font.BOLD, 20);
			g.setFont(f1);
			g.setColor(Color.green);
			g.drawString("CONGRATULATIONS! YOU WIN :) SCOR:"+Integer.toString(scor)+" PRESS ENTER TO REPLAY",10,790);
			t.stop();
			winner=0;
			scor=0;
		}
		
		if(game_over==1)
		{
			dir=1;
			Font f2 = new Font("Helvetica", Font.BOLD, 25);
			g.setFont(f2);
			g.setColor(Color.green);
			g.drawString("GAME OVER. PRESS ENTER TO REPLAY ", 60,790 ); // // modificare sa apara sub scor 
			t.stop();
			game_over=0;
			viata_pierduta=0;
		
		}
		else 
			if(viata_pierduta==1)
			{
				Font f3 = new Font("Helvetica", Font.BOLD, 25);
				g.setFont(f3);
				g.setColor(Color.green);
				g.drawString("YOU LOST A LIFE :(. PRESS ENTER TO CONTINUE", 40,790 ); // modificare sa apara sub scor 
				t.stop();
				viata_pierduta=0;
			}

		
		
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		
		
		//Pacman moves
		if(player.allowedToMove(_PacX-2, _PacY)&&stanga)_PacX-=2;
		else if(player.allowedToMove(_PacX+2, _PacY)&&dreapta)_PacX+=2;
		else if(player.allowedToMove(_PacX, _PacY-2)&&sus)_PacY-=2;
		else if(player.allowedToMove(_PacX, _PacY+2)&&jos)_PacY+=2;
		//Pacman eats
		if(player.meetsFood(_PacX, _PacY))
		{
			tabla.food.remove(player.getIndex());
			scor++;
		}
		if(tabla.food.isEmpty())
		{
			
			Board.player=new Player(32,32);
			Board.tabla=new Tabla();

			viata=3;
			winner=1;
			
		}
		if(tabla.life.isEmpty())
		{
			Board.player=new Player(32,32);
			Board.tabla=new Tabla();
			scor=0;
			viata=3;
			winner=0;
		}
		
		
		if(player.meetsEnemy(_PacX,_PacY))
		{
			viata_pierduta=1;
			viata--;
			if(viata>=0)
			{
			tabla.life.remove(viata);
			_PacX=32;
			_PacY=32;
			dir=1;
			if(viata==0)
				game_over=1;
			}
			
		}
		//Ghosts move random
		
		if(directie[1]==0)//stanga
		{
			if(ghost1.allowedToMove(g1x-1, g1y))g1x--;
			else directie[1]=random.nextInt(4);
		}
		else if(directie[1]==1)//dreapta
		{
			if(ghost1.allowedToMove(g1x+1, g1y))g1x++;
			else directie[1]=random.nextInt(4);
		}
		else if(directie[1]==2)//sus
		{
			if(ghost1.allowedToMove(g1x, g1y-1))g1y--;
			else directie[1]=random.nextInt(4);
		}
		else if(directie[1]==3)//jos
		{
			if(ghost1.allowedToMove(g1x, g1y+1))g1y++;
			else directie[1]=random.nextInt(4);
		}
		
		
	
		if(directie[2]==0)//stanga
		{
			if(ghost2.allowedToMove(g2x-1, g2y))g2x--;
			else directie[2]=random.nextInt(4);
		}
		else if(directie[2]==1)//dreapta
		{
			if(ghost2.allowedToMove(g2x+1, g2y))g2x++;
			else directie[2]=random.nextInt(4);
		}
		else if(directie[2]==2)//sus
		{
			if(ghost2.allowedToMove(g2x, g2y-1))g2y--;
			else directie[2]=random.nextInt(4);
		}
		else if(directie[2]==3)//jos
		{
			if(ghost2.allowedToMove(g2x, g2y+1))g2y++;
			else directie[2]=random.nextInt(4);
		}
		
		
		
		if(directie[3]==0)//stanga
		{
			if(ghost3.allowedToMove(g3x-1, g3y))g3x--;
			else directie[3]=random.nextInt(4);
		}
		else if(directie[3]==1)//dreapta
		{
			if(ghost3.allowedToMove(g3x+1, g3y))g3x++;
			else directie[3]=random.nextInt(4);
		}
		else if(directie[3]==2)//sus
		{
			if(ghost3.allowedToMove(g3x, g3y-1))g3y--;
			else directie[3]=random.nextInt(4);
		}
		else if(directie[3]==3)//jos
		{
			if(ghost3.allowedToMove(g3x, g3y+1))g3y++;
			else directie[3]=random.nextInt(4);
		}
		
		
		
		if(directie[4]==0)//stanga
		{
			if(ghost4.allowedToMove(g4x-1, g4y))g4x--;
			else directie[4]=random.nextInt(4);
		}
		else if(directie[4]==1)//dreapta
		{
			if(ghost4.allowedToMove(g4x+1, g4y))g4x++;
			else directie[4]=random.nextInt(4);
		}
		else if(directie[4]==2)//sus
		{
			if(ghost4.allowedToMove(g4x, g4y-1))g4y--;
			else directie[4]=random.nextInt(4);
		}
		else if(directie[4]==3)//jos
		{
			if(ghost4.allowedToMove(g4x, g4y+1))g4y++;
			else directie[4]=random.nextInt(4);
		}
		
		
		repaint();
	

	}
	
	public void keyPressed(KeyEvent e)
	{
		
		
		int c=e.getKeyCode();
		if(c==KeyEvent.VK_LEFT)
		{
			stanga=true;
			setDir(2);			
		}
		
		if(c==KeyEvent.VK_RIGHT)
		{
			dreapta=true;
			setDir(1);
		}
		
		if(c==KeyEvent.VK_UP)
		{
			sus=true;
			setDir(3);
		}
		
		if(c==KeyEvent.VK_DOWN)
		{
			jos=true;
			setDir(4);
		}
		if(c==KeyEvent.VK_ENTER)
		{
			t.start();
		}
		
			
	}
	public void keyTyped(KeyEvent e) {};
	public void keyReleased(KeyEvent e) {
		int c=e.getKeyCode();
		if(c==KeyEvent.VK_LEFT)stanga=false;

		if(c==KeyEvent.VK_RIGHT)dreapta=false;
		if(c==KeyEvent.VK_UP)sus=false;

		if(c==KeyEvent.VK_DOWN)jos=false;

	}
	public int getPacX() {
		return _PacX;
	}
	public void setPacX(int _PacX) {
		this._PacX = _PacX;
	}
	public int getPacY() {
		return _PacY;
	}
	public void setPacY(int _PacY) {
		this._PacY = _PacY;
	}
	public int getDir() {
		return dir;
	}
	public void setDir(int dir) {
		this.dir = dir;
	};
	public static int getG1x() {
		return g1x;
	}
	public static void setG1x(int g1x) {
		Board.g1x = g1x;
	}
	public static int getG1y() {
		return g1y;
	}
	public static void setG1y(int g1y) {
		Board.g1y = g1y;
	}
	public static void setG2x(int g2x) {
		Board.g2x = g2x;
	}
	public static void setG2y(int g2y) {
		Board.g2y = g2y;
	}
	
	public static void setG3x(int g3x) {
		Board.g3x = g3x;
	}
	public static void setG3y(int g3y) {
		Board.g3y = g3y;
	}
	
	public static void setG4x(int g4x) {
		Board.g4x = g4x;
	}
	public static void setG4y(int g4y) {
		Board.g4y = g4y;
	}
	public static int getG2x() {
		return g2x;
	}
	
	public static int getG2y() {
		return g2y;
	}
	
	public static int getG3x() {
		return g3x;
	}
	
	public static int getG3y() {
		return g3y;
	}
	public static int getG4x() {
		return g4x;
	}
	
	public static int getG4y() {
		return g4y;
	}
	
	

}

