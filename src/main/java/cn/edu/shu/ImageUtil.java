package cn.edu.shu;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @ Author     ：Liu-jianxiang
 * @ Date       ：Created in 2020/5/9 - 10:53
 * @ Description：用来旋转图片，避免加载多张图片
 * @ Modified By：
 * @Version:
 */
public class ImageUtil {
    public static BufferedImage rotateImage(final BufferedImage bufferedImage,final int degree){
        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();
        int type = bufferedImage.getColorModel().getTransferType();
        BufferedImage img;
        Graphics2D graphics2D;
        (graphics2D = (img = new BufferedImage(w,h,type)).createGraphics())
                .setRenderingHint( RenderingHints.KEY_INTERPOLATION,
                        RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.rotate(Math.toRadians(degree), w / 2, h / 2);
        graphics2D.drawImage(bufferedImage, 0, 0, null);
        graphics2D.dispose();
        return img;





    }
}
