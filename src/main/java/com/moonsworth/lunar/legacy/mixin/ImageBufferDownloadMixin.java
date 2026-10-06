package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.ichor.VersionGate;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.ImageObserver;
import net.minecraft.client.renderer.ImageBufferDownload;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@VersionGate(max = 0)
@Mixin(ImageBufferDownload.class)
public abstract class ImageBufferDownloadMixin {
   @Shadow
   public int[] imageData;
   @Shadow
   public int imageWidth;
   @Shadow
   public int imageHeight;

   public ImageBufferDownloadMixin() {
   }

   @Overwrite
   public BufferedImage parseUserSkin(BufferedImage bufferedimage1) {
      if (bufferedimage1 == null) {
         return null;
      }

      this.imageWidth = 64;
      this.imageHeight = 64;
      int number2 = bufferedimage1.getWidth();
      int number3 = bufferedimage1.getHeight();

      byte number4;
      for (number4 = 1; this.imageWidth < number2 || this.imageHeight < number3; number4 *= 2) {
         this.imageWidth *= 2;
         this.imageHeight *= 2;
      }

      BufferedImage bufferedimage5 = new BufferedImage(this.imageWidth, this.imageHeight, 2);
      Graphics graphics6 = bufferedimage5.getGraphics();
      graphics6.drawImage(bufferedimage1, 0, 0, (ImageObserver)null);
      if (bufferedimage1.getHeight() == 32 * number4) {
         graphics6.drawImage(bufferedimage5, 24 * number4, 48 * number4, 20 * number4, 52 * number4, 4 * number4, 16 * number4, 8 * number4, 20 * number4, (ImageObserver)null);
         graphics6.drawImage(bufferedimage5, 28 * number4, 48 * number4, 24 * number4, 52 * number4, 8 * number4, 16 * number4, 12 * number4, 20 * number4, (ImageObserver)null);
         graphics6.drawImage(bufferedimage5, 20 * number4, 52 * number4, 16 * number4, 64 * number4, 8 * number4, 20 * number4, 12 * number4, 32 * number4, (ImageObserver)null);
         graphics6.drawImage(bufferedimage5, 24 * number4, 52 * number4, 20 * number4, 64 * number4, 4 * number4, 20 * number4, 8 * number4, 32 * number4, (ImageObserver)null);
         graphics6.drawImage(bufferedimage5, 28 * number4, 52 * number4, 24 * number4, 64 * number4, 0 * number4, 20 * number4, 4 * number4, 32 * number4, (ImageObserver)null);
         graphics6.drawImage(bufferedimage5, 32 * number4, 52 * number4, 28 * number4, 64 * number4, 12 * number4, 20 * number4, 16 * number4, 32 * number4, (ImageObserver)null);
         graphics6.drawImage(bufferedimage5, 40 * number4, 48 * number4, 36 * number4, 52 * number4, 44 * number4, 16 * number4, 48 * number4, 20 * number4, (ImageObserver)null);
         graphics6.drawImage(bufferedimage5, 44 * number4, 48 * number4, 40 * number4, 52 * number4, 48 * number4, 16 * number4, 52 * number4, 20 * number4, (ImageObserver)null);
         graphics6.drawImage(bufferedimage5, 36 * number4, 52 * number4, 32 * number4, 64 * number4, 48 * number4, 20 * number4, 52 * number4, 32 * number4, (ImageObserver)null);
         graphics6.drawImage(bufferedimage5, 40 * number4, 52 * number4, 36 * number4, 64 * number4, 44 * number4, 20 * number4, 48 * number4, 32 * number4, (ImageObserver)null);
         graphics6.drawImage(bufferedimage5, 44 * number4, 52 * number4, 40 * number4, 64 * number4, 40 * number4, 20 * number4, 44 * number4, 32 * number4, (ImageObserver)null);
         graphics6.drawImage(bufferedimage5, 48 * number4, 52 * number4, 44 * number4, 64 * number4, 52 * number4, 20 * number4, 56 * number4, 32 * number4, (ImageObserver)null);
      }

      graphics6.dispose();
      this.imageData = ((DataBufferInt)bufferedimage5.getRaster().getDataBuffer()).getData();
      this.setAreaOpaque(0 * number4, 0 * number4, 32 * number4, 16 * number4);
      this.setAreaTransparent(32 * number4, 0 * number4, 64 * number4, 32 * number4);
      this.setAreaOpaque(0 * number4, 16 * number4, 64 * number4, 32 * number4);
      this.setAreaTransparent(0 * number4, 32 * number4, 16 * number4, 48 * number4);
      this.setAreaTransparent(16 * number4, 32 * number4, 40 * number4, 48 * number4);
      this.setAreaTransparent(40 * number4, 32 * number4, 56 * number4, 48 * number4);
      this.setAreaTransparent(0 * number4, 48 * number4, 16 * number4, 64 * number4);
      this.setAreaOpaque(16 * number4, 48 * number4, 48 * number4, 64 * number4);
      this.setAreaTransparent(48 * number4, 48 * number4, 64 * number4, 64 * number4);
      return bufferedimage5;
   }

   public void setAreaTransparent(int number1, int number2, int number3, int number4) {
      if (!this.hasTransparency(number1, number2, number3, number4)) {
         for (int index5 = number1; index5 < number3; index5++) {
            for (int index6 = number2; index6 < number4; index6++) {
               int[] items7 = this.imageData;
               int index8 = index5 + index6 * this.imageWidth;
               items7[index8] &= 16777215;
            }
         }
      }
   }

   public void setAreaOpaque(int number1, int number2, int number3, int number4) {
      for (int index5 = number1; index5 < number3; index5++) {
         for (int index6 = number2; index6 < number4; index6++) {
            int[] items7 = this.imageData;
            int index8 = index5 + index6 * this.imageWidth;
            items7[index8] |= -16777216;
         }
      }
   }

   public boolean hasTransparency(int number1, int number2, int number3, int number4) {
      for (int index5 = number1; index5 < number3; index5++) {
         for (int index6 = number2; index6 < number4; index6++) {
            int number7 = this.imageData[index5 + index6 * this.imageWidth];
            if ((number7 >> 24 & 0xFF) < 128) {
               return true;
            }
         }
      }

      return false;
   }
}
