package font;
import javax.swing.JApplet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel; 
import javax.swing.JPanel;

public class Applet extends JApplet{
	
	public void start(){
		
	}

	
	public void init(){
		
		setBackground(Color.green);
		
	}
	
	public void paint(Graphics g){
	
		

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	      String[] names = ge.getAvailableFontFamilyNames();
	      for ( int i=0; i<names.length; i++ )
	         {
	             g.drawString( names[i], 10,10*i );
	             
	         }
	}
}
