package com.moonsworth.lunar.bridge;

import java.awt.image.BufferedImage;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class BufferedImageBridge implements AutoCloseableExtension {
   @NotNull
   private final BufferedImage image;

   public int bridge$getWidth() {
      return this.image.getWidth();
   }

   public int bridge$getHeight() {
      return this.image.getHeight();
   }

   public boolean method1(int number1, int number2) {
      return (this.image.getRGB(number1, number2) & 0xFF000000) != 0;
   }

   public boolean method2(int number1, int number2) {
      return (this.image.getRGB(number1, number2) & 0xFF000000) == -16777216;
   }

   public void close() {
   }

   @Generated
   public BufferedImageBridge(@NotNull BufferedImage bufferedimage1) {
      if (bufferedimage1 == null) {
         throw new NullPointerException("image is marked non-null but is null");
      }

      this.image = bufferedimage1;
   }
}
