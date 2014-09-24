package swingthings;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Scatt on 9/23/2014.
 */
public class Die3 extends Die1 {
    public static void main(String[] args){

        Die3 die = new Die3();
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
        g.fillOval(60,10,20,20);
        g.fillOval(120,70,70,20);

    }
}
