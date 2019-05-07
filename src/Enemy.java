

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class Enemy extends Rectangle implements ImageObserver {


	/**
	 * 
	 */
	Board board=new Board();
	private static final long serialVersionUID = 1L;
	int numarFantoma=0;
	public Enemy(int x,int y,int nr) {
		setBounds(x,y,32,32);
		numarFantoma=nr;
	}
	
	public void paintComponent(Graphics g)
	{
		//Image ghost3=new ImageIcon("ghost3.png").getImage();
		//g.drawImage(ghost3,x+5,y,26,26,this);
		if(numarFantoma==1) {
		Image ghost1=new ImageIcon("ghost1.png").getImage();
		g.drawImage(ghost1,board.getG1x(),board.getG1y(),26,26,this);
		
		}
		if(numarFantoma==2) {
		Image ghost2=new ImageIcon("ghost2.png").getImage();
		g.drawImage(ghost2,board.getG2x(),board.getG2y(),26,26,this);
		}
		if(numarFantoma==3) {
		Image ghost3=new ImageIcon("ghost3.png").getImage();
		g.drawImage(ghost3,board.getG3x(),board.getG3y(),26,26,this);
		}
		if(numarFantoma==4) {
		Image ghost4=new ImageIcon("ghost4.png").getImage();
		g.drawImage(ghost4,board.getG4x(),board.getG4y(),26,26,this);
		}
	}
	public boolean allowedToMove(int nextX,int nextY) {
		Rectangle limit=new Rectangle(nextX,nextY,32,32);
		Tabla t=Board.tabla;
		
		for(int i=0;i<t.tile.length;i++)
		{
			for(int j=0;j<t.tile[0].length;j++)
			{
				if(t.tile[i][j]!=null)
				{
					if(limit.intersects(t.tile[i][j]))
					{
						return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}

}
