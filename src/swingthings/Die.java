package swingthings;

import java.awt.*;
import javax.swing.*;
/**
 * Created by Scatt on 9/23/2014.
 */
public class Die extends JComponent {
    public Die(){
        this.setPreferredSize(new Dimension(200, 200));
    }

    public static void main(String[] args){
        final Die die = new Die();

        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(die);
        frame.pack();
        frame.setVisible(true);

    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;
        Rectangle dieFace = new Rectangle(50, 0, 100, 100);
        g2D.draw(dieFace);

        g2D.drawLine(50,0,20,30);
        g2D.drawLine(50,100,20,120);
        g2D.drawLine(20,30,20,120);
        g2D.drawLine(150,100,120,120);
        g2D.drawLine(20,120,120,120);


    }
}
