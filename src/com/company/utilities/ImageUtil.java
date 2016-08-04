package com.company.utilities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by josh on 2016/07/31.
 */
public class ImageUtil {

    public ImageUtil() {
    }

    public File resizeImage(File image, int width, int height){
        File newImage = new File("resized.jpg");

        try{
            BufferedImage originalImage = ImageIO.read(image);
            int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

            BufferedImage resizeImageJpg = compressImage(originalImage, type, width, height);
            BufferedImage finalImage = roundCorners(resizeImageJpg, 200);
            ImageIO.write(finalImage, "jpg", newImage);
            newImage.deleteOnExit();

        }catch (IOException ioExcep){
            System.out.println("<----- IO Exception in Resize Image ----->");
            ioExcep.printStackTrace();
            System.out.println("<---------->\n");
        }

        return newImage;
    }

    private BufferedImage compressImage(BufferedImage originalImage, int type, int width, int height){
        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();

        return resizedImage;
    }

    /**
     * Round the corners of a given image
     * @param image The BufferedImage that will be returned with rounded corners
     * @param cornerRadius The degree to which the corners will be rounded. A higher number will result in more rounded
     *                     corners
     */
    public static BufferedImage roundCorners(BufferedImage image, int cornerRadius) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = output.createGraphics();

        // This is what we want, but it only does hard-clipping, i.e. aliasing
        // g2.setClip(new RoundRectangle2D ...)

        // so instead fake soft-clipping by first drawing the desired clip shape
        // in fully opaque white with antialiasing enabled...
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));

        // ... then compositing the image on top,
        // using the white shape from above as alpha source
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);

        g2.dispose();

        return output;
    }
}
