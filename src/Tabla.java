

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Tabla {
	
	public int width;
	public int height;
	public Board b=new Board();
	public Tile[][] tile;
	public List<Food> food=new ArrayList<Food>();
	public List<Enemy> enemy=new ArrayList<Enemy>();
	public List<Life> life=new ArrayList<Life>();
	public Tabla() {
		try {
			BufferedImage map=ImageIO.read(new File("map.png"));
			this.width=map.getWidth();
			this.height=map.getHeight();
			int[] pixel=new int[width*height];
			tile=new Tile[width][height];
			
			map.getRGB(0, 0, width, height, pixel, 0, width);
			
			for(int i=0;i<width;i++)
			{
				for(int j=0;j<height;j++)
				{
					int valoare=pixel[i+(j*width)];
					if(valoare==0xFF000000)
					{
						//Tile
						tile[i][j]=new Tile(i*32,j*32);
					}
					else if(valoare==0xFFFFD800)
					{ //Pacman
						b.setPacX(i*32);
						b.setPacY(j*32);
					}
					else if (valoare==0xFFFF0000)
					{ //Enemy
						//enemy.add(new Enemy(i*32,j*32,1));
						b.setG1x(i*32);
						b.setG1y(j*32);
					}
					else if(valoare==0xFFFF180C)
					{	//Enemy
						//enemy.add(new Enemy(i*32,j*32,2));
						b.setG2x(i*32);
						b.setG2y(j*32);
					}
					else if(valoare==0xFFFF0026)
					{
						//Enemy
						//enemy.add(new Enemy(i*32,j*32,3));
						b.setG3x(i*32);
						b.setG3y(j*32);
					}
					else if(valoare==0xFFFF075E)
					{
						//enemy
						//enemy.add(new Enemy(i*32,j*32,4));
						b.setG4x(i*32);
						b.setG4y(j*32);
					}
					else if(valoare==0xFFFFFFFF)
					{//Food
						food.add(new Food(i*32,j*32));
					}
					else if(valoare==0xFFFF6A00)
					{
						//Life
						life.add(new Life(i*32,j*32)); 
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g)
	{
		
		for(int i=0;i<width;i++)
		{
			for(int j=0;j<height;j++)
			{
				if(tile[i][j]!=null)tile[i][j].paintComponent(g);
			}
		}
		
		for(int k=0;k<food.size();k++)
		{
			food.get(k).paintComponent(g);
		}
		for(int l=0;l<enemy.size();l++)
		{
			enemy.get(l).paintComponent(g);
		}
		for(int m=0;m<life.size();m++)
		{
			life.get(m).paintComponent(g);
		}
	}
}


