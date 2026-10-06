package com.moonsworth.lunar.bridge;

import java.awt.image.BufferedImage;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class AwtImageBridge implements AutoCloseableExtension {
   @NotNull
   private final BufferedImage image;

   @Override
   public int bridge$getWidth() {
      return this.image.getWidth();
   }

   @Override
   public int bridge$getHeight() {
      return this.image.getHeight();
   }

   @Override
   public boolean method1(int var1, int var2) {
      return (this.image.getRGB(var1, var2) & 0xFF000000) != 0;
   }

   @Override
   public boolean method2(int var1, int var2) {
      return (this.image.getRGB(var1, var2) & 0xFF000000) == -16777216;
   }

   @Override
   public void close() {
   }

   @Generated
   public AwtImageBridge(@NotNull BufferedImage var1) {
      if (var1 == null) {
         throw new NullPointerException("image is marked non-null but is null");
      }

      this.image = var1;
   }
}
