import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.io.BufferedReader;
import java.io.IOException; // Import the IOException class to handle errors
import java.io.FileWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.awt.Image;
import javax.swing.*;

public class NonogramFullRunner{
	public static JFrame frontScreen;
	static JButton startButton;
	public static NonogramScreen level;
	public static void main(String[] args) {
		run();
	}
	
	public static void run() {
		frontScreen = new JFrame();
		frontScreen.setSize(768, 768);
		frontScreen.setLayout(null);
		startButton = new StartButton();
		frontScreen.add(startButton);
		frontScreen.setVisible(true);
		frontScreen.setDefaultCloseOperation(frontScreen.EXIT_ON_CLOSE);
	}
	public static class StartButton extends JButton implements ActionListener{
		StartButton(){
			this.setBounds(frontScreen.getWidth() / 2 - 100, frontScreen.getHeight() / 2 - 25, 200, 50);
			this.setText("Nono-ing grams");
			this.addActionListener(this);
		}
		public void actionPerformed(ActionEvent e) {
			frontScreen.setVisible(false);
		}
	}
	public static void win() {
		
	}
}
