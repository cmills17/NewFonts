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

public class fontComboBox extends JFrame implements ActionListener {
  JLabel fontLabel = new JLabel(
      "The quick brown fox jumps over the lazy dog.");

  private JComboBox fontComboBox;

  public fontComboBox() {
    setTitle("ComboBoxTest");
    setSize(300, 200);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    fontComboBox = new JComboBox();
    fontComboBox.setEditable(true);
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    String[] names = ge.getAvailableFontFamilyNames();
    for ( int i=0; i<names.length; i++ )
       {
    fontComboBox.addItem(names[i]);
    fontComboBox.addActionListener(this);
    }

    JPanel p = new JPanel();
    p.add(fontComboBox);
    getContentPane().add(p, "North");
    getContentPane().add(fontLabel, "Center");
  }

  public void actionPerformed(ActionEvent evt) {
    JComboBox source = (JComboBox) evt.getSource();
    String item = (String) source.getSelectedItem();
    fontLabel.setFont(new Font(item, Font.PLAIN, 12));
  }

  public static void main(String[] args) {
    JFrame frame = new fontComboBox();
    frame.show();
  }
}
           
         