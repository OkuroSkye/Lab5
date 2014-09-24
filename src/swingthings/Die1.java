package swingthings;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Scatt on 9/23/2014.
 */
public class Die1 extends Die {

    public static void main(String[] args){

        Die1 die = new Die1();
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(die);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        g.setColor(Color.cyan);
        g.fillOval(90,40,20,20);
    }
}
