import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class Game {
    static Puzzles puzzles;
    static int mode;
    static int x = 5;
    static int y = 5;
    static int progress = 0;
    static boolean complete;

    /*
     * either: each level is one game OR the game controls the progression of levels
     */

    public static JFrame frontScreen;
    static JButton startButton;
    static JButton helpButton;
    public static JPanel contentPane;
    public static NonogramScreen level;
    public static void main(String[] args) {
        run();
        complete = false;
        puzzles = new Puzzles();
        mode = 1;
    }

    public static void run() {
        frontScreen = new JFrame();
        frontScreen.setSize(768, 768);
        frontScreen.setLayout(null);
        contentPane = new JPanel();
        contentPane.setSize(768,768);
        contentPane.setLayout(new GridLayout(2,0, 10, 5));
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frontScreen.add(contentPane);
		
        startButton = new StartButton();
       
        helpButton = new HelpButton();
        
        contentPane.add(helpButton);
        contentPane.add(startButton); 
        helpButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        helpButton.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        startButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        startButton.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        contentPane.setBackground(new Color(226, 195, 145));
        frontScreen.setContentPane(contentPane);
        frontScreen.setVisible(true);
        frontScreen.setDefaultCloseOperation(frontScreen.EXIT_ON_CLOSE);
    }
    public static class StartButton extends JButton implements ActionListener {
        StartButton() {
            this.setBounds(frontScreen.getWidth() / 2 - 100, frontScreen.getHeight() / 2 - 25, 200, 50);
            this.setText("Nono-ing grams");
            this.setBackground(new Color(155, 190, 199));
            this.addActionListener(this);
        }
        public void actionPerformed(ActionEvent e) {
            frontScreen.setVisible(false);
            System.out.println(true);
            level = new NonogramScreen(new NonogramGrid(puzzles.getPuzzle(progress)));
        }
    }
    
    public static class HelpButton extends JButton implements ActionListener{
		private JFrame help;
		private JPanel content;
		private JTextArea instruct;
		private JButton next, back;
		private String[] conList = {"Nonograms, also known as Griddlers or Picross, are picture logic puzzles.", 
				"The game is simple. The numbers on the top and sides of the grid tell you how many black squares there are before an empty space.",
				"The numbers will highight red when there's a contradiction with the hints or green if the row/column has been solved.",  
				"Click on the box with the 'X' inside to eliminate change your click mode to eliminate spaces.",
				"Click on the green box to change your click mode to mark spaces to look at later.",
				"Click on the black box to change your click mode to fill in spaces.",
				"When a puzzle is completely solved, there will be a button to move onto the next puzzle!",
				"Good luck, and happy puzzling!"}; 
		private int i=0;
		
		HelpButton(){
			setBounds(600, 50, 100, 50);
			setText("Help");
			this.addActionListener(this);
			this.setActionCommand("Help");
			this.setBackground(new Color(155, 190, 199));
			
			help = new JFrame("Help");
			help.setSize(500, 500);
			help.setLayout(null);
			
			content = new JPanel();
			content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
			content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			help.add(content);
			
			content.setBackground(new Color(226, 195, 145));
			instruct = new JTextArea(conList[0]);
			instruct.setEditable(false);
			instruct.setBackground(new Color(226, 195, 145));
			instruct.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			content.add(instruct);
			
			next = new JButton("Next");
			next.addActionListener(this);
			next.setActionCommand("next");
			
			next.setBackground(new Color(155, 190, 199));
			content.add(next);
			
			back = new JButton("Back to main screen");
			back.addActionListener(this);
			back.setActionCommand("Back");
			back.setBackground(new Color(155, 190, 199));
			content.add(back);
			
			help.setContentPane(content);
			
			help.pack();
			help.setVisible(false);
		}
		
		public void actionPerformed(ActionEvent event) {
			
			String e = event.getActionCommand();
			if(e=="Help") {
				help.pack();
				help.setVisible(true);
				i=0;
			}
			if(e=="Back") {
				help.setVisible(false);
				i=0;
			}
			if(e=="next") {
				i++;
				if(i<conList.length) {
					instruct.setText(conList[i]);
					instruct.setWrapStyleWord(true);
					help.pack();
				} else {
					i=0;
					instruct.setText(conList[i]);
					instruct.setWrapStyleWord(true);
					help.pack();
				}
			}
		}
	}

    public static void win() {
        setProgress();
        level = null;
        System.out.println(progress + " yeah");
        level = new NonogramScreen(new NonogramGrid(puzzles.getPuzzle(progress)));
    }

    public void setMode(int m) {
        mode = m;
    }

    public int getMode() {
        return mode;
    }

    public Puzzles getPuzzles() {
        return puzzles;
    }

    static public void setProgress() {
        progress++;
    }

}