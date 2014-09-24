package swingthings;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Scatt on 9/23/2014.
 */
public class Die5 extends Die3 {
    public static void main(String[] args){

        Die5 die = new Die5();
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
        g.setColor(Color.yellow);
        g.fillOval(60,70,20,20);
        g.fillOval(120,10,20,20);

    }
}
