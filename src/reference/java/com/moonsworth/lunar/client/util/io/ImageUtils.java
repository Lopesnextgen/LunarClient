package com.moonsworth.lunar.client.util.io;

import com.google.common.io.Files;
import com.luciad.imageio.webp.WebP;
import com.moonsworth.lunar.bridge.ResourceBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import lombok.Generated;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.framework.Ref;

public final class ImageUtils {
   public static BufferedImage method1(InputStream input0, @Nullable File file1) {
      BufferedImage bufferedimage2 = null;
      if (file1 != null) {
         if (input0 != null) {
            FileUtils.copyInputStreamToFile(input0, file1);
         }

         if ("webp".equals(Files.getFileExtension(file1.getName()))) {
            ImageReader imagereader3 = null;

            try (ImageInputStream imageinputstream4 = ImageIO.createImageInputStream(file1)) {
               imagereader3 = WebP.IMAGE_READER.createReaderInstance("webp");
               imagereader3.setInput(imageinputstream4, true, true);
               bufferedimage2 = imagereader3.read(0);
            } finally {
               if (imagereader3 != null) {
                  imagereader3.dispose();
               }
            }
         } else {
            bufferedimage2 = ImageIO.read(file1);
         }
      } else {
         if (Ref.MC_VERSION >= 15 || input0 instanceof BufferedInputStream || input0 instanceof ImageInputStream || input0 instanceof FileInputStream) {
            ImageInputStream imageinputstream27 = ImageIO.createImageInputStream(input0);
            if (WebP.IMAGE_READER.canDecodeInput(imageinputstream27)) {
               ImageReader imagereader28 = null;

               try (imageinputstream27) {
                  imagereader28 = WebP.IMAGE_READER.createReaderInstance("webp");
                  imagereader28.setInput(imageinputstream27, true, true);
                  bufferedimage2 = imagereader28.read(0);
               } finally {
                  if (imagereader28 != null) {
                     imagereader28.dispose();
                  }
               }
            } else {
               bufferedimage2 = ImageIO.read(imageinputstream27);
            }
         }

         if (bufferedimage2 == null) {
            bufferedimage2 = ImageIO.read(input0);
         }
      }

      return bufferedimage2;
   }

   public static int[] method2(BufferedImage bufferedimage0, int number1, int number2, int number3, int number4, int[] items5, int index6, int index7) {
      int number8 = index6;
      int index11 = bufferedimage0.getRaster().getNumBands();
      int number12 = bufferedimage0.getRaster().getDataBuffer().getDataType();

      Object obj10 = switch (number12) {
         case 0 -> new byte[index11];
         case 1 -> new short[index11];
         default -> throw new IllegalArgumentException("Unknown data buffer type: " + number12);
         case 3 -> new int[index11];
         case 4 -> new float[index11];
         case 5 -> new double[index11];
      };
      if (items5 == null) {
         items5 = new int[index6 + number4 * index7];
      }

      for (int index13 = number2; index13 < number2 + number4; number8 += index7) {
         int index9 = number8;

         for (int index14 = number1; index14 < number1 + number3; index14++) {
            Object obj15 = bufferedimage0.getRaster().getDataElements(index14, index13, obj10);
            int number16 = bufferedimage0.getColorModel().getAlpha(obj15) << 24
               | bufferedimage0.getColorModel().getBlue(obj15) << 16
               | bufferedimage0.getColorModel().getGreen(obj15) << 8
               | bufferedimage0.getColorModel().getRed(obj15) << 0;
            items5[index9++] = number16;
         }

         index13++;
      }

      return items5;
   }

   public static BufferedImage method3(BufferedImage bufferedimage0) {
      int index1 = bufferedimage0.getWidth();
      int index2 = bufferedimage0.getHeight();
      ColorModel colormodel3 = bufferedimage0.getColorModel();
      int[] items4 = new int[index1 * index2];
      if (colormodel3.getNumColorComponents() == 1 && colormodel3.getColorSpace() != null && colormodel3.getColorSpace().getType() == 6) {
         for (int index10 = 0; index10 < index2; index10++) {
            for (int index11 = 0; index11 < index1; index11++) {
               int number12 = bufferedimage0.getRaster().getSample(index11, index10, 0);
               int number13 = colormodel3.hasAlpha() ? bufferedimage0.getRaster().getSample(index11, index10, 1) : 255;
               items4[index11 + index10 * index1] = number13 << 24 | number12 << 16 | number12 << 8 | number12;
            }
         }
      } else {
         if (colormodel3.hasAlpha() && colormodel3.isAlphaPremultiplied() && colormodel3 instanceof ComponentColorModel) {
            bufferedimage0 = new BufferedImage(colormodel3, bufferedimage0.getRaster(), false, null);
            colormodel3 = bufferedimage0.getColorModel();
         }

         Object obj5 = null;
         int index6 = 0;

         for (int index7 = 0; index7 < index2; index7++) {
            for (int index8 = 0; index8 < index1; index8++) {
               obj5 = bufferedimage0.getRaster().getDataElements(index8, index7, obj5);
               if (Ref.MC_VERSION > 5) {
                  items4[index6++] = colormodel3.getAlpha(obj5) << 24 | colormodel3.getBlue(obj5) << 16 | colormodel3.getGreen(obj5) << 8 | colormodel3.getRed(obj5);
               } else {
                  items4[index6++] = colormodel3.getAlpha(obj5) << 24 | colormodel3.getRed(obj5) << 16 | colormodel3.getGreen(obj5) << 8 | colormodel3.getBlue(obj5);
               }
            }
         }
      }

      bufferedimage0 = new BufferedImage(index1, index2, 2);
      bufferedimage0.setRGB(0, 0, index1, index2, items4, 0, index1);
      return bufferedimage0;
   }

   @Nullable
   public static BufferedImage method4(ResourceLocationBridge horsestats140) {
      ResourceBridge bridge151 = Ref.method3().bridge$getResourceManager().bridge$getResource(horsestats140);
      if (bridge151 != null) {
         try (InputStream input2 = bridge151.bridge$getInputStream()) {
            return ImageIO.read(input2);
         } catch (IOException exception7) {
         }
      }

      return null;
   }

   @Generated
   private ImageUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
