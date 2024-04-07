import java.awt.AWTException;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MouseMoverSwing extends Frame implements ActionListener {
 
	private static final long serialVersionUID = 1L;
	private static final int FIVE_SECONDS = 5000;
    private static final int MAX_Y = 400;
    private static final int MAX_X = 400;
    private volatile boolean running =false;
    private Thread mouseThread;
    static JFrame f;
    private boolean teamsRunning = isProcessRunning("Teams.exe");

    MouseMoverSwing() {

    }

    public static void main(String args[]) throws Exception {
        MouseMoverSwing mouseMoverSwing = new MouseMoverSwing();
        f = new JFrame("ROBOT MOUSE");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton run = new JButton("RUN");
        JButton stop = new JButton("STOP");
//        JButton check = new JButton("CHECK TEAMS");

        run.addActionListener(mouseMoverSwing);
        stop.addActionListener(mouseMoverSwing);
//        check.addActionListener(mouseMoverSwing);


        Panel p = new Panel();
        p.add(run);
        p.add(stop);
//        p.add(check);

        f.add(p);
        f.setSize(300, 100);
        f.show();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();

        if (command.equals("RUN")) {
            if (!running) {
                try {
					startMouseThread();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
            }
        } else if (command.equals("STOP")) {
            // Stop moving the mouse cursor
            stopMouseThread();
        }
//        else if(command.equals("CHECK TEAMS")) {
//        	if(teamsRunning) {
//        		System.out.println("already running");
//        	}else{
//        		System.out.println("not runned yet");
//        	}
//        }

    }

    public void startMouseThread() throws IOException{
        running = true;
        
        
        if (teamsRunning) {
        	System.out.println("Microsoft Teams already running.");
        } else {
        	Runtime.getRuntime().exec("C:\\Users\\Setup Game\\AppData\\Local\\Microsoft\\Teams\\current\\Teams.exe", null, new File("C:\\Users\\Setup Game\\AppData\\Local\\Microsoft\\Teams\\current"));
        }
        mouseThread = new Thread(() -> {
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
        });
        mouseThread.start();

    };
    public void stopMouseThread(){
        running = false;
        if (mouseThread != null) {
            mouseThread.interrupt();
            mouseThread = null;
        }
    };
    public static boolean isProcessRunning(String processName) {
        try {
            Process process = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(processName)) {
                    return true;
                }
            }

            reader.close();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

}