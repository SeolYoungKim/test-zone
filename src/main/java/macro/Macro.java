package macro;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

public class Macro {
    public static void main(String[] args)
            throws AWTException, InterruptedException, DocumentException, IOException {
        final Robot robot = new Robot();
        moveMouse(robot);
        clickWindow(robot);

        for (int i = 0; i < 15; i++) {
            final BufferedImage screenshot = robot.createScreenCapture(
                    new Rectangle(403, 70, 703, 904));
            final Image pdfImage = Image.getInstance(screenshot, null);
            pdfImage.setCompressionLevel(0);

            final Document document = new Document(pdfImage);
            PdfWriter.getInstance(document, new FileOutputStream(
                    "/Users/seolyoungkim/Documents/capture_macro/index_" + i + ".pdf"));

            document.open();
            document.add(pdfImage);
            document.close();

            Thread.sleep(500);

            nextPage(robot);
            Thread.sleep(1000);
        }
    }

    private static void moveMouse(final Robot robot) {
        robot.mouseMove(500, 48);
        robot.delay(100);
    }

    private static void clickWindow(final Robot robot) {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(100);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(100);
    }

    private static void nextPage(final Robot robot) throws InterruptedException {
        robot.keyPress(KeyEvent.VK_RIGHT);
        robot.keyRelease(KeyEvent.VK_RIGHT);
        robot.delay(100);
    }
}
