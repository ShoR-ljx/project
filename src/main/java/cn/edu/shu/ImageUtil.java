package cn.edu.shu;


import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 * @ Author     ：Liu-jianxiang
 * @ Date       ：Created in 2020/5/9 - 10:53
 * @ Description：用来旋转图片，避免加载多张图片
 * @ Modified By：
 * @Version:
 */
public class ImageUtil {
    public static BufferedImage rotateImage(final BufferedImage bufferedimage,
                                            final int degree) {
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(w, h, type))
                .createGraphics()).setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();
        return img;
    }
}
