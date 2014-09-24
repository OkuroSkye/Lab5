package swingthings;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by Scatt on 9/23/2014.
 */
public class Swing1 extends JFrame {

    public Swing1(){
        setSize(200, 200);
    }
    public static void main(String[] args){

        EventQueue.invokeLater(new Runnable() {
            public void run(){
                Swing1 mainFrame = new Swing1();

                mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                Random random = new Random();
                int randomNum = random.nextInt((6-1)+1);

                Die die = null;
                switch(randomNum){
                    case 1:
                        die = new Die1();
                        break;
                    case 2:
                        die = new Die2();
                        break;
                    case 3:
                        die = new Die3();
                        break;
                    case 4:
                        die = new Die4();
                        break;
                    case 5:
                        die = new Die5();
                        break;
                    case 6:
                        die = new Die6();
                        break;

                }

                mainFrame.add(die);
                mainFrame.pack();
                mainFrame.setVisible(true);
            }
        });
    }

    @Override
    public void setSize(int width, int height){
        super.setSize(width, height);
    }
}
