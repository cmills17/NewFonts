//This is a test change
import java.awt.BorderLayout;
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

public class FontProject extends JFrame
{
	public static void main(String[] args)
	{
		new FontProject();
	}

	private JLabel TheWords = new JLabel("HELLOOOOO");
	private JComboBox FontSelectorCB;
	private JComboBox SizeSelectorCB;
	private JCheckBox boldCheckB, italCheckB;
	private JComboBox BackgroundSelectorCB;
	private String[] fonts;
	private JToggleButton tglbtnEditBackground;
	private boolean background = false;

	public FontProject()
	{
		this.setSize(900, 235);
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

			Font f = new Font(name, style, size.intValue());
			TheWords.setFont(f);

		}
	}

}
