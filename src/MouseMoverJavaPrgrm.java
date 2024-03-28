import java.awt.*;
import java.util.Random;

public class MouseMoverJavaPrgrm  {

    public static final int FIVE_SECONDS = 5000;
    public static final int MAX_Y = 400;
    public static final int MAX_X = 400;

    MouseMoverJavaPrgrm() throws Exception{
       Robot robots = new Robot();
        Random random = new Random();
        while(true){
            robots.mouseMove(random.nextInt(MAX_X),random.nextInt(MAX_Y));
            Thread.sleep(FIVE_SECONDS);
        }
    }

    public static void main(String[] args) throws Exception {
        new MouseMoverJavaPrgrm();
    }
}
