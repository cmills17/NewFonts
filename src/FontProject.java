
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JApplet;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.*; 


public class FontProject extends JFrame {    //ask JD why doesn't work when JApplet--was Jframe
  public static void main(String[] args) {
    new FontProject();
  }

  private JLabel TheWords = new JLabel("HELLLLOOO");
  private JComboBox FontSelectorCB;
  private JComboBox SizeSelectorCB;
  private JCheckBox boldCheckB, italCheckB;
  private JComboBox BackgroundSelectorCB;
  private String[] fonts;
  public FontProject() {
    this.setSize(900, 235);
    //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Listener FontThing = new Listener();
    this.add(TheWords, BorderLayout.NORTH);
    GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
    fonts = g.getAvailableFontFamilyNames();

    JPanel ControlStuff = new JPanel();
    FontSelectorCB = new JComboBox(fonts);
    FontSelectorCB.addActionListener(FontThing);
    ControlStuff.add(new JLabel("font: "));
    ControlStuff.add(FontSelectorCB);
    
    italCheckB = new JCheckBox("italics");
    italCheckB.addActionListener(FontThing);
    ControlStuff.add(italCheckB);
    
    boldCheckB = new JCheckBox("bold");
    boldCheckB.addActionListener(FontThing);
    ControlStuff.add(boldCheckB);

    Integer[] sizes = {12, 14, 18, 20, 22, 24, 36 };

    SizeSelectorCB = new JComboBox(sizes);
    SizeSelectorCB.setSelectedIndex(4);
    SizeSelectorCB.addActionListener(FontThing);
    ControlStuff.add(new JLabel("size: "));
    ControlStuff.add(SizeSelectorCB);
    
    Color[] colors = {Color.black, Color.cyan}; 
    
 
    BackgroundSelectorCB = new JComboBox(colors);
    BackgroundSelectorCB.setSelectedItem(Color.GRAY);
    BackgroundSelectorCB.addActionListener(FontThing);
    ControlStuff.add(new JLabel("Color: "));
    ControlStuff.add(BackgroundSelectorCB); 
  
   // setBackground(Color.green); 
   // setForeground(); 
    
    this.add(ControlStuff, BorderLayout.SOUTH);
    FontThing.updateText();

    this.setVisible(true);
  }

  private class Listener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      updateText();
    }

    public void updateText() {
      String name = (String) FontSelectorCB.getSelectedItem();

      Integer size = (Integer) SizeSelectorCB.getSelectedItem();
      

      int style;
      if (boldCheckB.isSelected() && italCheckB.isSelected())
        style = Font.BOLD | Font.ITALIC;
      else if (boldCheckB.isSelected())
        style = Font.BOLD;
      else if (italCheckB.isSelected())
        style = Font.ITALIC;
      else
        style = Font.PLAIN;
      
   

      Font f = new Font(name, style, size.intValue());
      TheWords.setFont(f);
   
      
      //Background B = new Background(colors);
     // setForeground(B);
      setBackground(Color.green); 
      
   
      
      
    
      
      
    }
  }
 
}
