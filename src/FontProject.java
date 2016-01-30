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
	public static void main(String[] args)    //starts the code
	{
		new FontProject(); //is what lets it all run, all the code is part of this
	}

	private JLabel TheWords = new JLabel("HELLO JD. Please give us a good grade");  //creates JLabel "The Words" which is whatever we type in the box, will be the words that are affected by the code
	private JComboBox FontSelectorCB;   //makes combo box that allows user to select font--this line just names it. 
	private JComboBox SizeSelectorCB; //makes combo box that allows user to select size
	private JCheckBox boldCheckB, italCheckB; //makes checkboxes that allows user to ital or bold the JLabel-these name the variables
	private JComboBox BackgroundSelectorCB;   //Creates combo box that lets user select background--just naming it
	private String[] fonts; //makes a list that will hold whatever value for "fonts"--eventually i put in the list of fonts you gave us
	private JToggleButton tglbtnEditBackground;  //makes the toggle button which allows user to switch between using the color options to change the font or the background--right now, just names it
	private boolean background = false;  //sets the color options to at first be changing the font color, 

	public FontProject()  //after this, things will actually start to change
	{
		this.setSize(900, 600); //makes the size of the JFrame that will pop up 900x600
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //makes the Jframe close when you click close

		JPanel FontColor = new JPanel();   //creates Panel 
		final JColorChooser colorChooser = new JColorChooser( //JColorChooser is an already made thing cuz of what I imported, so this saves it in the variable
				TheWords.getBackground());  //takes the "the words" and lets you pick from the colorChooser to set their color
		colorChooser.setBorder(
				BorderFactory.createTitledBorder("Pick Foreground Color")); //puts these words next to the box so user knows what it controls
		ColorSelectionModel model = colorChooser.getSelectionModel();  //lets you see the colors in different model, this is possible cuz something I imported
		ChangeListener changeListener = new ChangeListener() { //defines a listener that will wait for  a change
			public void stateChanged(ChangeEvent changeEvent) //defining the listener for what happens when you click in the color chooser
			{
				Color newForegroundColor = colorChooser.getColor(); //gets the color and stores it 
				if (background)  //if on  background, set background color to the selected color from the chooser
				{
					getContentPane().setBackground(newForegroundColor);//gets the color and stores it
				}
				else //if not on background, it will change the font color
				{
					TheWords.setForeground(newForegroundColor); //changes font color
				}
			}
		};
		model.addChangeListener(changeListener); //adds the listener onto the JFrame
		this.getContentPane().add(colorChooser, BorderLayout.CENTER); //puts it in the center of the layout

		Listener FontThing = new Listener(); //creates a listener called FontThing
		getContentPane().add(TheWords, BorderLayout.NORTH); //puts "The Words" onto it and puts them at the top of the screen
		GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment(); //gets the graphics enviornment and saves it into "g"
		fonts = g.getAvailableFontFamilyNames(); //makes the list "fonts" contain what g has which is the font names

		JPanel ControlStuff = new JPanel();   //creates JPanel 
		FontSelectorCB = new JComboBox(fonts);  //makes combo box that contains the list of fonts
		FontSelectorCB.addActionListener(FontThing); //add FontThing Listener to the combo box for fonts
		ControlStuff.add(new JLabel("font: ")); //puts the words "font :" next to the box so that user knows what it controls
		ControlStuff.add(FontSelectorCB);  //puts the combo box onto the panel

		italCheckB = new JCheckBox("italics");  //makes a checkbox and labels it italics
		italCheckB.addActionListener(FontThing);   //add Font Thing Listener to the ital box
		ControlStuff.add(italCheckB); //adds the check box onto the panel

		boldCheckB = new JCheckBox("bold"); //puts "bold" next to the checkbox, so user knows what it does
		boldCheckB.addActionListener(FontThing); //adds  Font thing listener to bold check box
		ControlStuff.add(boldCheckB); //puts the checkbox onto the panel

		Integer[] sizes = { 12, 14, 18, 20, 22, 24, 36 }; //creates a list of numbers called sizes which will be used to change font size

		SizeSelectorCB = new JComboBox(sizes); //creates a combo box that contains the list of sizes
		SizeSelectorCB.setSelectedIndex(4);  //sets font to the fourth number in the list (20) each time you open the JFrame
		SizeSelectorCB.addActionListener(FontThing); //adds Font Thing Listener to the size selector combo box
		ControlStuff.add(new JLabel("size: ")); //puts the words "Size :" in front of the combo box with the sizes, so you know what it does
		ControlStuff.add(SizeSelectorCB); //puts the combo box that lets user change the size next to the box

		getContentPane().add(ControlStuff, BorderLayout.SOUTH); //puts all these controls at the bottom of the screen

		//this is for toggle button
		tglbtnEditBackground = new JToggleButton("Edit Background"); //creates toggle button labeled "Edit Background"
		tglbtnEditBackground.addItemListener(new ItemListener() { //creates another listener which is defined here
			public void itemStateChanged(ItemEvent e) //looks for the changed event--change in the words/background
			{
				if (e.getStateChange() == ItemEvent.SELECTED) //if the toggle button is pressed, then change background
				{
					background = true; //background is true at this point
					tglbtnEditBackground.setText("Edit Foreground"); //when background is true the label by the button should say edit foreground
				}
				else if (e.getStateChange() == ItemEvent.DESELECTED) //if toggle button is pressed the change background to false
				{
					background = false; //if it's false the toggle button should say edit edit background
					tglbtnEditBackground.setText("Edit Background");
				}
			}
		});
		ControlStuff.add(tglbtnEditBackground); //adds the toggle button to the bottom of the JFrame
		FontThing.updateText();  //updates the listener

		this.setVisible(true); //lets user be able to see it all on the JFrame
	}

	private class Listener implements ActionListener //creates a class within class called Listener 
	{
		public void actionPerformed(ActionEvent e) //the method that's going to get called 
		{
			updateText();  //what's going to be called
		}

		public void updateText() //creates function
		{
			String name = (String) FontSelectorCB.getSelectedItem(); //makes name equal whatever is selected from the font combo box

			Integer size = (Integer) SizeSelectorCB.getSelectedItem(); //whatever number the user chose from the sizes combo box, remember it as Integer: 'size'

			int style; //defines another variable
			if (boldCheckB.isSelected() && italCheckB.isSelected())  //if bold and italics are checked, set the style to have a font change and bold change
				style = Font.BOLD | Font.ITALIC;
			else if (boldCheckB.isSelected()) //if just bold is selected, set the style to bold
				style = Font.BOLD;
			else if (italCheckB.isSelected()) //if ital is checked, set the style to ital
				style = Font.ITALIC;
			else //if nothing is checked, leave the font style as is
				style = Font.PLAIN;

			Font f = new Font(name, style, size.intValue());  //sets f to have whatever style, font, size that we chose above
			TheWords.setFont(f); //makes the words change according to whatever f was set to

		}
	}

}
