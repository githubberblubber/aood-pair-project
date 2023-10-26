import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class ToggleMenu extends JPanel {
    NonogramGrid grid;
    int dimX;
    int dimY;
    int posX;
    int posY;
    int lineThickness = 5;
    int buttonLength = 30;
    int buttonSpacing = 20;
    int buttonInitialX = 100;
    int buttonInitialY = 0;
    ToggleMenu(NonogramGrid g) {
        this.grid = g;
        posX = 50;
        posY = 710;
        dimX = 500;
        dimY = 40;

        this.setBounds(posX, posY, dimX, dimY);
        this.addMouseListener(new mouse());
    }

    public void paintComponent(Graphics g) {
        g.setColor(new Color(226, 195, 145));
        g.fillRoundRect(0, 0, dimX, dimY, 5, 5);
        g.setColor(Color.black);
        g.drawRoundRect(0, 0, dimX, dimY, 5, 5);
        Color[] buttonColors = {
            Color.white,
            Color.black,
            Color.green,
            Color.white
        };
        for (int i = 0; i < buttonColors.length; i++) {
            g.setColor(buttonColors[i]);
            g.fillRoundRect(buttonInitialX + (buttonLength + buttonSpacing) * i,
                buttonInitialY, buttonLength, buttonLength, 5, 5);
            if(i==3) {
            	g.setColor(Color.black);
            	g.drawLine(buttonInitialX + (buttonLength + buttonSpacing) * i, buttonInitialY, (buttonInitialX + (buttonLength + buttonSpacing) * i)+buttonLength, buttonInitialY+buttonLength);
            	g.drawLine((buttonInitialX + (buttonLength+buttonSpacing)*i)+buttonLength, buttonInitialY, buttonInitialX + (buttonLength + buttonSpacing) * i, buttonInitialY+buttonLength);
            }
        }
    }

    public class mouse extends MouseFiller {
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            int mode = (x - buttonInitialX) / (buttonLength + buttonSpacing);
            if (y > buttonInitialY && y < buttonInitialY + buttonLength &&
                !inSpacing(x, y)) {
                grid.setMode(mode);
            }
        }
        public boolean inSpacing(int x, int y) {
            x = x - buttonInitialX - buttonLength;
            boolean isInSpacing = false;
            for (int i = 0; i < 4 && !isInSpacing; i++) {
                isInSpacing = x > (buttonLength + buttonSpacing) * i &&
                    x < ((buttonLength + buttonSpacing) * i + buttonSpacing);
            }
            return isInSpacing;
        }
    }
}



