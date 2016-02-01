import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ColorSample {

  public static void main(String args[]) {
    JFrame frame = new JFrame("JColorChooser Popup");
    //creates the JFrame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //exits the frame when closed as the default. It's only what makes sense
    Container contentPane = frame.getContentPane();
    //creates the container where the label and the colorchooser will be located

    final JLabel label = new JLabel("I Love Swing", JLabel.CENTER);
    //makes a new label
    label.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 48));
    //makes the font of the label, although not customizable like the other fonts of the project
    contentPane.add(label, BorderLayout.SOUTH);
    //another label

    final JColorChooser colorChooser = new JColorChooser(label
        .getBackground());
    //this is where the JColorChooser is made
    colorChooser.setBorder(BorderFactory
        .createTitledBorder("Pick Foreground Color"));
    //sets the border to say Pick Foreground color so the user knows what to do

    ColorSelectionModel model = colorChooser.getSelectionModel();
    //creates the choosing pane with all the colors
    ChangeListener changeListener = new ChangeListener() {
    //sees when the foreground, or background color changes
      public void stateChanged(ChangeEvent changeEvent) {
        Color newForegroundColor = colorChooser.getColor();
        label.setForeground(newForegroundColor);
      }
    };
    model.addChangeListener(changeListener);
    //creates a new changeListener
    contentPane.add(colorChooser, BorderLayout.CENTER);
    //another border

    frame.pack();
    frame.setVisible(true);
    //now you can see the frame
  }
}
