
import javax.swing.JFrame;



public class Main {

	public static void main(String[] args) {
		
		JFrame f =new JFrame();
		Board b=new Board();
		f.add(b);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(710,840);
		f.setResizable(false);
		f.setVisible(true);
		


	}
}


