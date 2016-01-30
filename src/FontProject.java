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

		JPanel FontColor = new JPanel();   //creates Panel that lets you choose colors for font
		final JColorChooser colorChooser = new JColorChooser(
				TheWords.getBackground());  //takes the string that equals "the words" and sets it to color you selected
		colorChooser.setBorder(
				BorderFactory.createTitledBorder("Pick Foreground Color")); //puts these words next to the box so user knows what it controls
		ColorSelectionModel model = colorChooser.getSelectionModel();  //lets you see the colors in different model
		ChangeListener changeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent changeEvent) //once the color has changed, let this be evident by the words
			{
				Color newForegroundColor = colorChooser.getColor(); //get the color the user chose and make that the new foreground color 
				if (background)  //if on background, 
				{
					getContentPane().setBackground(newForegroundColor);//change the background to a new color
				}
				else //if not on background, it will get the words and set them to a new color
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
		fonts = g.getAvailableFontFamilyNames(); //creates the list "fonts" by using a command that imports all the fonts

		JPanel ControlStuff = new JPanel();   //creates combo box that has all the fonts in it
		FontSelectorCB = new JComboBox(fonts);
		FontSelectorCB.addActionListener(FontThing);
		ControlStuff.add(new JLabel("font: ")); //puts the words "font :" next to the box so that user knows what it controls
		ControlStuff.add(FontSelectorCB);  //adds the Font Selector combo box to 

		italCheckB = new JCheckBox("italics");  //makes a checkbox and labels it italics
		italCheckB.addActionListener(FontThing);  
		ControlStuff.add(italCheckB); //adds the check box onto the panel

		boldCheckB = new JCheckBox("bold"); //puts "bold" next to the checkbox, so user knows what it does
		boldCheckB.addActionListener(FontThing);
		ControlStuff.add(boldCheckB);

		Integer[] sizes = { 12, 14, 18, 20, 22, 24, 36 }; //creates a list of numbers called sizes which will be used to change font size

		SizeSelectorCB = new JComboBox(sizes);
		SizeSelectorCB.setSelectedIndex(4);  //sets font to the fourth number in the list (20) each time you open the JFrame
		SizeSelectorCB.addActionListener(FontThing);
		ControlStuff.add(new JLabel("size: ")); //puts the words "Size :" in front of the combo box with the sizes, so you know what it does
		ControlStuff.add(SizeSelectorCB); //puts the combo box that lets user change the size next to the box

		getContentPane().add(ControlStuff, BorderLayout.SOUTH);

		tglbtnEditBackground = new JToggleButton("Edit Background"); //creates toggle button labeled "Edit Background"
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
		ControlStuff.add(tglbtnEditBackground); //adds the toggle button to the bottom of the JFrame
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
			String name = (String) FontSelectorCB.getSelectedItem(); //

			Integer size = (Integer) SizeSelectorCB.getSelectedItem(); //whatever number the user chose from the sizes combo box, remember it as Integer: 'size'

			int style;
			if (boldCheckB.isSelected() && italCheckB.isSelected())  //if bold and italics are checked, set the style to that
				style = Font.BOLD | Font.ITALIC;
			else if (boldCheckB.isSelected()) //if just bold is selected, set the style of font to bold
				style = Font.BOLD;
			else if (italCheckB.isSelected()) //if ital is checked, set the style of font to ital
				style = Font.ITALIC;
			else //if nothing is checked, leave the font style as is
				style = Font.PLAIN;

			Font f = new Font(name, style, size.intValue());  //sets the input of text "the Words" to whatever font, size, etc. we want
			TheWords.setFont(f);

		}
	}

}
