package com.moonsworth.lunar.client.framework.feature.screenshot;

import com.lunarclient.websocket.screenshot.v1.Dimensions;
import com.lunarclient.websocket.screenshot.v1.RecordScreenshotRequest;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.framework.Ref;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.function.Function;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageOutputStream;
import lombok.Generated;
import org.jspecify.annotations.Nullable;

public class ScreenshotThread extends Thread {
   private static final String field1 = "javax_imageio_png_1.0";
   private final com.moonsworth.lunar.client.mod.misc.screenshot.Screenshot field2;
   private int[] field3;
   private final int field4;
   private final int field5;
   private final Bridge3_24 field6;
   private final File field7;
   private final @Nullable Screenshot field8;
   private final Function<int[], BufferedImage> field9;
   private final Runnable field10;

   @Override
   public void run() {
      try {
         int[] items1 = this.field3;

         for (int index2 = 0; index2 < items1.length; index2++) {
            int number3 = items1[index2];
            int number4 = number3 & 0xFF;
            int number5 = number3 & 0xFF00;
            int number6 = number3 & 0xFF0000;
            items1[index2] = number4 << 16 | number5 | number6 >> 16;
         }

         ScreenshotCapture.method1(this.field3, this.field4, this.field5);
         BufferedImage bufferedimage9;
         if (this.field9 != null) {
            bufferedimage9 = this.field9.apply(this.field3);
         } else if (Bridge.method22().method1()) {
            bufferedimage9 = new BufferedImage(this.field6.bridge$framebufferWidth(), this.field6.bridge$framebufferHeight(), 1);
            int number10 = this.field6.bridge$framebufferTextureHeight() - this.field6.bridge$framebufferHeight();

            for (int index11 = number10; index11 < this.field6.bridge$framebufferTextureHeight(); index11++) {
               for (int index12 = 0; index12 < this.field6.bridge$framebufferWidth(); index12++) {
                  bufferedimage9.setRGB(index12, index11 - number10, this.field3[index11 * this.field6.bridge$framebufferTextureWidth() + index12]);
               }
            }
         } else {
            bufferedimage9 = new BufferedImage(this.field4, this.field5, 1);
            bufferedimage9.setRGB(0, 0, this.field4, this.field5, this.field3, 0, this.field4);
         }

         try {
            if (this.field8 == null) {
               this.field7.getParentFile().mkdirs();
               ImageIO.write(bufferedimage9, "png", this.field7);
               this.field10.run();
               return;
            }

            this.method1(bufferedimage9);
            ScreenshotCapture.method6(this.field2, this.field7, bufferedimage9, this.field8.method7(), true);
            this.field10.run();
         } catch (IOException exception7) {
            LunarLogger.warn("Couldn't save screenshot", exception7);
         }
      } catch (Throwable exception8) {
         throw exception8;
      }
   }

   public void method1(BufferedImage bufferedimage1) {
      ImageWriter imagewriter2 = ImageIO.getImageWritersByFormatName("png").next();
      ImageWriteParam imagewriteparam3 = imagewriter2.getDefaultWriteParam();

      try {
         IIOMetadata iiometadata4 = this.method2(imagewriter2, imagewriteparam3, bufferedimage1);

         try (ImageOutputStream imageoutputstream5 = ImageIO.createImageOutputStream(this.field7)) {
            if (imageoutputstream5 == null) {
               throw new IOException("Failed to create image output stream: " + this.field7.getAbsolutePath());
            }

            imagewriter2.setOutput(imageoutputstream5);
            imagewriter2.write(iiometadata4, new IIOImage(bufferedimage1, null, iiometadata4), imagewriteparam3);
         }
      } catch (Exception exception10) {
         CrashReporter.method5(exception10, "Save Screenshot with Metadata");
         ImageIO.write(bufferedimage1, "png", this.field7);
      }
   }

   private IIOMetadata method2(ImageWriter imagewriter1, ImageWriteParam imagewriteparam2, BufferedImage bufferedimage3) {
      ImageTypeSpecifier imagetypespecifier4 = ImageTypeSpecifier.createFromBufferedImageType(1);
      IIOMetadata iiometadata5 = imagewriter1.getDefaultImageMetadata(imagetypespecifier4, imagewriteparam2);
      if (!iiometadata5.isReadOnly() && iiometadata5.isStandardMetadataFormatSupported()) {
         IIOMetadataNode iiometadatanode6 = new IIOMetadataNode("tEXt");
         Encoder encoder7 = Base64.getEncoder();
         this.method3(iiometadatanode6, "lc_1", encoder7.encodeToString(this.field8.method8().toByteArray()));
         this.method3(iiometadatanode6, "lc_2", encoder7.encodeToString(this.field8.method9().toByteArray()));
         IIOMetadataNode iiometadatanode8 = new IIOMetadataNode("javax_imageio_png_1.0");
         iiometadatanode8.appendChild(iiometadatanode6);
         iiometadata5.mergeTree("javax_imageio_png_1.0", iiometadatanode8);
         if (Ref.method4() != null && Ref.method4().method35() != null) {
            Ref.method4()
               .method35()
               .method102()
               .recordScreenshot(
                  null,
                  RecordScreenshotRequest.newBuilder()
                     .setScreenshot(this.field8.method8().toBuilder().setSize(Dimensions.newBuilder().setWidth(bufferedimage3.getWidth()).setHeight(bufferedimage3.getHeight())))
                     .build(),
                  arg0 -> {}
               );
         }

         return iiometadata5;
      } else {
         throw new IllegalArgumentException("The writer does not support metadata!");
      }
   }

   private void method3(IIOMetadataNode iiometadatanode1, String text2, String text3) {
      IIOMetadataNode iiometadatanode4 = new IIOMetadataNode("tEXtEntry");
      iiometadatanode4.setAttribute("keyword", text2);
      iiometadatanode4.setAttribute("value", text3);
      iiometadatanode1.appendChild(iiometadatanode4);
   }

   @Generated
   public ScreenshotThread(
      com.moonsworth.lunar.client.mod.misc.screenshot.Screenshot screenshot1,
      int[] items2,
      int number3,
      int number4,
      Bridge3_24 bridge3_245,
      File file6,
      @Nullable Screenshot screenshot7,
      Function<int[], BufferedImage> function8,
      Runnable runnable9
   ) {
      this.field2 = screenshot1;
      this.field3 = items2;
      this.field4 = number3;
      this.field5 = number4;
      this.field6 = bridge3_245;
      this.field7 = file6;
      this.field8 = screenshot7;
      this.field9 = function8;
      this.field10 = runnable9;
   }
}
