//Sources: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html, http://www.java2s.com/Code/Java/Swing-JFC/FontChooserComboBox.htm 
//Sources: https://docs.oracle.com/javase/tutorial/uiswing/components/button.html, https://docs.oracle.com/javase/7/docs/api/javax/swing/JPanel.html
//Sources: http://stackoverflow.com/questions/4219919/how-to-change-the-background-color-on-a-java-panel, http://stackoverflow.com/questions/7524536/getting-the-state-of-jtogglebutton
//Sources:http://codereview.stackexchange.com/questions/78591/add-jpanel-to-jframe, http://stackoverflow.com/questions/20959053/jframe-buttons-that-change-background-color-of-window
//Sources: http://www.java2s.com/Code/Java/Swing-JFC/ColorChooserDemo.htm, http://www.java2s.com/Code/JavaAPI/javax.swing/JFramesetBackgroundColorc.htm
//Sources: http://stackoverflow.com/questions/1081486/setting-background-color-for-the-jframe, https://docs.oracle.com/javase/tutorial/uiswing/events/intro.html 
//Sources: http://stackoverflow.com/questions/20959053/jframe-buttons-that-change-background-color-of-window, http://www.dreamincode.net/forums/topic/140503-japplet-using-jpanel/
//Sources: http://www.dmc.fmph.uniba.sk/public_html/doc/Java/ch9.htm#TestingandSettingtheCurrentColors 
import java.awt.BorderLayout;     //imports all the stuff required to have the code run, we use commands that only work when these things are imported
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JToggleButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class FontProject extends JFrame      //making a JFrame in our class FontProject
{
	public static void main(String[] args)    
	{
		new FontProject();
	}

	private JLabel TheWords = new JLabel("HELLO JD. Please give us a good grade");  //creates JLabel "The Words" which is whatever we type in the box, will be the words that are affected by the code
	private JComboBox FontSelectorCB;   //makes combo box that allows user to select font
	private JComboBox SizeSelectorCB; //makes combo box that allows user to select size
	private JCheckBox boldCheckB, italCheckB; //makes checkboxes that allows user to ital or bold the JLabel
	private JComboBox BackgroundSelectorCB;   //Creates combo box that lets user select background, not e
	private String[] fonts;
	private JToggleButton tglbtnEditBackground;  //makes the toggle button which allows user to switch between using the color options to change the font or the background
	private boolean background = false;  //sets the color options to at first be changing the font color

	public FontProject() 
	{
		this.setSize(900, 600); //makes the size of the JFrame that will pop up 900x600
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		JPanel FontColor = new JPanel();
		final JColorChooser colorChooser = new JColorChooser(
				TheWords.getBackground());
		colorChooser.setBorder(
				BorderFactory.createTitledBorder("Pick Foreground Color"));
		ColorSelectionModel model = colorChooser.getSelectionModel();
		ChangeListener changeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent changeEvent)
			{
				Color newForegroundColor = colorChooser.getColor();
				if (background)
				{
					getContentPane().setBackground(newForegroundColor);
				}
				else
				{
					TheWords.setForeground(newForegroundColor);
				}
			}
		};
		model.addChangeListener(changeListener);
		this.getContentPane().add(colorChooser, BorderLayout.CENTER);

		Listener FontThing = new Listener();
		getContentPane().add(TheWords, BorderLayout.NORTH);
		GraphicsEnvironment g = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
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

		Integer[] sizes = { 12, 14, 18, 20, 22, 24, 36 };

		SizeSelectorCB = new JComboBox(sizes);
		SizeSelectorCB.setSelectedIndex(4);
		SizeSelectorCB.addActionListener(FontThing);
		ControlStuff.add(new JLabel("size: "));
		ControlStuff.add(SizeSelectorCB);

		getContentPane().add(ControlStuff, BorderLayout.SOUTH);

		tglbtnEditBackground = new JToggleButton("Edit Background");
		tglbtnEditBackground.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e)
			{
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					background = true;
					tglbtnEditBackground.setText("Edit Foreground");
				}
				else if (e.getStateChange() == ItemEvent.DESELECTED)
				{
					background = false;
					tglbtnEditBackground.setText("Edit Background");
				}
			}
		});
		ControlStuff.add(tglbtnEditBackground);
		FontThing.updateText();

		this.setVisible(true);
	}

	private class Listener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			updateText();
		}

		public void updateText()
		{
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

			Font f = new Font(name, style, size.intValue());  //sets the input of text "the Words" to whatever font, size, etc. we want
			TheWords.setFont(f);

		}
	}

}
