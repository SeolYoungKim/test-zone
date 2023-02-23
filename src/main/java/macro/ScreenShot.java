package macro;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ScreenShot {
    public static final long serialVersionUID = 1L;
    public static void main(String[] args) throws IOException {
        capture("/Users/seolyoungkim/Desktop/study/test-zone/test-zone/sh.png");
    }

     static void capture(final String path) {
        try {
            Thread.sleep(120);
            Robot r = new Robot();

            // Used to get ScreenSize and capture image
            Rectangle capture = new Rectangle(403, 70, 703, 904);
//                    new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage Image = r.createScreenCapture(capture);
            ImageIO.write(Image, "png", new File(path));
            System.out.println("Screenshot saved");
        }
        catch (AWTException | IOException | InterruptedException ex) {
            System.out.println(ex);
        }
    }
}