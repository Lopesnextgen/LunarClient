package com.moonsworth.lunar.legacy;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.render.texture.TextureProcessor;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.awt.image.BufferedImage;
import lombok.Generated;

public class MixinHelper4 implements TextureProcessor.Extension {
   private final String field1;
   private final String field2;
   private final int field3;
   private final int field4;
   private final BufferedImage field5;

   @Override
   public String name() {
      return this.field1;
   }

   @Override
   public String method1() {
      return this.field2;
   }

   @Override
   public int method2() {
      return this.field3;
   }

   @Override
   public int method3() {
      return this.field4;
   }

   @Override
   public int method4(int var1, int var2) {
      return this.field5.getRGB(var1, var2);
   }

   @Override
   public void method5(int var1, int var2, int var3) {
      this.field5.setRGB(var1, var2, var3);
   }

   public static void method6(BufferedImage bufferedImage, String var1, String var2) {
      Client var3 = ThreadModuleDump63.method4();
      if (bufferedImage != null && var1 != null && var3 != null && var3.method40() != null && var3.method40().method84() != null) {
         var3.method40().method84().method71().method5(var1, () -> new MixinHelper4(var1, var2, bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage));
      }
   }

   @Generated
   private MixinHelper4(String var1, String var2, int var3, int value, BufferedImage bufferedImage) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var3;
      this.field4 = value;
      this.field5 = bufferedImage;
   }
}
