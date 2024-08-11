package net.jukoz.me.utils.resources;

import net.jukoz.me.utils.LoggerUtil;
import net.jukoz.me.world.chunkgen.map.ImageUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class FileUtils {

    private static FileUtils single_instance = null;

    public static synchronized FileUtils getInstance()
    {
        if (single_instance == null)
            single_instance = new FileUtils(ClassLoader.getSystemClassLoader());

        return single_instance;
    }

    private ClassLoader classLoader;

    public FileUtils(ClassLoader classLoader){
        this.classLoader = classLoader;
    }

    public BufferedImage getResourceImage(String path) {
        try{
            return ImageUtils.fetchResourceImage(getClass().getClassLoader(), path);
        } catch (IOException e) {
            return null;
        }
    }

    public BufferedImage getRunImage(String path) {
        try{
            File f = new File(path);
            if(!f.exists())
                return null;
            return ImageIO.read(f);
        } catch (IOException e) {
            return null;
        }
    }

    public void saveImage(BufferedImage bufferedImage, String path, String fileName, FileType fileType) {
        try{
            new File(path).mkdirs();
            File f = new File(path + fileName);
            ImageIO.write(bufferedImage, fileType.extension, f);
        } catch(Exception e){
            LoggerUtil.logError("Image Utils couldn't save image for {0}.".formatted(path + fileName));
        }
    }


    /**
     * TODO : Optimise this part, it the longest process in World-Gen
     * about 60s before -> about 40s now
     */
    public static BufferedImage blur(BufferedImage image, int brushSize) {
        // Create new expended image :
        int width = image.getWidth();
        int height = image.getHeight();
        int newWidth = width + (2 * brushSize);
        int newHeight = height + (2 * brushSize);

        BufferedImage expendedImage = new BufferedImage(newWidth, newHeight, image.getType());
        Graphics2D g2d = expendedImage.createGraphics();

        // Copy image content (center)
        g2d.drawImage(image, brushSize, brushSize, null);

        // extend left & right
        g2d.drawImage(image, 0, brushSize, brushSize, height + brushSize, 0, 0, 1, height, null);
        g2d.drawImage(image, newWidth - brushSize, brushSize, newWidth, height + brushSize, width - 1, 0, width, height, null);

        // extend up & down
        g2d.drawImage(image, brushSize, 0, brushSize + width, brushSize, 0, 0, width, 1, null);
        g2d.drawImage(image, brushSize, newHeight - brushSize, brushSize + width, newHeight, 0, height - 1, width, height, null);

        // extend corners
        g2d.drawImage(image, 0, 0, brushSize, brushSize, 0, 0, 1, 1, null);
        g2d.drawImage(image, newWidth - brushSize, 0, newWidth, brushSize, width - 1, 0, width, 1, null);
        g2d.drawImage(image, 0, newHeight - brushSize, brushSize, newHeight, 0, height - 1, 1, height, null);
        g2d.drawImage(image, newWidth - brushSize, newHeight - brushSize, newWidth, newHeight, width - 1, height - 1, width, height, null);

        float[] blurKernel = new float[brushSize * brushSize];
        Arrays.fill(blurKernel, 1.0f / (brushSize * brushSize));
        Kernel kernel = new Kernel(brushSize, brushSize, blurKernel);
        ConvolveOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);

        expendedImage = op.filter(expendedImage, null);

        BufferedImage subImage = expendedImage.getSubimage(brushSize, brushSize, width, height);
        g2d.dispose();
        return subImage;
    }
}
