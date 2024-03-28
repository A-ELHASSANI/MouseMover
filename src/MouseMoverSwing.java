import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MouseMoverSwing extends Frame implements ActionListener {
    public static final int FIVE_SECONDS = 5000;
    public static final int MAX_Y = 400;
    public static final int MAX_X = 400;

    static JFrame f;

    MouseMoverSwing() {

    }

    // main function
    public static void main(String args[]) throws Exception {
        MouseMoverSwing MouseMoverSwing = new MouseMoverSwing();
        f = new JFrame("ROBOT MOUSE");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton run = new JButton("RUN");
        JButton stop = new JButton("STOP");
        run.addActionListener(MouseMoverSwing);

        Panel p = new Panel();
        p.add(run);
        p.add(stop);

        f.add(p);
        f.setSize(300, 100);
        f.show();
    }

    // if button is pressed

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            Robot robots = new Robot();
            Random random = new Random();

            while (true) {
                robots.mouseMove(random.nextInt(MAX_X), random.nextInt(MAX_Y));

                Thread.sleep(FIVE_SECONDS);

            }
        } catch (AWTException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}